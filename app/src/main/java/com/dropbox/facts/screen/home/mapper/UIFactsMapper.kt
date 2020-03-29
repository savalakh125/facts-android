package com.dropbox.facts.screen.home.mapper

import com.dropbox.facts.domain.usecase.facts.Facts
import com.dropbox.facts.mapper.UIBaseMapper
import com.dropbox.facts.screen.home.model.UIFactItem
import com.dropbox.facts.screen.home.model.UIFacts

/**
 * Mapper to map [Facts] from DOMAIN layer to [UIFacts]
 * which is used by the UI.
 */
class UIFactsMapper : UIBaseMapper<UIFacts, Facts>() {

    override fun getModel(domainModel: Facts): UIFacts {

        val list = domainModel.list.map {
            UIFactItem(it.imageHref, it.title, it.description)
        }.filter {
            !(it.body.isEmpty() && it.image.isEmpty() && it.title.isEmpty())
        }

        return UIFacts(domainModel.title, list)
    }

}