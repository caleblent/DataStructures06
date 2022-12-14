package balancedSymbols;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnitTests {
	
	@Test
	void testValidFiles() {
		BalancedSymbolChecker BSC = new BalancedSymbolChecker("validFiles/valid1.txt");
		assertTrue(BSC.verifyIfProperlyNested());
		BSC = new BalancedSymbolChecker("validFiles/valid2.txt");
		assertTrue(BSC.verifyIfProperlyNested());
		BSC = new BalancedSymbolChecker("validFiles/valid3.txt");
		assertTrue(BSC.verifyIfProperlyNested());
		BSC = new BalancedSymbolChecker("validFiles/valid4.txt");
		assertTrue(BSC.verifyIfProperlyNested());
	}
	
	@Test
	void testInvalidFiles() {
		BalancedSymbolChecker BSC = new BalancedSymbolChecker("invalidFiles/invalid1.txt");
		assertFalse(BSC.verifyIfProperlyNested());
		System.out.println(BSC.verifyIfProperlyNested());
		BSC = new BalancedSymbolChecker("invalidFiles/invalid2.txt");
		System.out.println(BSC.verifyIfProperlyNested());
		assertFalse(BSC.verifyIfProperlyNested());
		BSC = new BalancedSymbolChecker("invalidFiles/invalid3.txt");
		assertFalse(BSC.verifyIfProperlyNested());
		BSC = new BalancedSymbolChecker("invalidFiles/invalid4.txt");
		assertFalse(BSC.verifyIfProperlyNested());
	}
	
	@Test
	void testCommentsRemoval() {
		BalancedSymbolChecker Comments = new BalancedSymbolChecker("comments/Comments.java");
		BalancedSymbolChecker NoComments = new BalancedSymbolChecker("comments/NoComments.java");
		assertEquals(Comments.removeComments(), NoComments.keepComments(), "equal?");
	}

}
