# JPA

![JPA](./img/jpa.png)

## ENTITY Lifecycle

![entity lifecycle](./img/jpa-entity-lifecycle.png)

## @GeneratedValue

- has strategy type
  - GenerationType
    - `AUTO` : persistence provider should pick an appropriate strategy for the particular database
    - `TABLE`
    - `SEQUENCE`
    - `IDENTITY`

## JPA REPOSITORY

![jpa-repo](./img/jpa-repository-heirarchy.png)

![jpa feature repo](./img/features-jpa-repo.png)

## @Query

```java
    public interface ApplicantJpaRepository extends JpaRepository<Applicant, Long> {
    List<Applicant> findByStatusOrderByNameAsc(String status);

        @Query("SELECT a FROM Applicant a WHERE a.name LIKE %:name%")
        List<Applicant> findApplicantsByPartialName(@Param("name") String name);

    }

```

## Spring Data JPA Relation
