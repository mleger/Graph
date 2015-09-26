package graph.elements;

import java.util.Set;

/**
 * This interface specifies the properties of a graph that contains nodes and directed edges that
 * connect to two nodes. Loops are permitted, as well as multiple edges connecting to the same nodes.
 * Unconnected nodes are not permitted
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
public interface Graph<N extends Node, E extends Edge<? extends N>> {

    /**
     * Returns an unmodifiable Set of all the graph's edges.
     * 
     * <p>
     * NOTE: This set is backed by the graph, which means that changes to
     * the graph's edges will be reflected in this set. Therefore, one must
     * be careful not to make changes to the graph while iterating through
     * this set.
     * </p>
     * 
     * <p>
     * NOTE: The returned Set is unmodifiable and will therefore throw
     * UnsupportedOperationException if one attempts to call its add() or
     * remove() methods.
     * </p>
     * 
     * @return unmodifiable Set of all the edges contained in the graph.
     */
    Set<E> getAllEdges();

    /**
     * Returns a Set of all the edges that have the specified source node
     * and target node.
     * 
     * @param sourceNode edge's source node
     * @param targetNode edge's target node
     * 
     * @return Set of edges with the specified source and target nodes.
     * 
     * @throws IllegalArgumentException if sourceNode or targetNode is null.
     */
    Set<E> getEdges(N sourceNode, N targetNode);

    /**
     * Returns a Set of all the edges that connect to the specified node.
     * 
     * @param node to which the edges connect
     * 
     * @return Set of edges that connect to the specified node.
     * 
     * @throws IllegalArgumentException if node is null.
     */
    Set<E> getEdgesOf(N node);

    /**
     * Adds the supplied edge to the graph. Also adds the edge's nodes
     * to the graph if they are not already present.
     * 
     * @param edge to be added to the graph.
     * 
     * @return boolean indicating if the edge and its nodes were successfully added to the graph.
     * 
     * @throws IllegalArgumentException if edge is null.
     */
    boolean addEdge(E edge);
    
    /**
     * Adds all the supplied edges to the graph. Also adds the all the edge's nodes
     * to the graph if they are not already present.
     * 
     * @param edges set of edges to be added to the graph.
     * 
     * @return boolean indicating if the edges and their nodes were successfully added to the graph.
     * 
     * @throws IllegalArgumentException if edges is null.
     */
    boolean addAllEdges(Set<? extends E> edges);

    /**
     * Removes the supplied edge from the graph. Also removes any nodes from the
     * graph that, as a result of the edge removal, are note connected to any edge.
     * 
     * @param edge to be removed from the graph.
     * 
     * @return boolean indicating if the edge and the resulting unconnected nodes
     *         were successfully removed to the graph.
     * 
     * @throws IllegalArgumentException if edge is null.
     */
    boolean removeEdge(E edge);

    /**
     * Returns an unmodifiable Set of all the graph's nodes.
     * 
     * <p>
     * NOTE: This set is backed by the graph, which means that changes to
     * the graph's nodes will be reflected in this set. Therefore, one must
     * be careful not to make changes to the graph while iterating through
     * this set.
     * </p>
     * 
     * <p>
     * NOTE: The returned Set is unmodifiable and will therefore throw
     * UnsupportedOperationException if one attempts to call its add() or
     * remove() methods.
     * </p>
     * 
     * @return unmodifiable Set of all the nodes contained in the graph.
     */
    Set<N> getAllNodes();

    /**
     * Removes the supplied node from the graph. Also removes all the edges
     * connected to the node.
     * 
     * @param node to be removed from the graph.
     * 
     * @return boolean indicating if the node and its associated edges were
     *         successfully removed to the graph.
     * 
     * @throws IllegalArgumentException if node is null.
     */
    boolean removeNode(N node);

    /**
     * @return boolean indicating if the graph contains no edges (and by
     *         consequence of how edges are removed, no nodes).
     */
    boolean isEmpty();
}
