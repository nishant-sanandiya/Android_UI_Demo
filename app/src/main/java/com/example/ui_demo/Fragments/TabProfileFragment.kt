package com.example.ui_demo.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.ui_demo.Activities.RegisterUser
import com.example.ui_demo.R
import com.example.ui_demo.Utils.Constance
import com.example.ui_demo.Utils.SharedPreferenceUtil


class TabProfileFragment : Fragment() {

    private lateinit var logoutBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_layout, container, false)
        logoutBtn = view.findViewById(R.id.logoutBtn)
        if(activity?.applicationContext != null) {
            val username = SharedPreferenceUtil(activity?.applicationContext!!).getValue(Constance.userNameKey)
            logoutBtn.text = "$username Logout"
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logoutBtn.setOnClickListener {
            SharedPreferenceUtil(activity?.applicationContext!!).setValue(Constance.userNameKey,"")
            val context = activity?.applicationContext
            startActivity(Intent(context, RegisterUser::class.java))
            activity?.finish()
        }
    }
}