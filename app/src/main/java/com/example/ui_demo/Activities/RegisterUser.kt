package com.example.ui_demo.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.ui_demo.R
import com.example.ui_demo.Utils.Constance
import com.example.ui_demo.Utils.SharedPreferenceUtil

class RegisterUser : AppCompatActivity(), BaseActivity {

    private lateinit var input: EditText
    private lateinit var registerButton: Button
    private val sharedPreferenceInstance :SharedPreferenceUtil = SharedPreferenceUtil(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)
        uiBinding()
        attachListners()
        title = "Register"
    }

    override fun uiBinding() {
        input = findViewById(R.id.userNameInput)
        registerButton = findViewById(R.id.registerBtn)
    }

    override fun attachListners() {
        registerButton.setOnClickListener {
            val name = input.text.toString()
            if(name != ""){
                sharedPreferenceInstance.setValue(Constance.userNameKey,name.toString())
                startActivity(Intent(this,TabActivity::class.java))
                finish()
            }else{
                Toast.makeText(this,"Please enter valid username !",Toast.LENGTH_SHORT).show()
            }
        }
    }
}