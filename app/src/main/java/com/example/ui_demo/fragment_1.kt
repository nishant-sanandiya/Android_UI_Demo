package com.example.ui_demo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class fragment_1 : Fragment() {

    private var fragment1Btn: Button? = null
    private var fragment1Text: TextView? = null
    private var fragment1SendButton: Button? = null
    private var count_1: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_1, container, false)
        fragment1Btn = v.findViewById(R.id.fragment_1_button)
        fragment1Text = v.findViewById(R.id.fragment1Text)
        fragment1SendButton = v.findViewById(R.id.fragment_1_sendButton)

        val count = arguments?.getString("count");
        if (count !== null) {
            count_1 = count.toInt();
            fragment1Text?.text = count_1.toString()
        }

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment1Btn?.setOnClickListener {
            count_1++
            fragment1Text?.text = count_1.toString()
        }
        fragment1SendButton?.setOnClickListener {
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            val fragment = fragment_2()
            val bundle = Bundle()
            bundle.putString("count", "$count_1")
            fragment.arguments = bundle
            transaction.replace(R.id.fragment2, fragment)
            transaction.commit()
        }
    }
}