plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
}

group = "cn.jackuxl"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://www.jitpack.io") {
        name = "jitpack"
    }
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    // Kotlin Base
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    with("1.6.3"){
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$this")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$this")
    }
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

    // Fuel
    with("com.github.kittinunf.fuel" to "2.3.1"){
        listOf("fuel","fuel-kotlinx-serialization","fuel-coroutines").forEach {
            implementation(group = first, name = it, version = second)
        }
    }

    implementation("com.alibaba:fastjson:2.0.7")
    implementation("cn.hutool:hutool-all:5.8.4")

}