//package com.example.sparktest3
//
//import android.net.Uri
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import android.widget.TextView
//import android.widget.ImageView
//import com.squareup.picasso.Picasso
//
//class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
//        return PostViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
//        val post = posts[position]
//        holder.bind(post)
//    }
//
//    override fun getItemCount(): Int = posts.size
//
//    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
//        private val contextTextView: TextView = itemView.findViewById(R.id.contextTextView)
//        private val registrationLinkTextView: TextView = itemView.findViewById(R.id.registrationLinkTextView)
//        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
//
//        fun bind(post: Post) {
//            titleTextView.text = post.title
//            contextTextView.text = post.context
//            registrationLinkTextView.text = post.registrationLink
//
//            // Now imageUri is a String (URI), so we load it using Picasso or any other image loader
//            val imageUriString = post.imageUri
//            if (imageUriString.isNotEmpty()) {
//                val imageUri = Uri.parse(imageUriString) // Convert String URI to Uri
//                Picasso.get().load(imageUri).into(imageView) // Load image into ImageView using Picasso
//            }
//        }
//    }
//}
