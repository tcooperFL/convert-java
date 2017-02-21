/**
 * 
 */
package com.frontlineed.challenge.ast;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testing the parser
 */
public class AstFactoryTest {

	/**
	 * Test default input for {@link com.frontlineed.challenge.ast.AstFactory#create(java.lang.String)}.
	 */
	@Test
	public void testDefaultCase() {
		String input = "(id,created,employee(id,firstname,employeeType(id), lastname),location)";
		String expected = "id\ncreated\nemployee\n- id\n- firstname\n- employeeType\n-- id\n- lastname\nlocation\n";
		String sorted = "created\nemployee\n- employeeType\n-- id\n- firstname\n- id\n- lastname\nid\nlocation\n";
		testParse(input, expected, sorted);
	}
	
	/**
	 * Test for input missing surrounding parens {@link com.frontlineed.challenge.ast.AstFactory#create(java.lang.String)}.
	 */
	@Test
	public void testMissingParens() {
		String input = "id,created,employee(id,firstname,employeeType(id), lastname),location";
		testException(input, "Missing outer parens");
	}
	
	/**
	 * Test with insufficient close parens {@link com.frontlineed.challenge.ast.AstFactory#create(java.lang.String)}.
	 */
	@Test
	public void testMismatchedParens() {
		String input = "(foo (bar)";
		testException(input, "Unclosed parentheses");
	}
	
	/**
	 * Test with too many close parens {@link com.frontlineed.challenge.ast.AstFactory#create(java.lang.String)}.
	 */
	@Test
	public void testIllegalNesting() {
		String input = "(foo ((baz) bar), baz(bif)))";
		testException(input, "Too many close parentheses");
	}
	
	/**
	 * Test when too many close parens in the middle {@link com.frontlineed.challenge.ast.AstFactory#create(java.lang.String)}.
	 */ 
	@Test
	public void testOverclosedInside() {
		String input = "(foo (baz, bar))), (foo(bar(baz(bif)))";
		testException(input, "Too many close parentheses");
	}
	
	/**
	 * Test deeply nested {@link com.frontlineed.challenge.ast.AstFactory#create(java.lang.String)}.
	 */
	@Test
	public void testDeepNesting() {
		String input = "(foo (bar (deep (nesting (stuff (goes, here(period)), more)))))";
		String expected = "foo\n- bar\n-- deep\n--- nesting\n---- stuff\n----- goes\n----- here\n------ period\n---- more\n";
		String sorted = "foo\n- bar\n-- deep\n--- nesting\n---- more\n---- stuff\n----- goes\n----- here\n------ period\n";
		testParse(input, expected, sorted);
	}
	
	// Helpers
	private void testParse(String input, String expected, String sorted) {
		assertEquals(expected, AstFactory.INSTANCE.create(input).toString());
		assertEquals(sorted, AstFactory.INSTANCE.create(input).toString(true));
	}
	
	private void testException(String input, String expectedException) {
		try {
			AstFactory.INSTANCE.create(input);
			fail("Expected " + expectedException);
		} catch (AssertionError e) {
			assertEquals(expectedException, e.getMessage());
		}
	}

}
