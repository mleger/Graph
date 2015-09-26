package graph.components;

import graph.algorithms.AlgorithmUtilities;
import graph.elements.Edge;
import graph.elements.Graph;
import graph.elements.Node;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This class creates a basic graph that contains nodes and directed edges that
 * connect to two nodes. Loops are permitted, as well as multiple edges
 * connecting to the same nodes. Unconnected nodes are not permitted
 * 
 * @author Mathieu LŽger
 * @since Mar 16, 2014
 *
 * @param <N> is the type of node to which the graph's edges will connect.
 *        they can be of type Node or any of its subclasses
 * @param <E> is the type of edges contained in the graph. They can be of
 *        type Edge or any of its subclasses that connect to nodes of type
 *        N or any of its subclasses.
 */
public class BasicGraph<N extends Node, E extends Edge<? extends N>> implements Graph<N, E> {
    /**
     * Ordered Set containing all the graph edges
     */
    private final Set<E> edges = new LinkedHashSet<E>();

    /**
     * Ordered Set containing all the graph nodes
     */
    private final Set<N> nodes = new LinkedHashSet<N>();

    /**
     * Creates an basic graph.
     */
    public BasicGraph() {
        //Nothing to construct
    }

    @Override
    public Set<E> getAllEdges() {
        return Collections.unmodifiableSet(edges);
    }
    
    /**
     * Returns a modifiable set of the graph's edges for subclasses
     * 
     * @return  a modifiable set of the graph's edges for subclasses
     */
    Set<E> getModifiableEdgeSet() {
        return edges;
    }

    @Override
    public Set<E> getEdges(N sourceNode, N targetNode) {
        if(sourceNode == null || targetNode == null){
            throw new IllegalArgumentException("One or more of the Edges nodes were null");
        }

        Set<E> matchedEdges = new LinkedHashSet<E>();
        for(E edge : edges){
            if(edge.getSourceNode().equals(sourceNode) && edge.getTargetNode().equals(targetNode)){
                matchedEdges.add(edge);
            }
        }

        return matchedEdges;
    }

    @Override
    public Set<E> getEdgesOf(N node) {
        if(node == null){
            throw new IllegalArgumentException("The node is null");
        }

        Set<E> matchedEdges = new LinkedHashSet<E>();
        for(E edge : edges){
            if(edge.getSourceNode().equals(node) || edge.getTargetNode().equals(node)){
                matchedEdges.add(edge);
            }
        }

        return matchedEdges;
    }

    @Override
    public boolean addEdge(E edge) {
        if(edge == null){
            throw new IllegalArgumentException("The edge is null");
        }

        N sourceNode = edge.getSourceNode();
        N targetNode = edge.getTargetNode();

        nodes.add(sourceNode);
        nodes.add(targetNode);

        return edges.add(edge);
    }
    
    @Override
    public boolean addAllEdges(Set<? extends E> edges) {
    	boolean success = true;
        for(E edge : edges){
        	success = success && addEdge(edge);
        }
        return success;
    }

    @Override
    public boolean removeEdge(E edge) {
        if(edge == null){
            throw new IllegalArgumentException("The edge is null");
        }

        N sourceNode = edge.getSourceNode();
        N targetNode = edge.getTargetNode();

        boolean edgeDeleteSuccess;
        boolean sourceDeleteSuccess;
        boolean targetDeleteSuccess;
        edgeDeleteSuccess = edges.remove(edge);

        if(getEdgesOf(sourceNode).isEmpty()){
            sourceDeleteSuccess = nodes.remove(sourceNode);
        } else {
            sourceDeleteSuccess = true;
        }

        if(getEdgesOf(targetNode).isEmpty()){
            targetDeleteSuccess = nodes.remove(targetNode);
        } else {
            targetDeleteSuccess = true;
        }

        return edgeDeleteSuccess && sourceDeleteSuccess && targetDeleteSuccess;
    }

    @Override
    public Set<N> getAllNodes() {
        return Collections.unmodifiableSet(nodes);
    }

    /**
     * Returns a modifiable set of the graph's nodes for subclasses
     * 
     * @return  a modifiable set of the graph's nodes for subclasses
     */
    Set<N> getModifiableNodeSet() {
        return nodes;
    }
    
    @Override
    public boolean removeNode(N node) {
        if(node == null){
            throw new IllegalArgumentException("The node is null");
        }

        Set<E> associatedEdges = getEdgesOf(node);
        boolean edgesRemoved = edges.removeAll(associatedEdges);
        boolean nodeRemoved = nodes.remove(node);

        return edgesRemoved && nodeRemoved;
    }

    @Override
    public boolean isEmpty() {
        return edges.isEmpty();
    }

    @Override
    public String toString() {
        return AlgorithmUtilities.printEdgeSetToString("Graph", edges);
    }

}
