    package com.example.sparktest3

    import android.annotation.SuppressLint
    import android.content.ContentValues
    import android.content.Context
    import android.database.Cursor
    import android.database.sqlite.SQLiteDatabase
    import android.database.sqlite.SQLiteOpenHelper

    class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        companion object {
            private const val DATABASE_NAME = "spark_posts.db"
            // Increment version to force table creation for events.
            private const val DATABASE_VERSION = 3

            // Posts table (for Home Screen)
            private const val TABLE_POSTS = "posts"
            private const val COLUMN_ID = "id"
            private const val COLUMN_TITLE = "title"
            private const val COLUMN_CONTEXT = "context" // This is your caption
            private const val COLUMN_REGISTRATION_LINK = "registration_link"
            private const val COLUMN_IMAGE_URI = "image_uri"

            // Stories table (used on Home Screen)
            private const val TABLE_STORIES = "stories"
            private const val COLUMN_STORY_ID = "id"
            private const val COLUMN_STORY_IMAGE_URI = "image_uri"

            // Events table (detailed event info)
            private const val TABLE_EVENTS = "events"
            private const val COLUMN_EVENT_ID = "id"
            private const val COLUMN_EVENT_TITLE = "title"
            private const val COLUMN_EVENT_CAPTION = "caption"
            private const val COLUMN_EVENT_IMAGE_URI = "image_uri"
            private const val COLUMN_EVENT_REGISTRATION_LINK = "registration_link"
            private const val COLUMN_EVENT_DATE = "date"
            private const val COLUMN_EVENT_TIME = "time"
        }

        override fun onCreate(db: SQLiteDatabase?) {
            // Create posts table
            val createPostsTable = ("CREATE TABLE $TABLE_POSTS ("
                    + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "$COLUMN_TITLE TEXT, "
                    + "$COLUMN_CONTEXT TEXT, "
                    + "$COLUMN_REGISTRATION_LINK TEXT, "
                    + "$COLUMN_IMAGE_URI TEXT)")
            db?.execSQL(createPostsTable)

            // Create stories table
            val createStoriesTable = ("CREATE TABLE $TABLE_STORIES ("
                    + "$COLUMN_STORY_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "$COLUMN_STORY_IMAGE_URI TEXT)")
            db?.execSQL(createStoriesTable)

            // Create events table
            val createEventsTable = ("CREATE TABLE $TABLE_EVENTS ("
                    + "$COLUMN_EVENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "$COLUMN_EVENT_TITLE TEXT, "
                    + "$COLUMN_EVENT_CAPTION TEXT, "
                    + "$COLUMN_EVENT_IMAGE_URI TEXT, "
                    + "$COLUMN_EVENT_REGISTRATION_LINK TEXT, "
                    + "$COLUMN_EVENT_DATE TEXT, "
                    + "$COLUMN_EVENT_TIME TEXT)")
            db?.execSQL(createEventsTable)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            if (oldVersion < 2) {
                // Ensure stories table exists
                val createStoriesTable = ("CREATE TABLE IF NOT EXISTS $TABLE_STORIES ("
                        + "$COLUMN_STORY_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "$COLUMN_STORY_IMAGE_URI TEXT)")
                db?.execSQL(createStoriesTable)
            }
            if (oldVersion < 3) {
                // Create events table if upgrading to version 3
                val createEventsTable = ("CREATE TABLE IF NOT EXISTS $TABLE_EVENTS ("
                        + "$COLUMN_EVENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "$COLUMN_EVENT_TITLE TEXT, "
                        + "$COLUMN_EVENT_CAPTION TEXT, "
                        + "$COLUMN_EVENT_IMAGE_URI TEXT, "
                        + "$COLUMN_EVENT_REGISTRATION_LINK TEXT, "
                        + "$COLUMN_EVENT_DATE TEXT, "
                        + "$COLUMN_EVENT_TIME TEXT)")
                db?.execSQL(createEventsTable)
            }
        }

        // Method to add a post (for Home Screen)
        fun addPost(post: Post): Long {
            val db = writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_TITLE, post.title)
                put(COLUMN_CONTEXT, post.context)
                put(COLUMN_REGISTRATION_LINK, post.registrationLink)
                put(COLUMN_IMAGE_URI, post.imageUri)
            }
            return db.insert(TABLE_POSTS, null, values)
        }

        @SuppressLint("Range")
        fun getAllPosts(): MutableList<Post> {
            val posts = mutableListOf<Post>()
            val db = readableDatabase

            val cursor: Cursor = db.query(
                TABLE_POSTS,
                arrayOf(COLUMN_ID, COLUMN_TITLE, COLUMN_CONTEXT, COLUMN_REGISTRATION_LINK, COLUMN_IMAGE_URI),
                null, null, null, null,
                "$COLUMN_ID DESC"
            )

            if (cursor.moveToFirst()) {
                do {
                    val post = Post(
                        title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                        context = cursor.getString(cursor.getColumnIndex(COLUMN_CONTEXT)),
                        registrationLink = cursor.getString(cursor.getColumnIndex(COLUMN_REGISTRATION_LINK)),
                        imageUri = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URI))
                    )
                    posts.add(post)
                } while (cursor.moveToNext())
            }
            cursor.close()
            return posts
        }

        // Method to add a story
        fun addStory(story: Story): Long {
            val db = writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_STORY_IMAGE_URI, story.imageUri)
            }
            return db.insert(TABLE_STORIES, null, values)
        }

        @SuppressLint("Range")
        fun getAllStories(): MutableList<Story> {
            val stories = mutableListOf<Story>()
            val db = readableDatabase

            val cursor: Cursor = db.query(
                TABLE_STORIES,
                arrayOf(COLUMN_STORY_IMAGE_URI),
                null, null, null, null, null
            )

            if (cursor.moveToFirst()) {
                do {
                    val imageUri = cursor.getString(cursor.getColumnIndex(COLUMN_STORY_IMAGE_URI))
                    stories.add(Story(imageUri))
                } while (cursor.moveToNext())
            }
            cursor.close()
            return stories
        }

        // Method to add an event
        fun addEvent(event: Event): Long {
            val db = writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_EVENT_TITLE, event.title)
                put(COLUMN_EVENT_CAPTION, event.caption)
                put(COLUMN_EVENT_IMAGE_URI, event.imageUri)
                put(COLUMN_EVENT_REGISTRATION_LINK, event.registrationLink)
                put(COLUMN_EVENT_DATE, event.date)
                put(COLUMN_EVENT_TIME, event.time)
            }
            return db.insert(TABLE_EVENTS, null, values)
        }

        @SuppressLint("Range")
        fun getAllEvents(): MutableList<Event> {
            val events = mutableListOf<Event>()
            val db = readableDatabase

            val cursor: Cursor = db.query(
                TABLE_EVENTS,
                arrayOf(COLUMN_EVENT_ID, COLUMN_EVENT_TITLE, COLUMN_EVENT_CAPTION, COLUMN_EVENT_IMAGE_URI, COLUMN_EVENT_REGISTRATION_LINK, COLUMN_EVENT_DATE, COLUMN_EVENT_TIME),
                null, null, null, null,
                "$COLUMN_EVENT_ID DESC"
            )

            if (cursor.moveToFirst()) {
                do {
                    val event = Event(
                        title = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_TITLE)),
                        caption = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_CAPTION)),
                        imageUri = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_IMAGE_URI)),
                        registrationLink = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_REGISTRATION_LINK)),
                        date = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_DATE)),
                        time = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_TIME))
                    )
                    events.add(event)
                } while (cursor.moveToNext())
            }
            cursor.close()
            return events
        }

        // Optional: Fetch an event by image URI (to link a story to its event)
        @SuppressLint("Range")
        fun getEventByImageUri(imageUri: String): Event? {
            val db = readableDatabase
            val cursor: Cursor = db.query(
                TABLE_EVENTS,
                arrayOf(COLUMN_EVENT_ID, COLUMN_EVENT_TITLE, COLUMN_EVENT_CAPTION, COLUMN_EVENT_IMAGE_URI, COLUMN_EVENT_REGISTRATION_LINK, COLUMN_EVENT_DATE, COLUMN_EVENT_TIME),
                "$COLUMN_EVENT_IMAGE_URI = ?",
                arrayOf(imageUri),
                null, null, null
            )
            var event: Event? = null
            if (cursor.moveToFirst()) {
                event = Event(
                    title = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_TITLE)),
                    caption = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_CAPTION)),
                    imageUri = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_IMAGE_URI)),
                    registrationLink = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_REGISTRATION_LINK)),
                    date = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_DATE)),
                    time = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_TIME))
                )
            }
            cursor.close()
            return event
        }
    }
