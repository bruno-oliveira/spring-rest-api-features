# spring-rest-api-features
Showcase of features for developing REST APIs with SpringBoot

We have a very simple REST Api at the core of our Spring App:

- A simple `greet` endpoint, that accepts POST requests, and, as input, receives a `Person` JSON, and return a `Greet` JSON.

Both the `Person` and `Greet` classes are represented in the code as DTOs: Data Transfer Objects, which can be used to represent in Java the JSONs that our endpoint will be both consuming and responding with.

Jackson is used as the ObjectMapper to de/serialize to/from JSON.

Regarding com.springfeatures.demo.security, we have basic authentication in place, and we use Spring's com.springfeatures.demo.security capabilities to ensure that only authorized people can perform requests.

For integration tests, we explore the `MockMVc` class that we can use to mock real-world requests to our endpoint.

The application is developed from scratch, using IntelliJ IDEA Professional Edition 2019 and Spring Initializr to manage the Spring dependencies.

v1.0 - no security in place, basic showcase of creating a rest controller, delegating business logic to a service class and basic usage of `MockMvc` as means to write readable integration tests.
