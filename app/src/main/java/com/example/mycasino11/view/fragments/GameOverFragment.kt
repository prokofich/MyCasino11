package com.example.mycasino11.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import com.example.mycasino11.R
import com.example.mycasino11.constant.COMPLEXITY
import com.example.mycasino11.constant.COMPLEXITY_EASY
import com.example.mycasino11.constant.COMPLEXITY_HARD
import com.example.mycasino11.constant.COMPLEXITY_MIDDLE
import com.example.mycasino11.constant.CURRENT_RESULT
import com.example.mycasino11.constant.MAIN
import kotlinx.android.synthetic.main.fragment_game_over.id_gameover_button_change_comp
import kotlinx.android.synthetic.main.fragment_game_over.id_gameover_button_finish
import kotlinx.android.synthetic.main.fragment_game_over.id_gameover_button_restart
import kotlinx.android.synthetic.main.fragment_game_over.id_gameover_tv_current_result
import kotlinx.android.synthetic.main.fragment_game_over.id_gameover_tv_record
import kotlinx.android.synthetic.main.fragment_game_over.id_gameover_tv_result_title

class GameOverFragment : Fragment() {

    private val bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_over, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //показ всех результатов
        when(requireArguments().getString(COMPLEXITY)){
            COMPLEXITY_EASY -> {
                showResultInTextView(MAIN.getRecordEasy())
            }
            COMPLEXITY_MIDDLE -> {
                showResultInTextView(MAIN.getRecordMiddle())
            }
            COMPLEXITY_HARD -> {
                showResultInTextView(MAIN.getRecordHard())
            }
        }

        //выйти в меню
        id_gameover_button_finish.setOnClickListener {
            MAIN.navController.navigate(R.id.action_gameOverFragment_to_menuFragment)
        }

        //поменять сложность игры
        id_gameover_button_change_comp.setOnClickListener {
            MAIN.navController.navigate(R.id.action_gameOverFragment_to_complexityFragment)
        }

        //выйти в меню
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.navController.navigate(R.id.action_gameOverFragment_to_menuFragment)
        }

        //рестарт
        id_gameover_button_restart.setOnClickListener {
            bundle.putString(COMPLEXITY,requireArguments().getString(COMPLEXITY))
            MAIN.navController.navigate(R.id.action_gameOverFragment_to_gameFragment,bundle)
        }

    }

    //функция показов всех результатов
    @SuppressLint("SetTextI18n")
    private fun showResultInTextView(record:Int){
        if(requireArguments().getInt(CURRENT_RESULT) > record){
            id_gameover_tv_result_title.text = "good result!"
            id_gameover_tv_current_result.text = "you have completed ${requireArguments().getInt(CURRENT_RESULT)} levels"
            id_gameover_tv_record.text = "you have improved your score!"
            when(requireArguments().getString(COMPLEXITY)){
                COMPLEXITY_EASY -> { MAIN.setRecordEasy(requireArguments().getInt(CURRENT_RESULT)) }
                COMPLEXITY_MIDDLE -> { MAIN.setRecordMiddle(requireArguments().getInt(CURRENT_RESULT)) }
                COMPLEXITY_HARD -> { MAIN.setRecordHard(requireArguments().getInt(CURRENT_RESULT)) }
            }
        }else{
            id_gameover_tv_result_title.text = "not a bad result"
            id_gameover_tv_current_result.text = "you have completed ${requireArguments().getInt(CURRENT_RESULT)} levels"
            id_gameover_tv_record.text = "you have ${record-requireArguments().getInt(CURRENT_RESULT)} levels left before the record"
        }
    }


}