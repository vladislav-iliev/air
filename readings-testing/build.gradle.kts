plugins {
    id("lib-convention-plugin")
    id("test-convention-plugin")
}

android {
    namespace = "com.vladislaviliev.air.readings.testing"
}

dependencies {
    implementation(project(":readings"))
}