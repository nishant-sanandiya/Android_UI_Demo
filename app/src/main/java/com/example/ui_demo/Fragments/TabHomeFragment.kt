package com.example.ui_demo.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.ui_demo.Activities.Drawer
import com.example.ui_demo.R

class TabHomeFragment() : Fragment() {

    private var goToDrawerBtnView: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =  inflater.inflate(R.layout.home_layout, container, false)
        goToDrawerBtnView = v.findViewById(R.id.goToDrawerBtn)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToDrawerBtnView?.setOnClickListener {
            Intent(this.requireActivity(),Drawer::class.java).let {
                startActivity(it)
            }
        }
    }
}