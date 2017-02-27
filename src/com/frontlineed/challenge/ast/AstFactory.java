package com.frontlineed.challenge.ast;

import java.util.Deque;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A factory for creating abstract syntax trees.
 */
public final class AstFactory {

    // All for altering this for testing
    public static AstFactory INSTANCE = new AstFactory();

    /** Parsing regex picks out words and parens */
    private static final Pattern TOKEN_PATTERN = Pattern.compile("\\(|\\)|[^()\\t\\n,]+");
    private static final String LEFT_PAREN = "(";
    private static final String RIGHT_PAREN = ")";
    
    /** 
     * Prevent instantiation outside this class.
     */
    private AstFactory() {}

    /**
     * Given a string, parse it into a list of depth-associated tokens.
     * @param line With matching parens
     * @return Root node of the equivalent syntax tree
     * @throws assertion exceptions on mismatch parens
     */
    public AstNode create(String line) {
        AstNode root = new AstNode();

        // Use a stack to track the nesting of the ancestor nodes as we walk the nested syntax
        Deque<AstNode> ancestors = new LinkedList<>();
        AstNode lastNode = root;

        Matcher matcher = TOKEN_PATTERN.matcher(line);
        while (matcher.find()) {
            String name = matcher.group().trim();
            if (name.equals(LEFT_PAREN)) {
                assert lastNode != null : "Syntax error on opening paren";
                ancestors.push(lastNode);
            } else if (name.equals(RIGHT_PAREN)) {
                assert !ancestors.isEmpty() : "Too many close parentheses";
                ancestors.pop();
                lastNode = null;
            } else {
                assert !ancestors.isEmpty() : "Missing outer parens";
                lastNode = new AstNode(name, ancestors.peek());
            }
        }

        // If all parens are closed, the stack will contain the root at the head again.
        assert ancestors.isEmpty() : "Unclosed parentheses";

        return root;
    }
}
