package graph.components;

import static org.junit.Assert.assertTrue;
import graph.elements.Node;

import org.junit.Test;

public class BasicNodeTest {

    private static final String NODE_NAME = "node1";
    private static final String NEW_NODE_NAME = "node2";

    @Test
    public void createBasicNode_allItsSettersAndGettersShouldWork() {

        Node node = new BasicNode(NODE_NAME);

        assertTrue("The node does not contain the right name", node.getName().equals(NODE_NAME));

        node.setName(NEW_NODE_NAME);
        assertTrue("The node did not set its name properly", node.getName().equals(NEW_NODE_NAME));
    }
}
