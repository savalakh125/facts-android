package com.dropbox.facts.uicomponent.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.dropbox.facts.uicomponent.model.UIItem

/**
 * A generic view holder which contains the view data binding
 * of the respective Activity/Fragment, as needed
 *
 */
abstract class DItemViewHolder<BINDING: ViewDataBinding, UI_ITEM: UIItem>(protected val binding: BINDING) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: UI_ITEM, index: Int)

}
