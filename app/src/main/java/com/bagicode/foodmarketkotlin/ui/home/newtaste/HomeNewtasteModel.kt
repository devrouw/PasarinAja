package com.bagicode.foodmarketkotlin.ui.home.newtaste

class HomeNewtasteModel(title: String, subtitle: String, src: String, rating: Float) {
    var title = ""
    var subtitle = ""
    var src = ""
    var rating = 0f

    init {
        this.title = title
        this.subtitle = subtitle
        this.src = src
        this.rating = rating
    }
}