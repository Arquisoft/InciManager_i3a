# I3A Incident Management Module.
[![CircleCI](https://circleci.com/gh/Arquisoft/InciManager_i3a/tree/master.svg?style=svg)](https://circleci.com/gh/Arquisoft/InciManager_i3a/tree/master)
[![codecov](https://codecov.io/gh/Arquisoft/InciManager_i3a/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/InciManager_i3a)

**Welcome to our Incident Manager module**

> **Atention**
>
> This module and [Module 2](https://github.com/Arquisoft/Agents_i3a) had been added to heroku Continuos Delivery, so you can test the application at https://mana-module.herokuapp.com for web services and https://mana-module.herokuapp.com/sensor-feed for REST services.

This repository contains the incident manager module of the team I3A2. Developed during the 2018 Software Architecture course. This module is the incident management and queryes data from the Agents module, then sends it to the Dashboard. The big project consists on an incidence analysis system, composed of four modules:

- **[Module 1](https://github.com/Arquisoft/Loader_i3a)**: Information loader.
- **[Module 2](https://github.com/Arquisoft/Agents_i3a)**: Agent management and querying.
- **[Module 3](https://github.com/Arquisoft/InciManager_i3a)**: Incident Manager.
- **[Module 4](https://github.com/Arquisoft/InciDashboard_i3a)**: Incident Dashboard.

### Autors
- [Guillermo Facundo Colunga](https://github.com/thewilly)
- [Carlos García Hernández](https://github.com/CarlosGarciaHdez)
- [Victor Suárez Fernández](https://github.com/ByBordex)

## Contributing to InciManager_i3a
Contributions to the project are welcomed and encouraged! Please see the [Contribution guide](/CONTRIBUTING.md)

## Getting Started
Here you will learn how the module is build, how you can execute it and of course how to modify it.

### Module Architecture
The incident management module is composed of three micro-services, one (REST) for the sensors to send data automatically and continuosly, another (Web Interface Services) for Agents to load data manueally in te system through a web form. And finally a _core_ layer that connects with kafka and the database.
![Image of Yaktocat](.github/scheeme.png)

### System Requirements
As the project is developed in java macOS, Windows and Linux distributions are natively supported. Of course you will need the latest JDK available. Also, depending on where are you going to run the database, you will need internet connection or MongoDB installed and running on your machine.

### Java Development Kit (JDK)
A Java Development Kit (JDK) is a program development environment for writing Java applets and applications. It consists of a runtime environment that "sits on top" of the operating system layer as well as the tools and programming that developers need to compile, debug, and run applets and applications written in the Java programming language.

If you do not has the latest stable version download you can download it [here](http://www.oracle.com/technetwork/java/javase/downloads).

#### MongoDB
This project uses MongoDB as the database. You can check how to use it on [MongoDB install](https://github.com/Arquisoft/participants_i2b/wiki/MongoDB). By defatult a dummy server is up and running, it´s configured at the file `applications.properties`. Change this configuration as needed, should not interfeer with the module itself.

### Kafka and Zookeper
This module uses Apache Zookeper and Apache Kafka to process the incidences and to send data to other services.
To install and start Apache Zookeper and Apache Kafka you can download both from  [here](https://www.apache.org/dyn/closer.cgi?path=/kafka/1.0.1/kafka_2.11-1.0.1.tgz). For further information about its execution please go to [Kafka quickstart](https://kafka.apache.org/quickstart).

#### Run Zookeper
To run Apache Zookeper just run these instruction.
 ```bash
 sh bin/zookeeper-server-start.sh config/zookeeper.properties
 ```

And to run Apache Kafka just these one.
 ```bash
 sh bin/kafka-server-start.sh config/server.properties
 ```

#### Jasypt
This project uses Jasypt to encrypt the passwords. You don`t need to download it as far as its dependency its imported from maven central, but you can check it [here](http://www.jasypt.org/).

### Getting Sources for InciManager_i3a
First create a directory for all of the project sources:
```
mkdir incimanager_i3a
cd incimanager_i3a
```
**Cloning repository**
```
git clone https://github.com/Arquisoft/InciManager_i3a.git
```

### Working with InciManager_i3a
##### REST Services
The module includes a set of REST services that listen on `/senser-feed` this is for the sensors to feed data continuosly.

> To test the module you will have to download and execute the [Module 2](https://github.com/Arquisoft/Agents_i3a). More instructions are on its own GitHub repository.

Then, go to the directory where InciManager_i3a is and run it with: `mvn spring-boot:run`. Finally execute this query in your terminal application:
```bash
curl -H "Content-Type: application/json" -X POST -d '{"login":"45170000A","password":"4[[j[frVCUMJ>hU","kind":1,"message":{"incidenceName":"Fuego en coto carcedo","description":"Hay un fuego que se ha iniciado cerca del monte. Peligro para la población cercana","asignee":"","expiration":"1521475598","state":"open","tags":["fuego","peligro","población","castrillón"],"additional_information":["www.imagen1.com","www.imagen2.com","www.imagen3.com","www.imagen4.com"],"properties":{"prop1":"val1","prop2":"val2","prop3":"val3","prop4":"val4"}}}' http://localhost:8080/sensor-feed
```
After this point and incident will be submitted to Apache kafka and to the database of incidents.

##### Web Services
As described before this module includes a set of web services that can be run from the root directory with `mvn spring-boot:run`.

### Testing InciManager_i3a
To test the application just run `mvn clean` and `mvn test` from the root directory. **Please, notice that the project has 2 configurations, one for deployment and another for tests, when testing will try to reach a local Agents module to perform the authentication and when deployed will load from heroky services.**
