package com.example.dsmdesafiopractico2gc180313mm200149

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FrmActualizarTicket: AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_ticket)

            var txtTitulo = TextView(this)
            var txtDescripcion = TextView(this)
            var spDepartamento = Spinner(this)

            //Obteniendo opciones
            val departamentos = resources.getStringArray(R.array.departamentos)

            txtTitulo= findViewById(R.id.tituloActualizarID)
            txtDescripcion= findViewById(R.id.descripcionActualizarID)
            spDepartamento = findViewById(R.id.departamentosActualizarID)

            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, departamentos)
            spDepartamento.adapter = adapter

        val extras = intent.extras
            txtTitulo.text = extras?.getString("titulo").toString()
            txtDescripcion.text = extras?.getString("descripcion").toString()

            val valorSeleccionado = extras?.getString("departamento").toString()
            val posicionSeleccionada = departamentos.indexOf(valorSeleccionado)

            if (posicionSeleccionada != -1) {
                spDepartamento.setSelection(posicionSeleccionada)
            }

        }
}