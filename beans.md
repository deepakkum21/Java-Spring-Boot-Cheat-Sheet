## Bean

- `bean is an object that is managed by the Spring container`
- All beans are maintained by `SPRING IOC CONTAINER`

## Differences Between IoC Container and ApplicationContext

| **Aspect**                 | **IoC Container**                                       | **ApplicationContext**                                                                               |
| -------------------------- | ------------------------------------------------------- | ---------------------------------------------------------------------------------------------------- |
| **Definition**             | General term for Spring's mechanism for managing beans. | A specific, more advanced implementation of the IoC container.                                       |
| **Basic Role**             | `Manages beans and their lifecycle`.                    | `Extends IoC container features and adds additional` functionality.                                  |
| **Major Implementation**   | `BeanFactory` (the basic container).                    | `ApplicationContext` (e.g., `ClassPathXmlApplicationContext`, `AnnotationConfigApplicationContext`). |
| **Extra Features**         | Limited to managing beans.                              | Supports events, internationalization (i18n), and more.                                              |
| **Use in Spring Boot**     | Not directly used in most Spring Boot apps.             | `Primarily used in Spring Boot `apps.                                                                |
| **Default Implementation** | Basic `BeanFactory` container.                          | `AnnotationConfigApplicationContext` in Spring Boot.                                                 |

## Bean Creation

- **Java Based Configuration**

  - `@Configuration` - Class Level
  - `@Bean` - Method Level

    - `Bean Name [for Qualifier] = method Name`
    - `Bean Type = return Type of method`

            @Configuration
            public class AppConfig {
                @Bean
                public MyService myService() {
                    return new MyService();
                }
            }

- **Component Scanning**

  - `@Component`
  - `@Service`
  - `@Repository`
  - `@RestController`
  - `@Controller`

            @Component
            public class AppConfig {
            }

## Ways to check beans present in application

- **Actuator**
  - add dependency in pom `spring-boot-starter-actuator`
  - configure property to expose in browser
    - `management.endpoints.web.exposure.include=*`
    - `/actuator/beans` - UI
    - secure the URL using securityFilterChain`.requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ADMIN")` // Limit access to actuator endpoints to users with "ADMIN" role
- **Application Context**

        ConfigurableApplicationContext applicationContext = SpringApplication.run(EcomApplication.class, args);
        applicationContext.getBean("nameOfTheBean")

## Bean Creation

- `Eager`
  - `when application starts`
  - `singleton beans`
- Lazy
  - `When beans are needed`
  - `prototype / @Lazy`

## Bean LifeCycle [https://docs.spring.io/spring-framework/reference/core/beans/factory-nature.html]

1.  ### Bean Creation

    - The Spring container creates an instance of the bean.
    - using different `configuration methods (annotations like @Component, @Service, @Configuration, or Java-based configuration with @Bean)`

2.  ### Dependency Injection

    - Spring Boot uses Dependency Injection (DI) to inject the required dependencies into the bean after it is created. DI can be done via:
      - `Constructor-based` injection
      - `Setter-based` injection
      - `Field-based` injection

3.  ### Bean Initialization

    - Once the bean is created and dependencies are injected, Spring checks if there is `any custom initialization logic defined`.
    - There are several ways to define initialization logic in Spring:

      - Using `@PostConstruct` annotation: This method will be invoked after the bean is fully initialized and dependencies are injected.
      - `Implementing InitializingBean interface`: Spring will invoke the afterPropertiesSet() method after dependency injection.
      - Using `custom @Bean initialization methods in @Configuration classes`.

              @Configuration
              public class AppConfig {

                  @Bean(initMethod = "init")
                  public MyService myService() {
                      return new MyService();
                  }
              }

              class MyService {
                  // Initialization method to be called after bean creation
                  public void init() {
                      System.out.println("Custom initialization logic for MyService.");
                  }
              }

      - `Implementing BeanPostProcessor interface` = postProcessBeforeInitialization()

4.  ### Bean Destruction

    - After the bean is no longer needed or the Spring container is shut down, Spring handles the destruction of the bean. This can happen automatically during the context shutdown or through an explicit call.

            ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
            applicationContext.close();

    - Spring provides several ways to hook into the destruction process:

      - Using `@PreDestroy annotation`: This method will be called before the bean is destroyed.
      - `Implementing DisposableBean interface`: Spring will invoke the destroy() method of the interface when the bean is destroyed.
      - Using `custom @Bean destroy methods in @Configuration` classes.

                @Configuration
                public class AppConfig {

                    @Bean(destroyMethod = "cleanup")
                    public MyService myService() {
                        return new MyService();
                    }
                }

                class MyService {
                    // Custom destroy method to be called before the bean is destroyed
                    public void cleanup() {
                        System.out.println("Custom cleanup logic for MyService.");
                    }
                }

      - `Implementing BeanPostProcessor interface` = postProcessAfterInitialization()

## Order of Bean Creation

- `Constructor`
- `@PostConstruct`
- `InitializingBean[I] - afterPropertiesSet()`
- `custom @Bean initialization using @Configuration classes, @Bean(initMethod="methodName")`
- `Bean Post Process before Initialization => BeanPostProcessor[I] => postProcessBeforeInitialization()`
- `Bean Post Process after Initialization => BeanPostProcessor[I] => postProcessAfterInitialization()`
- `@PreDestroy`
- `Disposable[I] - Destroy()`
- `Custom @Bean Cleanup using @Configuration classes, @Bean(destroyMethod="methodName")`

## Control the initialization order of Spring beans

`@DependsOn`

        @Configuration
        public class AppConfig {

            @Bean
            @DependsOn("beanA")  // BeanB depends on BeanA, so BeanA will be created first
            public BeanB beanB() {
                System.out.println("BeanB is created");
                return new BeanB();
            }

            @Bean
            public BeanA beanA() {
                System.out.println("BeanA is created");
                return new BeanA();
            }
        }

## @ComponentScan

- `tells the Spring container where to search for Spring components (beans).`
- It helps Spring discover and register beans automatically based on annotations
- it is present as part of @SpringBootApplication
- `if using @ComponentScan mandatory to use @Configuration`
- ### Component Scan with Default Package
  - if @ComponentScan is used `without specifying the basePackages attribute`, Spring will `scan the package where the configuration class (@Configuration) is located`.
- ### Specifying Multiple Packages
        @Configuration
        @ComponentScan(basePackages = {"com.example.myapp", "com.example.other"})
            public class AppConfig {
        }
- ### Scan Specific Classes (basePackageClasses)
        @Configuration
        @ComponentScan(basePackageClasses = MyService.class)  // Specify the class to base the scan on
            public class AppConfig {
        }
- ### excludeFilters

  - exclude by Annotation

          @ComponentScan(
              basePackages = "com.example.myapp",
              excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Deprecated.class)
          )

  - exclude by type

         @ComponentScan(
            basePackages = "com.example.myapp",
            excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = MyService.class)
        )

- ### includeFilters

  - exclude by Annotation
  - exclude by type

- ### lazyInit (Default: false)
  - If set to true, Spring will initialize the scanned beans lazily, `meaning they will only be created when they are first needed`.
  - `@ComponentScan(lazyInit = true) `

## Limiting Creation of beans

- `using @Conditional` where class `implements Condition I`

        @Configuration
        public class MyConfiguration {

            @Bean
            @Conditional(MyCondition.class)
            public MyService myService() {
                return new MyService();
            }
        }

        class MyCondition implements Condition {
            @Override
            // matches method
        }

## Bean Scope

- `"singleton"`
  - (default)
  - `Eager initialized`
- `"prototype"` :-
  - new instance of the bean is created every time it is requested
  - `lazy initialization`
- `"request"`:

  - A new instance of the bean is `created for each HTTP request`
  - `lazy initialization`
  - gets a error of bean unsatisfied when request bean is present inside singleton bean

    - sol:- `create proxy`

            @Scope(value = "request", proxyMode = ScopedProxyMode. TARGET_CLASS )

- `"session"`:
  - A new instance of the bean is created for each HTTP session
- "`application`":
  - A single instance of the bean is created for the entire lifecycle of the Spring context
- "`websocket`":
  - Similar to the request and session scopes, but it is used specifically in the context of WebSockets.

## Q> in a bean defined as Singleton a property having Prototype Scope, whether the property will behave as prototype?

Ans> No as the same instance is returned as Upper level is Singleton and property is never getting chance ti re-initiate to behave as prototype

## Modern Way of creating object in SPRING boot instead of @Autowired

- `use @RequiredArgsConstructor`
- `make that object declaration as final`

      ```java
      @RequiredArgsConstructor
      public class CustomerController {

        private final CustomerService service;
      }
      ```

## How to configure profile config dynamically

. 2 ways
· 1 while application startup using command
. mvn spring-boot:run -Dspring-boot.run.profiles=prod
. Add profile in pom.xml

          <profiles>
            <profile>
              <id>local</id>
              <properties>
                <spring-boot.run.profiles>dev</spring-boot.run.profiles>
              </properties>
            </profile>
            <profile>
              <id>production</id>
              <properties>
                <spring-boot.run.profiles>prod</spring-boot.run.profiles>
              </properties>
            </profile>
          </profiles>

. And run using mvn spring-boot:run -Pproduction [pom profile id]

## @Profile Annotation

. Using Profile annotation we can tell spring boot, to create a bean only when particular profile is set
