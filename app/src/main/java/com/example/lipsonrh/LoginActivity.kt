package com.example.lipsonrh

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etCpf = findViewById<EditText>(R.id.etCpf)
        val etSenha = findViewById<EditText>(R.id.etSenha)
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)

        btnEntrar.setOnClickListener {
            val cpf = etCpf.text.toString().trim()
            val senha = etSenha.text.toString().trim()

            if (cpf.isNotEmpty() && senha.isNotEmpty()) {
                efetuarLogin(cpf, senha)
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun efetuarLogin(cpfDigitado: String, senhaDigitada: String) {
        // Alterado para usar o Singleton RetrofitClient.instance
        val service = RetrofitClient.instance

        // Certifique-se que o método buscarUsuarioPorCpf existe na sua UsuarioApiService
        service.buscarUsuarioPorCpf(cpfDigitado).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    val usuario = response.body()

                    // Verifica se o usuário existe e se a senha confere
                    if (usuario != null && usuario.senha == senhaDigitada) {
                        Log.d("SUCESSO_API", "Login efetuado: ${usuario.nome} - Cargo: ${usuario.cargo}")

                        val intent = Intent(this@LoginActivity, MainActivity::class.java)

                        // Enviando os dados para a MainActivity para controle de acesso (RH vs Colaborador)
                        intent.putExtra("NOME_USUARIO", usuario.nome)
                        intent.putExtra("USER_CPF", usuario.cpf)
                        intent.putExtra("USER_CARGO", usuario.cargo)

                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Senha incorreta!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("ERRO_API", "Código de erro: ${response.code()}")
                    Toast.makeText(baseContext, "Usuário não encontrado!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Log.e("FALHA_CONEXAO", "Erro: ${t.message}")
                Toast.makeText(baseContext, "Erro de conexão: Verifique se o servidor está rodando", Toast.LENGTH_LONG).show()
            }
        })
    }
}