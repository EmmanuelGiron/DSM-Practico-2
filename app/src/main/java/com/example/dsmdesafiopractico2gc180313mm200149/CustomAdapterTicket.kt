package com.example.dsmdesafiopractico2gc180313mm200149

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat.recreate
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Calendar


class CustomAdapterTicket: RecyclerView.Adapter<CustomAdapterTicket.ViewHolder>() {
    //Usuario
    val usurioActual = "Juan"
    //Trayendo datos de la base
    val database = FirebaseDatabase.getInstance()
    val reference = database.getReference("tickets")

    //Fecha Actual
    val calendario = Calendar.getInstance()
    val dia = calendario.get(Calendar.DAY_OF_MONTH)
    val mes = calendario.get(Calendar.MONTH) + 1
    val anio = calendario.get(Calendar.YEAR)
    val fechaActual = "$dia/$mes/$anio"



    var referencias = mutableListOf<String>()
    var numeros = mutableListOf<String>()
    var autores = mutableListOf<String>()
    var correos = mutableListOf<String>()
    var titulos = mutableListOf<String>()
    var descripciones = mutableListOf<String>()
    var departamentos = mutableListOf<String>()
    var fechasCreacion = mutableListOf<String>()
    var fechasFinaliacion = mutableListOf<String>()
    var estados = mutableListOf<String>()

    //var titulos = arrayOf("Prueba1","Prueba2","Prueba3")
    //var descripciones = arrayOf("Prueba4","Prueba5","Prueba6")
    //var departamentos = arrayOf("Prueba7","Prueba8","Prueba9")

    init {

        obteniendoDatos(usurioActual)
    }

    class ticket {
        var referencia: String? = null
        var numero: String? = null
        var autor: String? = null
        var correo: String? = null
        var titulo: String? = null
        var descripcion: String? = null
        var opcion: String? = null
        var fechaCreacion: String? = null
        var fechaFinalizacion: String? = null
        var estado: String? = null
        constructor() {
        }

        constructor(referencia: String, numero: String, autor:String, correo: String, titulo: String, descripcion: String, opcion: String,fechaCreacion: String, fechaFinalizacion: String, estado:String) {
            this.referencia = referencia
            this.numero = numero
            this.autor = autor
            this.correo = correo
            this.titulo = titulo
            this.descripcion = descripcion
            this.opcion = opcion
            this.fechaCreacion = fechaCreacion
            this.fechaFinalizacion = fechaFinalizacion
            this.estado = estado
        }
    }

    fun obteniendoDatos(nombreActual:String) {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val ticket = snapshot.getValue(ticket::class.java)
                    val childKey = snapshot.key
                    if(nombreActual != "Admin"){
                        if (ticket != null && nombreActual == ticket.autor.toString()) {
                            referencias.add(childKey.toString())
                            numeros.add(ticket.numero.toString())
                            autores.add(ticket.autor.toString())
                            correos.add(ticket.correo.toString())
                            titulos.add(ticket.titulo.toString())
                            descripciones.add(ticket.descripcion.toString())
                            departamentos.add(ticket.opcion.toString())
                            fechasCreacion.add(ticket.fechaCreacion.toString())
                            fechasFinaliacion.add(ticket.fechaFinalizacion.toString())
                            estados.add(ticket.estado.toString())
                        }
                    } else {
                        if (ticket != null) {
                            referencias.add(childKey.toString())
                            numeros.add(ticket.numero.toString())
                            autores.add(ticket.autor.toString())
                            correos.add(ticket.correo.toString())
                            titulos.add(ticket.titulo.toString())
                            descripciones.add(ticket.descripcion.toString())
                            departamentos.add(ticket.opcion.toString())
                            fechasCreacion.add(ticket.fechaCreacion.toString())
                            fechasFinaliacion.add(ticket.fechaFinalizacion.toString())
                            estados.add(ticket.estado.toString())
                        }
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

        viewHolder.itemReferenciaTicket.text = referencias[i]
        viewHolder.itemNumeroTicket.text = numeros[i]
        viewHolder.itemTituloTicket.text = titulos[i]
        viewHolder.itemDescripcionTicket.text = descripciones[i]
        viewHolder.itemDepartamentoTicket.text = departamentos[i]
        viewHolder.itemFechaCreacionTicket.text = fechasCreacion[i]
        viewHolder.itemFechaFinalizacionTicket.text = fechasFinaliacion[i]
        viewHolder.itemEstadoTicket.text = estados[i]
        viewHolder.itemAutorTicket.text = autores[i]
        viewHolder.itemCorreoTicket.text = correos[i]

        if(estados[i] == "Finalizado"){
            viewHolder.itemFinalizar.visibility = View.GONE
            viewHolder.itemEditar.visibility = View.GONE
        }else{
            viewHolder.itemFinalizar.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return titulos.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemReferenciaTicket: TextView
        var itemNumeroTicket: TextView
        var itemTituloTicket: TextView
        var itemDescripcionTicket: TextView
        var itemDepartamentoTicket: TextView
        var itemFechaCreacionTicket: TextView
        var itemFechaFinalizacionTicket: TextView
        var itemAutorTicket: TextView
        var itemCorreoTicket: TextView
        var itemEstadoTicket: TextView
        var itemEditar: Button
        var itemBorrar : Button
        var itemFinalizar : Button
        var itemLayOutAutor : LinearLayout
        var itemLayOutCorreo : LinearLayout





        init {
            itemReferenciaTicket = itemView.findViewById(R.id.referenciaCardID)
            itemNumeroTicket = itemView.findViewById(R.id.numeroCardID)
            itemTituloTicket = itemView.findViewById(R.id.tituloCardID)
            itemDescripcionTicket = itemView.findViewById(R.id.descripcionCardID)
            itemDepartamentoTicket = itemView.findViewById(R.id.departamentoCardID)
            itemFechaCreacionTicket = itemView.findViewById(R.id.creacionCardID)
            itemFechaFinalizacionTicket = itemView.findViewById(R.id.finalizacionCardID)
            itemAutorTicket = itemView.findViewById(R.id.autorCardID)
            itemCorreoTicket = itemView.findViewById(R.id.correoCardID)
            itemEstadoTicket = itemView.findViewById(R.id.estadoCardID)
            itemEditar = itemView.findViewById(R.id.btnEditar)
            itemBorrar = itemView.findViewById(R.id.btnEliminar)
            itemFinalizar = itemView.findViewById(R.id.btnFinalizar)
            itemLayOutAutor = itemView.findViewById(R.id.autorLayout)
            itemLayOutCorreo = itemView.findViewById(R.id.correoLayout)

            if(usurioActual == "Admin"){
                itemEditar.visibility = View.GONE
                itemBorrar.visibility = View.GONE
                itemFinalizar.visibility = View.VISIBLE

            }else{
                itemLayOutAutor.visibility = View.GONE
                itemLayOutCorreo.visibility = View.GONE
                itemAutorTicket.visibility = View.GONE
                itemCorreoTicket.visibility = View.GONE
                itemFinalizar.visibility = View.GONE
            }



            //Actualizar datos
            itemEditar.setOnClickListener{
                val intent = Intent(itemEditar.context,FrmActualizarTicket::class.java)
                intent.putExtra("referencia",itemReferenciaTicket.text.toString())
                intent.putExtra("titulo",itemTituloTicket.text.toString())
                intent.putExtra("descripcion",itemDescripcionTicket.text.toString())
                intent.putExtra("departamento",itemDepartamentoTicket.text.toString())
                itemEditar.context.startActivity(intent)
            }
            //Eliminar datos
            itemBorrar.setOnClickListener{
                val referecia_a_elimianr = database.getReference("tickets/${itemReferenciaTicket.text}")

                referecia_a_elimianr.removeValue()
                    .addOnSuccessListener {
                        //Alerta de confirmacion
                        val builder = AlertDialog.Builder(itemBorrar.context)
                        builder.setTitle("Resultado")
                        builder.setMessage("Se elimino correctamente")
                        builder.setPositiveButton("Aceptar") { dialog, which ->
                            val intent = Intent(itemBorrar.context,ListaTicket::class.java)
                            itemBorrar.context.startActivity(intent)
                        }
                        // Mostrando la alerta
                        val dialog: AlertDialog = builder.create()
                        dialog.show()
                    }
                    .addOnFailureListener { error ->
                        Log.e("TAG", "Error al eliminar: $error")
                    }

            }
            //Finalizar procesos de ticket
            itemFinalizar.setOnClickListener{
                val refereciaListaTickets = database.getReference("tickets/${itemReferenciaTicket.text}")

                val actualizaciones = mapOf<String, Any>(
                    "estado" to "Finalizado",
                    "fechaFinalizacion" to fechaActual,
                )

                refereciaListaTickets.updateChildren(actualizaciones)
                    .addOnSuccessListener {
                        //Alerta de confirmacion
                        val builder = AlertDialog.Builder(itemFinalizar.context)
                        builder.setTitle("Resultado")
                        builder.setMessage("Proceso finalizado con éxito!")
                        builder.setPositiveButton("Aceptar") { dialog, which -> itemFinalizar.visibility = View.GONE
                            val intent = Intent(itemFinalizar.context,ListaTicket::class.java)
                            itemFinalizar.context.startActivity(intent)
                        }
                        // Mostrando la alerta
                        val dialog: AlertDialog = builder.create()
                        dialog.show()
                    }
                    .addOnFailureListener {
                        println("Hubo un error")
                    }
            }
        }
    }
}