package com.dropbox.facts.screen.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dropbox.facts.R
import com.dropbox.facts.databinding.ItemFactBinding
import com.dropbox.facts.screen.home.model.UIFactItem
import com.dropbox.facts.screen.home.viewholder.FactItemViewHolder

/**
 * Adapter to handle the [UIFactItem] items.
 *
 * One possible future approach:
 * As the app functionality increases, a BaseAdapter can be created
 * which shall handle the common aspects of creation and then binding of the view holder.
 *
 * The view holders can then taken over the responsibility of setting the data for each view
 * contained in it, based on the data/item at that position.
 * This item would be fed into the view holder from the Base Adapter.
 */
class FactsAdapter : RecyclerView.Adapter<FactItemViewHolder>() {

    companion object {
        private val TAG = FactsAdapter::class.java.simpleName
    }

    private val items: MutableList<UIFactItem> = mutableListOf()

    fun setData(items: List<UIFactItem>) {
        Log.d(TAG, "list updated. new size: " + items.size)

        this.items.clear()
        this.items.addAll(items)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactItemViewHolder {
        val binding = DataBindingUtil.inflate<ItemFactBinding>(LayoutInflater.from(parent.context), R.layout.item_fact, parent, false)
        return FactItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: FactItemViewHolder, position: Int) {
        /*
            This check is to take care of the possibility that the list is being modified while
            onBindViewHolder is called - an edge case but still needs to be handled.
         */
        if (position < items.size) {
            val item = items[position]
            holder.bind(item, position)
        }
    }
}