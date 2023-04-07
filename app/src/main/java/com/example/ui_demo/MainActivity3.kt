package com.example.ui_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.widget.SwitchCompat
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity3 : AppCompatActivity() {

    private lateinit var switchView : SwitchCompat
    private lateinit var webViewView :WebView
    private lateinit var textInput : EditText
    private lateinit var floatingActionButtonView : FloatingActionButton
    private lateinit var goToNextButton : Button
    private var enterTextValue : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        switchView = findViewById(R.id.switch1)
        webViewView= findViewById(R.id.webView1)
        textInput = findViewById(R.id.editTextTextPersonName)
        floatingActionButtonView = findViewById(R.id.floatingActionButton)
        goToNextButton = findViewById(R.id.nextScreenButton)

        switchView.setOnCheckedChangeListener { buttonView, isChecked ->
            when (isChecked) {
                true -> switchView.text = "Selected"
                false -> switchView.text = "UnSelected"
            }
        }
        webViewView.loadUrl("https://www.agileinfoways.com/")
        goToNextButton.setOnClickListener {
            val intentInstance = Intent(this,MainActivity4::class.java)
            startActivity(intentInstance);
        }
    }
}