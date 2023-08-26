package com.example.mycasino11.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mycasino11.R
import com.example.mycasino11.constant.COMPLEXITY_EASY
import com.example.mycasino11.view.fragments.GameFragment
import kotlinx.android.synthetic.main.item_rv_number_easy.view.id_item_rv_easy_img
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EasyAdapter(private val context:Context,private val intrfc:InterfaceLevelUp) : RecyclerView.Adapter<EasyAdapter.EasyViewHolder>()  {

    private var listNumber = emptyList<Int>()
    private var listMyAnswers = mutableListOf<Int>()
    private var job:Job = Job()
    private var job2:Job = Job()
    private var currentLevel = 1

    private var fragmentStartTime = 0L
    private val clickDelay = 5000L // Задержка в миллисекундах (5 секунд)

    class EasyViewHolder(view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EasyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_number_easy,parent,false)
        return EasyViewHolder(view)
    }

    override fun onBindViewHolder(holder: EasyViewHolder, position: Int) {
        job = CoroutineScope(Dispatchers.Main).launch {
            holder.itemView.id_item_rv_easy_img.text = listNumber[position].toString()
            delay(5000)
            holder.itemView.id_item_rv_easy_img.text = ""
        }
    }

    override fun onViewAttachedToWindow(holder: EasyViewHolder) {
        super.onViewAttachedToWindow(holder)

        holder.itemView.setOnClickListener {
            var currentTime = System.currentTimeMillis()
            if (currentTime - fragmentStartTime >= clickDelay && listNumber[holder.adapterPosition] !in listMyAnswers) {

                job2 = CoroutineScope(Dispatchers.Main).launch {
                    holder.itemView.id_item_rv_easy_img.isEnabled = false
                    if(listMyAnswers.size==0){
                        if(listNumber[holder.adapterPosition].toString() == "1"){
                            holder.itemView.id_item_rv_easy_img.text = listNumber[holder.adapterPosition].toString()
                            listMyAnswers.add(listNumber[holder.adapterPosition])
                            showToast("$listMyAnswers")
                        }else{
                            listMyAnswers = listNumber.toMutableList()
                            holder.itemView.id_item_rv_easy_img.text = listNumber[holder.adapterPosition].toString()
                            showToast("wrong...")
                            delay(2000)
                            GameFragment.gameOver(COMPLEXITY_EASY,currentLevel)
                        }
                    }else{
                        if(listNumber[holder.adapterPosition] == listMyAnswers[listMyAnswers.size-1]+1){
                            if(listMyAnswers.size==8){
                                holder.itemView.id_item_rv_easy_img.text = listNumber[holder.adapterPosition].toString()
                                listMyAnswers.add(listNumber[holder.adapterPosition])
                                showToast("VICTORY!")
                                delay(2000)
                                listMyAnswers = mutableListOf<Int>()
                                currentLevel+=1
                                intrfc.levelUp(currentLevel)
                            }else{
                                holder.itemView.id_item_rv_easy_img.text = listNumber[holder.adapterPosition].toString()
                                listMyAnswers.add(listNumber[holder.adapterPosition])
                            }
                        }else{
                            listMyAnswers = listNumber.toMutableList()
                            holder.itemView.id_item_rv_easy_img.text = listNumber[holder.adapterPosition].toString()
                            showToast("wrong...")
                            delay(2000)
                            GameFragment.gameOver(COMPLEXITY_EASY,currentLevel)
                        }
                    }
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return listNumber.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list:List<Int>){
        listNumber = list
        notifyDataSetChanged()
    }

    fun setFragmentStartTime(startTime: Long) {
        fragmentStartTime = startTime
    }

    private fun showToast(message:String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

}