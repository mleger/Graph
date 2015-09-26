package graph.elements;

/**
 * This interface specifies the properties of a directed named edge that connects to two nodes.
 * 
 * <p>
 * NOTE: The edge's name does not necessarily need to be unique amongst the graph's edges.
 * </p>
 *
 * @author Mathieu LŽger
 * @since Mar 16, 2014
 *
 * @param <N> is the type of node to which the edge will connect, which can
 *        be of type Node or any of its subclasses
 */
public interface NamedEdge<N extends Node> extends Edge<N> {

    /**
     * Sets the edge's name.
     *
     * @param name is the name of the edge.
     * 
     * @throws IllegalArgumentException if name is null.
     */
    void setName(String name);

    /**
     * @return the name of the edge.
     */
    String getName();
}
