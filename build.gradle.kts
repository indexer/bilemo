buildscript {
    repositories {
        maven { url = uri("https://jitpack.io") }
        mavenCentral()
        google()
    }
    dependencies {
        classpath(BuildPlugins.GradlePlugin.androidToolsGradlePlugin)
        classpath(BuildPlugins.GradlePlugin.kotlinGradlePlugin)
        classpath(BuildPlugins.GradlePlugin.navigationPlugin)
        classpath(BuildPlugins.GradlePlugin.hilt)
        classpath(BuildPlugins.GradlePlugin.jetifier)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

