package com.example.project1.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project1.ApiConfig.ApiClient
import com.example.project1.ApiConfig.Apiinterface
import com.example.project1.R
import com.example.project1.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener,
CompoundButton.OnCheckedChangeListener {

    private lateinit var binding: ActivitySignupBinding
    var gender = ""
    lateinit var apiinterface: Apiinterface
    var type="USER"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        apiinterface = ApiClient().getconnect().create(Apiinterface::class.java)

        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.blue)
        binding.txtalreadyaccount.setOnClickListener {
            startActivity(Intent(applicationContext,LoginActivity::class.java))

        }
        binding.rbMale.setOnCheckedChangeListener(this)
        binding.rbFemale.setOnCheckedChangeListener(this)
        binding.radioGroup.setOnCheckedChangeListener(this)
        binding.btnsignup.setOnClickListener {

            var fname = binding.edtFirstName.text.toString()
            var lname = binding.edtLastName.text.toString()
            var phone = binding.edtPhone.text.toString()
            var gender1 = gender
            var email = binding.edtEmail.text.toString()
            var pass = binding.edtPassword.text.toString()


            var call: Call<Void> = apiinterface.signup(fname,lname,phone,gender1,email,pass,type)
            call.enqueue(object: Callback<Void>
            {
                override fun onResponse(call: Call<Void>, response: Response<Void>)
                {
                    startActivity(Intent(applicationContext,LoginActivity::class.java))
                }

                override fun onFailure(call: Call<Void>, t: Throwable)
                {
                    Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        if(binding.rbMale.isChecked)
        {
            gender = "male"
            Toast.makeText(applicationContext, ""+gender, Toast.LENGTH_SHORT).show()
        }
        if(binding.rbFemale.isChecked)
        {
            gender = "female"
            Toast.makeText(applicationContext, ""+gender, Toast.LENGTH_SHORT).show()

        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

    }

    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}