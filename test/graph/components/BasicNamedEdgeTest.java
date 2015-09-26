package graph.components;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import graph.elements.NamedEdge;
import graph.elements.Node;

import org.junit.Test;

public class BasicNamedEdgeTest {

    private static final String EDGE_NAME = "edge1";
    private static final String NEW_EDGE_NAME = "edge2";

    @Test
    public void createBasicNamedEdge_allItsSettersAndGettersShouldWork() {

        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");

        NamedEdge<Node> edge = new BasicNamedEdge<Node>(EDGE_NAME, node1, node2);

        assertTrue("The edge does not contain the right name", edge.getName().equals(EDGE_NAME));

        edge.setName(NEW_EDGE_NAME);
        assertTrue("The edge does not contain the right name", edge.getName().equals(NEW_EDGE_NAME));
    }

    @Test
    public void createBasicNamedEdgeWithTypeNodeAndAddNodeThatExtendsNode_shouldCompileProperly() {
        Node node1 = new BasicNode("node1");
        ExtendedNode node2 = new ExtendedNode("node2");

        try {
            // this would not compile if the generics were not done properly
            NamedEdge<Node> edge = new BasicNamedEdge<Node>(EDGE_NAME, node1, node2);
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
