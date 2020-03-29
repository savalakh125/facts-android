package com.dropbox.facts.screen.home.model

import com.dropbox.facts.uicomponent.model.UIItem
import java.util.*

class UIFactItem (val image: String,
                  val title: String,
                  val body: String) : UIItem {

    companion object {
        val ITEM_VIEW_TYPE = Objects.hash("UIFactItem")
    }

    override val itemViewType: Int
        get() = ITEM_VIEW_TYPE
}