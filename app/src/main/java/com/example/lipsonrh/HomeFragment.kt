package com.example.lipsonrh

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class HomeFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var txtBoasVindas: TextView
    private val sliderHandler = Handler(Looper.getMainLooper())

    private val sliderRunnable = object : Runnable {
        override fun run() {
            val adapter = viewPager.adapter
            if (adapter != null && adapter.itemCount > 0) {
                val size = adapter.itemCount
                val nextItem = (viewPager.currentItem + 1) % size
                viewPager.currentItem = nextItem

                // No slide do calendário (último), esperamos 10s, nas imagens 5s
                val delay = if (viewPager.currentItem == size - 1) 10000L else 5000L
                sliderHandler.postDelayed(this, delay)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Inicialização dos componentes
        viewPager = view.findViewById(R.id.viewPagerCarousel)
        txtBoasVindas = view.findViewById(R.id.txtBoasVindas)

        // Configuração do nome do usuário
        val nomeRecebido = arguments?.getString("USER_NAME")
        txtBoasVindas.text = if (!nomeRecebido.isNullOrEmpty()) {
            "OLÁ, ${nomeRecebido.uppercase()}"
        } else {
            "OLÁ, COLABORADOR"
        }

        configurarBotoesServico(view)

        // Configuração do Carrossel
        val images = listOf(
            R.drawable.imagema,
            R.drawable.imagemb,
            R.drawable.imagemc
        )

        viewPager.adapter = CarouselAdapter(images)
        sliderHandler.postDelayed(sliderRunnable, 5000)

        return view
    }

    private fun configurarBotoesServico(view: View) {
        val dadosServicos = listOf(
            Triple(R.id.btnBeneficios, "Benefícios", R.drawable.beneficios),
            Triple(R.id.btnHolerites, "Holerites", R.drawable.holerites),
            Triple(R.id.btnExames, "Exames", R.drawable.exames),
            Triple(R.id.btnAtestados, "Atestados", R.drawable.ic_atestados)
        )

        dadosServicos.forEach { (idCard, titulo, idIcone) ->
            val layoutBotao = view.findViewById<View>(idCard)

            layoutBotao?.findViewById<TextView>(R.id.txtServicoNome)?.text = titulo
            layoutBotao?.findViewById<ImageView>(R.id.imgServicoIcone)?.setImageResource(idIcone)
1


            layoutBotao?.setOnClickListener {
                when (idCard) {
                    R.id.btnBeneficios -> irParaFragment(BeneficiosFragment())
                    R.id.btnExames -> irParaFragment(ExamesFragment())
                    R.id.btnHolerites -> irParaFragment(HoleritesFragment())
                    R.id.btnAtestados -> irParaFragment(AtestadosFragment())
                }
            }
        }
    }

    // Função auxiliar para trocar de tela de forma limpa
    private fun irParaFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    inner class CarouselAdapter(private val imageList: List<Int>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private val TYPE_IMAGE = 0
        private val TYPE_CALENDAR = 1

        override fun getItemCount(): Int = imageList.size + 1

        override fun getItemViewType(position: Int): Int {
            return if (position == imageList.size) TYPE_CALENDAR else TYPE_IMAGE
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return if (viewType == TYPE_IMAGE) {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
                ImageViewHolder(v)
            } else {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_slide, parent, false)
                CalendarViewHolder(v)
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is ImageViewHolder) {
                holder.imageView.setImageResource(imageList[position])
            } else if (holder is CalendarViewHolder) {
                holder.itemView.setOnClickListener {
                    irParaFragment(CalendarioDetalheFragment())
                }
                holder.calendarView.isEnabled = false
                holder.calendarView.setOnClickListener {
                    holder.itemView.performClick()
                }
            }
        }

        inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val imageView: ImageView = view.findViewById(R.id.imgItem)
        }

        inner class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val calendarView: android.widget.CalendarView = view.findViewById(R.id.calendarItem)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sliderHandler.removeCallbacks(sliderRunnable)
    }
}class HoleritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout que contém o banner "Visualize seus holerites" que você criou
        return inflater.inflate(R.layout.activity_holerites, container, false)
    }
}