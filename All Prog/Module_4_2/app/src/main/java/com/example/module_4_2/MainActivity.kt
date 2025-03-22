package com.example.module_4_2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.util.Patterns
import android.widget.ProgressBar
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var nameInputLayout: TextInputLayout
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var phoneInputLayout: TextInputLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var registerButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        nameInputLayout = findViewById(R.id.nameInputLayout)
        emailInputLayout = findViewById(R.id.emailInputLayout)
        phoneInputLayout = findViewById(R.id.phoneInputLayout)
        progressBar = findViewById(R.id.progressBar)
        registerButton = findViewById(R.id.registerButton)

        // Set up button click listener
        registerButton.setOnClickListener {
            if (validateInputs()) {
                handleRegistration()
            }
        }

        // Set up text change listeners for real-time validation
        nameInputLayout.editText?.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateName()
            }
        }

        emailInputLayout.editText?.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateEmail()
            }
        }

        phoneInputLayout.editText?.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validatePhone()
            }
        }
    }

    private fun validateName(): Boolean {
        val name = nameInputLayout.editText?.text.toString().trim()
        return when {
            name.isEmpty() -> {
                nameInputLayout.error = "Name is required"
                false
            }
            name.length < 2 -> {
                nameInputLayout.error = "Name must be at least 2 characters long"
                false
            }
            else -> {
                nameInputLayout.error = null
                true
            }
        }
    }

    private fun validateEmail(): Boolean {
        val email = emailInputLayout.editText?.text.toString().trim()
        return when {
            email.isEmpty() -> {
                emailInputLayout.error = "Email is required"
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                emailInputLayout.error = "Please enter a valid email address"
                false
            }
            else -> {
                emailInputLayout.error = null
                true
            }
        }
    }

    private fun validatePhone(): Boolean {
        val phone = phoneInputLayout.editText?.text.toString().trim()
        return when {
            phone.isEmpty() -> {
                phoneInputLayout.error = "Phone number is required"
                false
            }
            !phone.matches(Regex("^\\+?[0-9\\s-]{10,}$")) -> {
                phoneInputLayout.error = "Please enter a valid phone number"
                false
            }
            else -> {
                phoneInputLayout.error = null
                true
            }
        }
    }

    private fun validateInputs(): Boolean {
        val nameValid = validateName()
        val emailValid = validateEmail()
        val phoneValid = validatePhone()
        return nameValid && emailValid && phoneValid
    }

    private fun handleRegistration() {
        // Show loading state
        setLoading(true)

        // Simulate API call with coroutines
        lifecycleScope.launch {
            try {
                // Simulate network delay
                delay(1500)

                // Get form data
                val name = nameInputLayout.editText?.text.toString().trim()
                val email = emailInputLayout.editText?.text.toString().trim()
                val phone = phoneInputLayout.editText?.text.toString().trim()

                // Log success (replace with actual API call)
                println("Registration successful: $name, $email, $phone")

                // Clear form
                nameInputLayout.editText?.setText("")
                emailInputLayout.editText?.setText("")
                phoneInputLayout.editText?.setText("")

                // Show success message
                Toast.makeText(
                    this@MainActivity,
                    "Registration successful!",
                    Toast.LENGTH_SHORT
                ).show()

            } catch (e: Exception) {
                // Handle error
                Toast.makeText(
                    this@MainActivity,
                    "Registration failed. Please try again.",
                    Toast.LENGTH_SHORT
                ).show()
            } finally {
                setLoading(false)
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        registerButton.isEnabled = !isLoading
    }
}