//package com.diy.software.test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
//import java.util.ArrayList;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import com.diy.hardware.DoItYourselfStation;
//import com.jimmyselectronics.AbstractDevice;
//import com.jimmyselectronics.AbstractDeviceListener;
//import com.jimmyselectronics.EmptyException;
//import com.jimmyselectronics.OverloadException;
//import com.jimmyselectronics.svenden.ReusableBag;
//import com.jimmyselectronics.svenden.ReusableBagDispenser;
//import com.jimmyselectronics.svenden.ReusableBagDispenserListener;
//
//public class BaggingOptionsTests {
//	
//	ReusableBag reuseableBag;
//	ReusableBagDispenser dispencer;
//	DoItYourselfStation station;
//	ArrayList<ReusableBag> bags = new ArrayList<ReusableBag>();
//	
//	int found;
//	
//	@Before
//	public void setup() throws OverloadException {
//		found = 0;
//		reuseableBag = new ReusableBag();
//		station = new DoItYourselfStation();
//		station.reusableBagDispenser.plugIn();
//		station.reusableBagDispenser.turnOn();
//		
//		ReusableBag[] bag = new ReusableBag[5];
//		for (int i = 0; i < 5; i++) {
//			bag[i] = new ReusableBag();
//		}
//		station.reusableBagDispenser.load(bag);
//		
//	}
//	
//	@Test
//	public void test() throws EmptyException, OverloadException {
//		station.reusableBagDispenser.register(new ReusableBagDispenserListener() {
//			
//			@Override
//			public void turnedOn(AbstractDevice<? extends AbstractDeviceListener> device) {
//				fail();
//			}
//			
//			@Override
//			public void turnedOff(AbstractDevice<? extends AbstractDeviceListener> device) {
//				fail();
//			}
//			
//			@Override
//			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
//				found++;
//			}
//			
//			@Override
//			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
//				fail();
//			}
//			
//			@Override
//			public void outOfBags(ReusableBagDispenser dispenser) {
//				fail();	
//			}
//			
//			@Override
//			public void bagsLoaded(ReusableBagDispenser dispenser, int count) {
//				fail();	
//			}
//			
//			@Override
//			public void bagDispensed(ReusableBagDispenser dispenser) {
//				found++;
//			}
//		});
//		station.reusableBagDispenser.enable();
//		station.reusableBagDispenser.dispense();
//		
//		assertEquals(2, found);
//	
//	}
//}
