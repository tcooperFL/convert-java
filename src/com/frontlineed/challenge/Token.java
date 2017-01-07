package com.frontlineed.challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A distinct word with its associated nesting depth level
 */
class Token implements Comparable<Token> {

    /** Parsing regex picks out words and parens */
    private static Pattern pattern = Pattern.compile("\\(|\\)|\\w+");

    /** When indentation starts, according to the requirements */
    private static int INDENTATION_LEVEL = 2;

    private String name;
    private int depth;

    /** Constructor */
    Token(String name, int depth) {
        assert name != null : "Token name cannot be null";
        assert(depth >= 0) : String.format("Too many right parens before \"%s\"", name);
        this.name = name;
        this.depth = depth;
    }

    /**
     * Given a string, parse it into a list of depth-associated tokens.
     * @param line With matching parens
     * @return Result of parsing the line into leveled tokens.
     */
    static List<Token> parse(String line) {
        ArrayList<Token> result = new ArrayList<>();
        int depth = 0;
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String tok = matcher.group();
            if (tok.equals("(")) {
                depth++;
            } else if (tok.equals(")")) {
                depth--;
            } else {
                result.add(new Token(tok, depth));
            }
        }
        assert (depth == 0) : "Unclosed parentheses";

        return result;
    }

    /**
     * Override toString for simple depth-formatted value
     * @return natural formatted string form for a Token
     */
    public String toString() {
        String result = name;
        if (depth >= INDENTATION_LEVEL) {
            StringBuilder token = new StringBuilder();
            for (int i = depth; i >= INDENTATION_LEVEL; --i) {
                token.append("-");
            }
            token.append(" ");
            token.append(name);
            result = token.toString();
        }
        return result;
    }

    /**
     * Compares tokens by name, not nesting depth
     * @param other The other Token
     * @return Comparison based on their names.
     */
    public int compareTo(Token other) {
        return this.name.compareTo(other.name);
    }
}
