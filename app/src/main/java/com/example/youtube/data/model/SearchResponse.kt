package com.example.youtube.data.model


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("search_information")
    val searchInformation: SearchInformation?,
    @SerializedName("search_metadata")
    val searchMetadata: SearchMetadata?,
    @SerializedName("search_parameters")
    val searchParameters: SearchParameters?,
    @SerializedName("video_results")
    val videoResults: List<VideoResult?>?
) {
    data class SearchInformation(
        @SerializedName("menu_items")
        val menuItems: List<MenuItem?>?,
        @SerializedName("query_displayed")
        val queryDisplayed: String?
    ) {
        data class MenuItem(
            @SerializedName("link")
            val link: String?,
            @SerializedName("position")
            val position: Int?,
            @SerializedName("serpapi_link")
            val serpapiLink: String?,
            @SerializedName("title")
            val title: String?
        )
    }

    data class SearchMetadata(
        @SerializedName("created_at")
        val createdAt: String?,
        @SerializedName("google_videos_url")
        val googleVideosUrl: String?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("json_endpoint")
        val jsonEndpoint: String?,
        @SerializedName("processed_at")
        val processedAt: String?,
        @SerializedName("raw_html_file")
        val rawHtmlFile: String?,
        @SerializedName("status")
        val status: String?,
        @SerializedName("total_time_taken")
        val totalTimeTaken: Double?
    )

    data class SearchParameters(
        @SerializedName("device")
        val device: String?,
        @SerializedName("engine")
        val engine: String?,
        @SerializedName("gl")
        val gl: String?,
        @SerializedName("google_domain")
        val googleDomain: String?,
        @SerializedName("hl")
        val hl: String?,
        @SerializedName("q")
        val q: String?
    )

    data class VideoResult(
        @SerializedName("author")
        val author: String?,
        @SerializedName("date")
        val date: String?,
        @SerializedName("duration")
        val duration: String?,
        @SerializedName("link")
        val link: String?,
        @SerializedName("position")
        val position: Int?,
        @SerializedName("source")
        val source: String?,
        @SerializedName("thumbnail")
        val thumbnail: String?,
        @SerializedName("title")
        val title: String?
    )
}