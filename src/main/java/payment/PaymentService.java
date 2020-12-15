package payment;

public class PaymentService {

    private final FraudService fraudService;
    private final PaymentGateway paymentGateway;

    public PaymentService(final FraudService fraudService,
                          final PaymentGateway paymentGateway) {
        this.fraudService = fraudService;
        this.paymentGateway = paymentGateway;
    }

    public void processPayment(User user,
                               PaymentDetails paymentDetails) {

        if (fraudService.isFraudulent(user, paymentDetails))
            throw new PaymentFraudulentException();

        paymentGateway.payWith(paymentDetails);
    }
}
