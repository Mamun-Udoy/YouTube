package com.example.youtube.utils

import com.example.youtube.data.model.SearchResponse
import com.example.youtube.data.model.VideoResult

object Dataset {

    val videoForSortsPage = arrayListOf(
        VideoResult(
            "@MamunUdoy",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4",
            "What care can you get for a grand?",
            "The Smoking Tire meets up with Chris and Jorge from CarsForAGrand.com to see just how far $1,000 can go when looking for a car.The Smoking Tire meets up with Chris and Jorge from CarsForAGrand.com to see just how far $1,000 can go when looking for a car.",
            "4k",
            "1.2k",
            "1k"
        ),
        VideoResult(
            "@CalvertGuru",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
            "We Are Going On Bullrun",
            "The Smoking Tire is going on the 2010 Bullrun Live Rally in a 2011 Shelby GT500, and posting a video from the road every single day! The only place to watch them is by subscribing to The Smoking Tire or watching at BlackMagicShine.com",
            "8k",
            "1.8k",
            "0.8k"
        ), VideoResult(
            "@CalvertGuru",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4",
            "We Are Going On Bullrun",
            "The Smoking Tire heads out to Adams Motorsports Park in Riverside, CA to test the most requested car of 2010, the Volkswagen GTI. Will it beat the Mazdaspeed3's standard-setting lap time? Watch and see...",
            "3k",
            "2.8k",
            "1.8k"
        ),
        VideoResult(
            "@CalvertGuru",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
            "We Are Going On Bullrun",
            "Tears of Steel was realized with crowd-funding by users",
            "3k",
            "2.8k",
            "1.8k"
        ),
    )
    val videoForPlayerPage = arrayListOf(
        SearchResponse.VideoResult(
            1,
            "Big Bunny's Tender",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            "https://serpapi.com/searches/6524f72c0574f5576c140df1/images/d51a43776fc0f3fde522a7f42294dde37c23614794605aedbce5b35aaefba8c8.jpeg",
            "1.2k",
            "2k",
            "5k",
            "2:30"
        ),
        SearchResponse.VideoResult(
            2,
            "Elephants are the largest",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            "https://serpapi.com/searches/6524f72c0574f5576c140df1/images/d51a43776fc0f3fde5b69869fc73bcbba2619a393ec30926627fb30acc32e91c.jpeg",
            "500",
            "46",
            "25",
            "2:30"
        ),
        SearchResponse.VideoResult(
            3,
            "A forest blaze in Greece",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
            "https://serpapi.com/searches/6524f72c0574f5576c140df1/images/d51a43776fc0f3fd2f4146fba4b7b83f51694d9d7695dbed9c7a9b13707f4755.jpeg",
            "7k",
            "2k",
            "839",
            "2:30"
        ),
        SearchResponse.VideoResult(
            4,
            "Big Bunny's Tender",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            "https://serpapi.com/searches/6524f72c0574f5576c140df1/images/d51a43776fc0f3fd4b26353e9d1b8e6d24b86020609796dc29b1894018f5a6eb.jpeg",
            "1.2k",
            "2k",
            "5k",
            "2:30"
        ),
        SearchResponse.VideoResult(
            5,
            "Elephants are the largest",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            "https://serpapi.com/searches/6524f72c0574f5576c140df1/images/d51a43776fc0f3fd6bfb5b4a1b59c5ff06551a06c512eed28bf6291891623d8b.jpeg",
            "500",
            "46",
            "25",
            "2:30"
        ),

    )

}