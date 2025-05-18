package com.example.mymeditation.fragment

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.mymeditation.R
import com.example.mymeditation.SetStatusBar
import com.example.mymeditation.activity.CustomGalleryActivity
import com.example.mymeditation.activity.SignInActivity
import com.example.mymeditation.databinding.FragmentUserBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import java.io.File

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var sharedPref: SharedPreferences

    private val selectImageLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val imagePath = result.data?.getStringExtra("selectedImagePath")
            if (imagePath != null) {
                val file = File(imagePath)
                if (file.exists()) {
                    binding.imgAvatar.setImageURI(Uri.fromFile(file))
                    sharedPref.edit().putString("avatar_uri", imagePath).apply()
                } else {
                    binding.imgAvatar.setImageResource(R.drawable.avatar)
                    sharedPref.edit().remove("avatar_uri").apply()
                }
            } else {
                binding.imgAvatar.setImageResource(R.drawable.avatar)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        val view = binding.root

        SetStatusBar.fromFragment(this, false)

        firebaseAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        sharedPref = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)

        val name = sharedPref.getString("name", "") ?: ""
        val email = sharedPref.getString("email", "") ?: ""

        binding.tvLogoutName.text = name
        binding.tvLogoutId.text = email

        val avatarUriString = sharedPref.getString("avatar_uri", null)
        if (!avatarUriString.isNullOrEmpty()) {
            val file = File(avatarUriString)
            if (file.exists()) {
                binding.imgAvatar.setImageURI(Uri.fromFile(file))
            } else {
                binding.imgAvatar.setImageResource(R.drawable.avatar)
            }
        } else {
            binding.imgAvatar.setImageResource(R.drawable.avatar)
        }

        binding.btnLogout.setOnClickListener {
            firebaseAuth.signOut()
            googleSignInClient.signOut().addOnCompleteListener {
                sharedPref.edit().clear().apply()
                val intent = Intent(requireContext(), SignInActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }
        }

        binding.imgAvatar.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_MEDIA_IMAGES)
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                } else {
                    openCustomGallery()
                }
            } else {
                if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                } else {
                    openCustomGallery()
                }
            }
        }


        return view
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                openCustomGallery()
            } else {
                Toast.makeText(requireContext(), "Permission required to access images.", Toast.LENGTH_SHORT).show()
            }
        }

    private fun openCustomGallery() {
        val intent = Intent(requireContext(), CustomGalleryActivity::class.java)
        selectImageLauncher.launch(intent)
    }
}
