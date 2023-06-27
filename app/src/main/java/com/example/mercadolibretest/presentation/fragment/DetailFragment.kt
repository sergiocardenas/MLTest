package com.example.mercadolibretest.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.mercadolibretest.R

class DetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_detail, container, false)
        val nextButton = root.findViewById<Button>(R.id.button_home)
        nextButton.setOnClickListener {
            goToHome()
        }
        return root
    }

    private fun goToHome(){
        findNavController().navigate(
            resId = R.id.action_DetailFragment_to_HomeFragment
        )
    }
}