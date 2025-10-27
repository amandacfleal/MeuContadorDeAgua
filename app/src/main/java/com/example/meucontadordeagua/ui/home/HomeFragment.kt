package com.example.meucontadordeagua.ui.home // (Confirme seu pacote)

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
// import com.example.meucontadordeagua.R // (O Android Studio vai adicionar isso)
import com.example.meucontadordeagua.databinding.FragmentHomeBinding // Import automático do ViewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint // Diz ao Hilt para injetar o ViewModel aqui
class HomeFragment : Fragment() {

    // Hilt vai criar e fornecer o ViewModel que já fizemos
    private val viewModel: HomeViewModel by viewModels()

    // ViewBinding (que ativamos no build.gradle) para acessar o XML
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Infla (desenha) o nosso layout fragment_home.xml
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    //
    // AQUI ESTÁ A CORREÇÃO PRINCIPAL:
    // A ordem correta é (view: View, savedInstanceState: Bundle?)
    //
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura os cliques dos botões
        setupClicks()

        // Começa a "ouvir" o ViewModel
        observeViewModel()
    }

    private fun setupClicks() {
        binding.btnAdd250.setOnClickListener {
            // A View SÓ avisa o ViewModel. Ela não faz lógica.
            viewModel.adicionarAgua(250)
        }

        binding.btnAdd500.setOnClickListener {
            // A View SÓ avisa o ViewModel.
            viewModel.adicionarAgua(500)
        }
    }

    private fun observeViewModel() {
        // Este bloco é o "observador" do LiveData/Flow
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                // Aqui, estamos "ouvindo" o StateFlow 'consumoHoje' do ViewModel
                viewModel.consumoHoje.collectLatest { totalMl ->

                    // AQUI ESTÁ A CORREÇÃO DO AVISO AMARELO:
                    // Usando String.format para evitar concatenação direta
                    binding.textTotalMl.text = String.format("%dml", totalMl)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpa a referência do binding
    }
}