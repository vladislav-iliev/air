plugins {
    id("lib-convention-plugin")
    id("test-convention-plugin")
}

android {
    namespace = "com.vladislaviliev.air.user.testing"
}

dependencies {
    implementation(project(":user"))
    implementation(libs.paging.runtime)
    testImplementation(libs.kotlinx.coroutines.test)
}