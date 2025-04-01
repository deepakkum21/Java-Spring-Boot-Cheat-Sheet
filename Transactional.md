# Transaction

- ACID
  - **A (Atomicity)**
    - Ensures all operations in `transaction are completed successfully. If any operation fails then entire transaction is rolled back`.
  - **C(Consistency)**
    - Ensures the `DB state before and after transaction in consistent`
  - **I (Isolation)**
    - Ensures that, if `multiple transactions are running in parallel, they do not interfere with each other`
  - **D(Durability)**
    - Ensures that `committed transaction will never be lost` despite of system failures

## 1. Programmatic way

- TransactionManager [I]
  - PlatformTransactionManager [I] implements TransactionManager
    - has methods
      - getTransaction()
      - commit()
      - rollback()
  - AbstractPlatformTransactionManager implements PlatformTransactionManager
    - provides implementation for
      - getTransaction()
      - commit()
      - rollback()
    - concrete class
      - DataSourceTransactionManager
        - JdbcTransactionManager
      - HibernateTransactionManager
      - JPATransactionManager
      - JTATransactionManager - for 2phase commit
- to know which transaction manager is used
  `TransactionAspectSupport.currentTransactionStatus()`

### 1. 1st way

```java
    @Component
    public class FirstProgrammaticApproach {
        PlatformTransactionManager transactionManager;

        public FirstProgrammaticApproach(PlatformTransactionManager transactionManager) { // PlatformTransactionManager bean is created down
            this.transactionManager = transactionManager;
        }

        public void updateUser(){
            TransactionStatus status = transactionManager.getTransaction( definition: null);
            try{
                System.out.println("Do Operations");
                transactionManager.commit(status);
            }catch (Exception e){
                transactionManager.rollback(status);
            }
        }
    }
```

### 2. USing Transaction Template

```java
    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager){
        return new TransactionTemplate(transactionManager);
    }
```

```java
    @Component
    public class SecondProgrammaticApproach {
        TransactionTemplate transactionTemplate;

        public SecondProgrammaticApproach(TransactionTemplate transactionTemplate) {
            this. transactionTemplate = transactionTemplate;
        }

        public void updateUser() {
            TransactionCallback<TransactionStatus> dbOperationTask = (TransactionStatus status)->{
                System.out.println("Perform Operations");
                return status;
            }

            transactionTemplate.execute(dbOperationTask);
        }
    }
```

## 2. Declarative way

- through transaction annotation
  - `@Transactional`
- spring will automatically choose the transaction manager, but we can tell which one to use explicitly also

### explicitly defining Transaction Manager

- In config class, `create bean for DATASOURCE`

```java
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSoyrce = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb");
        dataSource.setUsername("root");
        dataSource.setPassword("Password");
        return dataSource;
    }
```

- then `create a bean for PlatformTransactionManager` and pass datasource created in above point.

```java
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
```

- pass the platformTransactionManager method name

```java
    @Autowired
    BookRepository bookRepository;
    @Transactional(transactionManager = "transactionManager")
        public Book addBook(Book book) {
        return bookRepository.save(book);
    }
```
