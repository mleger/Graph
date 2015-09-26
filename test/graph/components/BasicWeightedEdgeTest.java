package graph.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import graph.elements.Node;
import graph.elements.WeightedEdge;

import org.junit.Test;

public class BasicWeightedEdgeTest {

    private static final long EDGE_WEIGHT = 1;
    private static final long NEW_EDGE_WEIGHT = 2;

    @Test
    public void createBasicWeightedEdge_allItsSettersAndGettersShouldWork() {

        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");

        WeightedEdge<Node> edge = new BasicWeightedEdge<Node>(node1, node2, EDGE_WEIGHT);

        assertEquals("The edge does not contain the right weight", EDGE_WEIGHT, edge.getWeight());

        edge.setWeight(NEW_EDGE_WEIGHT);
        assertEquals("The edge does not contain the right weight", NEW_EDGE_WEIGHT, edge.getWeight());
    }

    @Test
    public void createBasicWeightedEdgeWithDefaultWeight_weightShouldBeProperlySet() {

        Node node1 = new BasicNode("node1");
        Node node2 = new BasicNode("node2");

        WeightedEdge<Node> edge = new BasicWeightedEdge<Node>(node1, node2);

        assertEquals("The edge does not contain the right weight", BasicWeightedEdge.DEFAULT_EDGE_WEIGHT, edge.getWeight(), 0.00001);
    }

    @Test
    public void createBasicNamedEdgeWithTypeNodeAndAddNodeThatExtendsNode_shouldCompileProperly() {
        Node node1 = new BasicNode("node1");
        ExtendedNode node2 = new ExtendedNode("node2");

        try {
            // this would not compile if the generics were not done properly
            WeightedEdge<Node> edge = new BasicWeightedEdge<Node>(node1, node2, EDGE_WEIGHT);
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
