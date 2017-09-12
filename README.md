[![Build Status](https://travis-ci.org/bilalwahla/product-selection.svg?branch=master)](https://travis-ci.org/bilalwahla/product-selection)

# Product Selection

I have chosen to implement this project using microservices architecture. While the assignment itself is quite simple, the project does have some boilerplate code to depict my understanding of the architecture and the patterns that are important to be applied when implementing such architecture.

Basic skeleton of the project includes:

 * eurekasvr: a service discovery server using [Spring Cloud/Netflix Eureka](http://cloud.spring.io/spring-cloud-netflix/)
 * confsvr: a centralised configuration server using [Spring Cloud Config](http://cloud.spring.io/spring-cloud-config/) that is using GitHub as a distributed, version controlled and auditable place for all configuration.
 * zuulsvr: an API gateway server using [Spring Cloud/Netflix Zuul](http://cloud.spring.io/spring-cloud-netflix/). This offers a highly scalable single entry point to the system allowing for the implementation of cross-cutting concerns in one place e.g. corelating logs.

For more detailed understanding of above technologies and the microservices architectural patterns they try to address, please read my article [Microservices - More Than Writing Code](https://dzone.com/articles/microservices-more-than-writing-code).

Rest of the services in the project are the assignment requirements:

 * customer-location-service
 * catalogue-service
