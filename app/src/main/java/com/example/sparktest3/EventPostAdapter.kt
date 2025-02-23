package com.example.sparktest3

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class EventPostAdapter(private val events: List<Event>) : RecyclerView.Adapter<EventPostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post_event, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int = events.size

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eventImageView: ImageView = itemView.findViewById(R.id.eventImageView)
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val captionTextView: TextView = itemView.findViewById(R.id.captionTextView)
        fun bind(event: Event) {
            Picasso.get().load(Uri.parse(event.imageUri)).into(eventImageView)
            titleTextView.text = event.title
            captionTextView.text = event.caption
        }
    }
}
