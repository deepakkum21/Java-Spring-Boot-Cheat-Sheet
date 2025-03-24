## Steps in the SAGA Pattern

- Start the Saga: A service begins a transaction, triggering the saga. This may involve an API call or an event trigger.
- Perform Local Transactions: Each service involved in the saga `performs its local transaction`. These are isolated, independent operations that modify the service's local database.
- `Event Notification`: After a local transaction, each service publishes an event or sends a message to notify other services to proceed with their respective local transactions.
- `Failure Handling (Compensation)`: If any service `fails during its local transaction, it will invoke compensating transactions to undo the changes made by previous services`. These compensations must be predefined and handled by each service.
- `Commit or Compensation`: Either the `entire saga commits successfully, or compensating actions` are taken to ensure consistency across services.

# Types of SAGA Pattern

There are two primary ways to implement the SAGA Pattern, depending on how the services communicate and coordinate:

## 1. Choreography-based SAGA

In **choreography**, there is no central orchestrator. Each service involved in the saga knows about the next service in the process and communicates directly with it.

- Each service publishes events that other services listen to in order to trigger their own local transactions.
- If a service fails, other services are responsible for handling the failure, either by compensating the failed transaction or by rolling back their changes.

### Example:

- Service A initiates the transaction by sending an event to Service B.
- Service B listens to the event and proceeds with its local transaction.
- Service B, on success, sends an event to Service C.
- If any service fails, it will publish a **compensating event**, allowing each previous service to undo its transaction.

### Advantages:

- Decentralized approach where each service has more control over its actions.
- No central orchestrator, which can reduce bottlenecks and single points of failure.

### Disadvantages:

- More complex to implement and maintain, as each service must know how to handle both success and failure scenarios.
- Coordination between services can become challenging as the system grows.

---

## 2. Orchestration-based SAGA

In **orchestration**, there is a central **orchestrator** (also called a **coordinator**) that manages the entire saga.

- The orchestrator sends commands to each service, guiding the execution of each step in the transaction.
- If a service fails, the orchestrator handles compensating actions and directs the services to roll back or undo previous steps.

### Example:

- The orchestrator begins by instructing Service A to perform its local transaction.
- Once Service A completes successfully, the orchestrator directs Service B to perform its local transaction.
- If any service fails, the orchestrator manages compensations, such as instructing Service A to undo its transaction.

### Advantages:

- Centralized control and better visibility of the overall saga.
- Easier to manage failures and compensations because the orchestrator has the full picture of the transaction.

### Disadvantages:

- The orchestrator can become a single point of failure.
- Might introduce a bottleneck as all commands and failures are handled by the orchestrator.
