import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.ofSourceSet
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.myorg"
version = "1.0-SNAPSHOT"

val grpcKotlinVersion = findProperty("grpc_kotlin_version")
val grpcVersion = findProperty("grpc_version")
val javaProtobufLibraryVersion = findProperty("java_protobuf_library_version")
val kotestVersion = findProperty("kotest_version")
val logbackVersion = findProperty("logback_version")
val protobufVersion = findProperty("protobuf_version")
val slf4jVersion = findProperty("slf4j_version")
val springVersion = findProperty("spring_version")

plugins {
    application
    id("com.google.protobuf") version "0.8.17"
    idea
    jacoco
    kotlin("jvm") version "1.5.21"
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("ch.qos.logback:logback-core:$logbackVersion")
    implementation("org.springframework:spring-context:$springVersion")
    implementation("com.google.protobuf:protobuf-java:$javaProtobufLibraryVersion")
    implementation("com.google.protobuf:protobuf-java-util:$javaProtobufLibraryVersion")
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
    implementation("io.grpc:grpc-services:$grpcVersion")
    implementation("io.grpc:grpc-netty-shaded:$grpcVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("io.kotest:kotest-runner-junit5-jvm:4.6.0")

    testImplementation("io.grpc:grpc-testing:$grpcVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.mockk:mockk:1.12.0")
}

sourceSets {
    main {
        proto {
            srcDir("../../proto")
        }
    }
}

idea {
    module {
        generatedSourceDirs.add(file("build/generated/source/proto/main/grpc"))
        generatedSourceDirs.add(file("build/generated/source/proto/main/grpckt"))
        generatedSourceDirs.add(file("build/generated/source/proto/main/java"))
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion:jdk7@jar"
        }
    }

    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
        }
    }
}

application {
    mainClass.set("com.myorg.myservice.ApplicationKt")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events = setOf(
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
        )
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}
