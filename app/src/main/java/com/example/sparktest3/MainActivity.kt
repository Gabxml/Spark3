package com.example.sparktest3

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var chooseImageButton: Button
    private lateinit var eventImageView: ImageView
    private lateinit var titleEditText: EditText
    private lateinit var captionEditText: EditText
    private lateinit var registrationLinkEditText: EditText
    private lateinit var dateButton: Button
    private lateinit var timeButton: Button
    private lateinit var postButton: Button

    private var imageUriString: String? = null
    private var selectedDate: String = ""
    private var selectedTime: String = ""

    private val galleryResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val uri: Uri? = result.data?.data
                uri?.let { processImage(it) }
                    ?: Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to select image", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chooseImageButton = findViewById(R.id.chooseImageButton)
        eventImageView = findViewById(R.id.eventImageView)
        titleEditText = findViewById(R.id.titleEditText)
        captionEditText = findViewById(R.id.captionEditText)
        registrationLinkEditText = findViewById(R.id.registrationLinkEditText)
        dateButton = findViewById(R.id.dateButton)
        timeButton = findViewById(R.id.timeButton)
        postButton = findViewById(R.id.postButton)

        chooseImageButton.setOnClickListener { openGallery() }
        dateButton.setOnClickListener { openDatePicker() }
        timeButton.setOnClickListener { openTimePicker() }
        postButton.setOnClickListener { insertEvent() }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        galleryResultLauncher.launch(intent)
    }

    private fun processImage(uri: Uri) {
        imageUriString = uri.toString()
        Picasso.get().load(uri).into(eventImageView)
    }

    private fun openDatePicker() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(this, { _, year, month, day ->
            selectedDate = "$day/${month + 1}/$year"
            dateButton.text = selectedDate
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun openTimePicker() {
        val calendar = Calendar.getInstance()
        TimePickerDialog(this, { _, hour, minute ->
            selectedTime = "$hour:$minute"
            timeButton.text = selectedTime
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
    }

    private fun insertEvent() {
        val title = titleEditText.text.toString()
        val caption = captionEditText.text.toString()
        val registrationLink = registrationLinkEditText.text.toString()

        if (title.isNotEmpty() && caption.isNotEmpty() && registrationLink.isNotEmpty() &&
            imageUriString != null && selectedDate.isNotEmpty() && selectedTime.isNotEmpty()
        ) {
            val event = Event(title, caption, imageUriString!!, registrationLink, selectedDate, selectedTime)
            val dbHelper = DatabaseHelper(this)
            val result = dbHelper.addEvent(event)
            if (result != -1L) {
                Toast.makeText(this, "Event added successfully", Toast.LENGTH_SHORT).show()
                // Navigate to Home Screen (combined view of stories & posts)
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Failed to add event", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }
}
