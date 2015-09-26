package graph.algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import graph.components.BasicGraph;
import graph.components.BasicNode;
import graph.components.BasicWeightedEdge;
import graph.elements.Graph;
import graph.elements.Node;
import graph.elements.WeightedEdge;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class MinimumSpanningTreeTest {

    @Test
    public void sendGraph_findsValidMinimalSpanningTree() {
        List<Node> graphNodes = createGraphNodes();
        Set<WeightedEdge<Node>> expectedTreeEdges = createExpectedTreeEdges(graphNodes);
        Set<WeightedEdge<Node>> expectedCotreeEdges = createExpectedCotreeEdges(graphNodes);

        Graph<Node, WeightedEdge<Node>> graph = createGraphWithWeightedEdges(graphNodes, expectedTreeEdges, expectedCotreeEdges);
        MinimumSpanningTree<Node, WeightedEdge<Node>> tree = new MinimumSpanningTree<Node, WeightedEdge<Node>>(graph);
        Set<WeightedEdge<Node>> treeEdges = tree.getTreeEdges();
        long treeWeight = tree.getTreeWeight();

        assertEquals("The tree did not contain the right amount of edges", 10, treeEdges.size());

        Set<WeightedEdge<Node>> missingTreeEdges = new LinkedHashSet<WeightedEdge<Node>>();
        missingTreeEdges.addAll(expectedTreeEdges);
        missingTreeEdges.removeAll(treeEdges);

        Set<WeightedEdge<Node>> nonExpectedTreeEdges = new LinkedHashSet<WeightedEdge<Node>>();
        nonExpectedTreeEdges.addAll(treeEdges);
        nonExpectedTreeEdges.removeAll(expectedTreeEdges);
        assertTrue("The following edges were not found in the tree: " + missingTreeEdges.toString() + " and the following edges were in the tree instead: " + nonExpectedTreeEdges.toString() , missingTreeEdges.isEmpty());

        assertEquals("The tree weight is not correct", 57, treeWeight);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sendEmptyGraph_throwsIllegalArgumentException() {
        Graph<Node, WeightedEdge<Node>> graph = new BasicGraph<Node, WeightedEdge<Node>>();
        MinimumSpanningTree<Node, WeightedEdge<Node>> tree = new MinimumSpanningTree<Node, WeightedEdge<Node>>(graph);
        tree.getTreeEdges();
    }


    private static Graph<Node, WeightedEdge<Node>> createGraphWithWeightedEdges(List<Node> graphNodes, Set<WeightedEdge<Node>> expectedTreeEdges, Set<WeightedEdge<Node>> expectedCotreeEdges ) {

        Graph<Node, WeightedEdge<Node>> graph = new BasicGraph<Node, WeightedEdge<Node>>();

        for(WeightedEdge<Node> e : expectedTreeEdges ) {
            graph.addEdge(e);
        }

        for(WeightedEdge<Node> e : expectedCotreeEdges ) {
            graph.addEdge(e);
        }

        return graph;
    }

    private static List<Node> createGraphNodes() {
        List<Node> graphNodes = new ArrayList<Node>();

        graphNodes.add(new BasicNode("Node1"));
        graphNodes.add(new BasicNode("Node2"));
        graphNodes.add(new BasicNode("Node3"));
        graphNodes.add(new BasicNode("Node4"));
        graphNodes.add(new BasicNode("Node5"));
        graphNodes.add(new BasicNode("Node6"));
        graphNodes.add(new BasicNode("Node7"));
        graphNodes.add(new BasicNode("Node8"));
        graphNodes.add(new BasicNode("Node9"));
        graphNodes.add(new BasicNode("Node10"));
        graphNodes.add(new BasicNode("Node11"));

        return graphNodes;
    }

    private static Set<WeightedEdge<Node>> createExpectedTreeEdges(List<Node> nodes) {
        Set<WeightedEdge<Node>> expectedTreeEdges = new LinkedHashSet<WeightedEdge<Node>>();

        expectedTreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(2), nodes.get(3), 9));
        expectedTreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(2), nodes.get(10), 5));
        expectedTreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(9), nodes.get(10), 8));
        expectedTreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(10), nodes.get(0), 3));
        expectedTreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(0), nodes.get(8), 6));
        expectedTreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(8), nodes.get(7), 3));
        expectedTreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(6), nodes.get(7), 7));
        expectedTreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(6), nodes.get(5), 8));
        expectedTreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(4), nodes.get(6), 6));
        expectedTreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(1), nodes.get(4), 2));

        return expectedTreeEdges;
    }

    private static Set<WeightedEdge<Node>> createExpectedCotreeEdges(List<Node> nodes) {
        Set<WeightedEdge<Node>> expectedCotreeEdges = new LinkedHashSet<WeightedEdge<Node>>();

        expectedCotreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(9), nodes.get(8), 10));
        expectedCotreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(9), nodes.get(0), 12));
        expectedCotreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(0), nodes.get(7), 10));
        expectedCotreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(10), nodes.get(1), 8));
        expectedCotreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(0), nodes.get(1), 8));
        expectedCotreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(2), nodes.get(6), 9));
        expectedCotreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(2), nodes.get(1), 10));
        expectedCotreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(3), nodes.get(4), 13));
        expectedCotreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(3), nodes.get(5), 12));
        expectedCotreeEdges.add(new BasicWeightedEdge<Node>(nodes.get(4), nodes.get(5), 10));

        return expectedCotreeEdges;
    }

}