package com.example.ui_demo.Activities

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.ui_demo.Fragments.StartGameDialogFragment
import com.example.ui_demo.R

class Dialogs : AppCompatActivity(), BaseActivity, View.OnClickListener {

    // Custom Dialog with use of DialogFragment Class
    private val startGameDialogFragmentView: DialogFragment = StartGameDialogFragment()

    // button for open a dialog
    private lateinit var showAlertBtnView: Button
    private lateinit var customDialog: Dialog

    // button in a custom dialog
    private lateinit var customDialogBtnView: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialogs)
        title = "Dialogs Demo"
        uiBinding()
        attachListners()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.custom_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.helpMenu -> {
                Toast.makeText(this, "helpMenu", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.contactMenu -> {
                Toast.makeText(this, "contactMenu", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun uiBinding() {
        // open alert btn view
        showAlertBtnView = findViewById(R.id.showAlertBtn)

        // Custom Dialog with use of Dialog Class
        customDialog = Dialog(this)
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialog.setCancelable(false)
        customDialog.setContentView(R.layout.custom_dialog)

        // bind button of dialog from dialogView
        customDialogBtnView = customDialog.findViewById(R.id.btn_dialog)
    }

    override fun attachListners() {
        showAlertBtnView.setOnClickListener(this)
        customDialogBtnView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Log.d("View", v.toString())
        when (v) {
            showAlertBtnView -> {
                customDialog.show()
//                startGameDialogFragmentView.show(this.supportFragmentManager, "HI")
            }
            customDialogBtnView -> {
                customDialog.dismiss()
            }
        }
    }
}