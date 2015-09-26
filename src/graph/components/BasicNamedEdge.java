package graph.components;

import graph.elements.NamedEdge;
import graph.elements.Node;

/**
 * This class creates a named directed edge that starts at a source node
 * and ends at a target node.
 * 
 * @author Mathieu LŽger
 * @since Mar 16, 2014
 *
 * @param <N> type of node to which the edge will connect.
 */
public class BasicNamedEdge<N extends Node> extends BasicEdge<N> implements NamedEdge<N> {

    /**
     * Name of the edge
     */
    private String name;

    /**
     * Creates a named edge that starts at the source node
     * and ends (points) at the destination node.
     * 
     * @param name is the name of the edge
     * @param sourceNode is the source node of the edge
     * @param targetNode is the target node of the edge
     * 
     * @throws IllegalArgumentException if name is null or empty, or if sourceNode or targetNode is null.
     */
    public BasicNamedEdge(String name, N sourceNode, N targetNode) {
        super(sourceNode, targetNode);

        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name provided is null");
        }
        this.name = name;
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
        return "Edge[Name: " + name + ", Nodes: " + getSourceNode() + " -> " + getTargetNode() + "]";
    }
}
