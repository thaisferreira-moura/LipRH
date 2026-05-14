package com.example.lipsonrh

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GestaoUsuariosFragment : Fragment() {

    // Referências dos campos de texto conforme o layout fragment_gestao_usuarios.xml
    private lateinit var etNome: EditText
    private lateinit var etCpf: EditText
    private lateinit var etEndereco: EditText
    private lateinit var etAdmissao: EditText
    private lateinit var etCargo: EditText
    private lateinit var etSenha: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gestao_usuarios, container, false)

        // 1. Configuração visual do título (Sublinhado conforme image_c0059c.png)
        val txtTitulo = view.findViewById<TextView>(R.id.txtTituloGestao)
        txtTitulo?.paintFlags = (txtTitulo?.paintFlags ?: 0) or Paint.UNDERLINE_TEXT_FLAG

        // 2. Configuração da lista de funcionários cadastrados
        val rv = view.findViewById<RecyclerView>(R.id.rvFuncionariosCadastrados)
        rv.layoutManager = LinearLayoutManager(context)

        val listaExemplo: List<UsuarioItem> = listOf(
            UsuarioItem("João Silva", "123.456.789-00", "Analista de laboratório"),
            UsuarioItem("Marina Santos", "123.456.789-00", "Analista de laboratório")
        )
        rv.adapter = UsuariosCadastradosAdapter(listaExemplo)

        // 3. Inicialização dos campos do formulário
        etNome = view.findViewById(R.id.etCadNome)
        etCpf = view.findViewById(R.id.etCadCpf)
        etEndereco = view.findViewById(R.id.etCadEndereco)
        etAdmissao = view.findViewById(R.id.etCadAdmissao)
        etCargo = view.findViewById(R.id.etCadCargo)
        etSenha = view.findViewById(R.id.etCadSenha)
        val btnSalvar = view.findViewById<Button>(R.id.btnSalvarFuncionario)

        // 4. Clique para salvar no banco de dados MySQL
        btnSalvar.setOnClickListener {
            val novoUsuario = Usuario(
                cpf = etCpf.text.toString(),
                nome = etNome.text.toString(),
                endereco = etEndereco.text.toString(),
                dataAdmissao = etAdmissao.text.toString(), // Formato esperado: YYYY-MM-DD
                cargo = etCargo.text.toString().uppercase(), // RH ou COLABORADOR
                senha = etSenha.text.toString()
            )

            if (validarCampos(novoUsuario)) {
                enviarParaBanco(novoUsuario)
            }
        }

        return view
    }

    private fun validarCampos(u: Usuario): Boolean {
        if (u.cpf.isEmpty() || u.nome.isEmpty() || u.senha.isEmpty() || u.dataAdmissao.isEmpty()) {
            Toast.makeText(context, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun enviarParaBanco(usuario: Usuario) {
        Log.d("CADASTRO_RH", "Enviando dados para o Spring Boot: ${usuario.nome}")

        // Chamada real ao backend via Retrofit
        RetrofitClient.instance.cadastrarUsuario(usuario).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Usuário ${usuario.nome} salvo com sucesso no MySQL!", Toast.LENGTH_LONG).show()
                    limparCampos()
                } else {
                    Log.e("ERRO_API", "Código de erro: ${response.code()}")
                    Toast.makeText(context, "Erro ao salvar: Verifique os dados.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Log.e("ERRO_CONEXAO", "Falha: ${t.message}")
                Toast.makeText(context, "Falha na conexão com o servidor!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun limparCampos() {
        etNome.text.clear()
        etCpf.text.clear()
        etEndereco.text.clear()
        etAdmissao.text.clear()
        etCargo.text.clear()
        etSenha.text.clear()
    }
}