package graph.algorithms;

import static org.junit.Assert.assertEquals;
import graph.components.BasicEdge;
import graph.components.BasicNode;
import graph.elements.Edge;
import graph.elements.Node;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

public class AlgorithmUtilitiesTests {

    @Test
    public void callPrintEdgeSetToStringUtility_ouptutStringShoudBeProperlyFormatted() {

        String newLine = System.getProperty("line.separator");
        String testContainerName = "test";
        String testIndent =  "     ";

        Set<Edge<Node>> edges = new LinkedHashSet<Edge<Node>>();
        BasicEdge<Node> edge1 = new BasicEdge<Node>(new BasicNode("N1"), new BasicNode("N2"));
        BasicEdge<Node> edge2 = new BasicEdge<Node>(new BasicNode("N3"), new BasicNode("N4"));
        edges.add(edge1);
        edges.add(edge2);

        String expected = testContainerName + "[" + edge1 + newLine + testIndent + edge2 + newLine + "]";

        String result = AlgorithmUtilities.printEdgeSetToString(testContainerName, edges);

        assertEquals("The output String is different than expected", expected, result);
    }

}
