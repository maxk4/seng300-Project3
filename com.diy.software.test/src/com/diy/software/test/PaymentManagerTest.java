package com.diy.software.test;

import com.diy.hardware.DoItYourselfStation;
import com.diy.simulation.Customer;
import com.jimmyselectronics.opeechee.Card;
import org.junit.Test;
import payment.CardPaymentManager;
import payment.CardReadListener;
import payment.HoldException;
import payment.PaymentController;
import util.Bank;

import java.io.IOException;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PaymentManagerTest {
    @Test
    public void testAvailableFunds() {
        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        CardPaymentManager cardPaymentManager =
                new CardPaymentManager(new PaymentController(doItYourselfStation), doItYourselfStation);
        Calendar calendarInstance = Calendar.getInstance();
        calendarInstance.set(2023, Calendar.FEBRUARY, 21, 23, 59, 59);
        Bank.CARD_ISSUER.addCardData("8417992603318970", "Isaacs",
                calendarInstance, "276", 10000);
        Bank.CARD_ISSUER.addCardData("8417992603318971", "Isaacs",
                calendarInstance, "276", 10000);
        Bank.CARD_ISSUER.addCardData("8417992603318972", "Isaacs",
                calendarInstance, "276", 10000);

        try {
            cardPaymentManager.placeHold("8417992603318970", 20000L, "Membership");
            cardPaymentManager.placeHold("8417992603318971", 20000L, "Membership");
            cardPaymentManager.placeHold("8417992603318972", 20000L, "Membership");
        } catch (HoldException e) {
            e.printStackTrace();
            fail();
        }

        try {
            cardPaymentManager.pay(10000L);
            assertEquals(10000L, cardPaymentManager.availableFunds());
            System.out.println(cardPaymentManager.availableFunds());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCurrentBalance() throws IOException {

        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        CardPaymentManager cardPaymentManager =
                new CardPaymentManager(new PaymentController(doItYourselfStation), doItYourselfStation);
        doItYourselfStation.plugIn();
        doItYourselfStation.turnOn();


        doItYourselfStation.cardReader.enable();



        Calendar calendarInstance = Calendar.getInstance();
        calendarInstance.set(2023, Calendar.FEBRUARY, 21, 23, 59, 59);

        Bank.CARD_ISSUER.addCardData("8417992603318973", "Isaacs",
                calendarInstance, "276", 10000);
        Bank.CARD_ISSUER.addCardData("8417992603318974", "Isaacs",
                calendarInstance, "276", 10000);
        Bank.CARD_ISSUER.addCardData("8417992603318975", "Isaacs",
                calendarInstance, "276", 10000);

        Customer customer = new Customer();
        customer.useStation(doItYourselfStation);
        customer.wallet.cards.add(new Card("MasterCard1", "8417992603318973", "Isaacs", "276", "666666", true, true));
        customer.wallet.cards.add(new Card("MasterCard2", "8417992603318974", "Isaacs", "276", "666666", true, true));
        customer.wallet.cards.add(new Card("MasterCard3", "8417992603318975", "Isaacs", "276", "666666", true, true));


        try {
            customer.selectCard("MasterCard1");
            customer.insertCard("666666");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }


        try {
            cardPaymentManager.placeHold("8417992603318973", 20000L, "Membership");
            cardPaymentManager.placeHold("8417992603318974", 20000L, "Membership");
            cardPaymentManager.placeHold("8417992603318975", 20000L, "Membership");
        } catch (HoldException e) {
            e.printStackTrace();
            fail();
        }

        try {
            cardPaymentManager.pay(10000L);
            assertEquals(10000L, cardPaymentManager.availableFunds());
            System.out.println(cardPaymentManager.availableFunds());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        cardPaymentManager.currentBalance();
    }


}
