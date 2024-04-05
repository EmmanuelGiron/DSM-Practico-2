package com.example.dsmdesafiopractico2gc180313mm200149

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.google.firebase.database.FirebaseDatabase
class FrmIngresoTickets : AppCompatActivity(){
    class Ticket {
        var titulo = ""
        var descripcion = ""
        var opcion = ""
    }

    val ticket = Ticket()

    val database = FirebaseDatabase.getInstance()
    val reference = database.getReference("tickets")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_tickets)

        var txtTitulo = EditText(this)
        var txtDescripcion = EditText(this)
        var spDepartamento = Spinner(this)
        var btnIngresar = Button(this)
        //var txtPruebaSalida = TextView(this)


        txtTitulo = findViewById(R.id.tituloID)
        txtDescripcion = findViewById(R.id.descripcionID)


        btnIngresar = findViewById(R.id.btnIngresar)
        //txtPruebaSalida = findViewById(R.id.TxtPruebaSalidaID)

        //Obteniendo valor del item seleccionado
        spDepartamento = findViewById(R.id.departamentosID)

        spDepartamento.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                ticket.opcion = selectedItem.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // cuando no se ha seleccionado nada
            }
        }
        btnIngresar.setOnClickListener{
            ticket.titulo = txtTitulo.text.toString()
            ticket.descripcion = txtDescripcion.text.toString()
            //txtPruebaSalida.text = ticket.opcion
            val ticketRef = reference.push()
            ticketRef.setValue(ticket)
        }
    }
}