# COMPROT Web-Service

[![Build Status](https://travis-ci.org/medien-dresden/comprot-webservice.svg?branch=develop)](https://travis-ci.org/medien-dresden/comprot-webservice)
[![Stories in Ready](https://badge.waffle.io/medien-dresden/comprot-webservice.png?label=ready&title=Ready)](http://waffle.io/medien-dresden/comprot-webservice)

## Installation

* create your personal ```webservice/src/main/resources/data.properties```

    ```
    comprot.database.driver=com.mysql.jdbc.Driver
    comprot.database.url=jdbc:mysql://localhost:3306/comprot-source
    comprot.database.username=ralf
    comprot.database.password=flar
    
    app.database.driver=com.mysql.jdbc.Driver
    app.database.url=jdbc:mysql://localhost:3306/comprot-app
    app.database.username=ralf
    app.database.password=flar
    ```
    
* set up app-database creation either
  * manually by executing a [SQL-script](https://gist.github.com/phdd/fdc1e46f302cef7cdb32) or
  * automatically by setting _database.schema_ to _update_ (```webservice/src/main/resources/app.properties```)

* run in different terminals

    ```
    ./gradlew solr:run
    ./gradlew webservice:run
    ```
    
    
* indexing is done by three different jobs, access those through the REST API the following way

    ```
    curl -v -u admin:admin123 -H "Content-Type: application/vnd.comprot-v1.0+json" -d '{"name":"targetIndexingJob"}' http://localhost:8080/api/jobs/
    curl -v -u admin:admin123 -H "Content-Type: application/vnd.comprot-v1.0+json" -d '{"name":"compoundIndexingJob"}' http://localhost:8080/api/jobs/
    curl -v -u admin:admin123 -H "Content-Type: application/vnd.comprot-v1.0+json" -d '{"name":"bindingIndexingJob"}' http://localhost:8080/api/jobs/
    ```
    
## Platform & tools

* for [Hot code swapping](http://en.wikipedia.org/wiki/Hot_swapping) with IntelliJ IDEA use
    * [DCEVM plugin](http://blog.jetbrains.com/idea/2013/07/get-true-hot-swap-in-java-with-dcevm-and-intellij-idea/)
    * [Spring Loaded](https://github.com/spring-projects/spring-loaded)
    * in debug, press Ctrl+Shift+F9 (reload changed classes)

## License

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.
