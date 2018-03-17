# InciManager_i3a
[![CircleCI](https://circleci.com/gh/Arquisoft/InciManager_i3a/tree/master.svg?style=svg)](https://circleci.com/gh/Arquisoft/InciManager_i3a/tree/master)
[![codecov](https://codecov.io/gh/Arquisoft/InciManager_i3a/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/InciManager_i3a)

**Welcome to our Incident Manager module**

This repository contains the incident manager module of the team I3A2. Developed during the 2018 Software Architecture course. This module is the incident management and queryes data from the Agents module, then sends it to the Dashboard. The big project consists on an incidence analysis system, composed of four modules:

- **[Module 1](https://github.com/Arquisoft/Loader_i3a)**: Information loader.
- **[Module 2](https://github.com/Arquisoft/Agents_i3a)**: Agent management and querying.
- **[Module 3](https://github.com/Arquisoft/InciManager_i3a)**: Incident Manager.
- **[Module 4](https://github.com/Arquisoft/InciDashboard_i3a)**: Incident Dashboard.

### Autors
- [Guillermo Facundo Colunga](https://github.com/thewilly)
- [Carlos García Hernández](https://github.com/CarlosGarciaHdez)
- [Victor Suárez Fernández](https://github.com/ByBordex)

### Module Architecture
The incident management module is composed of three micro-services, one (REST) for the sensors to send data automatically and continuosly, another (Web Interface Services) for Agents to load data manueally in te system through a web form. And finally a _core_ layer that connects with kafka and the database.
![Image of Yaktocat](.github/scheeme.png)

### Using InciManager_i3a
#### REST
To test the module you will have to download the [Module 2](https://github.com/Arquisoft/Agents_i3a) and run it with:
```bash
mvn spring-boot:run
```
Then, go to the directory where InciManager_i3a i and run it with the same command. Finally execute this query in your terminal application:
```bash
curl -H "Content-Type: application/json" -X POST -d '{"login":"45170000A","password":"4[[j[frVCUMJ>hU","kind":1,"message":{"name":"Fuego en coto carcedo","description":"Hay un fuego que se ha iniciado cerca del monte. Peligro para la población cercana","tags":["la","le","li","lo"],"multimedia":["www.imagen1.com","www.imagen2.com","www.imagen3.com","www.imagen4.com"],"property-val":{"prop1":"val1","prop2":"val2","prop3":"val3","prop4":"val4"},"comments":["Please help!"]}}' http://localhost:8090/sensor-feed
```
After this point and incident will be submitted to Apache kafka and to the database of incidents.
