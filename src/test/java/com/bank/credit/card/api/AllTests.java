package com.bank.credit.card.api;

import com.bank.credit.card.api.model.BrandTest;
import com.bank.credit.card.api.model.CardTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BrandTest.class, CardTest.class})
public class AllTests {
}
