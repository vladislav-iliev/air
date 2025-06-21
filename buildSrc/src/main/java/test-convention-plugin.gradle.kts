plugins {
    id("org.jetbrains.kotlin.android")
    id("com.android.library")
}

val libs = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

dependencies {
    androidTestImplementation(libs.findLibrary("androidx.junit").get())
}