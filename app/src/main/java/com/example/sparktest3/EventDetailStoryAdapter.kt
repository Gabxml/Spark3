package com.example.sparktest3

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class EventDetailStoryAdapter(
    private val events: List<Event>,
    private val onStoryClick: (Event) -> Unit
) : RecyclerView.Adapter<EventDetailStoryAdapter.StoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int = events.size

    inner class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val storyImageView: ImageView = itemView.findViewById(R.id.storyImageView)
        fun bind(event: Event) {
            Picasso.get().load(Uri.parse(event.imageUri)).into(storyImageView)
            itemView.setOnClickListener {
                onStoryClick(event)
            }
        }
    }
}
