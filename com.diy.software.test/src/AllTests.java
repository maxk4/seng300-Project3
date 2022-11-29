import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddItemByScanningTests.class, CashPaymentTest.class,
		PayByCreditCardTest.class, PayByDebitCardTest.class, MembershipNumberTest.class, LowInkPaperTest.class })
public class AllTests {

}
