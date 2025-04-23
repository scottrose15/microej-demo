/*
 *  Kotlin
 *
 *  Copyright 2023-2025 MicroEJ Corp. All rights reserved.
 *  Use of this source code is governed by a BSD-style license that can be found with this software.
 *
 */

plugins {
    id("com.microej.gradle.application") version "1.0.0"
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
group = "com.microej.training.ui"
version = "1.3.0"

microej {
    applicationEntryPoint = "com.microej.exercise.ui.Main"
}

dependencies {
    // Foundation Libraries
    implementation("ej.api:edc:1.3.5")
    implementation("ej.api:bon:1.4.0")
    implementation("ej.api:microui:3.2.0")
    implementation("ej.api:drawing:1.0.5")
    implementation("ej.api:kf:1.7.0")

    // Addon Libraries
    implementation("ej.library.ui:mwt:3.5.0")
    implementation("ej.library.ui:widget:5.0.0")
    implementation("ej.library.util:observable:2.0.0")

    // VEE Port
    microejVee("com.microej.veeport.generic:wearable:1.5.0")
}