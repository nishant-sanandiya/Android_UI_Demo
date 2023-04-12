package com.example.ui_demo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ui_demo.ApiModels.Data

class UserAdapter(private val dataSet: List<Data>, private val context: Context) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.user_id_text)
        val firstNameView: TextView = view.findViewById(R.id.user_first_name_text)
        val lastNameView: TextView = view.findViewById(R.id.user_last_name_text)
        val emailView: TextView = view.findViewById(R.id.user_email_text)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.user_item, viewGroup, false)
        view.setOnClickListener {
            val intentInstance = Intent(context, MainActivity3::class.java)
            context.startActivity(intentInstance)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.idView.text = dataSet[position].id.toString()
        viewHolder.firstNameView.text = dataSet[position].firstName.toString()
        viewHolder.lastNameView.text = dataSet[position].lastName.toString()
        viewHolder.emailView.text = dataSet[position].email.toString()
    }

    override fun getItemCount() = dataSet.size
}