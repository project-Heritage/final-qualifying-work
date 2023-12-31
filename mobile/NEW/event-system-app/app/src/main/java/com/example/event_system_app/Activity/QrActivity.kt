package com.example.event_system_app.Activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.widget.ImageView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.event_system_app.Helper.ServerHelper
import com.example.event_system_app.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.zxing.WriterException

class QrActivity: AppCompatActivity()  {
    private lateinit var toolbar: MaterialToolbar
    private lateinit var qrFullscreen: ImageView
    private lateinit var serverHelper: ServerHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_fullcsreen)
        init()
        setSupportActionBar(toolbar)
        toolbar.isTitleCentered = true
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_back)
        toolbar.navigationIcon = getDrawable(R.drawable.icon_back)
        serverHelper = ServerHelper(this)
        checkConnection()

        val qrImage = intent.getStringExtra("qr")
        val eventTitle = intent.getStringExtra("eventTitle")
        title = eventTitle

        toolbar.setNavigationOnClickListener {
            super.onBackPressed();
            true
        }

        generateQRCode(qrImage!!)
    }

    private fun checkConnection() {
        if(!serverHelper.isOnline(this)){
            val i = Intent(this, NetworkErrorActivity::class.java)
            startActivity(i)
        }
    }

    private fun generateQRCode(uid: String){
        val qrCode = QRGEncoder(uid, null, QRGContents.Type.TEXT, 600)
        qrCode.colorBlack = Color.WHITE
        qrCode.colorWhite = Color.BLACK
        try {
            val bitMap = qrCode.bitmap
            qrFullscreen.setImageBitmap(bitMap)
        }
        catch (e: WriterException){
            println(e)
        }
    }

    //Инициализация компонентов
    private fun init(){
        toolbar = findViewById(R.id.toolbar)
        qrFullscreen = findViewById(R.id.qrFullscreen)
    }
    //////////////////////
}