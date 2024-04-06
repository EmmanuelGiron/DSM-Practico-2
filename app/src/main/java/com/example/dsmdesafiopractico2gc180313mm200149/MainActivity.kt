package com.example.dsmdesafiopractico2gc180313mm200149

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {
    //Usuario
    val usurioActual = "Admin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnIrFrmTicket = Button(this)
        var btnMostrar = Button(this)

        btnIrFrmTicket = findViewById(R.id.btnIrAFormularioIngresarTicket)
        btnMostrar = findViewById(R.id.btnMostrarLista)

        if(usurioActual == "Admin"){
            btnIrFrmTicket.visibility = View.GONE
        }

        btnIrFrmTicket.setOnClickListener{
            val intent = Intent(this, FrmIngresoTickets::class.java)
            startActivity(intent)
        }
        btnMostrar.setOnClickListener{
            val intent = Intent(this,ListaTicket::class.java)
            startActivity(intent)
        }
    }
}