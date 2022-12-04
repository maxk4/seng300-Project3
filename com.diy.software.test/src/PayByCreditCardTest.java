import java.io.IOException;
import java.util.Calendar;

import com.diy.hardware.AttendantStation;
import org.junit.Before;
import org.junit.Test;
import com.diy.hardware.DoItYourselfStation;
import com.diy.simulation.Customer;
import com.diy.hardware.external.CardIssuer;
import ca.ucalgary.seng300.simulation.SimulationException;
import payment.CardPaymentManager;
import payment.HoldException;
import payment.PaymentController;
import payment.PaymentManager;
import ui.AttendantUI;
import ui.CustomerUI;

import com.jimmyselectronics.opeechee.Card;
import com.jimmyselectronics.opeechee.CardReader;
import util.Bank;

import static org.junit.Assert.*;

public class PayByCreditCardTest {

    Card creditCard;
    Card notIssuedCredit;
    AttendantUI attendant;
    Customer customer;
    AttendantStation astation;
    DoItYourselfStation station;
    CustomerUI ui;
    //	PayWithCardListener pwcl = new PayWithCardListener(ui);
    CardIssuer cardIssuer;
    CardReader reader;
    CardReaderListenerStub listener;


    /**
     * Initializes credit card
     */
    @Before
    public void setup() {
        creditCard = new Card("credit", "8417992603318971", "Isaacs", "276", "0000", true, true);
        listener = new CardReaderListenerStub();

    }

    //Checks that null credit card throws exception
    @Test
    public void NullCreditCard() {
        try {
            creditCard = new Card(null, "8417992603318971", "Isaacs", "276", "0000", true, true);
            fail("Expected exception: Credit Card is null.");
        } catch (Exception e) {
            assertTrue(e instanceof SimulationException);
        }

    }

    //Checks that a valid credit card can be tapped
    @Test
    public void TapCreditCard() {
        creditCard = new Card("credit", "8417992603318971", "Isaacs", "276", "0000", true, true);
        assertTrue(creditCard.isTapEnabled);
    }

    //Checks that a valid credit card can be inserted
    @Test
    public void InsertCreditCard() {
        creditCard = new Card("credit", "8417992603318971", "Isaacs", "276", "0000", true, true);
        // Try inserting the card into a card reader stub
        reader = new CardReader();
        reader.plugIn();
        reader.turnOn();
        reader.enable();
        reader.register(listener);
        try {
            reader.insert(creditCard, "0000");
            assertTrue(listener.inserted);
        } catch (IOException e) {
            fail("Cound not insert card");
        }
    }

    //Checks that a valid credit card can be swiped
    @Test
    public void SwipeCreditCard() {
        creditCard = new Card("credit", "8417992603318971", "Isaacs", "276", "0000", true, true);
        //assertTrue(creditCard);
        // Is this necessary?
    }

    //Checks that a credit card with incorrect pin throws exception when inserted
    @Test
    public void InvalidPinCreditCard() {
        try {
            creditCard = new Card(null, "8417992603318971", "Isaacs", "276", "1111", true, true);
            fail("Invalid Pin");
        } catch (Exception e) {
            assertTrue("Invalid Pin", e instanceof SimulationException);
        }
    }

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
            fail("Should not pay with 500 because the hold amount is 200");
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
            fail("Should not pay with 500 because the hold amount is 200");
        }
        long returnedFunds = cardPaymentManager.returnFunds(100L);
        assertEquals(100L, returnedFunds);
    }
}

