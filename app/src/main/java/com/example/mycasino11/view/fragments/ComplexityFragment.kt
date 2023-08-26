package com.example.mycasino11.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.example.mycasino11.R
import com.example.mycasino11.constant.*
import kotlinx.android.synthetic.main.fragment_complexity.*

class ComplexityFragment : Fragment() {

    private val bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_complexity, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //выйти в меню
        id_comp_button_back.setOnClickListener {
            MAIN.navController.navigate(R.id.action_complexityFragment_to_menuFragment)
        }

        //выйти в меню
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.navController.navigate(R.id.action_complexityFragment_to_menuFragment)
        }



        id_comp_tv_record_easy.text = "best result:${MAIN.getRecordEasy()}"
        id_comp_tv_record_middle.text = "best result:${MAIN.getRecordMiddle()}"
        id_comp_tv_record_hard.text = "best result:${MAIN.getRecordHard()}"

        //играть на легком уровне
        id_comp_button_go_easy.setOnClickListener {
            bundle.putString(COMPLEXITY, COMPLEXITY_EASY)
            MAIN.navController.navigate(R.id.action_complexityFragment_to_gameFragment,bundle)
        }

        //играть на среднем уровне
        id_comp_button_go_middle.setOnClickListener {
            bundle.putString(COMPLEXITY, COMPLEXITY_MIDDLE)
            MAIN.navController.navigate(R.id.action_complexityFragment_to_gameFragment,bundle)
        }

        //играть на сложном уровне
        id_comp_button_go_hard.setOnClickListener {
            bundle.putString(COMPLEXITY, COMPLEXITY_HARD)
            MAIN.navController.navigate(R.id.action_complexityFragment_to_gameFragment,bundle)
        }



    }
}