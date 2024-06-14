# Headers in @ParameterObjects

A minimal sample Spring Boot application that demonstrates an issue with
mapping header values using `@ParameterObject` annotation.

See:
* https://github.com/springdoc/springdoc-openapi/issues/1893

## How to run

```shell
mvn spring-boot:run
curl -H 'testheader: header-value' http://localhost:8080/?testparam=param-value
```

Expected output:
```
Hello, param-value!
You sent the header: header-value
```

Actual output:
```
Hello, param-value!
You sent the header: null
```