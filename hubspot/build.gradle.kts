import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"

    // https://github.com/gradle-nexus/publish-plugin
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
    id("maven-publish")
    id("signing")
}

group = "org.boomevents"
version = "1.2.2"

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

java {
    withSourcesJar()
    withJavadocJar()
}

signing {
    val signingKey: String? = System.getenv("GRADLE_GPG_SIGN_KEY")
    val signingPassword: String? = System.getenv("GRADLE_GPG_SIGN_PASSWORD")

    useInMemoryPgpKeys(signingKey, signingPassword)

    sign(publishing.publications)
}


publishing {
    publications {
        create<MavenPublication>("sdk") {
            pom {
                groupId = "com.goforboom"
                artifactId = "hubspot-sdk"
                version = version

                name.set("HubSpot SDK")
                description.set("Implementation of HubSpot API for Java/Kotlin in tiny SDK")
                url.set("https://github.com/goforboom/hubspot")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("goforboom")
                        name.set("BOOM")
                        email.set("dev@goforboom.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com:goforboom/hubspot.git")
                    developerConnection.set("scm:git:ssh://github.com:goforboom/hubspot.git")
                    url.set("https://github.com/goforboom/hubspot")
                }
            }

            from(components["java"])
        }
    }
}

nexusPublishing {
    repositories {
        create("sdk") {
            nexusUrl.set(
                uri("https://s01.oss.sonatype.org/service/local/")
            )

            snapshotRepositoryUrl.set(
                uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            )

            username.set(
                System.getenv("SONATYPE_USERNAME")
            )
            password.set(
                System.getenv("SONATYPE_PASSWORD")
            )
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
