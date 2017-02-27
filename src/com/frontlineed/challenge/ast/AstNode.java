package com.frontlineed.challenge.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * A distinct word with its associated nesting depth level
 */
public class AstNode implements Comparable<AstNode> {

    /** When indented output starts, according to the requirements */
    private static final int INDENTATION_THRESHOLD = 2;

    private final String name;
    private final List<AstNode> children = new ArrayList<>();
    private final int depth;

    /**
     * Root constructor.
     */
    AstNode() {
        this.name = "";
        this.depth = 0;
    }

    /**
     * Construct a new child node.
     * @param name Name to give to the node
     * @param parent Node to be the parent
     */
    AstNode(String name, AstNode parent) {
        assert name != null : "AstNode name cannot be null";
        assert parent != null : "Child node must have a parent";
        this.name = name;
        this.depth = parent.depth + 1;
        parent.addChild(this);
    }

    /**
     * Add this node to my list of child nodes
     * @param child A node to be added to my children
     */
    private void addChild(AstNode child) {
        children.add(child);
    }

    /**
     * Override toString for simple depth-formatted value
     * @return natural formatted string form for a AstNode
     */
    @Override
    public String toString() {
        return toString(false);
    }

    /**
     * Produce a formatted string from this node and its descendants.
     * @param sorted If true, sort the child nodes at each level.
     * @return formatted string form with child nodes sorted
     */
    public String toString(boolean sorted) {
        StringBuilder result = new StringBuilder();
        buildString(result, sorted);
        return result.toString();
    }

    /**
     * Build onto the given string the format of this node and its children
     * based on its depth.
     * @param builder String builder to append to
     * @param sorted If true, sort the children before you recur
     */
    private void buildString(StringBuilder builder, boolean sorted) {
        if (depth > 0) {
            if (depth >= INDENTATION_THRESHOLD) {
                for (int i = depth; i >= INDENTATION_THRESHOLD; --i) {
                    builder.append("-");
                }
                builder.append(" ");
            }
            builder.append(name);
            builder.append("\n");
        }

        // Recursively build strings on children
        Stream<AstNode> childStream = children.stream();
        if (sorted) {
            childStream = childStream.sorted();
        }
        childStream.forEach(c -> c.buildString(builder, sorted));
    }

    /**
     * Compares tokens by name, not nesting depth
     * @param other The other AstNode
     * @return Comparison based on their names.
     */
    @Override
    public int compareTo(AstNode other) {
    	int result = 0;
    	
    	// Take into account either could have a null name.
    	if (this.name == null) {
    		if (other.name != null) {
    			result = -1;
    		}
    	} else if (other.name == null) {
    		result = 1;
    	} else {
    		result = this.name.compareTo(other.name);
    	}
        return result;
    }

}
