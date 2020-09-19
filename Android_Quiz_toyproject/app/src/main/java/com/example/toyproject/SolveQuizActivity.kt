package com.example.toyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_solve_quiz.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SolveQuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solve_quiz)

        val nickname = "test9"

        (application as MasterApplication).service.getNicknameQuiz(nickname)
            .enqueue(object: Callback<QuizList> {
                override fun onFailure(call: Call<QuizList>, t: Throwable) {
                    Toast.makeText(this@SolveQuizActivity, "Quiz 실패", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<QuizList>,
                    response: Response<QuizList>
                ) {
                    if (response.isSuccessful) {
                        val quizList = response.body()

                        Log.d("soo", ""+response.body())

                        Toast.makeText(this@SolveQuizActivity, "Quiz 성공", Toast.LENGTH_LONG).show()

                        val solveQuizAdapter = SolveQuizAdapter(quizList!!.quizList, LayoutInflater.from(this@SolveQuizActivity))
                        solve_recyclerview.adapter = solveQuizAdapter
                        solve_recyclerview.layoutManager = LinearLayoutManager(this@SolveQuizActivity)

                    }
                }
            })

        /*
        // 퀴즈 제출 버튼 클릭
        solve_finish.setOnClickListener {
            for (i in 0 until 5) {
                Log.d("msgg", "" + quizList[i].answer)
            }

            // 이미 제출한 사용자일 경우
            // if ()


            var score: Int = 0

            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_view, null)

            // dialog score 변경
            val dialogScore = dialogView.findViewById<TextView>(R.id.dialog_score)
            dialogScore.setText("$score 점")

            // dialog 띄움
            builder.setView(dialogView)
                .setPositiveButton("확인") { dialog, i ->
                    //val date = dialogDate.text.toString()
                    //val content = dialogContent.text.toString()
                    //planList.add(Plan(date, content))


                    // score 전달
                    val intent = Intent(this, MyifoActivity::class.java)
                    intent.apply {
                        this.putExtra("score", score)
                    }
                    startActivityForResult(intent, 200)

                    // val num1 = intent.getIntExtra("num1", 0)

                }
                .show()
        }
        */

    }
}