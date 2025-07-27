import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
}

kotlin {

    // It needs a target compatible with wasmJs, or wasmJs itself
    wasmJs {
        browser() // Or nodejs()
        binaries.executable() // If it's an executable Wasm module
    }

    androidLibrary {
        namespace = "com.task.businesslogicshared"
        compileSdk = 35
        minSdk = 29

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    // For iOS targets, this is also where you should
    // configure native binary output. For more information, see:
    // https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#build-xcframeworks

    // A step-by-step guide on how to include this library in an XCode
    // project can be found here:
    // https://developer.android.com/kotlin/multiplatform/migrate
    val xcfName = "businessLogicShared"
    val xcfFramework = XCFramework(xcfName)


    // iOS Targets
    val iosX64 = iosX64()
    val iosArm64 = iosArm64()
    val iosSimulatorArm64 = iosSimulatorArm64()

    // watchOS Targets
    val watchosArm32 = watchosArm32()
    val watchosArm64 = watchosArm64()
    val watchosX64 = watchosX64()
    val watchosDeviceArm64 = watchosDeviceArm64()
    val watchosSimulatorArm64 = watchosSimulatorArm64()


    val appleTargets = listOf(
        iosX64,
        iosArm64,
        iosSimulatorArm64,
        watchosArm32,
        watchosArm64,
        watchosX64,
        watchosDeviceArm64,
        watchosSimulatorArm64
    )

    appleTargets.forEach {
        it.binaries.framework {
            baseName = xcfName
            xcfFramework.add(this)
        }
    }

    // Source set declarations.
    // Declaring a target automatically creates a source set with the same name. By default, the
    // Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
    // common to share sources between related targets.
    // See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
    sourceSets {
        commonMain {
            dependencies {
                // Add KMP dependencies here
                implementation(libs.kotlin.stdlib)
                implementation(libs.bundles.ktorCommon)
                implementation(libs.koin.core)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                // Add Android-specific dependencies here. Note that this source set depends on
                // commonMain by default and will correctly pull the Android artifacts of any KMP
                // dependencies declared in commonMain.
                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.lifecycle.viewmodel.ktx)
                implementation(libs.ktorAndroid)
            }
        }

        wasmJsMain  {
            dependencies {

            }
        }

        getByName("androidDeviceTest") {
            dependencies {
                implementation(libs.androidx.runner)
                implementation(libs.androidx.core)
                implementation(libs.androidx.test.junit)
            }
        }

        val iosMain by creating {
            dependsOn(commonMain.get())
            dependencies {
                /* iOS-specific dependencies */
                implementation(libs.ktoriOS)
                implementation(libs.koin.core)

            }
        }
        // SourceSet-urile specifice arhitecturilor iOS (create de target-uri) depind de iosMain.
        getByName("iosX64Main").dependsOn(iosMain)
        getByName("iosArm64Main").dependsOn(iosMain)
        getByName("iosSimulatorArm64Main").dependsOn(iosMain)

        val watchosMain by creating {
            dependsOn(commonMain.get())
            dependencies { /* watchOS-specific dependencies */ }
        }
        // Conectarea explicită a sourceSet-urilor specifice arhitecturilor watchOS la watchosMain
        getByName("watchosArm32Main").dependsOn(watchosMain)
        getByName("watchosArm64Main").dependsOn(watchosMain)
        getByName("watchosX64Main").dependsOn(watchosMain)
        getByName("watchosDeviceArm64Main").dependsOn(watchosMain)
        getByName("watchosSimulatorArm64Main").dependsOn(watchosMain)

    }

}
