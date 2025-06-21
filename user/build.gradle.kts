plugins {
    id("lib-convention-plugin")
    id("prod-convention-plugin")
}

android {
    namespace = "com.vladislaviliev.air.user"
}

dependencies {
    testImplementation(project(":user-testing"))
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
}