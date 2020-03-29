package com.dropbox.facts.data.mapper

import com.dropbox.facts.data.response.facts.APIFacts
import com.dropbox.facts.domain.usecase.facts.Fact
import com.dropbox.facts.domain.usecase.facts.Facts

class APIFactsMapper : APIBaseMapper<APIFacts, Facts>() {

    override fun modelToDomain(dataModel: APIFacts): Facts {
        val rows = dataModel.list.orEmpty().map {
            Fact(it.title.orEmpty(), it.description.orEmpty(), it.imageHref.orEmpty())
        }

        return Facts(dataModel.title.orEmpty(), rows)
    }

}