package com.diy.software.test;
import com.diy.hardware.DoItYourselfStation;
import static org.junit.Assert.*;
import org.junit.Test;
import payment.CardPaymentManager;
import payment.HoldException;
import payment.PaymentController;
import util.Bank;
import util.GiftCardIssuer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PayByCardTest {
    @Test
    public void testCreditCardPaySuccessful() throws ParseException {
        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        CardPaymentManager cardPaymentManager =
                new CardPaymentManager(new PaymentController(doItYourselfStation), doItYourselfStation);
//        Calendar calendarInstance = Calendar.getInstance();
//        calendarInstance.set(2023, Calendar.FEBRUARY, 21, 23, 59, 59);
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-02-13 22:36:01");
        Bank.CARD_ISSUER.addCardData("8417992603318970", "Isaacs",
        		date, "276", 10000);
        Bank.CARD_ISSUER.addCardData("8417992603318971", "Isaacs",
        		date, "276", 10000);
        Bank.CARD_ISSUER.addCardData("8417992603318972", "Isaacs",
        		date, "276", 10000);

        try {
            cardPaymentManager.placeHold("8417992603318970", 20000, "Membership");
            cardPaymentManager.placeHold("8417992603318971", 20000, "Membership");
            cardPaymentManager.placeHold("8417992603318972", 20000, "Membership");
        } catch (HoldException e) {
            e.printStackTrace();
            //fail();
        }

        try {
            long returnedFunds = cardPaymentManager.pay(100);
            assertEquals(10000,returnedFunds);
            System.out.println("Returned Funds" + returnedFunds);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCreditCardPayOverHoldAmount() throws ParseException {
        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        doItYourselfStation.plugIn();
        doItYourselfStation.turnOn();
        CardPaymentManager cardPaymentManager = new CardPaymentManager(new PaymentController(doItYourselfStation), doItYourselfStation);

        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-02-13 22:36:01");
        Bank.CARD_ISSUER.addCardData("8417992603318973", "Isaacs", date, "276", 10000);

        try {
            cardPaymentManager.placeHold("8417992603318974", 20000, "Membership");
        } catch (HoldException e) {
            e.printStackTrace();
            //fail();
        }

        try {
            long returnedFunds = cardPaymentManager.pay(20000);
            System.out.println("Returned funds: " + returnedFunds);
           // fail("Should not pay with 500 because the hold amount is 200");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCardReturnFunds() throws ParseException {
        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        doItYourselfStation.plugIn();
        doItYourselfStation.turnOn();
        CardPaymentManager cardPaymentManager = new CardPaymentManager(new PaymentController(doItYourselfStation), doItYourselfStation);

        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-02-13 22:36:01");
        Bank.CARD_ISSUER.addCardData("8417992603318975", "Isaacs", date, "276", 10000);
        Bank.CARD_ISSUER.addCardData("8417992603318976", "Isaacs", date, "276", 10000);
        Bank.CARD_ISSUER.addCardData("8417992603318977", "Isaacs", date, "276", 10000);

        try {
            cardPaymentManager.placeHold("8417992603318975", 20000L, "Membership");
            cardPaymentManager.placeHold("8417992603318976", 20000L, "Membership");
            cardPaymentManager.placeHold("8417992603318977", 20000L, "Membership");
        } catch (HoldException e) {
            e.printStackTrace();
        }

//        try {
//            long returnedFunds = cardPaymentManager.pay(10000L);
//            assertEquals(10000L,returnedFunds);
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail();
//        }
        long returnedFunds = cardPaymentManager.returnFunds(600);
        assertEquals(600, returnedFunds);
    }

    @Test
    public void testGiftCardPaymentSuccessful() throws ParseException {
        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        doItYourselfStation.plugIn();
        doItYourselfStation.turnOn();
        CardPaymentManager cardPaymentManager = new CardPaymentManager(new PaymentController(doItYourselfStation), doItYourselfStation);

        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-02-13 22:36:01");
        GiftCardIssuer.CARD_ISSUER.addCardData("8417992603318976", "Isaacs", date, "276", 10000);

        try {
            cardPaymentManager.placeHold("8417992603318976", 20000L, "Gift Card");
        } catch (HoldException e) {
            e.printStackTrace();
            fail();
        }

        try {
            cardPaymentManager.pay(10000L);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }


    @Test
    public void testCardPaymentWithNoHold() throws ParseException {
        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        doItYourselfStation.plugIn();
        doItYourselfStation.turnOn();
        CardPaymentManager cardPaymentManager = new CardPaymentManager(new PaymentController(doItYourselfStation), doItYourselfStation);

        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-02-13 22:36:01");
        GiftCardIssuer.CARD_ISSUER.addCardData("8417992603318977", "Isaacs", date, "276", 10000);
        cardPaymentManager.pay(10000L);
   
    }
}
