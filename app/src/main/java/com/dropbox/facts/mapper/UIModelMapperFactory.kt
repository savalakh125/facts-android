package com.dropbox.facts.mapper

import com.dropbox.facts.screen.home.mapper.UIFactsMapper
import com.dropbox.facts.screen.home.model.UIFacts
import com.dropbox.facts.uicomponent.model.UIModel
import javax.inject.Inject

/**
 * Factory which provides the respective implementation
 * of [UIMapper] based on the ui model type to be mapped
 * from the DOMAIN layer model
 */
class UIModelMapperFactory @Inject constructor() {

    /**
     * Method to create a [UIMapper] for mapping [DOMAIN]
     * models into ui models of type:UIModel<UI>
     *
     * Currently there is only one mapper scenario which is
     * taken care of here. But this factory can be used in future
     * for all other [UIModel] related mappers as the app
     * functionality increases.
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalArgumentException::class)
    fun <UI, DOMAIN> create(uiModelType: Class<UI>) : UIMapper<UIModel<UI>, DOMAIN> {

        val mapper: UIMapper<*, *> = when (uiModelType) {

            UIFacts::class.java -> UIFactsMapper()

            else -> throw IllegalArgumentException("UI model class type unknown: $uiModelType")
        }

        return mapper as UIMapper<UIModel<UI>, DOMAIN>
    }

}