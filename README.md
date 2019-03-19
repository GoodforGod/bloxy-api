# ⚙️ Bloxy Api

![travis](https://travis-ci.org/GoodforGod/bloxy-api.svg?branch=master)
[![codecov](https://codecov.io/gh/GoodforGod/bloxy-api/branch/master/graph/badge.svg)](https://codecov.io/gh/GoodforGod/bloxy-api)

Kotlin & Java Library for all available [Bloxy](https://bloxy.info) API endpoints.

[Bloxy.info]((https://bloxy.info)) is a source of open, reliable, verifiable and objective data about the blockchain. 

[Readme Web Page](https://goodforgod.github.io/bloxy-api/)

## Dependency :rocket:

Library depends on [Klaxon](https://github.com/cbeust/klaxon) so jcenter repository is required.

**Maven**
```xml
<repositories>
    <repository>
        <id>jcenter</id>
        <url>https://jcenter.bintray.com/</url>
    </repository>
</repositories>
 
<dependencies>
    <dependency>
        <groupId>com.github.goodforgod</groupId>
        <artifactId>bloxy-api</artifactId>
        <version>1.0.4</version>
    </dependency>
</dependencies>
```

**Gradle**
```groovy
repositories {
    mavenCentral()
    jcenter()
}
 
dependencies {
    compile 'com.github.goodforgod:bloxy-api:1.0.4'
}
```

## Content
- [Getting Started](#getting-started)
- [Java Project Dependency](#java-project-dependency)
- [Custom HttpClient](#custom-httpclient)
- [API support](#api-modules)
- [API examples](#api-examples)
    - [Smart Contract](#smart-contract-api)
    - [DEX](#dex-api)
    - [DApp](#dapp-api)
- [Version History](#version-history)

## Getting Started

All you need to start is just to get *API key* to [Bloxy](https://bloxy.info/login/new), **key will be in the email (login link)**

*Bloxy Client in Java*
```java
BloxyApi api = new BloxyApi("YourApiKey");
```

*Bloxy Client in Kotlin*
```kotlin
val api = BloxyApi("YourApiKey")
```

## Java Project Dependency

In case you are using library in *Java* project and have *ParseException*'s, mostly its Klaxon missing some kotlin dependencies.
Try to manually add *kotlin stdlib*.

**Maven**
```xml
<dependencies>
    <dependency>
        <groupId>org.jetbrains.kotlin</groupId>
        <artifactId>kotlin-stdlib-jdk8</artifactId>
        <version>1.3.10</version> // Or your version
    </dependency>
</dependencies>
```

**Gradle**
```groovy
dependencies {
    compile "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.10" // Or your version
}
```

## Custom HttpClient

In case you need to set custom timeout, custom headers or better implementation for HttpClient, 
just implement **IHttpClient** by your self or initialize it with your values.

*Java*
```java
Supplier<IHttpClient> supplier = () -> new HttpClient(10000, 40000);
BloxyApi api = new BloxyApi("YourApiKey", supplier);
```

*Kotlin*
```kotlin
val supplier = Supplier { HttpClient(10000, 40000) }
val api = BloxyApi("YourApiKey", supplier)
```

## API Modules

Library supports all API [modules](https://bloxy.info/api_methods) with all endpoints support for each module:
* *Address*
* *DApp*
* *MakerDAO*
* *Smart Contract*
* *DEX*
* *Money Flow*
* *Token*
* *Tokensale*
* *dYdX*
* *Livepeer*
* *Transaction*
* *Maltego*

## API Examples

Below there are examples for **some** API modules.

You can read about all API available [here at Bloxy](https://bloxy.info/api_methods)

#### Smart Contract Api

*Java*
```java
BloxyApi api = new BloxyApi("YourApiKey");
Balance balance = api.getContract().methods("0xd26114cd6ee289accf82350c8d8487fedb8a0c07");
```

*Kotlin*
```kotlin
val api = BloxyApi("YourApiKey")
val balance = api.contract.methods("0xd26114cd6ee289accf82350c8d8487fedb8a0c07")
```

#### DEX Api

*Java*
```java
BloxyApi api = new BloxyApi("YourApiKey");
List<DexProtocol> protocols = api.getDex().protocols();
```

*Kotlin*
```kotlin
val api = BloxyApi("YourApiKey")
val protocols = api.dex.protocols()
```

#### DApp Api

*Java*
```java
BloxyApi api = new BloxyApi("YourApiKey");
List<DAppStats> appStats = api.getDapp().statistics();
```

*Kotlin*
```kotlin
val api = BloxyApi("YourApiKey")
val appStats = api.dapp.statistics()
```

## Version History

**1.0.4** - Livepeer & dYdY & Maltego API modules support.

**1.0.3** - DApp, MakerDAO API support & Address API extension support.

**1.0.2** - Contract API support.

**1.0.1** - Gradle/Kotlin dependency publish issue, BasicProvider getOffset fix, HttpClient decoding support, javadoc improvements.

**1.0.0** - Initial project with all API functionality, with tests coverage for all cases.

## License

This project is licensed under the MIT - see the [LICENSE](LICENSE) file for details.
