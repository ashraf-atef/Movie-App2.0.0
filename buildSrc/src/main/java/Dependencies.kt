object Versions {
    var versionCode = 1
    val versionName = "1.0.0"
    val koin_version = "2.1.5"
    val room_version = "2.2.5"
    val mvrx_version = "2.0.0-alpha7"
    val epoxyVersion = "4.0.0"
}

object Deps {
    val koin = "org.koin:koin-core:${Versions.koin_version}"
    val koinExtension = "org.koin:koin-core-ext:${Versions.koin_version}"
    val koinTest = "org.koin:koin-test:${Versions.koin_version}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin_version}"

    val room = "androidx.room:room-runtime:${Versions.room_version}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room_version}"
    val roomTesting = "androidx.room:room-testing:${Versions.room_version}"

    val mvrx = "com.airbnb.android:mvrx:${Versions.mvrx_version}"

    val epoxy = "com.airbnb.android:epoxy:${Versions.epoxyVersion}"
    val epoxyAnnotationProcessor = "com.airbnb.android:epoxy-processor:${Versions.epoxyVersion}"
}