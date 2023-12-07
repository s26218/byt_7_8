package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;

	@Before
	public void setUp() throws Exception {

		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10_000_000, SEK));

		// all tests failed with NullPointerException here
		SweBank.deposit("Alice", new Money(1_000_000, SEK));

	}

	@Test
	public void testAddRemoveTimedPayment() {

		assertFalse(testAccount.timedPaymentExists("01"));
		testAccount.addTimedPayment("01", 1, 2, new Money(1_000, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("01"));
		testAccount.removeTimedPayment("01");
		assertFalse(testAccount.timedPaymentExists("01"));

	}

	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {

		assertFalse(testAccount.timedPaymentExists("01"));

		testAccount.addTimedPayment("01", 1, 2, new Money(10_000, SEK), SweBank, "Alice");

		assertEquals(10_000_000, testAccount.getBalance().getAmount(), 0);

		testAccount.tick();
		testAccount.tick();
		assertEquals(9_990_000, testAccount.getBalance().getAmount(), 0);
		assertEquals(1_010_000, SweBank.getBalance("Alice"));

		testAccount.tick();
		assertEquals(9_980_000, testAccount.getBalance().getAmount(), 0);
		assertEquals(1_020_000, SweBank.getBalance("Alice"));

	}

	@Test
	public void testAddWithdraw() {

		testAccount.deposit(new Money(10_000, SEK));
		assertEquals(10_010_000, testAccount.getBalance().getAmount(), 0);
		testAccount.withdraw(new Money(10_000, SEK));
		assertEquals(10_000_000, testAccount.getBalance().getAmount(), 0);

	}

	@Test
	public void testGetBalance() {

		assertEquals(10_000_000, testAccount.getBalance().getAmount(), 0);

	}
}
