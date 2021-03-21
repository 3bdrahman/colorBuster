package com.ayosef.colorbuster.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayosef.colorbuster.R

class MainFragment : Fragment() {
private lateinit var recycler:RecyclerView
private lateinit var definitionTextView: TextView

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view= inflater.inflate(R.layout.main_fragment, container, false)
        recycler=view.findViewById(R.id.recyclerView)
        recycler.layoutManager=LinearLayoutManager(context)
        definitionTextView = view.findViewById(R.id.defintion_textView)
        definitionTextView.setText("")
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }
    private inner class BusterViewHolder(view: View):RecyclerView.ViewHolder(view), View.OnClickListener{
        private lateinit var phrase: phrase
        private val wordTextView:TextView = itemView.findViewById(R.id.term_textView)
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            definitionTextView.text = phrase.term
        }

    }

}