plugins {
    id("java")
    id("war")
}

group = "alfarius.goida"
version = ""

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")





    // https://mvnrepository.com/artifact/javax/javaee-api
    compileOnly("javax:javaee-api:8.0")
//    implementation("org.icefaces:icefaces:4.3.0")
//    implementation("org.icefaces:icefaces-ace:4.3.0")
    implementation("org.primefaces:primefaces:13.0.4")

    // https://mvnrepository.com/artifact/org.icefaces/icefaces-ace
//    implementation("org.icefaces:icefaces-ace:4.3.0")

}


tasks.test {
    useJUnitPlatform()
}