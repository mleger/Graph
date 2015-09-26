package graph.components;

import graph.elements.Node;
import graph.elements.WeightedEdge;

/**
 * This class creates a weighted directed edge that starts at a source node
 * and ends at a target node.
 * 
 * @author Mathieu LŽger
 * @since Mar 16, 2014
 *
 * @param <N> type of node to which the edge will connect.
 */
public class BasicWeightedEdge<N extends Node> extends BasicEdge<N> implements WeightedEdge<N> {

    /**
     * Weight assigned to the edge
     */
    private long weight;

    /**
     * Creates a weighted edge that starts at the source node
     * and ends (points) at the destination node.
     * 
     * @param sourceNode is the source node of the edge
     * @param targetNode is the target node of the edge
     * @param weight is the weight assigned to the edge
     * 
     * @throws IllegalArgumentException if sourceNode or targetNode is null.
     */
    public BasicWeightedEdge(N sourceNode, N targetNode, long weight){
        super(sourceNode, targetNode);
        this.weight = weight;
    }

    /**
     * Creates a weighted edge with a default weight specified
     * by the variable WeightedEdge.DEFAULT_EDGE_WEIGHT. The edge
     * starts at the source node and ends (points) at the destination node.
     * 
     * @param sourceNode is the source node of the edge
     * @param targetNode is the target node of the edge
     * 
     * @throws IllegalArgumentException if sourceNode or targetNode is null.
     */
    public BasicWeightedEdge(N sourceNode, N targetNode){
        this(sourceNode, targetNode, DEFAULT_EDGE_WEIGHT);
    }

    @Override
    public long getWeight() {
        return weight;
    }

    @Override
    public void setWeight(long weight) {
        this.weight = weight;
    }

    @Override
    public String toString(){
        return "Edge[Nodes: " + getSourceNode() + " -> " + getTargetNode() + ", Weight: " + weight + "]";
    }
}
