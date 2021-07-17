# geolocator

**geolocator** is a web app based on [Quarkus](https://quarkus.io/) and [Geo2IP Java API](https://github.com/maxmind/GeoIP2-java).
By sending a request, you can receive geolocation information by providing a host address, e.g. an IP address.

The app requires a GeoLite2 database (a *.mmdb* file) that can be downloaded for free after signing up, 
for more information please visit: https://dev.maxmind.com/geoip/geolite2-free-geolocation-data.

The application expects an environment variable `GEOLITE2_DATABASE_PATH` that contains the absolute path to the *.mmdb* 
file. For local development, you can create a `.env` file that sets this 
env var (e.g. `GEOLITE2_DATABASE_PATH=<path>/GeoLite2-City.mmdb`). The Quarkus framework will pick this up 
automatically. 

While running the app, you can send a request to get location information of an IP address like this:
`http://localhost:8080/geolocation?host=<ip_address>`

### Hint

I created this project to play around with Quarkus. Currently, I am not planning to develop it any further.

This project has been initially provisioned by running the maven archetype:
```shell script
mvn io.quarkus:quarkus-maven-plugin:1.12.2.Final:create -DprojectGroupId=org.renedupont -DprojectArtifactId=geolocator -DclassName="org.renedupont.geolocator.GeolocatorResource" -Dpath="/hello" -DbuildTool=gradle
```

## Quarkus specifics

### Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

### Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

### Creating a native executable

You can create a native executable using: 
```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/geolocator-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.