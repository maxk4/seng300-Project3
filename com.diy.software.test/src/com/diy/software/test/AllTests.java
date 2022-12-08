package com.diy.software.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.diy.software.test.MainTest.CustomerStationWrapperTest;


@RunWith(Suite.class)
@SuiteClasses(
		{ AddItemTests.class,
		  AttendantDatabaseTest.class,
		  AttendantUITest.class,
		  AttendantViewTest.class,
		  CustomerUITests.class,
		  LowInkPaperTest.class,
		  MembershipTestUnit.class,
		  OctetTest.class,
		  PayByCardTest.class,
		  PayByCashTest.class,
		  PaymentControllerTest.class, 
		  PaymentManagerTest.class,
		  PurchaseBagsGUITest.class,
		  //ProductListTests.class,
		  ProductListUtilTests.class,
		  ScaleTests.class,
		  SimulationTest.class,
		  CustomerUISimulatorTest.class,
		  MaintenanceSimulatorTest.class,
		  CustomerStationWrapperTest.class,
		  })
public class AllTests {
	
}
