package com.realchat.store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class StripePaymentService implements PaymentService {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Override
	public void processPayment(double amount) {
		logger.info("Processing payment of ${}", amount);
	}
}
