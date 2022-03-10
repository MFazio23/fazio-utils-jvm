plugins {
    `maven-publish`
    `java-library`

    id("org.jetbrains.kotlin.jvm") version "1.5.31"

    // Code coverage from Kover
    id("org.jetbrains.kotlinx.kover") version "0.4.2"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

group = "dev.mfazio.utils"
version = "0.2.0"

val artifactName = project.name
val artifactGroup = project.group.toString()
val artifactVersion = project.version.toString()
val publicationName = project.name

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("com.github.stefanbirkner:system-lambda:1.2.1")
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
            )
        )
    }
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use Kotlin Test test framework
            useKotlinTest()
        }
    }
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
}

publishing {
    publications {
        create<MavenPublication>(publicationName) {
            groupId = artifactGroup
            artifactId = artifactName
            version = artifactVersion
            from(components["kotlin"])

            artifact(sourcesJar)
        }
    }
}