package com.example.firstapp

import android.graphics.Insets.add
import android.widget.ImageView
import coil.ComponentRegistry
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import java.text.NumberFormat
import java.util.*

fun languagesToString(languages: List<Language>): String{

    var result = ""

    for ((index , language) in languages.withIndex())
        if (index != languages.lastIndex)
            result += "${language.name}, "
        else
            result += language.name
    return result
}

fun formatNumber(number: Long): String{
    val string = NumberFormat.getInstance().format(number)
    return string
}


suspend fun loadSvg(imageView: ImageView, url: String){
    if (url.lowercase(Locale.ENGLISH).endsWith("svg")){
        val imageLoader = ImageLoader.Builder(imageView.context)
            .components{
                add(SvgDecoder(imageView.context))
            }
        .build()
        val request = ImageRequest.Builder(imageView.context)
            .data(url)
            .target(imageView)
            .build()
        imageLoader.execute(request)
    }else{
        imageView.load(url)
    }
}

private fun ComponentRegistry.Builder.add(svgDecoder: SvgDecoder) {

}












