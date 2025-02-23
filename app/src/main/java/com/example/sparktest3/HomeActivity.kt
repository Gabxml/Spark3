package com.example.sparktest3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    private lateinit var storiesRecyclerView: RecyclerView
    private lateinit var postsRecyclerView: RecyclerView
    private lateinit var storyAdapter: EventStoryAdapter
    private lateinit var postAdapter: EventPostAdapter
    private lateinit var events: MutableList<Event>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        storiesRecyclerView = findViewById(R.id.storiesRecyclerView)
        postsRecyclerView = findViewById(R.id.postsRecyclerView)

        // Fetch events from the database
        val dbHelper = DatabaseHelper(this)
        events = dbHelper.getAllEvents()

        // (Optional) Reverse list so that the latest events appear first.
        // events.reverse()

        // Set up adapters for stories and posts
        storyAdapter = EventStoryAdapter(events)
        postAdapter = EventPostAdapter(events)

        // Horizontal layout for stories
        storiesRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        storiesRecyclerView.adapter = storyAdapter

        // Vertical layout for posts
        postsRecyclerView.layoutManager = LinearLayoutManager(this)
        postsRecyclerView.adapter = postAdapter
    }
}
