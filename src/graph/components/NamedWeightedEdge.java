package graph.components;

import graph.elements.NamedEdge;
import graph.elements.Node;
import graph.elements.WeightedEdge;

/**
 * This class creates a named weighted directed edge that starts at a source node
 * and ends at a target node.
 * 
 * @author Mathieu LŽger
 * @since Mar 16, 2014
 *
 * @param <N> type of node to which the edge will connect.
 */
public class NamedWeightedEdge<N extends Node> extends BasicEdge<N> implements NamedEdge<N>, WeightedEdge<N> {

    /**
     * Name of the edge
     */
    private String name;

    /**
     * Weight of the edge
     */
    private long weight;

    /**
     * Creates a named weighted edge that starts at the source node
     * and ends (points) at the destination node.
     * 
     * @param name is the name of the edge
     * @param sourceNode is the source node of the edge
     * @param targetNode is the target node of the edge
     * @param weight is the weight assigned to the edge
     * 
     * @throws IllegalArgumentException if name is null or empty, or if sourceNode or targetNode is null.
     */
    public NamedWeightedEdge(String name, N sourceNode, N targetNode, long weight) {
        super(sourceNode, targetNode);

        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name provided is null or empty");
        }
        this.name = name;
        this.weight = weight;
    }

    /**
     * Creates a named weighted edge with a default weight specified
     * by the variable WeightedEdge.DEFAULT_EDGE_WEIGHT. The edge
     * starts at the source node and ends (points) at the destination node.
     * 
     * @param name is the name of the edge
     * @param sourceNode is the source node of the edge
     * @param targetNode is the target node of the edge
     * 
     * @throws IllegalArgumentException if name is null or empty, or if sourceNode or targetNode is null.
     */
    public NamedWeightedEdge(String name, N sourceNode, N targetNode) {
        super(sourceNode, targetNode);

        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name provided is null or empty");
        }
        this.name = name;
        this.weight = DEFAULT_EDGE_WEIGHT;
    }

    @Override
    public void setWeight(long weight) {
        this.weight = weight;
    }

    @Override
    public long getWeight() {
        return weight;
    }


    @Override
    public void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name provided is null or empty");
        }

        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return "Edge[Name: " + name + ", Nodes: " + getSourceNode() + " -> " + getTargetNode() + ", Weight: " + weight + "]";
    }
}
