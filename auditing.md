# Audit

## Steps

- `@EnableJpaAuditing`
- `@EntityListeners(AuditingEntityListener.class)` in Entity Class

  - **@CreatedDate**:

    - `Automatically sets the timestamp` for when the entity is created.

  - **@LastModifiedDate**:

    - `Automatically updates the timestamp` when the entity is modified.

  - **@CreatedBy**:

    - Tracks the user who created the entity (requires additional setup).

  - **@LastModifiedBy**:
    - Tracks the user who last modified the entity (requires additional setup).

## Custom AuditorAware

- To track users with `@CreatedBy and @LastModifiedBy`, you'll need to `implement the AuditorAware interface` to provide the current user.

```java
        @Component
        public class CustomAuditorAware implements AuditorAware<String> {
            @Override
            public Optional<String> getCurrentAuditor() {
                // Logic to return the current user, e.g., from a security context or session
                return Optional.of("CurrentUser");
            }
        }
 ```
