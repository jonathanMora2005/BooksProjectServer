/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("buildlogic.java-library-conventions")
}
dependencies {
    implementation(project(":domain"))
    implementation("mysql:mysql-connector-java:8.0.33")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testImplementation(project(":utilities"))
}