package com.example.socialmedialogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task

class MainActivity<ConnectionResult : Any> : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {
    val SIGN_IN_NUM = 9001
    lateinit var btn1: SignInButton
    lateinit var mGoogleApiClient: GoogleApiClient
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.signin)

        btn1.setOnClickListener {

            signin1()
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleApiClient = GoogleApiClient.Builder(applicationContext)
            .enableAutoManage(this,this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()

    }

    private fun signin1()
    {
        var i =Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(i,SIGN_IN_NUM)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {

        if(requestCode==SIGN_IN_NUM)
        {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>)
    {
        val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
        Toast.makeText(applicationContext,""+account.email, Toast.LENGTH_LONG).show()
        Log.d("myemail",account.email.toString())
        Log.d("mydisplayname",account.displayName.toString())
        Log.d("myfamilyname",account.familyName.toString())
        Log.d("myaccount",account.account.toString())
        Log.d("myid",account.id.toString())
        Log.d("myphoto",account.photoUrl.toString())

        //data varibale store

        //API SEND - VOLLEY,RETROFIT


    }
    override fun onConnectionFailed(p0: com.google.android.gms.common.ConnectionResult) {

        Toast.makeText(applicationContext, "Connection Failed Error", Toast.LENGTH_SHORT).show()

    }
}
