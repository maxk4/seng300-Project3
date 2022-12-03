import com.diy.hardware.DoItYourselfStation;
import static org.junit.Assert.*;
import org.junit.Test;
import payment.CardPaymentManager;
import payment.HoldException;
import payment.PaymentController;
import util.Bank;
import util.GiftCardIssuer;

import java.util.Calendar;

import static org.junit.Assert.fail;

public class PayByCardTest {
    @Test
    public void testCreditCardPaySuccessful() {
        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        CardPaymentManager cardPaymentManager = new CardPaymentManager(new PaymentController(doItYourselfStation), doItYourselfStation);
        Calendar calendarInstance = Calendar.getInstance();
        calendarInstance.set(2023, Calendar.FEBRUARY, 21, 23, 59, 59);
        Bank.CARD_ISSUER.addCardData("8417992603318972", "Isaacs", calendarInstance, "276", 10000);

        try {
            cardPaymentManager.placeHold("8417992603318972", 200L, "Membership");
        } catch (HoldException e) {
            e.printStackTrace();
            fail();
        }

        try {
            cardPaymentManager.pay(100L);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCreditCardPayOverHoldAmount() {
        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        doItYourselfStation.plugIn();
        doItYourselfStation.turnOn();
        CardPaymentManager cardPaymentManager = new CardPaymentManager(new PaymentController(doItYourselfStation), doItYourselfStation);

        Calendar calendarInstance = Calendar.getInstance();
        calendarInstance.set(2023, Calendar.FEBRUARY, 21, 23, 59, 59);
        Bank.CARD_ISSUER.addCardData("8417992603318973", "Isaacs", calendarInstance, "276", 10000);

        try {
            cardPaymentManager.placeHold("8417992603318973", 200L, "Membership");
        } catch (HoldException e) {
            e.printStackTrace();
            fail();
        }

        try {
            cardPaymentManager.pay(500L);
            fail("Should not pay with 500 because the hold amount is 200");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCardReturnFunds() {
        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        doItYourselfStation.plugIn();
        doItYourselfStation.turnOn();
        CardPaymentManager cardPaymentManager = new CardPaymentManager(new PaymentController(doItYourselfStation), doItYourselfStation);

        Calendar calendarInstance = Calendar.getInstance();
        calendarInstance.set(2023, Calendar.FEBRUARY, 21, 23, 59, 59);
        Bank.CARD_ISSUER.addCardData("8417992603318974", "Isaacs", calendarInstance, "276", 10000);

        try {
            cardPaymentManager.placeHold("8417992603318974", 200L, "Membership");
        } catch (HoldException e) {
            e.printStackTrace();
            fail();
        }

        try {
            cardPaymentManager.pay(100L);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        long returnedFunds = cardPaymentManager.returnFunds(100L);
        assertEquals(100L, returnedFunds);
    }

    @Test
    public void testGiftCardPaymentSuccessful() {
        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        doItYourselfStation.plugIn();
        doItYourselfStation.turnOn();
        CardPaymentManager cardPaymentManager = new CardPaymentManager(new PaymentController(doItYourselfStation), doItYourselfStation);

        Calendar calendarInstance = Calendar.getInstance();
        calendarInstance.set(2023, Calendar.FEBRUARY, 21, 23, 59, 59);
        GiftCardIssuer.CARD_ISSUER.addCardData("8417992603318976", "Isaacs", calendarInstance, "276", 10000);

        try {
            cardPaymentManager.placeHold("8417992603318976", 200L, "Gift Card");
        } catch (HoldException e) {
            e.printStackTrace();
            fail();
        }

        try {
            cardPaymentManager.pay(100L);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }


    @Test
    public void testCardPaymentWithNoHold() {
        DoItYourselfStation doItYourselfStation = new DoItYourselfStation();
        doItYourselfStation.plugIn();
        doItYourselfStation.turnOn();
        CardPaymentManager cardPaymentManager = new CardPaymentManager(new PaymentController(doItYourselfStation), doItYourselfStation);

        Calendar calendarInstance = Calendar.getInstance();
        calendarInstance.set(2023, Calendar.FEBRUARY, 21, 23, 59, 59);
        GiftCardIssuer.CARD_ISSUER.addCardData("8417992603318977", "Isaacs", calendarInstance, "276", 10000);

        try {
            cardPaymentManager.pay(100L);
            fail("Can not Pay with no Holds!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
