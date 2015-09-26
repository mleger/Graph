package graph.elements;

/**
 * This interface specifies the properties of a Node that can be connected to edges in a graph.
 * 
 * NOTE: The node's name does not necessarily need to be unique amongst the graph's nodes.
 * 
 * @author Mathieu LŽger
 * @since Mar 16, 2014
 */
public interface Node {

    /**
     * Sets the node's name.
     *
     * @param name is the name of the node.
     * 
     * @throws IllegalArgumentException if name is null.
     */
    void setName(String name);

    /**
     * @return the name of the node.
     */
    String getName();
}
