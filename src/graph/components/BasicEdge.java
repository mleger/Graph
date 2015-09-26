package graph.components;

import graph.elements.Edge;
import graph.elements.Node;

/**
 * This class creates a basic directed edge that starts at a source node
 * and ends at a target node.
 * 
 * @author Mathieu LŽger
 * @since Mar 16, 2014
 *
 * @param <N> type of node to which the edge will connect.
 */
public class BasicEdge<N extends Node> implements Edge<N> {
    private N sourceNode;
    private N targetNode;

    /**
     * Creates an edge that starts at the source node
     * and ends (points) at the destination node.
     * 
     * @param sourceNode is the source node of the edge
     * @param targetNode is the target node of the edge
     * 
     * @throws IllegalArgumentException if sourceNode or targetNode is null.
     */
    public BasicEdge(N sourceNode, N targetNode) {
        if(sourceNode == null || targetNode == null){
            throw new IllegalArgumentException("One or more of the Edges nodes were null");
        }
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
    }

    @Override
    public N getSourceNode() {
        return sourceNode;
    }

    @Override
    public void setSourceNode(N sourceNode) {
        if(sourceNode == null){
            throw new IllegalArgumentException("Node is null");
        }
        this.sourceNode = sourceNode;
    }

    @Override
    public N getTargetNode() {
        return targetNode;
    }

    @Override
    public void setTargetNode(N targetNode) {
        if(targetNode == null){
            throw new IllegalArgumentException("Node is null");
        }
        this.targetNode = targetNode;
    }

    @Override
    public boolean connectsToNode(N node) {
        if(node == null){
            throw new IllegalArgumentException("Node is null");
        }
        return node.equals(sourceNode) || node.equals(targetNode);
    }

    @Override
    public String toString() {
        return "Edge[Nodes: " + getSourceNode() + " -> " + getTargetNode() + "]";
    }
}
