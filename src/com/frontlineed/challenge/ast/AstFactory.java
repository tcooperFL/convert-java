package com.frontlineed.challenge.ast;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A factory for creating abstract syntax trees.
 */
public class AstFactory {

    // All for altering this for testing
    public static AstFactory INSTANCE = new AstFactory();

    /** Parsing regex picks out words and parens */
    private static final Pattern pattern = Pattern.compile("\\(|\\)|[^()\\t\\n,]+");
    private static final String LEFT_PAREN = "(";
    private static final String RIGHT_PAREN = ")";

    /**
     * Given a string, parse it into a list of depth-associated tokens.
     * @param line With matching parens
     * @return Root node of the equivalent syntax tree
     * @throws assertion exceptions on mismatch parens
     */
    public AstNode create(String line) {
        AstNode root = new AstNode();

        // Use a stack to track the nesting of the ancestor nodes as we walk the nested syntax
        Stack<AstNode> ancestors = new Stack<>();
        AstNode lastNode = root;

        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String name = matcher.group().trim();
            if (name.equals(LEFT_PAREN)) {
                assert (lastNode != null) : "Syntax error on opening paren";
                ancestors.push(lastNode);
            } else if (name.equals(RIGHT_PAREN)) {
                assert (!ancestors.isEmpty()) : "Too many close parentheses";
                ancestors.pop();
                lastNode = null;
            } else {
                assert (!ancestors.isEmpty()) : "Missing outer parens";
                lastNode = new AstNode(name, ancestors.peek());
            }
        }

        // If all parens are closed, the stack will contain the root at the head again.
        assert (ancestors.isEmpty()) : "Unclosed parentheses";

        return root;
    }
}
