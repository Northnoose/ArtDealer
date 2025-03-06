package com.example.artdealer.data

object ArtistDataSource {
    fun loadArtists(): List<ArtistData> {
        return listOf(
            ArtistData(100, "Carl", "Bjerke"),
            ArtistData(101, "Petter", "Hasselquist"),
            ArtistData(102, "Frida", "Kahlo"),
            ArtistData(103, "Edvard", "Munch"),
            ArtistData(104, "Ansel", "Adams"),
            ArtistData(105, "Cindy", "Sherman")
        )
    }
}
