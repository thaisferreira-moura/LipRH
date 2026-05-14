package com.example.lipsonrh

import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private var nomeUsuario: String? = null
    private var cpfUsuario: String? = null
    private var cargoUsuario: String? = null

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialização de componentes
        drawerLayout = findViewById(R.id.drawer_layout)
        val headerToolbar = findViewById<RelativeLayout>(R.id.header_toolbar)
        val navSideView = findViewById<NavigationView>(R.id.nav_view_side)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val btnOpenMenu = findViewById<ImageView>(R.id.btnOpenMenu)

        // --- SOLUÇÃO PARA A BARRA BRANCA E CLIQUE NO MENU ---
        ViewCompat.setOnApplyWindowInsetsListener(headerToolbar) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // 1. Ajusta a altura da barra vermelha: 60dp de conteúdo + altura da barra de status
            val density = resources.displayMetrics.density
            val params = view.layoutParams
            params.height = (60 * density).toInt() + systemBars.top
            view.layoutParams = params

            // 2. Aplica padding no topo da barra vermelha para empurrar os ícones para baixo
            // Isso remove a barra branca e mantém os ícones clicáveis
            view.setPadding(0, systemBars.top, 0, 0)

            insets
        }

        // Recebe os dados enviados pela LoginActivity
        nomeUsuario = intent.getStringExtra("NOME_USUARIO")
        cpfUsuario = intent.getStringExtra("USER_CPF")
        cargoUsuario = intent.getStringExtra("USER_CARGO")

        // --- CARREGAMENTO INICIAL: HomeFragment ---
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        // Clique no ícone de Hambúrguer
        btnOpenMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Listener do Menu Lateral (Drawer)
        navSideView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.side_perfil -> loadFragment(ProfileFragment())
                R.id.side_funcionarios -> loadFragment(GestaoUsuariosFragment())
                R.id.side_beneficios -> loadFragment(GestaoBeneficiosFragment())
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Listener do Menu Inferior (Bottom Navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_servicos -> loadFragment(ServicesFragment())
                R.id.nav_calendario -> loadFragment(CalendarioFragment())
                R.id.nav_perfil -> loadFragment(ProfileFragment())
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val bundle = Bundle()
        bundle.putString("USER_NAME", nomeUsuario)
        bundle.putString("USER_CPF", cpfUsuario)
        bundle.putString("USER_CARGO", cargoUsuario)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}