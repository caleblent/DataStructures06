package balancedSymbols;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnitTests {

	@Test
	void test() {
		assertTrue(true, "This thing is true!");
		assertFalse(false, "This thing is false!");
		assertEquals(1, 1, "1 does in fact equal 1");
	}
	
	@Test
	void testValidFiles() {
		BalancedSymbolChecker BSC = new BalancedSymbolChecker("ValidClass.java");
		
		assertTrue(BSC.verifyIfProperlyNested(), "Ha!");
	}

}
