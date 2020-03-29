package com.dropbox.facts.data.response.facts

import com.google.gson.annotations.SerializedName

class APIFact (@SerializedName("title")
               val title: String? = null,
               @SerializedName("description")
               val description: String? = null,
               @SerializedName("imageHref")
               val imageHref: String? = null)