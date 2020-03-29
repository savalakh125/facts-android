package com.dropbox.facts.mapper

import com.dropbox.facts.domain.carrier.DResult
import com.dropbox.facts.uicomponent.model.UIError
import com.dropbox.facts.uicomponent.model.UIModel

/**
 *
 * Base UI Mapper implementation to handle all common mappings
 * from DOMAIN layer.
 * And also to handle common error scenarios.
 *
 * Based on the current set of requirements,
 * it is assumed that all the UI Models shall conform to [UIModel]
 * with ERROR always being of type [UIError].
 *
 * [UIMapper] can be directly implemented for models containing more complex
 * data structures for UI_MODEL and ERROR components
 */
abstract class UIBaseMapper<UI_MODEL, DOMAIN_MODEL> :
    UIMapper<UIModel<UI_MODEL>, DResult<DOMAIN_MODEL>> {

    override fun fromDomain(domain: DResult<DOMAIN_MODEL>): UIModel<UI_MODEL> {
        val model: UIModel<UI_MODEL> = UIModel()

        val domainSuccess = domain.success
        if (domainSuccess != null) {
            model.data = getModel(domainSuccess)
        } else {
            model.error = UIError(domain.error?.message)
        }

        return model
    }

    protected abstract fun getModel(domainModel: DOMAIN_MODEL): UI_MODEL
}