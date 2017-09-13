[![Build Status](https://travis-ci.org/bilalwahla/product-selection.svg?branch=master)](https://travis-ci.org/bilalwahla/product-selection) [![codecov](https://codecov.io/gh/bilalwahla/product-selection/branch/master/graph/badge.svg)](https://codecov.io/gh/bilalwahla/product-selection)

# Product Selection

I have chosen to implement this project using microservices architecture. While the assignment itself is quite simple, the project does have some boilerplate code to depict my understanding of the architecture and the patterns that are important to be applied when implementing such architecture.

Basic skeleton of the project includes:

 * **eurekasvr**: a service discovery server using [Spring Cloud/Netflix Eureka](http://cloud.spring.io/spring-cloud-netflix/)
 * **confsvr**: a centralised configuration server using [Spring Cloud Config](http://cloud.spring.io/spring-cloud-config/) that is using GitHub as a distributed, version controlled and auditable place for all configuration.
 * **zuulsvr**: an API gateway server using [Spring Cloud/Netflix Zuul](http://cloud.spring.io/spring-cloud-netflix/). This offers a highly scalable single entry point to the system allowing for the implementation of cross-cutting concerns in one place e.g. correlating logs.

For more detailed understanding of above technologies and the microservices architectural patterns they try to address, please read my article [Microservices - More Than Writing Code](https://dzone.com/articles/microservices-more-than-writing-code).

Rest of the services in the project are the assignment requirements:

 * **customer-location-service** (also demonstrates log trace using [Spring Cloud Sleuth](http://cloud.spring.io/spring-cloud-sleuth/))
 * **catalogue-service** (also demonstrates client resiliency patterns using [Spring Cloud/Netflix Hystrix](http://cloud.spring.io/spring-cloud-netflix/))

## Software needed to build and run
1. [Docker](http://docker.com).
2. [Maven](https://maven.apache.org)

### Building the Docker Images
To build the project as a docker images, open a command-line window change to the root directory where you have downloaded the project source code.

Run the following _maven_ command. This command will execute the [Spotify docker plugin](https://github.com/spotify/docker-maven-plugin) defined in the _pom.xml_ file.  

```bash
mvn clean package docker:build
```

If everything builds successfully you should see a message indicating that the build was successful.

### Running the service

I have created a _docker-compose_ file that we can use to start the actual images.  To start the docker images we created above, issue the following _docker-compose_ command from the root of the project:

```bash
docker-compose -f docker/common/docker-compose.yml up
```

If everything starts up correctly you should see a bunch of Spring Boot information fly by on standard out. At this point the service will be up and running.

## Postman collection

There is no web application that consumes the **customer-location-service** and **catalogue-service** (via Zuul Server - the API Gateway Service) but please find a simple Postman collection under /Postman in the root of the project that you can import into your [Postman](https://www.getpostman.com) and give the services a go.

