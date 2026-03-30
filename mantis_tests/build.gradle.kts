plugins {
    id("java")
    id("org.openapi.generator") version "6.6.0"
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
    testImplementation("org.eclipse.angus:angus-mail:2.1.0-M1")

    implementation("com.google.code.findbugs:jsr305:3.0.2")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.google.code.gson:gson:2.9.1")
    implementation("io.gsonfire:gson-fire:1.9.0")
    implementation("jakarta.ws.rs:jakarta.ws.rs-api:2.1.6")
    implementation("org.openapitools:jackson-databind-nullable:0.2.8")
    implementation(group = "org.apache.commons", name = "commons-lang3", version = "3.18.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.3")
    testImplementation("org.mockito:mockito-core:3.12.4")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.3")
    implementation("biz.futureware.mantis:mantis-axis-soap-client:1.2.19")
}

tasks.test {
    useJUnitPlatform()
}

openApiGenerate {
    generatorName.set("java")
    inputSpec.set("$rootDir/swagger.json")
    outputDir.set("$buildDir/generated")
    library.set("okhttp-gson")
    apiPackage.set("org.mantis.api")
    modelPackage.set("org.mantis.model")
}