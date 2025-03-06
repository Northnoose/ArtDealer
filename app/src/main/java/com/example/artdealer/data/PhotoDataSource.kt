package com.example.artdealer.data

import com.example.artdealer.R

object PhotoDataSource {
    fun loadPictures(): List<Photo> {
        return listOf(
            // Carl Bjerke
            Photo(
                id = 200,
                title = "Refleksjoner i Byen",
                imageID = R.drawable.refleksjoner_i_byen,
                artist = ArtistData(100, "Carl", "Bjerke"),
                category = Category.NATURE,
                price = 1200f
            ),
            Photo(
                id = 201,
                title = "Skyggespill",
                imageID = R.drawable.skyggespill,
                artist = ArtistData(100, "Carl", "Bjerke"),
                category = Category.ABSTRACT,
                price = 1400f
            ),
            Photo(
                id = 202,
                title = "Natt over Oslofjorden",
                imageID = R.drawable.natt_over_oslofjorden,
                artist = ArtistData(100, "Carl", "Bjerke"),
                category = Category.NATURE,
                price = 1600f
            ),
            Photo(
                id = 203,
                title = "Neonlys i Regn",
                imageID = R.drawable.neonlys_i_regn,
                artist = ArtistData(100, "Carl", "Bjerke"),
                category = Category.ABSTRACT,
                price = 1800f
            ),

            // Petter Hasselquist (naturfotograf)
            Photo(
                id = 204,
                title = "Nordlys over Tromsø",
                imageID = R.drawable.nordlys_tromso,
                artist = ArtistData(101, "Petter", "Hasselquist"),
                category = Category.NATURE,
                price = 1500f
            ),
            Photo(
                id = 205,
                title = "Vinterlandskap i Sverige",
                imageID = R.drawable.vinterlandskap_sverige,
                artist = ArtistData(101, "Petter", "Hasselquist"),
                category = Category.NATURE,
                price = 1300f
            ),
            Photo(
                id = 206,
                title = "Rådyr i Morgengry",
                imageID = R.drawable.radyret_morgengry,
                artist = ArtistData(101, "Petter", "Hasselquist"),
                category = Category.ANIMALS,
                price = 1400f
            ),
            Photo(
                id = 207,
                title = "Fjelltoppens Stillhet",
                imageID = R.drawable.fjelltopp_stillhet,
                artist = ArtistData(101, "Petter", "Hasselquist"),
                category = Category.NATURE,
                price = 1700f
            ),

            // Frida Kahlo (kunstmaler)
            Photo(
                id = 208,
                title = "Selvportrett med Tornhalsbånd",
                imageID = R.drawable.selvportrett_tornhalsband,
                artist = ArtistData(102, "Frida", "Kahlo"),
                category = Category.PORTRAIT,
                price = 2100f
            ),
            Photo(
                id = 209,
                title = "De To Fridaer",
                imageID = R.drawable.de_to_fridaer,
                artist = ArtistData(102, "Frida", "Kahlo"),
                category = Category.PORTRAIT,
                price = 2200f
            ),
            Photo(
                id = 210,
                title = "Min Sykepleier og Jeg",
                imageID = R.drawable.min_sykepleier_og_jeg,
                artist = ArtistData(102, "Frida", "Kahlo"),
                category = Category.PORTRAIT,
                price = 2000f
            ),
            Photo(
                id = 211,
                title = "Naturens Kall",
                imageID = R.drawable.naturens_kall,
                artist = ArtistData(102, "Frida", "Kahlo"),
                category = Category.ABSTRACT,
                price = 2300f
            ),

            // Edvard Munch (ekspresjonistisk maleri)
            Photo(
                id = 212,
                title = "Skrik",
                imageID = R.drawable.skrik,
                artist = ArtistData(103, "Edvard", "Munch"),
                category = Category.ABSTRACT,
                price = 3000f
            ),
            Photo(
                id = 213,
                title = "Madonna",
                imageID = R.drawable.madonna,
                artist = ArtistData(103, "Edvard", "Munch"),
                category = Category.PORTRAIT,
                price = 2800f
            ),
            Photo(
                id = 214,
                title = "Det Syke Barn",
                imageID = R.drawable.det_syke_barn,
                artist = ArtistData(103, "Edvard", "Munch"),
                category = Category.PORTRAIT,
                price = 2600f
            ),
            Photo(
                id = 215,
                title = "Vampyr",
                imageID = R.drawable.vampyr,
                artist = ArtistData(103, "Edvard", "Munch"),
                category = Category.ABSTRACT,
                price = 2500f
            ),

            // Ansel Adams (svart-hvitt landskapsfotografi)
            Photo(
                id = 216,
                title = "Moonrise over Hernandez",
                imageID = R.drawable.moonrise_hernandez,
                artist = ArtistData(104, "Ansel", "Adams"),
                category = Category.NATURE,
                price = 1800f
            ),
            Photo(
                id = 217,
                title = "Clearing Winter Storm",
                imageID = R.drawable.clearing_winter_storm,
                artist = ArtistData(104, "Ansel", "Adams"),
                category = Category.NATURE,
                price = 2000f
            ),
            Photo(
                id = 218,
                title = "Tetons and Snake River",
                imageID = R.drawable.tetons_snake_river,
                artist = ArtistData(104, "Ansel", "Adams"),
                category = Category.NATURE,
                price = 2200f
            ),
            Photo(
                id = 219,
                title = "Yosemite Valley",
                imageID = R.drawable.yosemite_valley,
                artist = ArtistData(104, "Ansel", "Adams"),
                category = Category.NATURE,
                price = 1900f
            ),

            // Cindy Sherman (moderne kunstfotografi, konseptuell kunst)
            Photo(
                id = 220,
                title = "Untitled Film Still #21",
                imageID = R.drawable.untitled_film_still_21,
                artist = ArtistData(105, "Cindy", "Sherman"),
                category = Category.PORTRAIT,
                price = 2500f
            ),
            Photo(
                id = 221,
                title = "Untitled #96",
                imageID = R.drawable.untitled_96,
                artist = ArtistData(105, "Cindy", "Sherman"),
                category = Category.PORTRAIT,
                price = 2700f
            ),
            Photo(
                id = 222,
                title = "Untitled #153",
                imageID = R.drawable.untitled_153,
                artist = ArtistData(105, "Cindy", "Sherman"),
                category = Category.PORTRAIT,
                price = 2900f
            ),
            Photo(
                id = 223,
                title = "Untitled #466",
                imageID = R.drawable.untitled_466,
                artist = ArtistData(105, "Cindy", "Sherman"),
                category = Category.ABSTRACT,
                price = 3100f
            )
        )
    }
}