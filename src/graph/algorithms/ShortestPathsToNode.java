package graph.algorithms;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import graph.elements.Graph;
import graph.elements.Node;
import graph.elements.WeightedEdge;

/**
 * This class can be used to find the shortest path from any node in the 
 * system to the source node provided. It is based of off Dijkstra’s
 * Algorithm.
 *
 * @author Mathieu Léger
 * @since Mar 16, 2014
 */
public class ShortestPathsToNode<N extends Node, E extends WeightedEdge<N>> {

	/**
	 * Map of the shortest path between every node in the graph and the source node
	 */
	private final Map<N,Long> shortestPaths;
	
	/**
	 * Set of edges of the tree used to find the shortest path to each node to the source node
	 */
	private final Set<E> shortestPathsTreeEdges;

	/**
     * Finds all the shortest path to a source node using Dijkstra’s Algorithm.
     *
     * @param graph to run the shortest path problem algorithm on.
	 * @param sourceNode node to which all the weights are calculated.
	 * 
	 * @throws IllegalArgumentException if the graph is empty, the source node
	 *         is null, or the source node is not in the graph.
	 */
	public ShortestPathsToNode(Graph<N, E> graph, N sourceNode) {
        if(graph.isEmpty()) {
            throw new IllegalArgumentException("The graph cannot be empty");
        }
        if(sourceNode == null) {
        	throw new IllegalArgumentException("The source node cannot be null");
        }

        Set<E> treeEdges = new LinkedHashSet<E>();
        Set<N> treeNodes = new LinkedHashSet<N>();
        Set<E> cotreeEdges = new LinkedHashSet<E>();
        Set<N> cotreeNodes = new LinkedHashSet<N>();
        
        cotreeEdges.addAll(graph.getAllEdges());
        cotreeNodes.addAll(graph.getAllNodes());
        
        if(!cotreeNodes.contains(sourceNode)) {
        	throw new IllegalArgumentException("The source node must be a node in the graph");
        }

        // Initialize the shortest path weight (SPW) to each variable to the largest value possible
        Map<N,Long> shortestPathWeight = new HashMap<N,Long>();
        for(N node : cotreeNodes){
        	shortestPathWeight.put(node, Long.MAX_VALUE);
        }
        
        // Set the SPW of the source node to zero and add the source node to the tree
        shortestPathWeight.put(sourceNode, 0L);
        treeNodes.add(sourceNode);
        cotreeNodes.remove(sourceNode);
        
        while( !cotreeNodes.isEmpty() ){
            long weightToAdd = Long.MAX_VALUE;
            E edgeToAdd = null;
            N nodeToAdd = null;

            // Find all the potential edges ( edges that connect to one node in the present tree 
            // (T node) and one node not in the present tree (C node))
            for(E edge : cotreeEdges) {
                
            	// For each of the potential edges, find the SPW of its C node
            	// (C node SPW = Edge Weight + SPW of the edge’s T node) and add the 
            	// edge with the lowest C node SPW to the tree.
            	N edgeSourceNode = edge.getSourceNode();
                N edgeTragetNode = edge.getTargetNode();
                boolean sourceIsInTree = treeNodes.contains(edgeSourceNode);
                boolean targetIsInTree = treeNodes.contains(edgeTragetNode);

                if( (sourceIsInTree && !targetIsInTree) || (!sourceIsInTree && targetIsInTree)) {      
                	N treeNode;
                	N cotreeNode;
                	if(sourceIsInTree) {
                    	treeNode = edgeSourceNode;
                    	cotreeNode = edgeTragetNode;
                    } else {
                    	treeNode = edgeTragetNode;
                    	cotreeNode = edgeSourceNode;
                    }
                    
                    long cotreeNodeWeight = shortestPathWeight.get(treeNode) + edge.getWeight();
                	if(cotreeNodeWeight <= weightToAdd) {
                		weightToAdd = cotreeNodeWeight;
                		edgeToAdd = edge;
                		nodeToAdd = cotreeNode;
                	}
                }
            }
		
            if(edgeToAdd != null && nodeToAdd != null) {
                treeEdges.add(edgeToAdd);
                treeNodes.add(nodeToAdd);
                cotreeEdges.remove(edgeToAdd);
                cotreeNodes.remove(nodeToAdd);
                shortestPathWeight.put(nodeToAdd, weightToAdd);
            } else {
            	// This should never happen
                throw new IllegalStateException("No edge from cotree could be added to the tree");
            }     
        }
		
        shortestPaths = shortestPathWeight;
        shortestPathsTreeEdges = treeEdges;
	}

	/**
	 * Returns a map of the shortest path weight for every node in the graph
	 * 
	 * <p>
     * NOTE: The returned Set is unmodifiable and will therefore throw
     * UnsupportedOperationException if one attempts to call its put() or
     * remove() methods.
     * </p>
     * 
	 * @return a map of the shortest path weight for every node in the graph
	 */
	public Map<N,Long> getShortestPaths() {
		return Collections.unmodifiableMap(shortestPaths);
	}
	
	/**
	 * Returns the shortest path weight between the target node and the source node.
	 * 
	 * @param targetNode
	 * @return the shortest path to the target node.
	 */
	
	public long getShortestPathToTarget(N targetNode) {
		return shortestPaths.get(targetNode);
	}

	/**
	 * Set of edges of the tree used to find the shortest path to each node to the source node
	 *
	 * @return the tree edges that result in the shortest path to each node to the source node
	 */
	public Set<E> getShortestPathsTreeEdges() {
		return shortestPathsTreeEdges;
	}
}
