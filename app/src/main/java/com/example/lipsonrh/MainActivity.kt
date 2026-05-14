package com.example.lipsonrh

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
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
        val navSideView = findViewById<NavigationView>(R.id.nav_view_side)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val btnOpenMenu = findViewById<ImageView>(R.id.btnOpenMenu)

        // Recebe os dados enviados pela LoginActivity
        nomeUsuario = intent.getStringExtra("NOME_USUARIO")
        cpfUsuario = intent.getStringExtra("USER_CPF")
        cargoUsuario = intent.getStringExtra("USER_CARGO")

        // 1. Redirecionamento Automático Inicial (RH ou Colaborador)
        if (cargoUsuario?.uppercase() == "RH") {
            loadFragment(HomeRHFragment())
        } else {
            loadFragment(HomeFragment())
        }

        // 2. Clique no ícone de Hambúrguer (Abre o Drawer)
        btnOpenMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // 3. Listener do Menu Lateral (Drawer) - imagem image_ca0d28.png
        navSideView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.side_perfil -> loadFragment(ProfileFragment())

                // Redirecionamento adicionado para a Gestão de Funcionários
                R.id.side_funcionarios -> loadFragment(GestaoUsuariosFragment())

                R.id.side_beneficios -> loadFragment(GestaoBeneficiosFragment())

                R.id.side_faq -> {
                    // loadFragment(FaqFragment())
                }
                R.id.side_atestados -> {
                    // loadFragment(AtestadosFragment())
                }
                R.id.side_ferias -> {
                    // loadFragment(FeriasFragment())
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // 4. Listener do Menu Inferior (Bottom Navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    if (cargoUsuario?.uppercase() == "RH") {
                        loadFragment(HomeRHFragment())
                    } else {
                        loadFragment(HomeFragment())
                    }
                }
                R.id.nav_servicos -> loadFragment(ServicesFragment())
                R.id.nav_calendario -> loadFragment(CalendarioFragment())
                R.id.nav_perfil -> loadFragment(ProfileFragment())
            }
            true
        }
    }

    // Função para carregar fragmentos com passagem de dados
    private fun loadFragment(fragment: Fragment) {
        val bundle = Bundle()
        bundle.putString("USER_NAME", nomeUsuario)
        bundle.putString("USER_CPF", cpfUsuario)
        bundle.putString("USER_CARGO", cargoUsuario)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null) // Permite voltar para a tela anterior
            .commit()
    }

    // Fecha o menu lateral com o botão voltar do celular
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}