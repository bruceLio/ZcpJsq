package com.zcp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val answerAdapter: AnswerAdapter by lazy { AnswerAdapter(this) }

    private val tvQuestion: TextView by lazy { findViewById<TextView>(R.id.tvQuestion) }

    private val rvKey: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recycleView) }

    private val rvAnswer: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rvAnswer) }

    private val buffer = ArrayList<String>()

    private val maxSize = 11

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvKey.adapter = KeyAdapter(this).apply {
            onItemClick = {
                when (it) {
                    Key.KEY_CLEAR -> {
                        buffer.clear()
                        tvQuestion.text = ""
                    }
                    Key.KEY_DEL -> {
                        if (buffer.size > 0) {
                            buffer.removeAt(buffer.size - 1)
                            tvQuestion.text = getQuestionText()
                        }
                    }
                    Key.KEY_EQUAL -> {
                        val toIntList = toIntList()
                        val result = ZcpCa().getResult(toIntList)
                        val toMutableList = result.toMutableList()
                        toMutableList.sortByDescending { it.getSize() }
                        answerAdapter.setData(toMutableList)
                        buffer.clear()
                    }
                    else -> {
                        if (buffer.size >= maxSize) {
                            Toast.makeText(this@MainActivity, "最大${maxSize}位", Toast.LENGTH_SHORT).show()
                        } else {
                            buffer.add(it)
                            tvQuestion.text = getQuestionText()
                        }
                    }
                }
            }
        }
        rvAnswer.adapter = answerAdapter
    }

    private fun getQuestionText(): CharSequence? {
        val string = StringBuffer()
        buffer.forEach {
            string.append(it)
            string.append(",")
        }
        if (string.isNotEmpty()) {
            string.delete(string.length - 1, string.length)
        }
        return string.toString()
    }

    private fun toIntList(): ArrayList<Int> {
        val list = ArrayList<Int>()
        buffer.forEach {
            when (it) {
                Key.KEY_A -> list.add(1)
                Key.KEY_J -> list.add(11)
                Key.KEY_Q -> list.add(12)
                Key.KEY_K -> list.add(13)
                else -> list.add(it.toInt())
            }
        }
        return list
    }
}