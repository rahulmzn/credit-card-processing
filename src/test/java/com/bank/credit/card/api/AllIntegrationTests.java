package com.bank.credit.card.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.stereotype.Component;

@RunWith(Suite.class)
@SuiteClasses({ CreditCardIntegrationTests.class})
@Component
public class AllIntegrationTests {

}
