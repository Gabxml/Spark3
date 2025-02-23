package com.example.sparktest3

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class EventDetailActivity : AppCompatActivity() {

    private lateinit var eventImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var dateTimeTextView: TextView
    private lateinit var captionTextView: TextView
    private lateinit var registrationLinkTextView: TextView
    private lateinit var storiesRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        eventImageView = findViewById(R.id.eventImageView)
        titleTextView = findViewById(R.id.titleTextView)
        dateTimeTextView = findViewById(R.id.dateTimeTextView)
        captionTextView = findViewById(R.id.captionTextView)
        registrationLinkTextView = findViewById(R.id.registrationLinkTextView)
        storiesRecyclerView = findViewById(R.id.storiesRecyclerView)

        // Load the initial event based on intent extra (if provided)
        val imageUri = intent.getStringExtra("imageUri")
        val dbHelper = DatabaseHelper(this)
        if (imageUri != null) {
            val event = dbHelper.getEventByImageUri(imageUri)
            if (event != null) {
                updateHeadline(event)
            }
        }

        // Set up the stories RecyclerView with all events
        storiesRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val eventsList = dbHelper.getAllEvents()
        val adapter = EventDetailStoryAdapter(eventsList) { event ->
            // When a story is clicked, update the headline with that event's details
            updateHeadline(event)
        }
        storiesRecyclerView.adapter = adapter
    }

    private fun updateHeadline(event: Event) {
        Picasso.get().load(event.imageUri).into(eventImageView)
        titleTextView.text = event.title
        dateTimeTextView.text = "${event.date} ${event.time}"
        captionTextView.text = event.caption
        registrationLinkTextView.text = event.registrationLink
    }
}
