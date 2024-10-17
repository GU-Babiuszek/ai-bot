plugins {
    id("java")
    id("io.spring.dependency-management") version "1.1.6"
}
val springCloudVersion by extra("2023.0.3")

group = "com.grapeup.aibot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter:3.3.4")
    implementation("org.springframework.boot:spring-boot-starter-websocket:3.3.4")
    implementation("org.springframework.boot:spring-boot-starter-logging:3.3.4")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.3.4")
//    implementation("org.springframework.ai:spring-ai-openai-spring-boot-starter:?")

    implementation("org.apache.httpcomponents.client5:httpclient5:5.4")
    implementation("org.webjars:sockjs-client:1.0.2")
    implementation("org.webjars:stomp-websocket:2.3.3")
    implementation("org.postgresql:postgresql:42.7.4")
    implementation("org.springframework.cloud:spring-cloud-function-context")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
}

tasks.test {
    useJUnitPlatform()
}