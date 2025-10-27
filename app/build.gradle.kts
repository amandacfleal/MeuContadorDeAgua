plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // APLIQUE OS PLUGINS AQUI
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp") // Use KSP
}


android {
    namespace = "com.example.meucontadordeagua" // CONFIRME SEU PACOTE AQUI
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.meucontadordeagua" // CONFIRME SEU PACOTE AQUI
        minSdk = 24
        targetSdk = 34
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    // Habilita o ViewBinding, que facilita mexer na UI
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity-ktx:1.9.0")

    // ViewModel e LiveData (para MVVM)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.3")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.3")

    // Navigation (para navegar entre Fragments)
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // Room (Banco de Dados)
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1") // Para Coroutines
    ksp("androidx.room:room-compiler:2.6.1") // Use ksp aqui

    // Hilt (Injeção de Dependência)
    implementation("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-compiler:2.51.1") // Use ksp aqui
}