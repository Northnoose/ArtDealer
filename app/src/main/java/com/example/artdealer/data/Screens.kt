package com.example.artdealer.data

enum class Screens(val route: String) {
    Home("home"),
    DetailedView("details/{photoId}"),
    ShoppingCart("cart"),
    Artist("artist"),
    Category("category"),
    Gallery("gallery");

    companion object {
        fun fromRoute(route: String?): Screens =
            Screens.entries.find { screen ->
                screen.route.substringBefore("/{") == route?.substringBefore("/")
            } ?: Home
    }

    fun withArgs(vararg args: Any): String {
        return route.replace(Regex("""\{.*?\}""")) { matchResult ->
            val index = route.split("/").indexOf(matchResult.value)
            args.getOrNull(index - 1)?.toString() ?: matchResult.value
        }
    }
}
