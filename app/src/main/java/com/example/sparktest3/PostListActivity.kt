//package com.example.sparktest3
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//
//class PostListActivity : AppCompatActivity() {
//
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var postAdapter: PostAdapter
//    private lateinit var posts: MutableList<Post>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_post_list)
//
//        recyclerView = findViewById(R.id.recyclerView)
//        posts = loadPosts() // Fetch posts from the database
//        postAdapter = PostAdapter(posts)
//
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = postAdapter
//    }
//
//    // Function to load posts from the database
//    private fun loadPosts(): MutableList<Post> {
//        val databaseHelper = DatabaseHelper(this)
//        return databaseHelper.getAllPosts() // Assuming you have this method to fetch all posts
//    }
//}
