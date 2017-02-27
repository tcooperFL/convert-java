/**
 * Code Challenge: Parse nested string, printing in original and sorted order
 *
 * Author: Tom Cooper
 * Date: 2017-01-06
 */
package com.frontlineed.challenge;

import com.frontlineed.challenge.ast.AstFactory;
import com.frontlineed.challenge.ast.AstNode;

public final class Main {

    /** The given challenge string */
    private static final String DEFAULT_INPUT =
            "(id,created,employee(id,firstname,employeeType(id), lastname),location)";
    /**
     * Private constructor prevents instantiation
     */
    private Main() {}

    /**
     * Main program. Demonstrates parsing of the input string, or default challenge string.
     * @param args - optional alternate string
     */
    public static void main(String[] args) {
        // Allow user to provide other test cases on the command line.
        if (args.length > 1) {
            System.err.println("Please provide a single quoted string argument.");
            System.exit(1);

        } else {
            // Default to the challenge problem
            String string = (args.length > 0) ? args[0] : DEFAULT_INPUT;

            try {
                convert(string);
            } catch (AssertionError error) {
                System.err.printf("Conversion failed: %s%n", error.getMessage());
                System.exit(1);
            }
        }
    }

    /**
     * Demonstrate the conversion of the line into depth-indicated
     * lines in original or sorted order by token name.
     * @param line input string to parse
     */
    private static void convert(String line) {
        AstNode root = AstFactory.INSTANCE.create(line);

        // Original problem
        System.out.println("Original order:");
        System.out.println(root.toString());

        // Bonus solution
        System.out.println("\nSorted order:");
        System.out.println(root.toString(true));
    }

}
