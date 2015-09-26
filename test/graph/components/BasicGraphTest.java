package graph.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import graph.elements.Edge;
import graph.elements.Graph;
import graph.elements.Node;
import graph.elements.WeightedEdge;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

public class BasicGraphTest {

    @Test
    public void createBasicGraph_allEdgesAndNodesShouldBeProperlyAdded() {

        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");
        Node node3 = new BasicNode("node3");
        Node node4 = new BasicNode("node4");

        Edge<Node> edge1 = new BasicEdge<Node>(node1, node2);
        Edge<Node> edge2 = new BasicEdge<Node>(node2, node3);
        Edge<Node> edge3 = new BasicEdge<Node>(node3, node4);
        Edge<Node> edge4 = new BasicEdge<Node>(node4, node1);

        Graph<Node, Edge<Node>> graph = new BasicGraph<Node, Edge<Node>>();
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);

        Set<Node> expectedNodeSet = new LinkedHashSet<Node>();
        expectedNodeSet.add(node1);
        expectedNodeSet.add(node2);
        expectedNodeSet.add(node3);
        expectedNodeSet.add(node4);

        Set<Edge<Node>> expectedEdgeSet = new LinkedHashSet<Edge<Node>>();
        expectedEdgeSet.add(edge1);
        expectedEdgeSet.add(edge2);
        expectedEdgeSet.add(edge3);
        expectedEdgeSet.add(edge4);

        // Testing: getUnmodifiableNodeSet()
        Set<Edge<Node>> graphEdges = graph.getAllEdges();
        assertEquals("The graph did not contain the right amount of edges", 4, graphEdges.size());
        expectedEdgeSet.removeAll(graphEdges);
        assertTrue("The following edges were not found in the graph: " + expectedEdgeSet.toString(), expectedEdgeSet.isEmpty());

        // Testing: getUnmodifiableNodeSet()
        Set<Node> graphNodes = graph.getAllNodes();
        assertEquals("The graph did not contain the right amount of nodes", 4, graphNodes.size());
        expectedNodeSet.removeAll(graphNodes);
        assertTrue("The following nodes were not found in the graph: " + expectedNodeSet.toString(), expectedNodeSet.isEmpty());
    }

    @Test
    public void addEdgesIndividualyToGraph_edgesAndItsNodesShouldBeAdded() {

        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");
        Node node3 = new BasicNode("node3");

        Edge<Node> edge1 = new BasicEdge<Node>(node1, node2);
        Edge<Node> edge2 = new BasicEdge<Node>(node2, node3);

        Graph<Node, Edge<Node>> graph = new BasicGraph<Node, Edge<Node>>();

        //Testing: adding one edge
        boolean edgeAdded = graph.addEdge(edge1);
        assertTrue("The edge was not added to the graph", edgeAdded);
        Set<Edge<Node>> graphEdges = graph.getAllEdges();
        assertEquals("The graph did not contain the right amount of edges", 1, graphEdges.size());
        assertTrue("The graph did not properly insert edge1", graphEdges.contains(edge1));

        Set<Node> graphNodes = graph.getAllNodes();
        assertEquals("The graph did not contain the right amount of nodes", 2, graphNodes.size());
        assertTrue("The graph did not contain node1 after added an edge connected to it", graphNodes.contains(node1));
        assertTrue("The graph did not contain node2 after added an edge connected to it", graphNodes.contains(node2));

        // Testing: Adding second edge
        graph.addEdge(edge2);
        graphEdges = graph.getAllEdges();
        assertEquals("The graph did not contain the right amount of edges", 2, graphEdges.size());
        assertTrue("The graph did not properly insert edge2", graphEdges.contains(edge1));

        graphNodes = graph.getAllNodes();
        assertEquals("The graph did not contain the right amount of nodes", 3, graphNodes.size());
        assertTrue("The graph did not contain node3 after added an edge connected to it", graphNodes.contains(node3));
    }
    
    @Test
    public void addMultipleEdgesToGraph_edgesAndTheirNodesShouldBeAdded() {
        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");
        Node node3 = new BasicNode("node3");

        Edge<Node> edge1 = new BasicEdge<Node>(node1, node2);
        Edge<Node> edge2 = new BasicEdge<Node>(node2, node3);
        
        Set<Edge<Node>> edgesToBeAdded = new LinkedHashSet<Edge<Node>>();
        edgesToBeAdded.add(edge1);
        edgesToBeAdded.add(edge2);
        
        Graph<Node, Edge<Node>> graph = new BasicGraph<Node, Edge<Node>>();
        graph.addAllEdges(edgesToBeAdded);
        Set<Edge<Node>> graphEdges = graph.getAllEdges();
        assertEquals("The graph did not contain the right amount of edges", 2, graphEdges.size());
        assertTrue("The graph did not properly insert a set of edges", graphEdges.contains(edge1));
        assertTrue("The graph did not properly insert a set of edges", graphEdges.contains(edge1));
    }
    
    @Test
    public void addMultipleWeightedEdgesToGraph_edgesAndTheirNodesShouldBeAdded() {
        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");
        Node node3 = new BasicNode("node3");

        WeightedEdge<Node> edge1 = new BasicWeightedEdge<Node>(node1, node2);
        WeightedEdge<Node> edge2 = new BasicWeightedEdge<Node>(node2, node3);
        
        Set<WeightedEdge<Node>> edgesToBeAdded = new LinkedHashSet<WeightedEdge<Node>>();
        edgesToBeAdded.add(edge1);
        edgesToBeAdded.add(edge2);
        
        Graph<Node, Edge<Node>> graph = new BasicGraph<Node, Edge<Node>>();
        graph.addAllEdges(edgesToBeAdded);
        Set<Edge<Node>> graphEdges = graph.getAllEdges();
        assertEquals("The graph did not contain the right amount of edges", 2, graphEdges.size());
        assertTrue("The graph did not properly insert a set of edges", graphEdges.contains(edge1));
        assertTrue("The graph did not properly insert a set of edges", graphEdges.contains(edge2));
    }
    
    @Test
    public void addMultipleEdgesWithDifferentTypeNodesToGraph_edgesAndTheirNodesShouldBeAdded() {
        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");
        ExtendedNode node3 = new ExtendedNode("node3");
        ExtendedNode node4 = new ExtendedNode("node4");

        Edge<Node> edge1 = new BasicEdge<Node>(node1, node2);
        Edge<Node> edge2 = new BasicEdge<Node>(node2, node3);
        Edge<ExtendedNode> edge3 = new BasicEdge<ExtendedNode>(node3, node4);
        Set<Edge<? extends Node>> edgesToAdd = new LinkedHashSet<Edge<? extends Node>>();
        edgesToAdd.add(edge1);
        edgesToAdd.add(edge2);
        edgesToAdd.add(edge3);

        Graph<Node, Edge<? extends Node>> graph = new BasicGraph<Node, Edge<? extends Node>>();
        graph.addAllEdges(edgesToAdd);
        
        Set<Edge<? extends Node>> graphEdges = graph.getAllEdges();
        assertEquals("The graph did not contain the right amount of edges", 3, graphEdges.size());
        assertTrue("The graph did not properly insert a set of edges", graphEdges.contains(edge1));
        assertTrue("The graph did not properly insert a set of edges", graphEdges.contains(edge2));
        assertTrue("The graph did not properly insert a set of edges", graphEdges.contains(edge3));
    }

    @Test
    public void getEdgesFromGraph_allTheRightEdgesShouldBeReturned() {

        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");
        Node node3 = new BasicNode("node3");
        Node node4 = new BasicNode("node4");

        Edge<Node> edge1 = new BasicEdge<Node>(node1, node2);
        Edge<Node> edge2 = new BasicEdge<Node>(node2, node3);
        Edge<Node> edge3 = new BasicEdge<Node>(node3, node4);
        Edge<Node> edge4 = new BasicEdge<Node>(node4, node1);
        Edge<Node> edge5 = new BasicEdge<Node>(node4, node1);

        Graph<Node, Edge<Node>> graph = new BasicGraph<Node, Edge<Node>>();
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);

        // Testing: getEdges(N sourceNode, N targetNode)
        Set<Edge<Node>> edgesWithNodes = graph.getEdges(node4, node1);
        assertTrue("The graph did not return edge4 when getting all edges connecting node4 and node1", edgesWithNodes.contains(edge4));
        assertTrue("The graph did not return edge5 when getting all edges connecting node4 and node1", edgesWithNodes.contains(edge5));

        // Testing: getEdgesOf(N node)
        Set<Edge<Node>> edgesOfNode = graph.getEdgesOf(node4);
        assertTrue("The graph did not return edge3 when getting all edges connecting to node4", edgesOfNode.contains(edge3));
        assertTrue("The graph did not return edge4 when getting all edges connecting to node4", edgesOfNode.contains(edge4));
        assertTrue("The graph did not return edge5 when getting all edges connecting to node4", edgesOfNode.contains(edge5));
    }

    @Test
    public void addTwoIdenticalEdgesToAGraph_theGraphShouldNotContainDuplicateEdges() {

        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");
        Node node3 = new BasicNode("node3");

        Edge<Node> edge1 = new BasicEdge<Node>(node1, node2);
        Edge<Node> edge2 = new BasicEdge<Node>(node2, node3);

        Graph<Node, Edge<Node>> graph = new BasicGraph<Node, Edge<Node>>();
        graph.addEdge(edge1);
        graph.addEdge(edge2);

        // Adding duplicate edge
        graph.addEdge(edge2);
        Set<Edge<Node>> graphEdges = graph.getAllEdges();
        assertEquals("The graph contains duplicate edges", 2, graphEdges.size());
    }

    @Test
    public void removeEdgesToGraph_edgesAndUnconnectedNodesShouldBeRemoved() {

        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");
        Node node3 = new BasicNode("node3");
        Node node4 = new BasicNode("node4");

        Edge<Node> edge1 = new BasicEdge<Node>(node1, node2);
        Edge<Node> edge2 = new BasicEdge<Node>(node2, node3);
        Edge<Node> edge3 = new BasicEdge<Node>(node3, node4);
        Edge<Node> edge4 = new BasicEdge<Node>(node4, node1);

        Graph<Node, Edge<Node>> graph = new BasicGraph<Node, Edge<Node>>();
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);

        //Testing: removing one edge
        boolean edgeRemoved = graph.removeEdge(edge1);
        assertTrue("The edge was not removed to the graph", edgeRemoved);
        Set<Edge<Node>> graphEdges = graph.getAllEdges();
        assertEquals("The graph did not contain the right amount of edges", 3, graphEdges.size());
        assertTrue("The graph did not properly remove edge1", !graphEdges.contains(edge1));

        Set<Node> graphNodes = graph.getAllNodes();
        assertEquals("The graph did not contain the right amount of nodes", 4, graphNodes.size());

        // Testing: Removing a second edge and its node
        graph.removeEdge(edge2);
        graphEdges = graph.getAllEdges();
        assertEquals("The graph did not contain the right amount of edges", 2, graphEdges.size());
        assertTrue("The graph did not properly remove edge2", !graphEdges.contains(edge2));

        graphNodes = graph.getAllNodes();
        assertEquals("The graph did not contain the right amount of nodes", 3, graphNodes.size());
        assertTrue("The graph did not proprely remove node2 from the graph", !graphNodes.contains(node2));
    }

    @Test
    public void removeNodeFromGraph_nodeAndItsConnectedEdgesShouldBeRemoved() {

        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");
        Node node3 = new BasicNode("node3");
        Node node4 = new BasicNode("node4");

        Edge<Node> edge1 = new BasicEdge<Node>(node1, node2);
        Edge<Node> edge2 = new BasicEdge<Node>(node2, node3);
        Edge<Node> edge3 = new BasicEdge<Node>(node3, node4);
        Edge<Node> edge4 = new BasicEdge<Node>(node4, node1);

        Graph<Node, Edge<Node>> graph = new BasicGraph<Node, Edge<Node>>();
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);

        //Testing: removing a node
        boolean nodeRemoved = graph.removeNode(node1);
        assertTrue("The node was not removed to the graph", nodeRemoved);
        Set<Edge<Node>> graphEdges = graph.getAllEdges();
        assertEquals("The graph did not contain the right amount of edges", 2, graphEdges.size());
        assertTrue("The graph did not properly remove edge1", !graphEdges.contains(edge1));
        assertTrue("The graph did not properly remove edge4", !graphEdges.contains(edge1));

        Set<Node> graphNodes = graph.getAllNodes();
        assertEquals("The graph did not contain the right amount of nodes", 3, graphNodes.size());
        assertTrue("The graph did not proprely remove node1 from the graph", !graphNodes.contains(node1));
    }

    @Test
    public void callIsEmptyMethodOnAnEmptyGraph_shouldReturnTrue() {

        Graph<Node, Edge<Node>> graph = new BasicGraph<Node, Edge<Node>>();

        assertTrue("The isEmpty() method returned false for an empty graph", graph.isEmpty());
    }

    @Test
    public void createBasicGraphWithBasicEdgesAndEdgesExtendingBasicEdge_thisTestShouldComplileProperly() {

        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");
        Node node3 = new BasicNode("node3");

        Edge<Node> edge1 = new BasicEdge<Node>(node1, node2);
        WeightedEdge<Node> edge2 = new BasicWeightedEdge<Node>(node2, node3, 2);
        NamedWeightedEdge<Node> edge3 = new NamedWeightedEdge<Node>("Edge3", node3, node1, 3);

        Graph<Node, Edge<Node>> graph = new BasicGraph<Node, Edge<Node>>();
        graph.addEdge(edge1);

        //If the genetics are done wrong, this will fail to compile
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        
        Set<Edge<Node>> graphEdges = graph.getAllEdges();
        assertEquals("The graph did not contain the right amount of edges", 3, graphEdges.size());
        assertTrue("The graph did not properly insert a set of edges", graphEdges.contains(edge1));
        assertTrue("The graph did not properly insert a set of edges", graphEdges.contains(edge2));
        assertTrue("The graph did not properly insert a set of edges", graphEdges.contains(edge3));
        
    }

    @Test
    public void createBasicGraphWithDifferentTypesOfNodes_thisTestShouldComplileProperly() {
        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");
        ExtendedNode node3 = new ExtendedNode("node3");
        ExtendedNode node4 = new ExtendedNode("node4");

        Edge<Node> edge1 = new BasicEdge<Node>(node1, node2);
        Edge<Node> edge2 = new BasicEdge<Node>(node2, node3);
        Edge<ExtendedNode> edge3 = new BasicEdge<ExtendedNode>(node3, node4);

        Graph<Node, Edge<? extends Node>> graph = new BasicGraph<Node, Edge<? extends Node>>();
        graph.addEdge(edge1);

        //If the genetics are done wrong, this will fail to compile
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        
        Set<Edge<? extends Node>> graphEdges = graph.getAllEdges();
        assertEquals("The graph did not contain the right amount of edges", 3, graphEdges.size());
        assertTrue("The graph did not properly insert a set of edges", graphEdges.contains(edge1));
        assertTrue("The graph did not properly insert a set of edges", graphEdges.contains(edge2));
        assertTrue("The graph did not properly insert a set of edges", graphEdges.contains(edge3));
    }

    private class ExtendedNode extends BasicNode {
        public ExtendedNode(String name) {
            super(name);
        }
    }

}
