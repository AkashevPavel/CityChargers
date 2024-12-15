package com.akashev.citychargers.presentation.chargers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akashev.citychargers.R
import com.akashev.citychargers.data.model.Charger
import com.akashev.citychargers.databinding.ChargerListItemBinding

class ChargersAdapter : RecyclerView.Adapter<ChargersViewHolder>() {

    var items = emptyList<Charger>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun updateItems(list: List<Charger>) {
        DiffUtil.calculateDiff(DiffCallback(items, list)).also {
            it.dispatchUpdatesTo(this)
        }
        items = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ChargersViewHolder(
        ChargerListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ChargersViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class ChargersViewHolder(
    private val binding: ChargerListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Charger) {
        binding.chargerName.text = item.name
        binding.chargerAddress.text = item.address

        val background = R.drawable.red_background.takeIf { item.isBusy }
            ?: R.drawable.green_background
        binding.root.background = ResourcesCompat.getDrawable(
            binding.root.resources, background, null
        )
    }
}

class DiffCallback(
    private val oldList: List<Charger>,
    private val newList: List<Charger>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].address.hashCode() == newList[newItemPosition].address.hashCode()

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}