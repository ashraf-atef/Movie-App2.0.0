package com.ashraf.movie.discovery

const val LOCAL_PAGE_SIZE = 20
const val LOCAL_SEARCH_YEAR_LIMIT = 5

const val REMOTE_PAGE_SIZE = 10

const val FARM_KEY = "{FARM}"
const val SERVER_KEY = "{SERVER}"
const val ID_KEY = "{ID}"
const val SECRET_KEY = "{SECRET}"
const val FLICKR_PHOTO_URL_TEMPLATE = "http://farm$FARM_KEY.static.flickr.com/$SERVER_KEY/${ID_KEY}_$SECRET_KEY.jpg"
