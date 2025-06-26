import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    kotlin("plugin.serialization") version "1.9.23"
}

kotlin {
    jvm("desktop"){
        compilations["main"].defaultSourceSet {
            resources.srcDir("src/desktopMain/composeResources")
        }
    }
    jvmToolchain(21)
    
    sourceSets {
        val desktopMain by getting
        
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
            implementation("com.google.zxing:core:3.5.2")
            api(libs.koin.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
            implementation("org.jetbrains.skiko:skiko-awt-runtime-windows-x64:0.9.2") // or mac/linux depending on platform
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.9.0")
            implementation(libs.koin.compose)
            implementation(libs.koin.composeVM)
        }
    }
}


compose.desktop {
    application {
        mainClass = "com.slemenceu.insuflow.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Exe)
            packageName = "com.slemenceu.insuflow"
            packageVersion = "1.0.0"
            includeAllModules = true

            windows {
//                iconFile.set(project.file("src/desktopMain/composeResources/drawable/app_icon.ico"))
                shortcut = true
                menuGroup = "InsuFlow"
                console = true
                jvmArgs += listOf("--add-modules", "jdk.charsets")

            }
        }
    }
}
tasks.withType<JavaExec>().matching { it.name == "desktopRunHot" }.configureEach {
    mainClass.set("com.slemenceu.insuflow.MainKt")
}
