import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import payment.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@ExtendWith(MockitoExtension.class)
class PaymentServiceShould {
    private static final PaymentDetails PAYMENT_DETAILS = new PaymentDetails();
    private static final User USER = new User();

    @Mock private FraudService fraudService;
    @Mock private PaymentGateway paymentGateway;

    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentService(fraudService, paymentGateway);
    }

    @Test
    void throw_exception_if_payment_is_fraudulent() {
        given(fraudService.isFraudulent(USER, PAYMENT_DETAILS))
                .willReturn(true);

        assertThrows(PaymentFraudulentException.class,
                () -> paymentService.processPayment(USER, PAYMENT_DETAILS));

        verify(fraudService).isFraudulent(USER, PAYMENT_DETAILS);
        verifyZeroInteractions(paymentGateway);
    }

    @Test
    void process_payment_when_payment_is_not_fraudulent() {
        paymentService.processPayment(USER, PAYMENT_DETAILS);

        verify(paymentGateway).payWith(PAYMENT_DETAILS);
    }

}
