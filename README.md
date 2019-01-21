# ⚙️ Bloxy Api

![travis](https://travis-ci.org/GoodforGod/bloxy-api.svg?branch=master)
[![codecov](https://codecov.io/gh/GoodforGod/bloxy-api/branch/master/graph/badge.svg)](https://codecov.io/gh/GoodforGod/bloxy-api)

Kotlin & Java Library for all available [Bloxy](https://bloxy.info) API endpoints.

[Bloxy.info]((https://bloxy.info)) is as a source of open, reliable, verifiable and objective data about the blockchain. 

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
        <version>1.0.3</version>
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
    compile 'com.github.goodforgod:bloxy-api:1.0.3'
}
```

## Content
- [Getting Started](#getting-started)
- [Java Project Dependency](#java-project-dependency)
- [Custom HttpClient](#custom-httpclient)
- [API examples](#api-examples)
    - [Token](#token-api)
    - [Address](#address-api)
    - [Smart Contract](#smart-contract-api)
    - [DEX](#dex-api)
    - [DApp](#dapp-api)
    - [Token Sale](#token-sale-api)
    - [Money Flow](#money-flow-api)
    - [MakerDAO](#makerdao-api)
    - [Transaction](#transaction-api)
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
Supplier<IHttpClient> clientSupplier = () -> new HttpClient(10000, 40000);
BloxyApi api = new BloxyApi("YourApiKey", supplier);
```

*Kotlin*
```kotlin
val api = BloxyApi("YourApiKey", Supplier { HttpClient(6000, 10000) })
```

## API Examples

Below there are examples for each API module.

You can read about all API available [here at Bloxy](https://bloxy.info/api_methods)

### Token Api
**Get token holders**

*Java*
```java
BloxyApi api = new BloxyApi("YourApiKey");
List<Holder> holders = api.getToken().holders("0xB97048628DB6B661D4C2aA833e95Dbe1A905B280");
```

*Kotlin*
```kotlin
val api = BloxyApi("YourApiKey")
val holders = api.token.holders("0xB97048628DB6B661D4C2aA833e95Dbe1A905B280")
```

### Address Api
**Get address balance**

*Java*
```java
BloxyApi api = new BloxyApi("YourApiKey");
Balance balance = api.getAddress().balance("0xB97048628DB6B661D4C2aA833e95Dbe1A905B280");
```

*Kotlin*
```kotlin
val api = BloxyApi("YourApiKey")
val balance = api.address.balance("0x9eAb08daA285183F9A04269747D4125F08e634B0")
```

### Smart Contract Api
**List of smart contract methods and call statistics**

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

### DEX Api
**Get DEX protocols**

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

### DApp Api
**Lists smart contracts with users and volume statistics**

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

### Token Sale Api
**Lists recent token sale aggregated statistics**

*Java*
```java
BloxyApi api = new BloxyApi("YourApiKey");
List<Sale> sales = api.getTokenSale().sales();
```

*Kotlin*
```kotlin
val api = BloxyApi("YourApiKey")
val sales = api.tokenSale.sales()
```

### Money Flow Api
**List of all transfers to/from the given address**

*Java*
```java
BloxyApi api = new BloxyApi("YourApiKey");
List<String> addresses = Collections.singletonList("0xC0ea08A2d404d3172d2AdD29A45be56dA40e2949");
List<AddrTransfer> transfers = api.getMoneyFlow().transfersAll(addresses);
```

*Kotlin*
```kotlin
val api = BloxyApi("YourApiKey")
val addresses = listOf("0xC0ea08A2d404d3172d2AdD29A45be56dA40e2949")
val result = api.moneyFlow.transfersAll(addresses)
```

### MakerDAO Api
**Query poke transaction and values**

Poke **DOES NOT** provide *value* field for JSON object, due to Klaxon errors, this will be fixed in upcoming releases.

*Java*
```java
BloxyApi api = new BloxyApi("YourApiKey");
List<Poke> pokes = api.getMakerDao().poke("0x729d19f657bd0614b4985cf1d82531c67569197b");
```

*Kotlin*
```kotlin
val api = BloxyApi("YourApiKey")
val pokes = api.makerDao.poke("0x729d19f657bd0614b4985cf1d82531c67569197b")
```

### Transaction Api
**List of all transfers in the given transaction**

*Java*
```java
BloxyApi api = new BloxyApi("YourApiKey");
List<String> txhashs = Arrays.asList("0x52a9a7dfe6f002b7d7deb5555e356e319839fc4dc280a68de55778524a41f986");
List<TxTransfer> txTransfers = api.getTx().transfers(txhashs);
```

*Kotlin*
```kotlin
val api = BloxyApi("YourApiKey")
val txhashs = listOf("0x52a9a7dfe6f002b7d7deb5555e356e319839fc4dc280a68de55778524a41f986")
val transfers = api.tx.transfers(list)
```

## Version History

**1.0.3** - DApp, MakerDAO API support & Address API extension support.

**1.0.2** - Contract API support.

**1.0.1** - Gradle/Kotlin dependency publish issue, BasicProvider getOffset fix, HttpClient decoding support, javadoc improvements.

**1.0.0** - Initial project with all API functionality, with tests coverage for all cases.

## License

This project is licensed under the MIT - see the [LICENSE](LICENSE) file for details.
