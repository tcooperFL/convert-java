/**
 * 
 */
package com.frontlineed.challenge.ast;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test string formats from various node constructions
 */
public class AstNodeTest {

	@Test
	/** Test that a root has an empty string form */
	public void testRoot() {
		AstNode parent = new AstNode();
		assertEquals("", parent.toString());
		assertEquals("", parent.toString(true));
	}
	
	@Test
	/** Test output with one child */
	public void testWithChild() {
		AstNode parent = new AstNode();
		new AstNode("child", parent);
		assertEquals("child\n", parent.toString());
		assertEquals("child\n", parent.toString(true));
	}

	@Test
	/** Test output with one child */
	public void testWithChildren() {
		AstNode parent = new AstNode();
		new AstNode("child2", parent);
		new AstNode("child1", parent);
		assertEquals("child2\nchild1\n", parent.toString());
		assertEquals("child1\nchild2\n", parent.toString(true));
	}

	@Test
	/** Test output with one child */
	public void testWithDepth() {
		AstNode parent = new AstNode();
		AstNode child2 = new AstNode("child2", parent);
		new AstNode("child1", parent);
		new AstNode("grandchild2", child2);
		new AstNode("grandchild1", child2);
		assertEquals("child2\n- grandchild2\n- grandchild1\nchild1\n", parent.toString());
		assertEquals("child1\nchild2\n- grandchild1\n- grandchild2\n", parent.toString(true));
	}

	@Test
	/** Test output with one child */
	public void testDeep() {
		AstNode parent = new AstNode();
		AstNode child2 = new AstNode("child2", parent);
		new AstNode("child1", parent);
		AstNode grandchild2 = new AstNode("grandchild2", child2);
		new AstNode("grandchild1", child2);
		new AstNode("great", grandchild2);
		assertEquals("child2\n- grandchild2\n-- great\n- grandchild1\nchild1\n", parent.toString());
		assertEquals("child1\nchild2\n- grandchild1\n- grandchild2\n-- great\n", parent.toString(true));
	}
}
