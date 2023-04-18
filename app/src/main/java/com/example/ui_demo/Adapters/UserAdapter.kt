package com.example.ui_demo.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ui_demo.ApiModels.Data
import com.example.ui_demo.R

class UserAdapter(private val dataSet: List<Data>, private val context: Context) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.user_id_text)
        val firstNameView: TextView = view.findViewById(R.id.user_first_name_text)
        val lastNameView: TextView = view.findViewById(R.id.user_last_name_text)
        val emailView: TextView = view.findViewById(R.id.user_email_text)
        val userImageView: ImageView = view.findViewById(R.id.userImage)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.user_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.idView.text = dataSet[position].id.toString()
        viewHolder.firstNameView.text = dataSet[position].firstName.toString()
        viewHolder.lastNameView.text = dataSet[position].lastName.toString()
        viewHolder.emailView.text = dataSet[position].email.toString()
        Glide
            .with(this.context)
            .load(dataSet[position].avatar)
            .fitCenter()
            .placeholder(R.drawable.image1)
            .into(viewHolder.userImageView);
    }

    override fun getItemCount() = dataSet.size
}