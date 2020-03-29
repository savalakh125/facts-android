package com.dropbox.facts.utils

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import com.dropbox.facts.R
import com.squareup.picasso.Picasso

object ImageUtils {

    fun loadImage(url: String, imageView: ImageView) {
        if (url.isNotEmpty()) {
            imageView.visibility = VISIBLE

            Picasso.get()
                .load(url)
                .placeholder(R.color.colorPrimary)
                .error(R.color.colorAccent)
                .into(imageView)
        } else {
            imageView.visibility = GONE
        }

    }

}