package com.adrianoca.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adrianoca.myapplication.entity.Usuario
import com.adrianoca.myapplication.ui.theme.MyApplicationTheme
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : ComponentActivity() {
    var tvRegistro: TextView?=null
    private lateinit var tietCorreo : TextInputEditText
    private lateinit var tietClave : TextInputEditText
    private lateinit var tilCorreo : TextInputLayout
    private lateinit var tilClave : TextInputLayout
    private lateinit var btnAcceso : Button

    //Lista para probar la aplicacion
    private val listaUsers = mutableListOf(
        Usuario(1, "Adriano Stephano", "Chavez","Aburto","Estudiante",1300.00,"15/05/2025","Masculino","adriano@gmail.com","1234"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvRegistro = findViewById(R.id.tvRegistro)
        tietCorreo = findViewById(R.id.tietCorreo)
        tietClave = findViewById(R.id.tietClave)
        tilCorreo = findViewById(R.id.tilCorreo)
        tilClave = findViewById(R.id.tilClave)
        btnAcceso = findViewById(R.id.btnAcceso)

        btnAcceso.setOnClickListener {
            validarCampos()
        }
        tvRegistro?.setOnClickListener {
            cambioActivity(RegistroActivity::class.java)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                maxOf(systemBars.bottom, imeInsets.bottom)
            )
            insets
        }
    }

    private fun validarCampos() {
        val correo = tietCorreo.text.toString().trim()
        val clave = tietClave.text.toString().trim()
        var error : Boolean = false

        if (correo.isEmpty()) {
            tilCorreo.error = "Ingrese un correo"
            error = true
        }else{
            tilCorreo.error =""
        }

        if(clave.isEmpty()){
            tilClave.error = "Ingrese una clave"
            error = true
        }else{
            tilClave.error = ""
        }

        if(error){
            return
        }else{
            Toast.makeText(this,"Validación correcta. Procesando Login...",Toast.LENGTH_SHORT).show()
            var usuarioEncontrado : Usuario? = null
            for(u in listaUsers){
                if(u.correo == correo && u.clave == clave){
                    usuarioEncontrado = u
                    break
                }
            }
            if (usuarioEncontrado != null){
                Toast.makeText(this,"Bienvenido ${usuarioEncontrado.nombres}",Toast.LENGTH_SHORT).show()
                val sharedPref = getSharedPreferences("usuario_pref", MODE_PRIVATE)
                sharedPref.edit().putString("nombre_usuario", usuarioEncontrado.nombres).apply()
                startActivity(Intent(this, InicioActivity::class.java))
            }else{
                Toast.makeText(this,"Usuario o contraseña incorrectos",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun cambioActivity(activityDestino: Class<out Activity>) {
        val intent = Intent(this, activityDestino)
        startActivity(intent)
    }

}
