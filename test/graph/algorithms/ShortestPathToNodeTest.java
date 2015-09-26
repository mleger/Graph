package graph.algorithms;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import graph.components.BasicGraph;
import graph.components.BasicNode;
import graph.components.BasicWeightedEdge;
import graph.elements.Graph;
import graph.elements.Node;
import graph.elements.WeightedEdge;

import org.junit.Test;

public class ShortestPathToNodeTest {

	@Test
	public void runTheAlgorithmOnATestSystem_resutlsShouldBeCorrect() {
		
    	Node nO = new BasicNode("O");
    	Node nA = new BasicNode("A");
    	Node nB = new BasicNode("B");
    	Node nC = new BasicNode("C");
    	Node nD = new BasicNode("D");
    	Node nE = new BasicNode("E");
    	Node nF = new BasicNode("F");
    	Node nT = new BasicNode("T");
		
    	Set<WeightedEdge<Node>> edges = new LinkedHashSet<WeightedEdge<Node>>();
    	edges.add(new BasicWeightedEdge<Node>(nO, nA, 2));
    	edges.add(new BasicWeightedEdge<Node>(nO, nB, 5));
    	edges.add(new BasicWeightedEdge<Node>(nO, nC, 4));
    	edges.add(new BasicWeightedEdge<Node>(nA, nB, 2));
    	edges.add(new BasicWeightedEdge<Node>(nC, nB, 1));
    	edges.add(new BasicWeightedEdge<Node>(nA, nF, 12));
    	edges.add(new BasicWeightedEdge<Node>(nA, nD, 7));
    	edges.add(new BasicWeightedEdge<Node>(nB, nD, 4));
    	edges.add(new BasicWeightedEdge<Node>(nB, nE, 3));
    	edges.add(new BasicWeightedEdge<Node>(nC, nE, 4));
    	edges.add(new BasicWeightedEdge<Node>(nD, nT, 5));
    	edges.add(new BasicWeightedEdge<Node>(nD, nE, 1));
    	edges.add(new BasicWeightedEdge<Node>(nE, nT, 7));
    	edges.add(new BasicWeightedEdge<Node>(nF, nT, 3));
    	
    	Graph<Node, WeightedEdge<Node>> graph = new BasicGraph<Node, WeightedEdge<Node>>();
    	graph.addAllEdges(edges);
		
		ShortestPathsToNode<Node, WeightedEdge<Node>> findShortestPaths = new ShortestPathsToNode<Node, WeightedEdge<Node>>(graph, nO);
		Map<Node,Long> shortestPaths = findShortestPaths.getShortestPaths();
		
		assertEquals("The shortest path weight for Node O is wrong", 0, (long) shortestPaths.get(nO));
		assertEquals("The shortest path weight for Node O is wrong", 2, (long) shortestPaths.get(nA));
		assertEquals("The shortest path weight for Node O is wrong", 4, (long) shortestPaths.get(nB));
		assertEquals("The shortest path weight for Node O is wrong", 4, (long) shortestPaths.get(nC));
		assertEquals("The shortest path weight for Node O is wrong", 8, (long) shortestPaths.get(nD));
		assertEquals("The shortest path weight for Node O is wrong", 7, (long) shortestPaths.get(nE));
		assertEquals("The shortest path weight for Node O is wrong", 14, (long) shortestPaths.get(nF));
		assertEquals("The shortest path weight for Node O is wrong", 13, (long) shortestPaths.get(nT));
		
		assertEquals("The shortest path weight for Node O is wrong", 7, findShortestPaths.getShortestPathToTarget(nE));
	}

}
