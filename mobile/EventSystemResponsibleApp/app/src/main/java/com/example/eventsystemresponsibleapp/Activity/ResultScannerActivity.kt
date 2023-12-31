package com.example.eventsystemresponsibleapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.eventsystemresponsibleapp.Model.User
import com.example.eventsystemresponsibleapp.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ResultScannerActivity : AppCompatActivity() {
    private lateinit var userImage: ImageView
    private lateinit var nameUserText: TextView
    private lateinit var userGroupText: TextView
    private lateinit var confirmButton: MaterialButton
    private lateinit var toolbar: MaterialToolbar

    private lateinit var eventTitle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_scanner)
        init()

        val user = intent.getSerializableExtra("user") as User

        setInfo(user)
        eventTitle = intent.getStringExtra("event_title")!!
        setSupportActionBar(toolbar)
        toolbar.isTitleCentered = true
        title = eventTitle
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_back)
        toolbar.navigationIcon = getDrawable(R.drawable.icon_back)

        toolbar.setNavigationOnClickListener {
            val i = Intent(this, ScannerActivity::class.java)
            i.putExtra("event_title", eventTitle)
            startActivity(i)
            true
        }

        confirmButton.setOnClickListener {
            val rnd = (1..2).random()

            if(rnd == 1){
                showSuccessfulDialog()
            }
            else{
                showErrorDialog()
            }
        }
    }

    private fun showSuccessfulDialog() {
        val builder = MaterialAlertDialogBuilder(this, R.style.MaterialAlertDialog_Rounded)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_successful_confirmation, null)
        builder.setView(dialogView)

        val okButton = dialogView.findViewById<MaterialButton>(R.id.ok_button)
        val dialog = builder.create()

        okButton.setOnClickListener {
            dialog.dismiss();
            val i = Intent(this, ScannerActivity::class.java)
            i.putExtra("event_title", eventTitle)
            startActivity(i)
        }

        dialog.show()
    }

    private fun showErrorDialog() {
        val builder = MaterialAlertDialogBuilder(this, R.style.MaterialAlertDialog_Rounded)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_confirmation_error, null)
        builder.setView(dialogView)

        val okButton = dialogView.findViewById<MaterialButton>(R.id.ok_button)
        val dialog = builder.create()

        okButton.setOnClickListener {
            dialog.dismiss();
            val i = Intent(this, ScannerActivity::class.java)
            i.putExtra("event_title", eventTitle)
            startActivity(i)
        }

        dialog.show()
    }

    private fun setInfo(user: User) {
        nameUserText.text = user.userName
        userGroupText.text = user.userGroup
        Glide.with(this)
            .load(user.userImageURL)
            .placeholder(R.drawable.icon_events)
            .into(userImage);
    }

    private fun init(){
        userImage = findViewById(R.id.userImage)
        nameUserText = findViewById(R.id.nameUserText)
        userGroupText = findViewById(R.id.userGroupText)
        confirmButton = findViewById(R.id.confirm_participation_button)
        toolbar = findViewById(R.id.toolbar)
    }
}