package com.example.ui_demo.Fragments

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.ui_demo.R


interface HeadlineListener<T : Activity?> : java.io.Serializable{
    fun onArticleSelected(activity: T, position: Int)
}

class StartGameDialogFragment() : DialogFragment(){

    private lateinit var dialog : Dialog
    var mCallback: HeadlineListener<*>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout to use as dialog or embedded fragment
        val view =  inflater.inflate(R.layout.custom_dialog, container, false)
        val btnView = view.findViewById<Button>(R.id.btn_dialog)
        btnView.setOnClickListener {
            onDismiss(dialog)
        }
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val tempDialog = super.onCreateDialog(savedInstanceState)
            tempDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            tempDialog.setCancelable(true)
            dialog = tempDialog
            return dialog
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onCancel(dialog: DialogInterface) {
        onClickHandler()
        super.onCancel(dialog)
    }

    override fun onDismiss(dialog: DialogInterface) {
        onClickHandler()
        super.onDismiss(dialog)
    }

    fun onClickHandler() {
        mCallback?.onArticleSelected(activity as Nothing,2)
    }
}