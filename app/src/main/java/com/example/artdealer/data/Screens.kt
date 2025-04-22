package com.example.artdealer.data

enum class Screens(val route: String) {
    Home("home"),
    DetailedView("details/{photoId}"),
    Artist("artist"),
    Category("category"),
    Gallery("gallery");

    fun withArgs(vararg args: Any): String {
        return route.replace(Regex("""\{.*?\}""")) { matchResult ->
            val index = route.split("/").indexOf(matchResult.value)
            args.getOrNull(index - 1)?.toString() ?: matchResult.value
        }
    }
}
