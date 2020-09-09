object Versions {
    var versionCode = 1
    val versionName = "1.0.0"
    val koin_version = "2.1.5"
    val room_version = "2.2.5"
    val mvrx_version = "2.0.0-alpha2"
    val epoxyVersion = "3.10.0"
    val retrofit_version = "2.5.0"
    val okhttp3_version = "3.14.2"
    val rxAndroid = "2.1.1"
    val rxJava = "2.2.10"
    val glide_version = "4.11.0"
}

object Deps {
    val koin = "org.koin:koin-core:${Versions.koin_version}"
    val koinExtension = "org.koin:koin-core-ext:${Versions.koin_version}"
    val koinTest = "org.koin:koin-test:${Versions.koin_version}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin_version}"

    val room = "androidx.room:room-runtime:${Versions.room_version}"
    val roomRX = "androidx.room:room-rxjava2:${Versions.room_version}"
    val roomKTS = "androidx.room:room-ktx:${Versions.room_version}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room_version}"
    val roomTesting = "androidx.room:room-testing:${Versions.room_version}"

    val mvrx = "com.airbnb.android:mvrx:${Versions.mvrx_version}"

    val epoxy = "com.airbnb.android:epoxy:${Versions.epoxyVersion}"
    val epoxyAnnotationProcessor = "com.airbnb.android:epoxy-processor:${Versions.epoxyVersion}"

    //Retrofit
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
    val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit_version}"
    val okHttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3_version}"
    val okhttp3LogginInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3_version}"

    //RxJava
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"

    //Glide
    val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"
    val glideAnnotationProcessor = "com.github.bumptech.glide:compiler:${Versions.glide_version}"
}