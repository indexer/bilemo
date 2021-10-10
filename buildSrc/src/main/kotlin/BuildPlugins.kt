object BuildPlugins {

    private const val kotlinVersion = "1.5.30"
    private const val kotlinCoroutineVersion = "1.5.2"
    private const val navigationVersion = "2.3.5"
    private const val hilt_version = "2.38.1"

    const val compileSDK = 31

    object GradlePlugin {
        private const val gradleVersion = "7.0.2"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val androidToolsGradlePlugin = "com.android.tools.build:gradle:$gradleVersion"
        const val navigationPlugin =
            "androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion"
        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:2.38.1"
        const val jetifier = "com.android.tools.build.jetifier:jetifier-processor:1.0.0-beta10"
    }

    object Kotlin {
        const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
        const val kotlinCoroutineCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutineVersion"
        const val kotlinCoroutineAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinCoroutineVersion"
    }

    object Room {
        //database
        const val roomRunTime = "androidx.room:room-runtime:2.4.0-alpha05"
        const val roomKtx = "androidx.room:room-ktx:2.4.0-alpha05"
        const val roomKapt = "androidx.room:room-compiler:2.4.0-alpha05"
        const val roomRxjava = "androidx.room:room-rxjava2:2.3.0"
    }

    object Network {
        const val retrofitConverter = "com.squareup.retrofit2:converter-gson:2.9.0"
        const val okhtttpLogging = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    }

    object AndroidX {

        object AppCombatLibraries {
            const val androidSupportAnnotation = "com.android.support:support-annotations:28.0.0"
            const val appCompat = "androidx.appcompat:appcompat:1.4.0-alpha03"
            const val ktxCore = "androidx.core:core-ktx:1.7.0-alpha02"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"
            const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0"


        }

        object GoogleMap {
            const val googleMap = "com.google.android.gms:play-services-maps:17.0.1"
            const val fusedLocation = "com.google.android.gms:play-services-location:18.0.0"
        }

        object LifeCycle {
            const val liveDatalifeCycle = "androidx.lifecycle:lifecycle-livedata-ktx:2.3.1"
            const val liveDataLifeCycleKotlin = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
        }

        object AndroidNavigation {
            const val navigationFragment = "androidx.navigation:navigation-fragment:2.3.5"
            const val navigationFragmentKotlin =
                "androidx.navigation:navigation-fragment-ktx:2.3.5"
            const val navigationUiKotlin = "androidx.navigation:navigation-ui-ktx:2.3.5"
        }


        object DataStore {
            const val dataStoreAndroid = "androidx.datastore:datastore-preferences:1.0.0"
            const val dataStore = "androidx.datastore:datastore-preferences-core:1.0.0"
            const val dataStoreKtx = "androidx.preference:preference-ktx:1.1.1"
        }

        object MaterialDesign {
            const val materialComponent = "com.google.android.material:material:1.5.0-alpha03"
            const val materialDesignIcon = "androidx.compose.material:material-icons-core:1.0.1"
            const val materialDesignIconExtended =
                "androidx.compose.material:material-icons-extended:1.0.1"
        }

        object PermissionLib {
            const val corePermission =
                "com.github.fondesa:kpermissions:3.2.1"
            const val permissionLib = "com.github.fondesa:kpermissions-coroutines:3.2.1"
        }

        object Hilt {
            const val annotationProcesserHilt = "com.google.dagger:hilt-compiler:$hilt_version"
            const val hiltAndroid = "com.google.dagger:hilt-android:$hilt_version"
            const val hiltKapt = "com.google.dagger:hilt-android-compiler:$hilt_version"
        }

    }

}