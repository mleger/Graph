package graph.algorithms;

import graph.elements.Graph;
import graph.elements.Node;
import graph.elements.WeightedEdge;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This class can be used to find the minimum spanning tree of a graph.
 * The algorithm used is based off of Prim's algorithm.
 *
 * @author Mathieu LŽger
 * @since Mar 16, 2014
 */
public class MinimumSpanningTree<N extends Node, E extends WeightedEdge<N>> {

    private final Set<E> spanningTreeEdges;
    private final long spanningTreeWeight;

    /**
     * Finds the minimum spanning tree of a graph. The algorithm used is 
     * based off of Prim's algorithm.
     *
     * @param graph to run the tree selection algorithm on.
     * 
     * @throws IllegalArgumentException if the graph is empty.
     */
    public MinimumSpanningTree(Graph<N, E> graph) {
        if(graph.isEmpty()) {
            throw new IllegalArgumentException("The graph cannot be empty");
        }

        Set<E> treeEdges = new LinkedHashSet<E>();
        Set<N> treeNodes = new LinkedHashSet<N>();
        Set<E> cotreeEdges = new LinkedHashSet<E>();
        Set<N> cotreeNodes = new LinkedHashSet<N>();

        cotreeEdges.addAll(graph.getAllEdges());
        cotreeNodes.addAll(graph.getAllNodes());

        long treeWeight = 0;

        //Add a first Node to the tree (and remove from the cotree)
        Iterator<N> iter = cotreeNodes.iterator();
        N groundNode = iter.next();
        treeNodes.add(groundNode);
        cotreeNodes.remove(groundNode);


        while( !cotreeNodes.isEmpty() ){
            long weightToAdd = Long.MAX_VALUE;
            E edgeToAdd = null;
            N nodeToAdd = null;

            // Find all the potential edges (edges that connect to one node in the
            // present tree and one node not in the present tree). From these
            // potential edges, find the edge with the lowest weight. Add this edge
            // and its new node to the tree (and remove them from the cotree).
            for(E edge : cotreeEdges) {
                N sourceNode = edge.getSourceNode();
                N targetNode = edge.getTargetNode();
                boolean sourceIsInTree = treeNodes.contains(sourceNode);
                boolean targetIsInTree = treeNodes.contains(targetNode);

                if( (sourceIsInTree && !targetIsInTree) || (!sourceIsInTree && targetIsInTree)) {
                    // Note: By using <= instead of < ensures that the edges with
                    // a weight of Long.MAX_VALUE can be placed in the tree.
                    if(edge.getWeight() <= weightToAdd){
                        weightToAdd = edge.getWeight();
                        edgeToAdd = edge;
                        if(!sourceIsInTree) {
                            nodeToAdd = sourceNode;
                        } else {
                            nodeToAdd = targetNode;
                        }
                    }
                }
            }

            if(edgeToAdd != null && nodeToAdd != null) {
                treeEdges.add(edgeToAdd);
                treeNodes.add(nodeToAdd);
                cotreeEdges.remove(edgeToAdd);
                cotreeNodes.remove(nodeToAdd);
                treeWeight += weightToAdd;
            } else {
            	// This should never happen
                throw new IllegalStateException("No edge from cotree could be added to the tree");
            }
        }
        spanningTreeEdges = treeEdges;
        spanningTreeWeight = treeWeight;
    }

    /**
     * Returns an unmodifiable Set of tree edges.
     * 
     * <p>
     * NOTE: The returned Set is unmodifiable and will therefore throw
     * UnsupportedOperationException if one attempts to call its add() or
     * remove() methods.
     * </p>
     * 
     * @return unmodifiable Set of edges contained in the tree.
     */
    public Set<E> getTreeEdges() {
        return Collections.unmodifiableSet(spanningTreeEdges);
    }

    /**
     * @return the sum of the weight of all the edges in the tree.
     */
    public long getTreeWeight() {
        return spanningTreeWeight;
    }
}
