plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.maderajan.cosmosnow.database"
    compileSdk = 34
//
//    defaultConfig {
//        // The schemas directory contains a schema file for each version of the Room database.
//        // This is required to enable Room auto migrations.
//        // See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
//        ksp {
//            arg("room.schemaLocation", "$projectDir/schemas")
//        }
//
//
//    }

    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.incremental"] = "true"
            }
        }

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    sourceSets {
        getByName("androidTest").assets.srcDirs("$projectDir/schemas")
    }

//    sourceSets.getByName("test") {
//        assets.srcDir(files("$projectDir/schemas"))
//    }

    defaultConfig {
        minSdk = 24

    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.androidx.hilt.compiler)

    androidTestImplementation(libs.androidx.room.testing)
    androidTestImplementation("androidx.test:runner:1.6.2")
    androidTestImplementation(libs.turbine)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
}