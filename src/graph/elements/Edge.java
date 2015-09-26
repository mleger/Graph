package graph.elements;

/**
 * This interface specifies the properties of a basic graph edge that connects to two nodes.
 * The edge is directed and starts at the source node and ends at the
 * target node.
 * 
 * @author Mathieu LŽger
 * @since Mar 16, 2014
 *
 * @param <N> is the type of node to which the edge will connect, which can
 *        be of type Node or any of its subclasses
 */
public interface Edge<N extends Node> {

    /**
     * @return the edge's source node.
     */
    N getSourceNode();

    /**
     * Sets the edge's source node.
     * 
     * @param sourceNode source node to be set.
     * 
     * @throws IllegalArgumentException if sourceNode is null.
     */
    void setSourceNode(N sourceNode);

    /**
     * @return the edge's target node.
     */
    N getTargetNode();

    /**
     * Sets the edge's target node.
     * 
     * @param targetNode target node to be set.
     * 
     * @throws IllegalArgumentException if targetNode is null.
     */
    void setTargetNode(N targetNode);

    /**
     * Determines if the edge is connected to a specified node.
     * 
     * @param node to which the edge may be connected
     * 
     * @return boolean indicating if the edge is connected to the node
     * 
     * @throws IllegalArgumentException if node is null.
     */
    boolean connectsToNode(N node);

}