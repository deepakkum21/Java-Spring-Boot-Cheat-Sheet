# RATE LIMITER [Distributed ENV] [429]

- **`not on geographical base`**
  - as people `can bypass the rate limiter via VPN`
- **`Logging Cache`**
  - can have common cache or can have different read and write cache and then have syncing mechanism
- **`Rule engine`**
  - no need to have multiple rule engine as the `rules don't change frequently`

## clients to handle 429 - rate limiting

- retry mechanism
- gracefully handle 429 by showing appropriate message 'PLEASE TRY AFTER SOME TIME'

![Rate Limiter](./img/rate-limiter.png)

## RATE LIMITER TYPES

- 1. **Token bucket [bucket4J]**

  - `bucket capacity`
  - `Refiller` - refills token in the bucket in X min Y tokens
  - eg
    - customer care

- 2. **Leaky bucket**

  - implemented using `QUEUE`
  - any new request comes added to queue
  - if queue is full request will be denied
  - cons
    - `not suited to burst request` [HOTSTAR IPL MATCH]
  - eg
    - youtube video upload

- 3. **Fixed Window Counter**
  - for every fixed window eg 5min a constant eg 10 request will be allowed
  - cons
    - since we know for each fixed window of 5 min 10 request is allowed and our system is designed to handle that, but there could be a `edge case where in that window no request came till 4min50 sec and in last 10sec 5 request case and all are in process and as soon the window of 5 min ended and new window started withing first 10sec 5 more request case and now in total 10req is there for which our system was not ready`
- 4. **Sliding Window Log**
  - keeps the log of timestamp when the request comes.
  - for request where the window is over the request timestamp is logged and then denied
  - when new request comes when the window is over then the first log is removed and window gets slide
  - cons
    - timestamp of denied re is also stored, which increases the size[space]
- Sliding Window Counter
