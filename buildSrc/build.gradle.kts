plugins {
    `kotlin-dsl`
}

fun plugin(plugin: Provider<PluginDependency>) =
    plugin.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }

dependencies {
    implementation(plugin(libs.plugins.android.library))
    implementation(plugin(libs.plugins.kotlin.android))
    implementation(plugin(libs.plugins.ksp))
    implementation(plugin(libs.plugins.jetbrains.kotlin.jvm))
}