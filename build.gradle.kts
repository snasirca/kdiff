plugins {
    kotlin("jvm") version "1.1.51"
}

dependencies {
    compile(kotlin("stdlib"))
    testCompile("com.willowtreeapps.assertk:assertk:0.9")
}

repositories {
    mavenCentral()
}
