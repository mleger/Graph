package graph.components;

import graph.algorithms.AlgorithmUtilities;
import graph.elements.Node;
import graph.elements.WeightedEdge;
import graph.elements.WeightedGraph;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This class creates a weighted graph that contains nodes and weighted directed edges that
 * connect to two nodes. Loops are permitted, as well as multiple edges
 * connecting to the same nodes. Unconnected nodes are not permitted
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
public class BasicWeightedGraph<N extends Node, E extends WeightedEdge<? extends N>> extends BasicGraph<N, E> implements WeightedGraph<N, E> {

    /**
     * Creates an basic graph.
     */
    public BasicWeightedGraph() {
        super();
    }


    @Override
    public Set<E> getEdgesWithWeight(long weight) {

        Set<E> matchedEdges = new LinkedHashSet<E>();
        for(E edge : getAllEdges()){
            if(edge.getWeight() == weight) {
                matchedEdges.add(edge);
            }
        }
        return matchedEdges;
    }

    @Override
    public Set<E> getEdgesWithWeightAbove(long weight) {

        Set<E> matchedEdges = new LinkedHashSet<E>();
        for(E edge : getAllEdges()){
            if(edge.getWeight() > weight) {
                matchedEdges.add(edge);
            }
        }
        return matchedEdges;
    }

    @Override
    public Set<E> getEdgesWithWeightBelow(long weight) {

        Set<E> matchedEdges = new LinkedHashSet<E>();
        for(E edge : getAllEdges()){
            if(edge.getWeight() < weight) {
                matchedEdges.add(edge);
            }
        }
        return matchedEdges;
    }

    @Override
    public void resetEdgeWeights() {
        for(E edge : getAllEdges()){
            edge.setWeight(E.DEFAULT_EDGE_WEIGHT);
        }
    }

    @Override
    public String toString() {
        return AlgorithmUtilities.printEdgeSetToString("WeightedGraph", getAllEdges());
    }

}
