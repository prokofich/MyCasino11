package com.example.mycasino11.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import com.example.mycasino11.R
import com.example.mycasino11.constant.MAIN
import com.example.mycasino11.constant.url_image_background
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка фоновой картинки
        loadBackgroundImage()

        //переход к игре
        id_menu_cs_play.setOnClickListener {
            MAIN.navController.navigate(R.id.action_menuFragment_to_complexityFragment)
        }

        //выход из игры
        id_menu_cs_exit.setOnClickListener {
            MAIN.finishAffinity()
        }

        //выход из игры
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.finishAffinity()
        }

    }

    //функция загрузки фоновой картинки
    private fun loadBackgroundImage(){
        Glide.with(requireContext())
            .load(url_image_background)
            .into(id_menu_img)
    }

}