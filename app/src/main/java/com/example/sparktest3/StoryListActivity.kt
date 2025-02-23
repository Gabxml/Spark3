//package com.example.sparktest3
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//
//class StoryListActivity : AppCompatActivity() {
//
//    private lateinit var storyRecyclerView: RecyclerView
//    private lateinit var storyAdapter: StoryAdapter
//    private lateinit var stories: MutableList<Story>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_story_list)
//
//        storyRecyclerView = findViewById(R.id.storyRecyclerView)
//        stories = loadStories()
//        storyAdapter = StoryAdapter(stories)
//
//        // Set the RecyclerView to horizontal orientation
//        storyRecyclerView.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        storyRecyclerView.adapter = storyAdapter
//    }
//
//    private fun loadStories(): MutableList<Story> {
//        val databaseHelper = DatabaseHelper(this)
//        return databaseHelper.getAllStories()
//    }
//}
