plugins {
    id("org.jetbrains.kotlin.android")
    id("com.android.library")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.kapt")
}

val libs = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

dependencies {
    implementation(libs.findLibrary("room.runtime").get())
    implementation(libs.findLibrary("room.ktx").get())
    implementation(libs.findLibrary("room.paging").get())
    ksp(libs.findLibrary("room.compiler").get())

    implementation(libs.findLibrary("datastore.preferences").get())
}