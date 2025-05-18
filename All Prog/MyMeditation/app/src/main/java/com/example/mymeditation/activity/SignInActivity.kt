package com.example.mymeditation.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mymeditation.databinding.ActivitySignInBinding
import com.example.mymeditation.MainActivity
import com.example.mymeditation.SetStatusBar
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.*
import com.google.firebase.database.*

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var usersRef: DatabaseReference
    private lateinit var googleSignInClient: GoogleSignInClient

    private val RC_SIGN_IN = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        usersRef = database.getReference("users")

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(com.example.mymeditation.R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString()

            if (!isValidEmail(email)) {
                binding.edtEmail.error = "Enter a valid email"
                return@setOnClickListener
            }

            if (password.length < 6) {
                binding.edtPassword.error = "Password must be at least 6 characters"
                return@setOnClickListener
            }
// its working good as expected but when i
            signInWithFirebase(email, password)
        }

        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

        binding.imgSignInBackArrow.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btnGoogle.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun signInWithFirebase(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val userId = firebaseAuth.currentUser?.uid
                    if (userId != null) {
                        fetchUserData(userId)
                    } else {
                        showToast("Login failed: User ID is null")
                    }
                } else {
                    showToast("Invalid email or password")
                }
            }
    }

    private fun fetchUserData(userId: String) {
        usersRef.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                if (user != null) {
                    val prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE).edit()
                    prefs.putBoolean("is_logged_in", true)
                    prefs.putString("name", user.name)
                    prefs.putString("email", user.email)
                    prefs.apply()

                    val intent = Intent(this@SignInActivity, WelcomeActivity::class.java)
                    intent.putExtra("name", user.name)
                    startActivity(intent)
                    finish()
                } else {
                    showToast("User data not found.")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                showToast("Failed to retrieve user data: ${error.message}")
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                showToast("Google sign-in failed: ${e.message}")
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    if (user != null) {
                        val userId = user.uid
                        usersRef.child(userId).get().addOnSuccessListener { snapshot ->
                            if (!snapshot.exists()) {
                                val newUser = User(user.displayName ?: "User", user.email ?: "", user.uid)
                                usersRef.child(userId).setValue(newUser)
                            }

                            val prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE).edit()
                            prefs.putBoolean("is_logged_in", true)
                            prefs.putString("name", user.displayName ?: "User")
                            prefs.putString("email", user.email ?: "")
                            prefs.apply()

                            val intent = Intent(this, WelcomeActivity::class.java)
                            intent.putExtra("name", user.displayName ?: "User")
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    showToast("Firebase auth failed: ${task.exception?.message}")
                }
            }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
