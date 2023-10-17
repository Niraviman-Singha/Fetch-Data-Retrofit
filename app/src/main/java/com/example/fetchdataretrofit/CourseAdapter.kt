package com.example.fetchdataretrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.NonDisposableHandle.parent

class CourseAdapter (private var courseList: ArrayList<CourseModel>): RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {
    class CourseViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val courseTitle = itemView.findViewById<TextView>(R.id.courseTitle)
        val courseImage = itemView.findViewById<ImageView>(R.id.courseImage)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.course_item,parent,false)
        return CourseViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.courseTitle.text = courseList.get(position).languageName
        Picasso.get().load(courseList.get(position).languageImg).into(holder.courseImage)
    }

}