package graph.components;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import graph.elements.Edge;
import graph.elements.Node;

import org.junit.Test;

public class BasicEdgeTest {

    @Test
    public void createBasicEdge_allItsSettersAndGettersShouldWork() {
        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");
        Node node3 = new BasicNode("node3");
        Node node4 = new BasicNode("node4");

        Edge<Node> edge = new BasicEdge<Node>(node1, node2);

        assertTrue("The edge does not contain the right source node", edge.getSourceNode().equals(node1));
        assertTrue("The edge does not contain the right target node", edge.getTargetNode().equals(node2));

        assertTrue("The edge does not know it is conncected to one of its nodes", edge.connectsToNode(node1));
        assertTrue("The edge does not know it is conncected to one of its nodes", edge.connectsToNode(node2));
        assertTrue("The edge thinks it's connected to the wrong node", !edge.connectsToNode(node3));

        edge.setSourceNode(node3);
        assertTrue("The edge did not set the source node properly", edge.getSourceNode().equals(node3));
        edge.setTargetNode(node4);
        assertTrue("The edge did not set the target node properly", edge.getTargetNode().equals(node4));
    }

    @Test
    public void createBasicEdgeWithTypeNodeAndAddNodeThatExtendsNode_shouldCompileProperly() {
        Node node1 = new BasicNode("node1");
        ExtendedNode node2 = new ExtendedNode("node2");

        try {
            // this would not compile if the generics were not done properly
            Edge<Node> edge = new BasicEdge<Node>(node1, node2);
            edge.toString();
        } catch(Exception e) {
            fail();
        }
    }

    private class ExtendedNode extends BasicNode {
        public ExtendedNode(String name) {
            super(name);
        }
    }
}
