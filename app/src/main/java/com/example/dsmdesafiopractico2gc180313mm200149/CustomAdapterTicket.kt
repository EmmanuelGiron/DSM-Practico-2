package com.example.dsmdesafiopractico2gc180313mm200149

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class CustomAdapterTicket: RecyclerView.Adapter<CustomAdapterTicket.ViewHolder>() {

    //Trayendo datos de la base
    val database = FirebaseDatabase.getInstance()
    val reference = database.getReference("tickets")

    var titulos = mutableListOf<String>()
    var descripciones = mutableListOf<String>()
    var departamentos = mutableListOf<String>()

    //var titulos = arrayOf("Prueba1","Prueba2","Prueba3")
    //var descripciones = arrayOf("Prueba4","Prueba5","Prueba6")
    //var departamentos = arrayOf("Prueba7","Prueba8","Prueba9")

    init {
        obteniendoDatos()
    }

    class ticket {
        var titulo: String? = null
        var descripcion: String? = null
        var opcion: String? = null

        constructor() {
        }

        constructor(titulo: String, descripcion: String, opcion: String) {
            this.titulo = titulo
            this.descripcion = descripcion
            this.opcion = opcion
        }
    }

    fun obteniendoDatos() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val ticket = snapshot.getValue(ticket::class.java)
                    if (ticket != null) {
                        titulos.add(ticket.titulo.toString())
                        descripciones.add(ticket.descripcion.toString())
                       departamentos.add(ticket.opcion.toString())
                    }
                }
                notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_ticket,viewGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTituloTicket.text = titulos[i]
        viewHolder.itemDescripcionTicket.text = descripciones[i]
        viewHolder.itemDepartamentoTicket.text = departamentos[i]
    }

    override fun getItemCount(): Int {
        return titulos.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var itemTituloTicket: TextView
        var itemDescripcionTicket: TextView
        var itemDepartamentoTicket: TextView
        var itemEditar: Button


        init {
            itemTituloTicket = itemView.findViewById(R.id.tituloCardID)
            itemDescripcionTicket = itemView.findViewById(R.id.descripcionCardID)
            itemDepartamentoTicket = itemView.findViewById(R.id.departamentoCardID)
            itemEditar = itemView.findViewById(R.id.btnEditar)

            itemEditar.setOnClickListener{
                val intent = Intent(itemEditar.context,FrmActualizarTicket::class.java)
                intent.putExtra("titulo",itemTituloTicket.text.toString())
                intent.putExtra("descripcion",itemDescripcionTicket.text.toString())
                intent.putExtra("departamento",itemDepartamentoTicket.text.toString())
                itemEditar.context.startActivity(intent)
            }
        }
    }
}