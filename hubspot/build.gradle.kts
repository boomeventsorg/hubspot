import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"

    id("maven-publish")
}

group = "com.goforboom"
version = "0.0.1"


java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    implementation("com.konghq:unirest-java:3.13.6")
    implementation("com.konghq:unirest-objectmapper-jackson:3.13.6")

    implementation(
        "org.jetbrains.kotlin:kotlin-stdlib:1.6.10"
    )

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.junit.vintage:junit-vintage-engine:5.8.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
}

publishing {
    publications {
        create<MavenPublication>("kotlin") {
            groupId = "com.goforboom"
            artifactId = "hubspot"
            version = version

            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "goforboom"
            url = uri("https://maven.pkg.github.com/goforboom/hubspot")
            credentials {

                // Credentials for GitHub package publish
                username = System.getenv("GITHUB_USERNAME")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()

    failFast = true // End after first failed test

    outputs.upToDateWhen { false }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf(
            "-Xjsr305=strict",
            "-Xallow-result-return-type"
        )
        jvmTarget = "11"
    }
}
