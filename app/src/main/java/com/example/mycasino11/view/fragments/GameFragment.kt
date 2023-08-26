package com.example.mycasino11.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mycasino11.R
import com.example.mycasino11.adapter.EasyAdapter
import com.example.mycasino11.adapter.HardAdapter
import com.example.mycasino11.adapter.InterfaceLevelUp
import com.example.mycasino11.adapter.MiddleAdapter
import com.example.mycasino11.constant.COMPLEXITY
import com.example.mycasino11.constant.COMPLEXITY_EASY
import com.example.mycasino11.constant.COMPLEXITY_HARD
import com.example.mycasino11.constant.COMPLEXITY_MIDDLE
import com.example.mycasino11.constant.CURRENT_RESULT
import com.example.mycasino11.constant.MAIN
import com.example.mycasino11.constant.listNumberForEasy
import com.example.mycasino11.constant.listNumberForHard
import com.example.mycasino11.constant.listNumberForMiddle
import com.example.mycasino11.constant.url_image_background
import kotlinx.android.synthetic.main.fragment_game.id_game_img
import kotlinx.android.synthetic.main.fragment_game.id_game_rv
import kotlinx.android.synthetic.main.fragment_game.id_game_tv_level
import kotlinx.android.synthetic.main.fragment_menu.id_menu_img

class GameFragment : Fragment(),InterfaceLevelUp {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterEasy: EasyAdapter
    private lateinit var adapterMiddle: MiddleAdapter
    private lateinit var adapterHard: HardAdapter

    private var listForAdapter = listOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadBackgroundImage()

        showToast("remember the places of the numbers and select them in order")

        recyclerView = id_game_rv
        val startTime = System.currentTimeMillis()

        when(requireArguments().getString(COMPLEXITY)){
            COMPLEXITY_EASY -> {
                adapterEasy = EasyAdapter(requireContext(),this)
                recyclerView.layoutManager = GridLayoutManager(requireContext(),3)
                recyclerView.adapter = adapterEasy
                adapterEasy.setFragmentStartTime(startTime)
                listForAdapter = listNumberForEasy.shuffled()
                adapterEasy.setList(listForAdapter)
            }
            COMPLEXITY_MIDDLE -> {
                adapterMiddle = MiddleAdapter(requireContext(),this)
                recyclerView.layoutManager = GridLayoutManager(requireContext(),4)
                recyclerView.adapter = adapterMiddle
                adapterMiddle.setFragmentStartTime(startTime)
                listForAdapter = listNumberForMiddle.shuffled()
                adapterMiddle.setList(listForAdapter)
            }
            COMPLEXITY_HARD -> {
                adapterHard = HardAdapter(requireContext(),this)
                recyclerView.layoutManager = GridLayoutManager(requireContext(),5)
                recyclerView.adapter = adapterHard
                adapterHard.setFragmentStartTime(startTime)
                listForAdapter = listNumberForHard.shuffled()
                adapterHard.setList(listForAdapter)
            }
        }

    }


    companion object{
        fun gameOver(complexity:String,result:Int){
            val bundle = Bundle()
            bundle.putString(COMPLEXITY,complexity)
            bundle.putInt(CURRENT_RESULT,result)
            MAIN.navController.navigate(R.id.action_gameFragment_to_gameOverFragment,bundle)
        }
    }

    private fun showToast(message:String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }

    //функция загрузки фоновой картинки
    private fun loadBackgroundImage(){
        Glide.with(requireContext())
            .load(url_image_background)
            .into(id_game_img)
    }

    override fun levelUp(level: Int) {
        id_game_tv_level.text = "$level lvl"
        when(requireArguments().getString(COMPLEXITY)){
            COMPLEXITY_EASY -> {
                adapterEasy.setFragmentStartTime(System.currentTimeMillis())
                listForAdapter = listNumberForEasy.shuffled()
                adapterEasy.setList(listForAdapter)
            }
            COMPLEXITY_MIDDLE -> {
                adapterMiddle.setFragmentStartTime(System.currentTimeMillis())
                listForAdapter = listNumberForMiddle.shuffled()
                adapterMiddle.setList(listForAdapter)
            }
            COMPLEXITY_HARD -> {
                adapterHard.setFragmentStartTime(System.currentTimeMillis())
                listForAdapter = listNumberForHard.shuffled()
                adapterHard.setList(listForAdapter)
            }
        }
    }

}