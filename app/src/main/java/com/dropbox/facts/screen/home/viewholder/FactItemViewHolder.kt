package com.dropbox.facts.screen.home.viewholder

import com.dropbox.facts.databinding.ItemFactBinding
import com.dropbox.facts.screen.home.model.UIFactItem
import com.dropbox.facts.uicomponent.viewholder.DItemViewHolder
import com.dropbox.facts.utils.ImageUtils

class FactItemViewHolder(binding: ItemFactBinding) : DItemViewHolder<ItemFactBinding, UIFactItem>(binding) {

    override fun bind(item: UIFactItem, index: Int) {
        binding.body.text = item.body
        binding.title.text = item.title

        ImageUtils.loadImage(item.image, binding.image)
    }
}