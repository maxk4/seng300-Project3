package com.diy.software.test;
import ca.powerutility.PowerGrid;
import com.diy.hardware.DoItYourselfStation;
import com.unitedbankingservices.DisabledException;
import com.unitedbankingservices.Sink;
import com.unitedbankingservices.TooMuchCashException;
import com.unitedbankingservices.banknote.Banknote;
import com.unitedbankingservices.banknote.BanknoteValidator;
import com.unitedbankingservices.coin.Coin;
import com.unitedbankingservices.coin.CoinValidator;
import org.junit.Before;
import org.junit.Test;
import payment.CashPaymentManager;
import payment.PaymentController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

import static org.junit.Assert.*;

public class PayByCashTest {
    private DoItYourselfStation checkoutStation;

    @Before
    public void setup() throws TooMuchCashException {

        // configure denominations
        int[] banknoteDenominations = {5000, 2000, 1000, 500};
        BigDecimal[] coinDenominations = {new BigDecimal(200), new BigDecimal(100), new BigDecimal(25), new BigDecimal(10), new BigDecimal(5)};
        DoItYourselfStation.configureBanknoteDenominations(banknoteDenominations);
        DoItYourselfStation.configureCoinDenominations(coinDenominations);

        // setup checkout station
        checkoutStation = new DoItYourselfStation();
        PowerGrid.engageUninterruptiblePowerSource();
        PowerGrid.instance().forcePowerRestore();
        checkoutStation.plugIn();
        checkoutStation.turnOn();

        // setup banknote and coin dispensers
        for (int denomination : banknoteDenominations) {
            checkoutStation.banknoteDispensers.get(denomination).load(new Banknote(Currency.getInstance(Locale.CANADA), denomination));
            checkoutStation.banknoteDispensers.get(denomination).sink = new Sink<>() {
                @Override
                public void receive(Banknote cash) throws TooMuchCashException, DisabledException {
                }

                @Override
                public boolean hasSpace() {
                    return true;
                }
            };
        }

        for (BigDecimal denomination : coinDenominations) {
            checkoutStation.coinDispensers.get(denomination).load(new Coin(Currency.getInstance(Locale.CANADA), denomination));
        }
    }

    @Test
    public void testCashPaymentSucceed() {
        PaymentController paymentController = new PaymentController(checkoutStation);
//        paymentController.register(new PaymentListener() {
//            @Override
//            public void cardPaymentSucceeded() {
//
//            }
//
//            @Override
//            public void cashInserted() {
//
//            }
//        })
        CashPaymentManager cashPaymentManager = new CashPaymentManager(paymentController, checkoutStation);

        int[] banknoteDenominations = {5000, 2000, 1000, 500};

        cashPaymentManager.validBanknoteDetected(new BanknoteValidator(Currency.getInstance(Locale.CANADA), banknoteDenominations), Currency.getInstance(Locale.CANADA), 500L);
        cashPaymentManager.validCoinDetected(
                new CoinValidator(
                        Currency.getInstance(Locale.CANADA), Arrays.asList(new BigDecimal(200), new BigDecimal(100), new BigDecimal(25), new BigDecimal(10), new BigDecimal(5))),
                new BigDecimal(100));

        assertEquals(300L, cashPaymentManager.pay(300L));
    }

    @Test
    public void testCashPaymentOverTheFund() {
        PaymentController paymentController = new PaymentController(checkoutStation);
//        paymentController.register(new PaymentListener() {
//            @Override
//            public void cardPaymentSucceeded() {
//
//            }
//
//            @Override
//            public void cashInserted() {
//
//            }
//        })
        CashPaymentManager cashPaymentManager = new CashPaymentManager(paymentController, checkoutStation);

        int[] banknoteDenominations = {5000, 2000, 1000, 500};

        cashPaymentManager.validBanknoteDetected(new BanknoteValidator(Currency.getInstance(Locale.CANADA), banknoteDenominations), Currency.getInstance(Locale.CANADA), 500L);
        cashPaymentManager.validCoinDetected(
                new CoinValidator(
                        Currency.getInstance(Locale.CANADA), Arrays.asList(new BigDecimal(200), new BigDecimal(100), new BigDecimal(25), new BigDecimal(10), new BigDecimal(5))),
                new BigDecimal(100));
//        cashPaymentManager.validCoinDetected(new CoinValidator(Currency.getInstance(Locale.CANADA), Arrays.asList(200L, 100L, 25L, 10L, 5L)), 100L);

        assertEquals(600L, cashPaymentManager.pay(600L));
    }

    @Test
    public void testCashPaymentReturnFundSucceed() {
        PaymentController paymentController = new PaymentController(checkoutStation);
//        paymentController.register(new PaymentListener() {
//            @Override
//            public void cardPaymentSucceeded() {
//
//            }
//
//            @Override
//            public void cashInserted() {
//
//            }
//        })
        CashPaymentManager cashPaymentManager = new CashPaymentManager(paymentController, checkoutStation);

        int[] banknoteDenominations = {5000, 2000, 1000, 500};

        cashPaymentManager.validBanknoteDetected(new BanknoteValidator(Currency.getInstance(Locale.CANADA), banknoteDenominations), Currency.getInstance(Locale.CANADA), 500L);
//        cashPaymentManager.validCoinDetected(new CoinValidator(Currency.getInstance(Locale.CANADA), Arrays.asList(200L, 100L, 25L, 10L, 5L)), 100L);
        cashPaymentManager.validCoinDetected(
                new CoinValidator(
                        Currency.getInstance(Locale.CANADA), Arrays.asList(new BigDecimal(200), new BigDecimal(100), new BigDecimal(25), new BigDecimal(10), new BigDecimal(5))),
                new BigDecimal(100));
        assertEquals(200L, cashPaymentManager.pay(200L));

        try {
            assertEquals(340L, cashPaymentManager.returnFunds(400L));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCashPaymentReturnFundWithExceededAmount() {
        PaymentController paymentController = new PaymentController(checkoutStation);
//        paymentController.register(new PaymentListener() {
//            @Override
//            public void cardPaymentSucceeded() {
//
//            }
//
//            @Override
//            public void cashInserted() {
//
//            }
//        })
        CashPaymentManager cashPaymentManager = new CashPaymentManager(paymentController, checkoutStation);

        int[] banknoteDenominations = {5000, 2000, 1000, 500};

        cashPaymentManager.validBanknoteDetected(new BanknoteValidator(Currency.getInstance(Locale.CANADA), banknoteDenominations), Currency.getInstance(Locale.CANADA), 500L);
//        cashPaymentManager.validCoinDetected(new CoinValidator(Currency.getInstance(Locale.CANADA), Arrays.asList(200L, 100L, 25L, 10L, 5L)), 100L);
        cashPaymentManager.validCoinDetected(
                new CoinValidator(
                        Currency.getInstance(Locale.CANADA), Arrays.asList(new BigDecimal(200), new BigDecimal(100), new BigDecimal(25), new BigDecimal(10), new BigDecimal(5))),
                new BigDecimal(100));
        assertEquals(300L, cashPaymentManager.pay(300L));
        try {
            assertEquals(500L, cashPaymentManager.returnFunds(500L));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetValidBanknoteCount() {
        PaymentController paymentController = new PaymentController(checkoutStation);
//        paymentController.register(new PaymentListener() {
//            @Override
//            public void cardPaymentSucceeded() {
//
//            }
//
//            @Override
//            public void cashInserted() {
//
//            }
//        })
        CashPaymentManager cashPaymentManager = new CashPaymentManager(paymentController, checkoutStation);

        int[] banknoteDenominations = {5000, 2000, 1000, 500};

        cashPaymentManager.validBanknoteDetected(new BanknoteValidator(Currency.getInstance(Locale.CANADA), banknoteDenominations), Currency.getInstance(Locale.CANADA), 500L);
        cashPaymentManager.validBanknoteDetected(new BanknoteValidator(Currency.getInstance(Locale.CANADA), banknoteDenominations), Currency.getInstance(Locale.CANADA), 500L);
        cashPaymentManager.validBanknoteDetected(new BanknoteValidator(Currency.getInstance(Locale.CANADA), banknoteDenominations), Currency.getInstance(Locale.CANADA), 500L);
//        cashPaymentManager.validCoinDetected(new CoinValidator(Currency.getInstance(Locale.CANADA), Arrays.asList(200L, 100L, 25L, 10L, 5L)), 100L);

        assertEquals(3,cashPaymentManager.getValidBanknoteCount());
    }

    @Test
    public void testGetValidCoinDetected() {
        PaymentController paymentController = new PaymentController(checkoutStation);
//        paymentController.register(new PaymentListener() {
//            @Override
//            public void cardPaymentSucceeded() {
//
//            }
//
//            @Override
//            public void cashInserted() {
//
//            }
//        })
        CashPaymentManager cashPaymentManager = new CashPaymentManager(paymentController, checkoutStation);
        cashPaymentManager.validCoinDetected(
                new CoinValidator(
                        Currency.getInstance(Locale.CANADA), Arrays.asList(new BigDecimal(200), new BigDecimal(100), new BigDecimal(25), new BigDecimal(10), new BigDecimal(5))),
                new BigDecimal(100));
        cashPaymentManager.validCoinDetected(
                new CoinValidator(
                        Currency.getInstance(Locale.CANADA), Arrays.asList(new BigDecimal(200), new BigDecimal(100), new BigDecimal(25), new BigDecimal(10), new BigDecimal(5))),
                new BigDecimal(100));
        cashPaymentManager.validCoinDetected(
                new CoinValidator(
                        Currency.getInstance(Locale.CANADA), Arrays.asList(new BigDecimal(200), new BigDecimal(100), new BigDecimal(25), new BigDecimal(10), new BigDecimal(5))),
                new BigDecimal(100));
        cashPaymentManager.validCoinDetected(
                new CoinValidator(
                        Currency.getInstance(Locale.CANADA), Arrays.asList(new BigDecimal(200), new BigDecimal(100), new BigDecimal(25), new BigDecimal(10), new BigDecimal(5))),
                new BigDecimal(100));
//        cashPaymentManager.validCoinDetected(new CoinValidator(Currency.getInstance(Locale.CANADA), Arrays.asList(200L, 100L, 25L, 10L, 5L)), 100L);
//        cashPaymentManager.validCoinDetected(new CoinValidator(Currency.getInstance(Locale.CANADA), Arrays.asList(200L, 100L, 25L, 10L, 5L)), 100L);
//        cashPaymentManager.validCoinDetected(new CoinValidator(Currency.getInstance(Locale.CANADA), Arrays.asList(200L, 100L, 25L, 10L, 5L)), 100L);
//        cashPaymentManager.validCoinDetected(new CoinValidator(Currency.getInstance(Locale.CANADA), Arrays.asList(200L, 100L, 25L, 10L, 5L)), 100L);

        assertEquals(4, cashPaymentManager.getValidCoinCount());
    }
}
