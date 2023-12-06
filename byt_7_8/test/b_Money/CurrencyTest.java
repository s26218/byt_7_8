package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR, PLN, RUB;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		PLN = new Currency("PLN", 0.25);
		RUB = new Currency("RUB", 0.011);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}
	
	@Test
	public void testGetRate() {
		assertEquals((Double) 0.15, SEK.getRate());
		assertEquals((Double) 0.20, DKK.getRate());
		assertEquals((Double) 1.5, EUR.getRate());
	}
	
	@Test
	public void testSetRate() {
		assertEquals((Double) 0.15, SEK.getRate());
		SEK.setRate(.16);
		assertEquals((Double) 0.16, SEK.getRate());
	}
	
	@Test
	public void testGlobalValue() {
		// PLN to USD
		assertEquals((Integer) 100, PLN.universalValue(400));
		assertEquals((Integer) 137, RUB.universalValue(12_500));
	}
	
	@Test
	public void testValueInThisCurrency() {
		assertEquals(548, PLN.valueInThisCurrency(12_500, RUB), 1);
	}

}
