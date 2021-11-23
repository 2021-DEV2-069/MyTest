package com.samples.test.view.game.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samples.test.databinding.CellItemLayoutBinding
import com.samples.test.model.Cell

class GameAdapter :
    ListAdapter<Cell, GameAdapter.MyViewHolder>(CellListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rootView = CellItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MyViewHolder(private val binding: CellItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cellValue: Cell) {
        }
    }
}

class CellListDiffCallback : DiffUtil.ItemCallback<Cell>() {
    override fun areItemsTheSame(oldItem: Cell, newItem: Cell): Boolean =
        oldItem.state == newItem.state

    override fun areContentsTheSame(oldItem: Cell, newItem: Cell): Boolean =
        oldItem == newItem
}
