package com.example.ui_demo.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.ui_demo.R

class RegisterUser : AppCompatActivity(), BaseActivity {

    private lateinit var input: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)
        uiBinding()
        attachListners()
    }

    override fun uiBinding() {
        input = findViewById(R.id.userNameInput)
        registerButton = findViewById(R.id.registerBtn)
    }

    override fun attachListners() {
        registerButton.setOnClickListener {
            val name = input.text
            if(name !== null){

            }
        }
    }
}