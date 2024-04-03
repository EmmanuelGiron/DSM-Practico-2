package com.example.dsmdesafiopractico2gc180313mm200149

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnIrFrmTicket = Button(this)

        btnIrFrmTicket = findViewById(R.id.btnIrAFormularioIngresarTicket)

        btnIrFrmTicket.setOnClickListener{
            val intent = Intent(this, FrmIngresoTickets::class.java)
            startActivity(intent)
        }
    }



}