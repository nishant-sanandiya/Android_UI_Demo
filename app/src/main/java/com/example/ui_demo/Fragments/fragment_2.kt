package com.example.ui_demo.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.example.ui_demo.R

class fragment_2 : Fragment() {

    private var fragment2Btn: Button? = null
    private var fragment2Text: TextView? = null
    private var fragment2SendButton: Button? = null
    private var count_2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v = inflater.inflate(R.layout.fragment_2, container, false)
        fragment2Btn = v.findViewById(R.id.fragment_2_button)
        fragment2Text = v.findViewById(R.id.fragment2Text)
        fragment2SendButton = v.findViewById(R.id.fragment_2_sendButton)

        val count = arguments?.getString("count");
        if (count !== null) {
            count_2 = count.toInt();
            fragment2Text?.text = count_2.toString()
        }

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment2Btn?.setOnClickListener {
            count_2++
            fragment2Text?.text = count_2.toString()
        }
        fragment2SendButton?.setOnClickListener {
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            val fragment = fragment_1()
            val bundle = Bundle()
            bundle.putString("count", "$count_2")
            fragment.arguments = bundle
            transaction.replace(R.id.fragment1, fragment)
            transaction.commit()
        }
    }
}