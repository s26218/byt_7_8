package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		assertEquals((Integer) 10000, SEK100.getAmount());
		assertEquals((Integer) 1000, EUR10.getAmount());
		assertEquals((Integer) 20000, SEK200.getAmount());
		assertEquals((Integer) 2000, EUR20.getAmount());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SEK100.getCurrency());
		assertEquals(EUR, EUR10.getCurrency());
		assertEquals(SEK, SEK200.getCurrency());
		assertEquals(EUR, EUR20.getCurrency());
	}

	@Test
	public void testToString() {
		assertEquals("1000 EUR", EUR10.toString());
	}

	@Test
	public void testGlobalValue() {
		assertEquals(9_000, SEK100.universalValue() + SEK200.universalValue() + EUR10.universalValue() + EUR20.universalValue());
	}

	@Test
	public void testEqualsMoney() {
		assertTrue("Their amount in universal currency should be the same", SEK100.equals(EUR10));
		assertTrue("Their amount in universal currency should be the same", SEK200.equals(EUR20));
	}

	@Test
	public void testAdd() {
		assertEquals((Integer) 20_000, SEK100.add(EUR10).getAmount());
		assertEquals((Integer) 40_000, SEK200.add(EUR20).getAmount());
	}

	@Test
	public void testSub() {
		assertEquals((Integer) 0, SEK100.sub(EUR10).getAmount());
		assertEquals((Integer) 0, SEK200.sub(EUR20).getAmount());
	}

	@Test
	public void testIsZero() {
		assertTrue("The amount of money should be zero (0)", SEK0.isZero());
		assertTrue("The amount of money should be zero (0)", EUR0.isZero());
	}

	@Test
	public void testNegate() {
		assertEquals(SEKn100.getAmount(), SEK100.negate().getAmount());
		assertEquals(SEKn100.toString(), SEK100.negate().toString());
	}

	@Test
	public void testCompareTo() {
		assertEquals(0, SEK100.compareTo(EUR10));
		assertEquals(0, SEK200.compareTo(EUR20));
		assertTrue(SEK100.compareTo(EUR20) < 0);
		assertTrue(SEK200.compareTo(EUR10) > 0);
	}
}
