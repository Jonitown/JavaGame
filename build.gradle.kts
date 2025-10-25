plugins {
    id("java")
}

group = "de.jonitown"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.jmonkeyengine:jme3-core:3.8.0-stable")
    implementation("org.jmonkeyengine:jme3-desktop:3.8.0-stable")
    implementation("org.jmonkeyengine:jme3-lwjgl3:3.8.0-stable")
    implementation("org.jmonkeyengine:jme3-plugins:3.8.0-stable")
   // implementation("org.jmonkeyengine:jme3-bullet:3.8.0-stable")
// optional
}

tasks.test {
    useJUnitPlatform()
}