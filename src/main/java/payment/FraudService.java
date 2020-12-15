package payment;

public interface FraudService {

    boolean isFraudulent(User user, PaymentDetails paymentDetails);
}
