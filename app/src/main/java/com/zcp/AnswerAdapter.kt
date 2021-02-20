package com.zcp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnswerAdapter(private val context: Context) :
    RecyclerView.Adapter<AnswerAdapter.AnswerHolder>() {
    private val answers = ArrayList<Answer>()
    private var maxSize = 0

    inner class AnswerHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerHolder {
        return AnswerHolder(
            LayoutInflater.from(context).inflate(R.layout.item_answer, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return answers.size
    }

    override fun onBindViewHolder(holder: AnswerHolder, position: Int) {
        val answer = answers[position]
        if (position == 0) {
            maxSize = answer.getSize()
        }
        holder.textView.setBackgroundColor(context.resources.getColor(if (maxSize == answer.getSize()) R.color.purple_200 else R.color.trans))
        holder.textView.text = "${answer.oneSet} = ${answer.nextSet}"
    }

    fun setData(data: Collection<Answer>) {
        answers.clear()
        answers.addAll(data)
        notifyDataSetChanged()
    }
}