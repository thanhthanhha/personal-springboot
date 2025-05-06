package com.realchat.store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("paypalPayment")
public class PaypalPaymentClass implements PaymentService {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Override
	public void processPayment(double amount) {
		// TODO Auto-generated method stub
		logger.info("Processing paypal payment of ${}", amount);
	}

}
