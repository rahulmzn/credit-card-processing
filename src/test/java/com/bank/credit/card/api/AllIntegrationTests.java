package com.bank.credit.card.api;

import com.bank.credit.card.api.controller.CreditCardControllerIntegrationTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.stereotype.Component;

@RunWith(Suite.class)
@SuiteClasses({ CreditCardControllerIntegrationTests.class})
@Component
public class AllIntegrationTests {

}
