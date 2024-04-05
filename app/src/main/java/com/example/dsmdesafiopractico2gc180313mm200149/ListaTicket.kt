package com.example.dsmdesafiopractico2gc180313mm200149

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaTicket: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_ticket_cliente)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewTickets)?: return
        val adapter = CustomAdapterTicket()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }
}