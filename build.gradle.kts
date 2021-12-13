plugins {
    kotlin("multiplatform") version "1.5.31"
}

group = "com.github.ehennes775"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    val nativeTarget = System.getProperty("os.name").let {
        when {
            it == "Mac OS X" -> macosX64("native")
            it == "Linux" -> linuxX64("native")
            it.startsWith("Windows") -> mingwX64("native")
            else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
        }
    }

    nativeTarget.apply {
        compilations["main"].cinterops {
            val sdl2 by creating
        }
        binaries {
            executable {
                entryPoint = "main"
                if (System.getProperty("os.name") == "Mac OS X") {
                    linkerOpts("-F/Library/Frameworks")
                }
            }
        }
    }

    sourceSets {
        val nativeMain by getting
        val nativeTest by getting
    }
}
