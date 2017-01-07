/**
 * Code Challenge: Parse nested string, printing in original and sorted order
 *
 * Author: Tom Cooper
 * Date: 2017-01-06
 */
package com.frontlineed.challenge;

import java.util.List;

public class Main {

    /** The given challenge string */
    private static String DEFAULT_INPUT =
            "(id,created,employee(id,firstname,employeeType(id), lastname),location)";

    /**
     * Main program. Demonstrates parsing of the input string, or default challenge string.
     * @param args - optional alternate string
     */
    public static void main(String[] args) {
        // Allow user to provide other test cases on the command line.
        if (args.length > 1) {
            System.err.printf("Please provide a single quoted string argument.\n", args[0]);
            System.exit(1);

        } else {
            // Default to the challenge problem
            String string = (args.length > 0) ? args[0] : DEFAULT_INPUT;

            try {
                convert(string);
            } catch (AssertionError error) {
                System.err.printf("Conversion failed: %s\n", error.getMessage());
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
        List<Token> result = Token.parse(line);

        // Original problem
        System.out.println("Original order:");
        result.forEach(System.out::println);

        // Bonus solution
        System.out.println("\nSorted order:");
        result.stream().sorted().forEach(System.out::println);
    }


}
