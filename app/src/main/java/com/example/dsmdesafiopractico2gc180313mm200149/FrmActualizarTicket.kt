package com.example.dsmdesafiopractico2gc180313mm200149

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class FrmActualizarTicket: AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_ticket)

            var arrowLeft: ImageButton

            arrowLeft = findViewById(R.id.btn_back)

            arrowLeft.setOnClickListener{
                val intent = Intent(this,ListaTicket::class.java)
                startActivity(intent)
            }

            //Firbase
            val database = FirebaseDatabase.getInstance()
            var txtReferencia = TextView(this)
            var txtTitulo = TextView(this)
            var txtDescripcion = TextView(this)
            var spDepartamento = Spinner(this)
            var txtOpcion = TextView(this)
            var btnActualizar = Button(this)

            //Obteniendo opciones
            val departamentos = resources.getStringArray(R.array.departamentos)

            txtReferencia = findViewById(R.id.referenciaActualizarID)
            txtTitulo= findViewById(R.id.tituloActualizarID)
            txtDescripcion= findViewById(R.id.descripcionActualizarID)
            spDepartamento = findViewById(R.id.departamentosActualizarID)
            txtOpcion = findViewById(R.id.opcionSeleccionadaID)
            btnActualizar = findViewById(R.id.btnActualizar)

            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, departamentos)
            spDepartamento.adapter = adapter

            val extras = intent.extras
            txtReferencia.text = extras?.getString("referencia").toString()
            txtTitulo.text = extras?.getString("titulo").toString()
            txtDescripcion.text = extras?.getString("descripcion").toString()

            val valorSeleccionado = extras?.getString("departamento").toString()
            val posicionSeleccionada = departamentos.indexOf(valorSeleccionado)

            if (posicionSeleccionada != -1) {
                spDepartamento.setSelection(posicionSeleccionada)
            }

            spDepartamento.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    txtOpcion.text = selectedItem.toString()
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // cuando no se ha seleccionado nada
                }
            }

            btnActualizar.setOnClickListener{
                val refereciaListaTickets = database.getReference("tickets/${txtReferencia.text}")


                if(txtTitulo.text.trim().toString() != "" && txtDescripcion.text.trim().toString() != "") {
                    println(txtTitulo.text.trim().toString())
                    println(txtDescripcion.text.trim().toString())
                    val actualizaciones = mapOf<String, Any>(
                        "titulo" to txtTitulo.text.toString(),
                        "descripcion" to txtDescripcion.text.toString(),
                        "opcion" to txtOpcion.text.toString()
                    )

                    refereciaListaTickets.updateChildren(actualizaciones)
                        .addOnSuccessListener {
                            //Alerta de confirmacion
                            val builder = AlertDialog.Builder(this)
                            builder.setTitle("Resultado")
                            builder.setMessage("Ticket actualizado con éxito!")
                            builder.setPositiveButton("Aceptar") { dialog, which ->
                                val intent = Intent(this, ListaTicket::class.java)
                                startActivity(intent)
                            }
                            // Mostrando la alerta
                            val dialog: AlertDialog = builder.create()
                            dialog.show()
                        }
                        .addOnFailureListener {
                            println("Hubo un error")
                        }
                }else{
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Atención!!")
                    builder.setMessage("Debe llenar todos los campos!")
                    builder.setPositiveButton("Aceptar") { dialog, which ->

                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }

            }

        }
}