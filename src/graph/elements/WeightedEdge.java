package graph.elements;

/**
 * This interface specifies the properties of a directed edge with a that
 * connects to two nodes and has a weight specified by a long.
 * 
 * @author Mathieu LŽger
 * @since Mar 16, 2014
 *
 * @param <N> is the type of node to which the edge will connect, which can
 *        be of type Node or any of its subclasses
 */
public interface WeightedEdge<N extends Node> extends Edge<N> {

    /**
     * Default weight assigned to an edge if no weight is specified.
     */
    long DEFAULT_EDGE_WEIGHT = 0;

    /**
     * Sets the edge's weight.
     *
     * @param weight to be assigned to the edge.
     */
    void setWeight(long weight);

    /**
     * @return the edge's weight.
     */
    long getWeight();
}
