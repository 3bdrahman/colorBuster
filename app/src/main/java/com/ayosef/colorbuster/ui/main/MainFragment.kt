package com.ayosef.colorbuster.ui.main

import android.graphics.Color
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
       definitionTextView.text=""
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.vocabulary.observe(viewLifecycleOwner,{recycler.adapter=BusterAdapter(it)})
    }
    private inner class BusterViewHolder(view: View):RecyclerView.ViewHolder(view), View.OnClickListener{
        private lateinit var phrase: phrase
        private val wordTextView:TextView = itemView.findViewById(R.id.term_textView)
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            definitionTextView.text = "ColorItem(colorId= " + phrase.colorId.toString() + " ,hexString= " + phrase.hexString + " ,name= "+phrase.name + ")"
        }
        fun bind(phrase: phrase){
            this.phrase=phrase
            wordTextView.text=phrase.name
            definitionTextView.text = "ColorItem(colorId= " + phrase.colorId.toString() + " ,hexString= " + phrase.hexString + " ,name= "+phrase.name+")"
            itemView.setBackgroundColor(Color.parseColor(phrase.hexString))
        }

    }
    private inner class BusterAdapter(private val list: List<phrase>):RecyclerView.Adapter<BusterViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusterViewHolder {
            val view=layoutInflater.inflate(R.layout.recycler_item,parent,false)
            return BusterViewHolder(view)
        }

        override fun onBindViewHolder(holder: BusterViewHolder, position: Int) {
            holder.bind(list[position])
        }

        override fun getItemCount() = list.size

    }

}