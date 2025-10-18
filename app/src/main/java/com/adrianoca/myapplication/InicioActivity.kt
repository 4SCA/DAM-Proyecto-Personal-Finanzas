package com.adrianoca.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.adrianoca.myapplication.fragments.FragmentHistorial
import com.adrianoca.myapplication.fragments.RegistrarFinanzaFragment
import com.google.android.material.navigation.NavigationView

class InicioActivity : AppCompatActivity() {
    private lateinit var dlayMenu : DrawerLayout
    private lateinit var nvMenu : NavigationView
    private lateinit var ivMenu : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)
        dlayMenu = findViewById<DrawerLayout>(R.id.dlayMenu)
        ivMenu = findViewById<ImageView>(R.id.ivMenu)
        nvMenu = findViewById<NavigationView>(R.id.nvMenu)

        val sharedPref = getSharedPreferences("usuario_pref", MODE_PRIVATE)
        val nombreUsuario = sharedPref.getString("nombre_usuario", "Usuario")

        val headerView = nvMenu.getHeaderView(0) // Accede al header del NavigationView
        val tvNombre = headerView.findViewById<TextView>(R.id.tvNombre)
        tvNombre.text = "$nombreUsuario"
        ivMenu.setOnClickListener {
            dlayMenu.open()
        }

        nvMenu.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            dlayMenu.closeDrawers()

            when(menuItem.itemId){
                R.id.itInicio -> replaceFragment(RegistrarFinanzaFragment())
                R.id.itHistorial -> replaceFragment(FragmentHistorial())
            }
            true
        }
        replaceFragment(RegistrarFinanzaFragment())

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dlayMenu)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun replaceFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flayContenedor, fragment)
            .commit()
    }
}