//package com.example.sparktest3
//
//import android.content.Intent
//import android.net.Uri
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import androidx.recyclerview.widget.RecyclerView
//import com.squareup.picasso.Picasso
//
//class StoryAdapter(private val stories: List<Story>) : RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)
//        return StoryViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
//        val story = stories[position]
//        holder.bind(story)
//    }
//
//    override fun getItemCount(): Int = stories.size
//
//    class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val storyImageView: ImageView = itemView.findViewById(R.id.storyImageView)
//        fun bind(story: Story) {
//            Picasso.get().load(Uri.parse(story.imageUri)).into(storyImageView)
//            // Clicking the story launches EventDetailActivity with the image URI extra.
//            itemView.setOnClickListener {
//                val context = itemView.context
//                val intent = Intent(context, EventDetailActivity::class.java)
//                intent.putExtra("imageUri", story.imageUri)
//                context.startActivity(intent)
//            }
//        }
//    }
//}
//
