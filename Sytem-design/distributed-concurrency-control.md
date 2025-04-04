# Concurrency Control

https://www.youtube.com/watch?v=lceenm34m-w
-first read transactions management

![concurrency control](./img/distributed-concurrency.png)

## Optimistic Concurrency Control

![optimistic](./img/optimistic-concurrency.png)

## Pessimistic Concurrency Control

- `deadlock possible`

---

## 2-Phase locking

- Has two phases
  - `Growing phase`
    - can only acquire locks
  - `shrinking phase`
    - can only release the locks
- types
  - Basic
    - deadlock
  - Conservative
    - take all the locks at the start of transaction, if any lock is failed to gain, it will wait to get all transactions.
    - no deadlock
    - less concurrent
    - not used
  - Regress [strong strict locking]
