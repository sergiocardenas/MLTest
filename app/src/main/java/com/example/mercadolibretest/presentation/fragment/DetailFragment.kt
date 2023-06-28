package com.example.mercadolibretest.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mercadolibretest.R
import com.example.mercadolibretest.presentation.screen.DetailMLItemScreen
import com.example.mercadolibretest.presentation.viewmodel.DetailViewModel
import com.example.mercadolibretest.presentation.viewmodel.HomeViewModel
import com.example.mercadolibretest.presentation.viewmodel.NavViewModel

class DetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var sharedViewModel: NavViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        sharedViewModel = ViewModelProvider(requireActivity())[NavViewModel::class.java]

        sharedViewModel.item.value?.let {
            detailViewModel.setItem(it)
        }


    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val composeView = ComposeView(requireContext())
        composeView.setContent {
            DetailMLItemScreen(viewModel = detailViewModel)
        }

        return composeView
    }

    private fun goToHome(){
        findNavController().navigateUp()
    }
}