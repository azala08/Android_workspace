package com.example.mymeditation.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mymeditation.R
import com.example.mymeditation.databinding.ActivitySignUpBinding
import com.example.mymeditation.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private var isPasswordVisible = false
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        applyWindowInsets(binding.signUp)
        setupLiveValidation()
        setupPasswordVisibilityToggle()

        binding.ibBackArrow.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        binding.btnGetStart.setOnClickListener {
            val name = binding.edtName.text.toString().trim()
            val email = binding.edtEmailAddress.text.toString().trim()
            val password = binding.edtPwd.text.toString()
            val isChecked = binding.chkcheck.isChecked

            var isValid = true

            if (name.isEmpty()) {
                binding.edtName.error = "Please enter your name"
                isValid = false
            } else {
                binding.edtName.error = null
            }

            if (!isValidEmail(email)) {
                binding.edtEmailAddress.error = "Please enter a valid email"
                isValid = false
            } else {
                binding.edtEmailAddress.error = null
            }

            if (password.length < 6) {
                binding.edtPwd.error = "Password must be at least 6 characters"
                isValid = false
            } else if (password.contains(" ")) {
                binding.edtPwd.error = "Password cannot contain spaces"
                isValid = false
            } else {
                binding.edtPwd.error = null
            }

            if (!isChecked) {
                showToast("Please agree to the Privacy Policy")
                isValid = false
            }

            if (!isValid) return@setOnClickListener

            registerUserWithEmail(email, password, name)
        }
    }

    private fun registerUserWithEmail(email: String, password: String, name: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val userId = firebaseAuth.currentUser?.uid ?: return@addOnCompleteListener
                    saveUserDataToFirebase(userId, name, email, password)
                } else {
                    showToast("Registration failed: ${task.exception?.message}")
                }
            }
    }

    private fun saveUserDataToFirebase(userId: String, name: String, email: String, password: String) {
        val user = User(name, email, password)
        val userRef = database.getReference("users").child(userId)

        userRef.setValue(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Save name to SharedPreferences
                val sharedPref = getSharedPreferences("UserData", Context.MODE_PRIVATE)
                sharedPref.edit().putString("name", name).apply()

                showToast("Account created successfully!")

                // Pass name via Intent
                val intent = Intent(applicationContext, WelcomeActivity::class.java)
                intent.putExtra("name", name)
                startActivity(intent)
                finish()
            } else {
                showToast("Failed to create account. Please try again.")
            }
        }
    }

    private fun applyWindowInsets(rootView: View) {
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBarsInsets.left,
                systemBarsInsets.top,
                systemBarsInsets.right,
                systemBarsInsets.bottom
            )
            insets
        }
    }

    private fun setupLiveValidation() {
        binding.edtName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val rawInput = s?.toString() ?: ""
                val cleanedInput = rawInput.replace(" ", "")
                val length = cleanedInput.length
                binding.tvNameCount.text = "$length/50"

                if (length > 50) {
                    showAlert("Name cannot exceed 50 characters")
                    val trimmed = cleanedInput.take(50)
                    binding.edtName.setText(trimmed)
                    binding.edtName.setSelection(trimmed.length)
                    return
                }

                val isValid = cleanedInput.isNotEmpty()
                val icon = if (isValid) R.drawable.check else 0
                binding.edtName.setCompoundDrawablesWithIntrinsicBounds(0, 0, icon, 0)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.edtEmailAddress.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val rawEmail = s?.toString() ?: ""
                val cleanedEmail = rawEmail.replace(" ", "")

                if (rawEmail != cleanedEmail) {
                    binding.edtEmailAddress.setText(cleanedEmail)
                    binding.edtEmailAddress.setSelection(cleanedEmail.length)
                    return
                }

                val isValid = cleanedEmail.isNotEmpty() && isValidEmail(cleanedEmail)
                val icon = if (isValid) R.drawable.check else 0
                binding.edtEmailAddress.setCompoundDrawablesWithIntrinsicBounds(0, 0, icon, 0)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.edtPwd.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    val noSpaces = it.toString().replace(" ", "")
                    if (noSpaces != it.toString()) {
                        binding.edtPwd.setText(noSpaces)
                        binding.edtPwd.setSelection(noSpaces.length)
                        showToast("Spaces are not allowed in password")
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupPasswordVisibilityToggle() {
        binding.edtPwd.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = 2
                val drawable = binding.edtPwd.compoundDrawables[drawableEnd]
                drawable?.let {
                    val bounds = it.bounds
                    val x = event.rawX.toInt()
                    val right = binding.edtPwd.right
                    val drawableWidth = bounds.width()

                    if (x >= right - drawableWidth - binding.edtPwd.paddingRight) {
                        isPasswordVisible = !isPasswordVisible
                        if (isPasswordVisible) {
                            binding.edtPwd.transformationMethod = null
                            binding.edtPwd.setCompoundDrawablesWithIntrinsicBounds(
                                0, 0, R.drawable.show, 0
                            )
                        } else {
                            binding.edtPwd.transformationMethod = PasswordTransformationMethod.getInstance()
                            binding.edtPwd.setCompoundDrawablesWithIntrinsicBounds(
                                0, 0, R.drawable.hide, 0
                            )
                        }
                        binding.edtPwd.setSelection(binding.edtPwd.text?.length ?: 0)
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Input Limit Reached")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

    @Deprecated("Use OnBackPressedDispatcher instead")
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }
}

data class User(
    val name: String = "",
    val email: String = "",
    val password: String = ""
)
