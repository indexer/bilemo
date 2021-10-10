plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    kotlin("android.extensions")
    id("kotlin-android")
    id("androidx.navigation.safeargs")

}

android {
    compileSdk = BuildPlugins.compileSDK
    defaultConfig {
        applicationId = "com.indexer.bilemo"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

}



dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(BuildPlugins.Kotlin.kotlinStdLib)
    implementation(BuildPlugins.AndroidX.AppCombatLibraries.appCompat)
    implementation(BuildPlugins.AndroidX.AppCombatLibraries.ktxCore)
    implementation(BuildPlugins.AndroidX.AppCombatLibraries.constraintLayout)
    implementation(BuildPlugins.AndroidX.MaterialDesign.materialComponent)
    implementation(BuildPlugins.AndroidX.MaterialDesign.materialDesignIcon)
    implementation(BuildPlugins.AndroidX.MaterialDesign.materialDesignIconExtended)
    implementation(BuildPlugins.Kotlin.kotlinCoroutineAndroid)
    implementation(BuildPlugins.Kotlin.kotlinCoroutineCore)
    implementation(BuildPlugins.AndroidX.AndroidNavigation.navigationFragment)
    implementation(BuildPlugins.AndroidX.AndroidNavigation.navigationUiKotlin)
    implementation(BuildPlugins.AndroidX.AndroidNavigation.navigationFragmentKotlin)
    implementation(BuildPlugins.AndroidX.LifeCycle.liveDataLifeCycleKotlin)
    implementation(BuildPlugins.AndroidX.LifeCycle.liveDatalifeCycle)
    implementation(BuildPlugins.AndroidX.DataStore.dataStore)
    implementation(BuildPlugins.AndroidX.DataStore.dataStoreKtx)
    implementation(BuildPlugins.AndroidX.AppCombatLibraries.coroutines)
    implementation(BuildPlugins.AndroidX.DataStore.dataStoreAndroid)
    implementation(BuildPlugins.AndroidX.Hilt.hiltAndroid)
    implementation(BuildPlugins.AndroidX.GoogleMap.googleMap)
    implementation(BuildPlugins.AndroidX.GoogleMap.fusedLocation)
    kapt(BuildPlugins.AndroidX.Hilt.hiltKapt)
    annotationProcessor(BuildPlugins.AndroidX.Hilt.annotationProcesserHilt)
    kapt(BuildPlugins.Room.roomKapt)
    implementation(BuildPlugins.Room.roomKtx)
    implementation(BuildPlugins.Room.roomRunTime)
    implementation(BuildPlugins.Room.roomRxjava)
    implementation(BuildPlugins.AndroidX.AppCombatLibraries.androidSupportAnnotation)
    implementation(BuildPlugins.Network.retrofit)
    implementation(BuildPlugins.Network.retrofitConverter)
    implementation(BuildPlugins.Network.okhtttpLogging)
    implementation(BuildPlugins.AndroidX.PermissionLib.corePermission)
    implementation(BuildPlugins.AndroidX.PermissionLib.permissionLib)

}
