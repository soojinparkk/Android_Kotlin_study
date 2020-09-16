package com.example.toyproject

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SolveQuizAdapter (
    val quizList: ArrayList<Quiz>,
    val inflater: LayoutInflater
): RecyclerView.Adapter<SolveQuizAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val quizContent: TextView
        val yesBtn: ImageView
        val noBtn: ImageView

        init {
            quizContent = itemView.findViewById(R.id.solve_content)
            yesBtn = itemView.findViewById(R.id.solve_yes)
            noBtn = itemView.findViewById(R.id.solve_no)
        }

/*
        // yesBtn 누르면 1, noBtn 누르면 0으로 변경
        fun set(quiz: Quiz, index: Int) {
            yesBtn.setOnClickListener {
                if (quiz.answer != 1) {
                    quiz.answer = 1
                    yesBtn.setBackgroundColor(Color.GRAY)
                    noBtn.setBackgroundColor(Color.BLACK)
                } else {
                    quiz.answer = -1
                    yesBtn.setBackgroundColor(Color.BLACK)
                }
            }

            noBtn.setOnClickListener {
                if (quiz.answer != 0) {
                    quiz.answer = 0
                    yesBtn.setBackgroundColor(Color.BLACK)
                    noBtn.setBackgroundColor(Color.GRAY)
                } else {
                    quiz.answer = -1
                    yesBtn.setBackgroundColor(Color.BLACK)
                }
            }
        }
        */
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.solve_quiz_item, parent, false)
        return ViewHolder(view)
    }

    // item 총 갯수 return
    override fun getItemCount(): Int {
        return quizList.size
    }

    // onCreateViewHolder에서 만든 view와 실제 입력되는 각각의 데이터 연결
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.quizContent.setText(quizList[position].content)
    }
}