# Distributed Design Pattern

![Distributed Design Pattern](./img/distributed-designpattern.png)

## 1. Ambassador

Client --> Ambassador --> RemoteService

1. `Client`: The system that wants to use the service.

2. `Ambassador`: A local representative of the remote service. It handles connection, retries, timeouts, logging, etc.

3. `Remote Service`: The actual external resource or service.

![ambassador](./img/ambassador.png)

---

## 2. circuit Breaker

The **Circuit Breaker Pattern** is a **resilience pattern** used in distributed systems to prevent an application from repeatedly trying to execute an operation that's likely to fail — especially remote service calls.

### 🧠 Why Use It?

When a **remote service** is **down** or **slow**, continuously making requests can:

- Waste resources
- Cause cascading failures
- Increase response time

A **circuit breaker** `breaks the connection temporarily after repeated failures`, allowing the system to recover gracefully.

### 🔄 Circuit Breaker States

1. **`Closed`**

   - Everything works normally.
   - Requests flow through.
   - Failures are monitored.

2. **`Open`**

   - Too many failures occurred.
   - Requests are **immediately blocked**.
   - A fallback response is usually returned.

3. **`Half-Open`**
   - After a timeout, a few trial requests are allowed.
   - If successful, the circuit moves to **Closed**.
   - If not, it returns to **Open**.

![circuit-breaker-state](./img/circuit-breaker.png)

### ✅ Benefits

- Prevents **resource exhaustion**
- Encourages **fallback** and **self-healing**
- Improves **resilience** in microservices and distributed systems
