# Logging

- `recording important application events(request,error,warning) so that developers can monitor and debug` what the application is doing at runtime.

## How logging works for singleton app

![Logging in Singleton App](./img/logg-singleton-app.png)

- Spring boot has **SL4J** interface
  - its default implementation is **Logback**
    - default appender is **CONSOLE**

```java
@RestController
public class PaymentController {
    Logger log = LoggerFactory.getLogger(PaymentController.class);

    @GetMapping("/payments")
    public String getPayments() {
        log.info("fetch the payments successfully");
        return "successfully fetched all payments";
    }
}
```

---

## When Spring Dependency is added what happens

![Dependency is added what happens](./img/when-dependecy-is-added.png)

## IMPORTANT QUESTION

- By `default Springboot brings the "Logback" library`.
- But what if I want to use "Log4j2". Then `I have to add the dependency of "Log4j2", like below`:

```xml
<dependency>
    <groupld>org.springframework.boot</groupld>
    <artifactld>spring-boot-starter-log4j2</artifactld>
</dependency>
```

- Now, in c`lass path we have 2 implementation library of SLf4J`:
  - **Logback (default) one**
  - **Log4j2, which we have manually added**.
- Which implementation library will be used now?

- `LoggerFactory (Framework code)`

```java
private final static void bind() {
    try {
        List<SLF4JServiceProvider> providersList = findServiceProviders();
        reportMultipleBindingAmbiguity(providersList);
        if (providersList != null && !providersList.isEmpty()) {
            PROVIDER = providersList.get(0);     // from the list we will get the 1st one, but which one Logback or Log4J2
        }
    }
}
```

- We will `not get any error if both are present but we will get the warning`
  ![warning-multiple-](./img/warning-multiple-log-impl.png)

- In prod we `should exclude the exclude the default LOGBACK and inorder to include the LOG4J2`

```xml
<dependency>
    <groupld>org.springframework.boot</groupld>
    <artifactld>spring-boot-starter</artifactld>
    <exclusions>
        <exclusion>
            <groupld>org.springframework.boot</groupld>
            <artifactld>spring-boot-starter-logging</artifactld>
        </exclusion>
    </exclusions>
</dependency>

<dependency>
    <groupld>org.springframework.boot</groupld>
    <artifactld>spring-boot-starter-log4j2</artifactld>
</dependency>
```
