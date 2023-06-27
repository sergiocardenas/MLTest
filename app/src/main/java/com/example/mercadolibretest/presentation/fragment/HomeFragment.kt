package com.example.mercadolibretest.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mercadolibretest.R
import com.example.mercadolibretest.presentation.screen.HomeSearchScreen
import com.example.mercadolibretest.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment()  {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val composeView = ComposeView(requireContext())
        composeView.setContent {
            HomeSearchScreen(homeViewModel)
        }

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val nextButton = root.findViewById<Button>(R.id.button_home)
        nextButton.setOnClickListener {
            goToDetail()
        }

        return composeView
    }

    private fun goToDetail(){
        findNavController().navigate(
            resId = R.id.action_HomeFragment_to_DetailFragment
        )
    }



}