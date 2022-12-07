package com.diy.software.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses(
		{ AddItemTests.class,
		  AttendantDatabaseTest.class,
		  AttendantUITest.class,
		  AttendantViewTest.class,
		  //BaggingOptionsTests.class,
		  CustomerUITests.class,
		  LowInkPaperTest.class,
		  MembershipTestUnit.class,
		  OctetTest.class,
		  PayByCardTest.class,
		  PayByCashTest.class,
		  PaymentControllerTest.class, 
		  PaymentManagerTest.class,
		  ProductListTests.class,
		  ScaleTests.class })
public class AllTests {
	
}
