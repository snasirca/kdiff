import org.junit.platform.gradle.plugin.FiltersExtension
import org.junit.platform.gradle.plugin.JUnitPlatformExtension

plugins {
    kotlin("jvm") version "1.1.51"
    id("com.jfrog.bintray") version "1.8.0"
}

group = "ca.snasir"
version = "0.1.0"

buildscript {
    dependencies {
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.0")
    }
}

apply {
    plugin("org.junit.platform.gradle.plugin")
    from("groovy.gradle") // temporary until https://github.com/bintray/gradle-bintray-plugin/pull/194 is merged
}

configure<JUnitPlatformExtension> {
    filters {
        engines {
            include("spek")
        }
    }
}

repositories {
    jcenter()
}

dependencies {
    compile(kotlin("stdlib"))
    testCompile(kotlin("reflect"))

    testCompile("org.junit.platform:junit-platform-runner:1.0.1")
    testCompile("org.jetbrains.spek:spek-api:1.1.5")
    testRuntime("org.jetbrains.spek:spek-junit-platform-engine:1.1.5")

    testCompile("com.winterbe:expekt:0.2.0")
}

// temporary until https://github.com/bintray/gradle-bintray-plugin/pull/194 is merged
val bintrayPackage: groovy.lang.Closure<Any> by extra
bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    pkg(bintrayPackage)
}

fun JUnitPlatformExtension.filters(setup: FiltersExtension.() -> Unit) {
    when (this) {
        is ExtensionAware -> extensions.getByType(FiltersExtension::class.java).setup()
        else -> throw Exception("${this::class} must be an instance of ExtensionAware")
    }
}
