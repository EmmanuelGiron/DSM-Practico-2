package com.example.dsmdesafiopractico2gc180313mm200149

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase
import java.util.Calendar
class FrmIngresoTickets : AppCompatActivity(){
    //Usuario
    val usurioActuaal = "Juan"
    val correoUsuarioActial = "juan@gmail.com"
    val database = FirebaseDatabase.getInstance()
    val reference = database.getReference("tickets")


    //Obteniendo fecha actual
    val calendario = Calendar.getInstance()
    val dia = calendario.get(Calendar.DAY_OF_MONTH)
    val mes = calendario.get(Calendar.MONTH) + 1
    val anio = calendario.get(Calendar.YEAR)
    val fechaActual = "$dia/$mes/$anio"



    class Ticket {
        var numero = ""
        var autor = ""
        var correo = ""
        var titulo = ""
        var descripcion = ""
        var opcion = ""
        var fechaCreacion = ""
        var fechaFinalizacion = "---"
        var estado = "Activo"
    }

    val ticket = Ticket()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_tickets)

        var arrowLeft: ImageButton

        arrowLeft = findViewById(R.id.btn_back)

        arrowLeft.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
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
            ticket.numero = "1"
            ticket.autor = usurioActuaal
            ticket.correo = correoUsuarioActial
            ticket.titulo = txtTitulo.text.toString()
            ticket.descripcion = txtDescripcion.text.toString()
            ticket.fechaCreacion = fechaActual
            //txtPruebaSalida.text = ticket.opcion

            val builder = AlertDialog.Builder(this)

            if(ticket.titulo != "" && ticket.descripcion != "")
            {
                val ticketRef = reference.push()
                ticketRef.setValue(ticket)

                //Alerta de confirmacion

                builder.setTitle("Resultado")
                builder.setMessage("Ticket ingresado con éxito!")
                builder.setPositiveButton("Aceptar") { dialog, which ->
                    val intent = Intent(this,ListaTicket::class.java)
                    startActivity(intent)
                }
            }else{
                builder.setTitle("Atención!!")
                builder.setMessage("Debe llenar todos los campos!")
                builder.setPositiveButton("Aceptar") { dialog, which ->

                }

            }

            // Mostrando la alerta
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
}