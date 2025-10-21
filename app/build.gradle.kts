plugins {

    id("com.android.application") version "8.6.0"
    id("org.jetbrains.kotlin.android") version "2.0.0"
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.example.whitesymphonymobil"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.whitesymphonymobil"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

}

    android {
        namespace = "com.example.whitesymphonymobil"
        compileSdk = 36

        defaultConfig {
            applicationId = "com.example.whitesymphonymobil"
            minSdk = 21
            targetSdk = 36
            versionCode = 1
            versionName = "1.0"
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.1"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        kotlinOptions {
            jvmTarget = "17"
        }

        packaging {
            resources.excludes += setOf("META-INF/AL2.0", "META-INF/LGPL2.1")
        }
    }


    dependencies {

        implementation("androidx.core:core-ktx:1.13.1")
        implementation("androidx.activity:activity-compose:1.9.2")


        implementation("androidx.compose.ui:ui:1.7.3")
        implementation("androidx.compose.ui:ui-tooling-preview:1.7.3")
        implementation("androidx.compose.material3:material3:1.3.0")
        implementation("androidx.compose.material:material-icons-extended:1.7.3")
        debugImplementation("androidx.compose.ui:ui-tooling:1.7.3")


        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.2.1")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
        androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.7.3")
        implementation("androidx.appcompat:appcompat:1.7.0")
    }
}






