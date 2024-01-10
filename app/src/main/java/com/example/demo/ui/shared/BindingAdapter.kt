package com.example.demo.ui.shared

import android.annotation.SuppressLint
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demo.R


@SuppressLint("SetTextI18n")
@BindingAdapter("bindHelloText")
fun bindHelloText(textView: TextView, text: String?) {
    if (!text.isNullOrEmpty()) {
        textView.text = "Hello $text"
    }
}

@BindingAdapter("bindRecyclerLayout")
fun bindRecyclerLayout(recyclerView: RecyclerView, horizontal: Boolean) {
    if (horizontal) {
        val layout =
            LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layout
    }
}

@BindingAdapter("bindImage")
fun bindImage(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}

@SuppressLint("UseCompatLoadingForDrawables")
@BindingAdapter("bindFav")
fun bindFav(imageButton: ImageButton, isFav: Boolean) {
    if (isFav)
        imageButton.setImageDrawable(imageButton.context.getDrawable(R.drawable.ic_yes_fav))
    else
        imageButton.setImageDrawable(imageButton.context.getDrawable(R.drawable.ic_yes_fav))
}

@SuppressLint("SetTextI18n")
@BindingAdapter("bindDistance")
fun bindDistance(textView: TextView, distacne: Int) {
    textView.text = "$distacne m"
}

@BindingAdapter("bindStars")
fun bindStars(imageView: ImageView, rate: String) {

}