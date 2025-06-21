plugins {
    id("lib-convention-plugin")
    id("prod-convention-plugin")
}

android {
    namespace = "com.vladislaviliev.air.readings"
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui.graphics)
}