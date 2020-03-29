package com.dropbox.facts.data.response.facts

import com.google.gson.annotations.SerializedName

class APIFacts (@SerializedName("title")
                val title: String? = null,
                @SerializedName("rows")
                val list: List<APIFact>? = null)