package graph.elements;

import java.util.Set;

/**
 * This interface specifies the properties of a graph that contains nodes and directed
 * weighted edges that connect to two nodes.
 * 
 * @author Mathieu LŽger
 * @since Mar 16, 2014
 *
 * @param <N> is the type of node to which the graph's edges will connect.
 *        they can be of type Node or any of its subclasses
 * @param <E> is the type of edges contained in the graph. They can be of
 *        type WeightedEdge or any of its subclasses that connect to nodes of type
 *        N or any of its subclasses.
 */
public interface WeightedGraph<N extends Node, E extends WeightedEdge<? extends N>> extends Graph<N, E> {

    /**
     * Returns a set of edges who's weight is equal to the requested weight.
     * 
     * @param weight is the weight to be matched
     * 
     * @return Set of edges with a weight equal to the requested value
     */
    Set<E> getEdgesWithWeight(long weight);

    /**
     * Returns a set of edges who's weight is above the specified value.
     * 
     * @param weight weight above which the match edges weight must be.
     * 
     * @return Set of edges with a weight above the specified value.
     */
    Set<E> getEdgesWithWeightAbove(long weight);

    /**
     * Returns a set of edges who's weight is below the specified value.
     * 
     * @param weight weight below which the match edges weight must be.
     * 
     * @return Set of edges with a weight below the specified value.
     */
    Set<E> getEdgesWithWeightBelow(long weight);

    /**
     * Resets the weights of all the edges in the graph to the
     * WeightedEdge.DEFAULT_EDGE_WEIGHT value.
     */
    void resetEdgeWeights();
}
