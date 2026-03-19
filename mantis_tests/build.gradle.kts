plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.40.0")
    testImplementation("com.squareup.okhttp3:okhttp:5.3.0")
    testImplementation("com.squareup.okhttp3:okhttp-urlconnection:5.3.0")
    testImplementation("org.apache.james:james-server-fetchmail:3.8.2")
    testImplementation("org.apache.commons:commons-exec:1.6.0")
}

tasks.test {
    useJUnitPlatform()
}