package graph.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import graph.elements.Node;
import graph.elements.WeightedEdge;
import graph.elements.WeightedGraph;

import java.util.Set;

import org.junit.Test;

public class BasicWeightedGraphTest {

    @Test
    public void getEdgesWithWeightBetween_shouldReturnTheRightEdges() {
        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");
        Node node3 = new BasicNode("node3");
        Node node4 = new BasicNode("node4");

        WeightedEdge<Node> edge1 = new BasicWeightedEdge<Node>(node1, node2, 1);
        WeightedEdge<Node> edge2 = new BasicWeightedEdge<Node>(node2, node3, 1);
        WeightedEdge<Node> edge3 = new BasicWeightedEdge<Node>(node3, node4, -3);
        WeightedEdge<Node> edge4 = new BasicWeightedEdge<Node>(node4, node1, 800);

        WeightedGraph<Node, WeightedEdge<Node>> graph = new BasicWeightedGraph<Node, WeightedEdge<Node>>();
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);

        Set<WeightedEdge<Node>> matchedEdges = graph.getEdgesWithWeight(1);
        assertEquals("The weighted graph did not found the right number of edges for the weight provided", 2, matchedEdges.size());
        assertTrue("The weighted graph did not match the edge with the right weight", matchedEdges.contains(edge1));
        assertTrue("The weighted graph did not match the edge with the right weight", matchedEdges.contains(edge2));

        matchedEdges = graph.getEdgesWithWeight(100000);
        assertTrue("The weighted graph should not have found any edges for the weight provided", matchedEdges.isEmpty());
    }

    @Test
    public void getEdgesWithWeightAbove_shouldReturnTheRightEdges() {
        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");
        Node node3 = new BasicNode("node3");
        Node node4 = new BasicNode("node4");

        WeightedEdge<Node> edge1 = new BasicWeightedEdge<Node>(node1, node2, 1);
        WeightedEdge<Node> edge2 = new BasicWeightedEdge<Node>(node2, node3, 1);
        WeightedEdge<Node> edge3 = new BasicWeightedEdge<Node>(node3, node4, -3);
        WeightedEdge<Node> edge4 = new BasicWeightedEdge<Node>(node4, node1, 800);

        WeightedGraph<Node, WeightedEdge<Node>> graph = new BasicWeightedGraph<Node, WeightedEdge<Node>>();
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);

        Set<WeightedEdge<Node>> matchedEdges = graph.getEdgesWithWeightAbove(0);
        assertEquals("The weighted graph did not found the right number of edges for the weight interval provided", 3, matchedEdges.size());
        assertTrue("The weighted graph did not match the edge with the right weight", matchedEdges.contains(edge1));
        assertTrue("The weighted graph did not match the edge with the right weight", matchedEdges.contains(edge2));
        assertTrue("The weighted graph did not match the edge with the right weight", matchedEdges.contains(edge4));

        matchedEdges = graph.getEdgesWithWeightAbove(9000);
        assertTrue("The weighted graph should not have found any edges for the weight interval provided", matchedEdges.isEmpty());
    }

    @Test
    public void getEdgesWithWeightBelow_shouldReturnTheRightEdges() {
        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");
        Node node3 = new BasicNode("node3");
        Node node4 = new BasicNode("node4");

        WeightedEdge<Node> edge1 = new BasicWeightedEdge<Node>(node1, node2, 1);
        WeightedEdge<Node> edge2 = new BasicWeightedEdge<Node>(node2, node3, 1);
        WeightedEdge<Node> edge3 = new BasicWeightedEdge<Node>(node3, node4, -3);
        WeightedEdge<Node> edge4 = new BasicWeightedEdge<Node>(node4, node1, 800);

        WeightedGraph<Node, WeightedEdge<Node>> graph = new BasicWeightedGraph<Node, WeightedEdge<Node>>();
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);

        Set<WeightedEdge<Node>> matchedEdges = graph.getEdgesWithWeightBelow(20);
        assertEquals("The weighted graph did not found the right number of edges for the weight interval provided", 3, matchedEdges.size());
        assertTrue("The weighted graph did not match the edge with the right weight", matchedEdges.contains(edge1));
        assertTrue("The weighted graph did not match the edge with the right weight", matchedEdges.contains(edge2));
        assertTrue("The weighted graph did not match the edge with the right weight", matchedEdges.contains(edge3));

        matchedEdges = graph.getEdgesWithWeightBelow(-10);
        assertTrue("The weighted graph should not have found any edges for the weight interval provided", matchedEdges.isEmpty());
    }

    @Test
    public void resetEdgeWeights_shouldResetTheEdgeWeightsToTheDefaultValue() {
        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");
        Node node3 = new BasicNode("node3");
        Node node4 = new BasicNode("node4");

        WeightedEdge<Node> edge1 = new BasicWeightedEdge<Node>(node1, node2, 1);
        WeightedEdge<Node> edge2 = new BasicWeightedEdge<Node>(node2, node3, 1);
        WeightedEdge<Node> edge3 = new BasicWeightedEdge<Node>(node3, node4, -3);
        WeightedEdge<Node> edge4 = new BasicWeightedEdge<Node>(node4, node1, 800);

        WeightedGraph<Node, WeightedEdge<Node>> graph = new BasicWeightedGraph<Node, WeightedEdge<Node>>();
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);

        graph.resetEdgeWeights();

        Set<WeightedEdge<Node>> matchedEdges = graph.getEdgesWithWeight(WeightedEdge.DEFAULT_EDGE_WEIGHT);
        assertEquals("The weighted graph did not reset the weight of all the edges", 4, matchedEdges.size());
        assertTrue("The weighted graph did not reset the weight of all the edges", matchedEdges.contains(edge1));
        assertTrue("The weighted graph did not reset the weight of all the edges", matchedEdges.contains(edge2));
        assertTrue("The weighted graph did not reset the weight of all the edges", matchedEdges.contains(edge3));
        assertTrue("The weighted graph did not reset the weight of all the edges", matchedEdges.contains(edge4));
    }

}
