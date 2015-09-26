package graph.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import graph.elements.Node;

import org.junit.Test;

public class NamedWeightedEdgeTest {

    private static final String EDGE_NAME = "edge1";
    private static final String NEW_EDGE_NAME = "edge2";

    private static final long EDGE_WEIGHT = 1;
    private static final long NEW_EDGE_WEIGHT = 2;

    @Test
    public void createNamedWeightedEdge_allItsSettersAndGettersShouldWork() {

        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");

        NamedWeightedEdge<Node> edge = new NamedWeightedEdge<Node>(EDGE_NAME, node1, node2, EDGE_WEIGHT);

        assertTrue("The edge does not contain the right name", edge.getName().equals(EDGE_NAME));

        edge.setName(NEW_EDGE_NAME);
        assertTrue("The edge does not contain the right name", edge.getName().equals(NEW_EDGE_NAME));

        assertEquals("The edge does not contain the right weight", EDGE_WEIGHT, edge.getWeight());

        edge.setWeight(NEW_EDGE_WEIGHT);
        assertEquals("The edge does not contain the right weight", NEW_EDGE_WEIGHT, edge.getWeight());
    }

    @Test
    public void createNamedWeightedEdgeWithDefaultWeight_weightShouldBeProperlySet() {

        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");

        NamedWeightedEdge<Node> edge = new NamedWeightedEdge<Node>(EDGE_NAME, node1, node2);

        assertEquals("The edge does not contain the right weight", NamedWeightedEdge.DEFAULT_EDGE_WEIGHT, edge.getWeight());
    }

    @Test
    public void createNamedWeightedEdgeWithTypeNodeAndAddNodeThatExtendsNode_shouldCompileProperly() {
        Node node1 = new BasicNode("node1");
        ExtendedNode node2 = new ExtendedNode("node2");

        try {
            // this would not compile if the generics were not done properly
            NamedWeightedEdge<Node> edge = new NamedWeightedEdge<Node>(EDGE_NAME, node1, node2, EDGE_WEIGHT);
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
