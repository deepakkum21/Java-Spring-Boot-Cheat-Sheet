## Dynamic Bean Creation

```java
// Service
public interface PaymentService {

    String pay(String amount,String mode,
               String sender, String receiver);
}

// Service Impl  - Paypal
@Service("paypal")
@Slf4j
public class PaypalPaymentService implements PaymentService {

    @Override
    public String pay(String amount, String mode, String sender, String receiver) {
        log.info("Processing payment with PayPal: amount={}, mode={}, sender={}, receiver={}", amount, mode, sender, receiver);
        return "paid with paypal " + amount + " from " + sender + " to " + receiver + " using mode " + mode;
    }
}

// Service Impl  - RazorPay
@Service("razorpay")
@Slf4j
public class RazorpayPaymentService implements PaymentService {

    @Override
    public String pay(String amount, String mode, String sender, String receiver) {
        log.info("Processing payment with Razorpay: amount={}, mode={}, sender={}, receiver={}", amount, mode, sender, receiver);
        return "paid with razorpay " + amount + " from " + sender + " to " + receiver + " using mode " + mode;
    }
}

// Service Impl  - Stripe
@Service("stripe")
@Slf4j
public class StripePaymentService implements PaymentService {

    @Override
    public String pay(String amount, String mode, String sender, String receiver) {
        // Simulate payment processing logic
        log.info("Processing payment with Stripe: amount={}, mode={}, sender={}, receiver={}", amount, mode, sender, receiver);
        return "paid with stripe " + amount + " from " + sender + " to " + receiver + " using mode " + mode;
    }
}
```

```java
@RestController
@RequestMapping("/api/payment/v2")
public class PaymentControllerV2 {


    private final Map<String, PaymentService> paymentServicesMap;

    public PaymentControllerV2(Map<String, PaymentService> paymentServicesMap) {
        this.paymentServicesMap = paymentServicesMap;
    }
    // key                      value
    // paypalPaymentService- PaypalPaymentService
    // razorpayPaymentService- RazorpayPaymentService
    // stripePaymentService- StripePaymentService

    // When alias is given to the bean
    // stripe - StripePaymentService
    // paypal - PaypalPaymentService
    // razorpay - RazorpayPaymentService

    @PostMapping("/pay/v2")
    public String pay(@RequestBody PaymentRequest paymentRequest) {

        String paymentType = paymentRequest.getPaymentType().toLowerCase();

        PaymentService service = paymentServicesMap.get(paymentType);

        if (service == null) {
            throw new IllegalArgumentException("Unsupported payment mode: " + paymentType);
        }
        return service.pay(
                paymentRequest.getAmount(),
                paymentType,
                paymentRequest.getSender(),
                paymentRequest.getReceiver()
        );

    }
}
```
