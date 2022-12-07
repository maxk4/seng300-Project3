package com.diy.software.test;

import com.diy.hardware.DoItYourselfStation;
import org.junit.Test;
import payment.CardPaymentManager;
import payment.HoldException;
import payment.PaymentController;
import payment.PaymentListener;
import util.Bank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class PaymentControllerTest {
    @Test
    public void testPaymentControllerConstructor(){
        try {
            new PaymentController(new DoItYourselfStation());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCompleteTransaction() throws ParseException {
        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        PaymentController paymentController = new PaymentController(doItYourselfStation);
        CardPaymentManager cardPaymentManager =
                new CardPaymentManager(paymentController, doItYourselfStation);
        doItYourselfStation.plugIn();
        doItYourselfStation.turnOn();

        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-02-13 22:36:01");
        Bank.CARD_ISSUER.addCardData("8417992603318973", "Isaacs",
                date, "276", 10000);

        try {
            cardPaymentManager.placeHold("8417992603318973", 30000L, "Membership");
        } catch (HoldException e) {
            e.printStackTrace();
            fail();
        }

        paymentController.addCost(20000L);

        try {
            paymentController.completeTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testHasRemainingBalance() throws ParseException {
        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        PaymentController paymentController = new PaymentController(doItYourselfStation);
        CardPaymentManager cardPaymentManager =
                new CardPaymentManager(paymentController, doItYourselfStation);
        doItYourselfStation.plugIn();
        doItYourselfStation.turnOn();


        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-02-13 22:36:01");
        Bank.CARD_ISSUER.addCardData("8417992603318974", "Isaacs",
        		date, "276", 10000);

//        Customer customer = new Customer();
//        customer.useStation(doItYourselfStation);
//        customer.wallet.cards.add(new Card("MasterCard1", "8417992603318973", "Isaacs", "276", "666666", true, true));
//        customer.wallet.cards.add(new Card("MasterCard2", "8417992603318974", "Isaacs", "276", "666666", true, true));
//        customer.wallet.cards.add(new Card("MasterCard3", "8417992603318975", "Isaacs", "276", "666666", true, true));
//
//
//        try {
//            customer.selectCard("MasterCard1");
//            customer.insertCard("666666");
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail();
//        }


        try {
            cardPaymentManager.placeHold("8417992603318974", 20000L, "Membership");
        } catch (HoldException e) {
            e.printStackTrace();
            fail();
        }

        try {
            cardPaymentManager.pay(10000L);
            System.out.println(cardPaymentManager.availableFunds());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        paymentController.addCost(20000L);

        assertTrue(paymentController.hasRemainingBalance());
    }

    @Test
    public void testGetRemainingBalance() {
        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        PaymentController paymentController = new PaymentController(doItYourselfStation);
        CardPaymentManager cardPaymentManager =
                new CardPaymentManager(paymentController, doItYourselfStation);
        doItYourselfStation.plugIn();
        doItYourselfStation.turnOn();

        paymentController.addCost(30000L);
        assertEquals(30000L,paymentController.getRemainingBalance());
    }

    @Test
    public void testGetAvailableFunds() {
        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        PaymentController paymentController = new PaymentController(doItYourselfStation);
        CardPaymentManager cardPaymentManager =
                new CardPaymentManager(paymentController, doItYourselfStation);
        doItYourselfStation.plugIn();
        doItYourselfStation.turnOn();

        paymentController.getAvailableFunds();
    }

    @Test
    public void testNotifyCashPayment() {
        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        PaymentController paymentController = new PaymentController(doItYourselfStation);

        CardPaymentManager cardPaymentManager =
                new CardPaymentManager(paymentController, doItYourselfStation);
        doItYourselfStation.plugIn();
        doItYourselfStation.turnOn();

        PaymentListenerStub paymentListenerStub = new PaymentListenerStub();
        paymentController.register(paymentListenerStub);
        paymentController.notifyCashPayment();

        assertTrue(paymentListenerStub.getIfCashInserted());
    }

    @Test
    public void testAvailableCash() {
        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        PaymentController paymentController = new PaymentController(doItYourselfStation);

        CardPaymentManager cardPaymentManager =
                new CardPaymentManager(paymentController, doItYourselfStation);
        doItYourselfStation.plugIn();
        doItYourselfStation.turnOn();

        try {
            paymentController.availableCash();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testRegisterAndDeregister() {
        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        PaymentController paymentController = new PaymentController(doItYourselfStation);

        CardPaymentManager cardPaymentManager =
                new CardPaymentManager(paymentController, doItYourselfStation);
        doItYourselfStation.plugIn();
        doItYourselfStation.turnOn();

        PaymentListenerStub paymentListenerStub = new PaymentListenerStub();
        assertTrue(paymentController.register(paymentListenerStub));
        assertTrue(paymentController.deregister(paymentListenerStub));
    }

    class PaymentListenerStub implements PaymentListener {
        boolean ifCashInserted = false;

        public boolean getIfCashInserted() {
            return ifCashInserted;
        }

        @Override
        public void cardPaymentSucceeded() {
        }

        @Override
        public void cashInserted() {
            ifCashInserted  = true;
        }
    }

}
