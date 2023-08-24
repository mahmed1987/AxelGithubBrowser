package com.axel.githubbrowser.models.view

data class ViewSearchResults(val totalCount:Int = 0, val users:List<ViewSearchResult> = emptyList())
data class ViewSearchResult(val name: String, val imageUrl: String, val type: String)