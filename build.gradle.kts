plugins {
    java
}

group = "me.lord0z"
version = "0.1.0"

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

compileOnly("io.papermc.paper:paper-api:26.2.build.+")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}
