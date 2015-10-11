package graph.demo;

import graph.algorithms.AlgorithmUtilities;
import graph.algorithms.MinimumSpanningTree;
import graph.algorithms.ShortestPathsToNode;
import graph.components.BasicEdge;
import graph.components.BasicGraph;
import graph.components.BasicNamedEdge;
import graph.components.BasicNode;
import graph.components.BasicWeightedEdge;
import graph.components.BasicWeightedGraph;
import graph.components.NamedWeightedEdge;
import graph.elements.Edge;
import graph.elements.Graph;
import graph.elements.NamedEdge;
import graph.elements.Node;
import graph.elements.WeightedEdge;
import graph.elements.WeightedGraph;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class contains the main class, which runs a series of Demos
 * that demonstrate how to use the code base.
 * 
 * @author Mathieu Léger
 * @since Mar 16, 2014
 */
public final class Demo {

    public static void main(String[] args) {
        String newLine = System.getProperty("line.separator");
        
        /*
         * This package is designed to be a library that can be used to create various types
         * of graphs upon which graph theoretical algorithms can be performed. It currently
         * implements a few basic types of graphs and algorithms. The main goal of this
         * package is to serve as a basic infrastructure that can be easily extended to support
         * many other different types of graphs and algorithms in the future.
         * 
         * Graph theory is a method that is used to describe a system’s topology. To accomplish
         * this, the topology of the system is described using nodes and edges. Nodes are
         * schematically represented by dots and edges are schematically represented by arrows
         * that connect two nodes together.
         * 
         * Nodes are based on the Node interface whose most basic implementation is the 
         * BasicNode.
         */
        Node node1 = new BasicNode("node1");
        System.out.println("This is how a node named 'node1' is displayed:" + newLine 
                         + node1.toString() + newLine);
        
        /*
         * Edges are based on the Edge interface. As there could be many types of nodes,
         * Edge is a generic interface that requires a parameter type that specifies the type of 
         * node being used. It will accept all nodes that extend the Node interface. The basic 
         * implementation of the Edge interface is the BasicEdge.
         */
        Node node2 = new BasicNode("node2");
        Edge<Node> edge1 = new BasicEdge<Node>(node1, node2);
        System.out.println("This is how an edge connecting from the source node 'node1' to the target node 'node2' is displayed:" + newLine 
                 + edge1.toString() + newLine);
        
        /*      
         * There exist other types of edges which extend the basic functionality provided by Edge. For
         * example, there are weighted edges (WeightedEdge interface) and named edges
         * (NamedEdge interface). Both of these edge types are subtypes of Edge and their basic
         * implementations are BasicWeightedEdge and NamedEdge respectively. There could be many
         * other types of edges added to this library in the future. There can also be edges
         * that are a combination of two or more different edge types, such as the named weighted
         * edges that implement both the NamedEdge and WeightedEdge interfaces.
         */
        WeightedEdge<Node> weightedEdge1 = new BasicWeightedEdge<Node>(node1, node2, 100);
        NamedEdge<Node> namedEdge1 = new BasicNamedEdge<Node>("edge1", node1, node2);
        NamedWeightedEdge<Node> namedWeightedEdge1 = new NamedWeightedEdge<Node>("edge1", node1, node2, 100);
        System.out.println("This is how an weighted edge is displayed:" + newLine 
                 + weightedEdge1.toString() + newLine);
        System.out.println("This is how an named edge is displayed:" + newLine 
                 + namedEdge1.toString() + newLine);
        System.out.println("This is how an named weighted edge is displayed:" + newLine 
                 + namedWeightedEdge1.toString() + newLine);
        
        /*   
         * Graphs are based on the Graph interface. The Graph interface is generic and 
         * requires parameter types that specify the type of Node and the type of Edge used. Its 
         * basic implementation is the BasicGraph. It supports any node that is a subtype of Node 
         * and any Edge that is a subtype of Edge. Edges can be added and removed from the graph 
         * using the addEdge(E edge) and removeEdge(E edge) methods. Nodes are added to the graph
         * by virtue of the graph containing an edge that connects to that node. Nodes are removed
         * from the graph when no edges connect anymore to the given node.
         */
        Node n1 = new BasicNode("n3");
        Node n2 = new BasicNode("n4");
        Node n3 = new BasicNode("n3");
        Node n4 = new BasicNode("n4");
        
        Edge<Node> e1 = new BasicEdge<Node>(n1, n2);
        WeightedEdge<Node> we2 = new BasicWeightedEdge<Node>(n2, n3, 100);
        NamedEdge<Node> ne3 = new BasicNamedEdge<Node>("ne3", n3, n4);
        NamedWeightedEdge<Node> nwe4 = new NamedWeightedEdge<Node>("nwe4", n4, n1, 100);
        
        Graph<Node, Edge<Node>> graph1 = new BasicGraph<Node, Edge<Node>>();
        graph1.addEdge(e1);
        graph1.addEdge(we2); // Subtypes of Edge can be added to Graphs using the parameter type of Edge
        graph1.addEdge(ne3);
        graph1.addEdge(nwe4);
        
        System.out.println("This is how a basic graph is displayed:" + newLine 
                 + graph1.toString() + newLine);
        
        /*      
         * Graphs contain many helper methods. Of note are the getAllNodes() and getAllEdges()
         * methods. Both of these methods return sets that are backed by the graph. This
         * means that changes to the graphs' edges and nodes will be reflected in these sets.
         * These types of graph backed sets are very useful, but they can be dangerous in cases
         * where they are modified from outside the graph. For example, adding an edge to the
         * set returned by the getAllEdges() set will add an edge to the graph, but it will not
         * automatically add any of the edge's nodes to the graph. In order to prevent this type
         * of issue, both getAllNodes() and getAllEdges() return unmodifiable sets that throw
         * UnsupportedOperationException if one attempts to call their add() or remove() methods.
         */     
        Set<Edge<Node>> edgeSet = graph1.getAllEdges();
        
        // Edge sets can be visualized in a formatted string using AlgorithmUtilities.printEdgeSetToString()
        System.out.println("This is the set of edges returned by the graph's getAllEdges() method:" + newLine
                + AlgorithmUtilities.printEdgeSetToString("edgeSet", edgeSet) + newLine);
        try {
            edgeSet.add(new BasicEdge<Node>(n1, n3));
        } catch(UnsupportedOperationException e) {
            System.out.println("New edges cannot be added to this edge set because it is unmodifiable" + newLine);
        }

        /* 
         * One more type of graph is currently implemented, the weighted graph (WeightedGraph 
         * interface). It can be noted that weighted graphs can be implemented by using the 
         * following type of graph: BasicGraph<Node, WeightedEdge<Node>>. However, weighted
         * graphs very frequently in graph theory and it is some times useful to have some
         * extra helper methods to better manage a graph's weighted edges. The WeightedGraph 
         * interface is also generic and accepts all subtypes of Node and all subtypes of 
         * WeightedEdge. It is implemented by the BasicWeightedGraph class.
         * UnsupportedOperationException if one attempts to call their add() or remove() methods.
         */  
        WeightedGraph<Node, WeightedEdge<Node>> weightedGraph1 = new BasicWeightedGraph<Node, WeightedEdge<Node>>();
        weightedGraph1.addEdge(we2);
        weightedGraph1.addEdge(nwe4);
        
        System.out.println("This is how a weighted graph is displayed:" + newLine 
                 + weightedGraph1.toString() + newLine);
        
        /* 
         * Using graphs to describe a system's topology allows for the use of a wide variety
         * of graph theoretical algorithms that can help analyze and transform the system.
         * One of the basic types of algorithm in graph theory is an algorithm that finds the
         * minimum spanning tree of the system. A graph's spanning tree consists of a series 
         * of edges that connect to every node in the graph, but does not form a closed loop.
         * A minimum spanning tree is the spanning tree in a weighted graph whose edges have a 
         * combined weight that is the lowest possible for the given graph. Prim's algorithm for 
         * finding a minimal spanning tree is implemented in AlgorithmUtilities.MinimumSpanningTree. 
         * Here is a simple example of how it can be used:
         */  
        Set<WeightedEdge<Node>> wEdges = new LinkedHashSet<WeightedEdge<Node>>();
        wEdges.add(new BasicWeightedEdge<Node>(n1, n2, 1));
        wEdges.add(new BasicWeightedEdge<Node>(n2, n3, 100));
        wEdges.add(new BasicWeightedEdge<Node>(n3, n4, 50));
        wEdges.add(new BasicWeightedEdge<Node>(n4, n1, 3));
        
        WeightedGraph<Node, WeightedEdge<Node>> weightedGraph2 = new BasicWeightedGraph<Node, WeightedEdge<Node>>();
        weightedGraph2.addAllEdges(wEdges);
        System.out.println("This is the graph in which we are searching for the minimal spanning tree: " + newLine + weightedGraph2 + newLine);
        
        MinimumSpanningTree<Node, WeightedEdge<Node>> treeFinder1 = new MinimumSpanningTree<Node, WeightedEdge<Node>>(weightedGraph2);
        Set<WeightedEdge<Node>> tree1 = treeFinder1.getTreeEdges();
        String treeString1 = AlgorithmUtilities.printEdgeSetToString("TreeEdges", tree1);
        System.out.println("These are the tree edges that were found: " + newLine + treeString1 + newLine);
        
        /*
         * Another graph theoretical algorithm currently implemented in this project uses Dijkstra’s algorithm
         * to find the shortest path between a source node and all other nodes in a weighted
         * graph.
         */
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
        
        Graph<Node, WeightedEdge<Node>> shortestPathGraph = new BasicGraph<Node, WeightedEdge<Node>>();
        shortestPathGraph.addAllEdges(edges);
        System.out.println("This is the graph on which we will be applying Dijkstra’s algorithm: " + newLine + shortestPathGraph + newLine);
        
        ShortestPathsToNode<Node, WeightedEdge<Node>> findShortestPaths = new ShortestPathsToNode<Node, WeightedEdge<Node>>(shortestPathGraph, nO);
        Map<Node,Long> shortestPaths = findShortestPaths.getShortestPaths();
        
        System.out.println("These are the shortest path weights between the node " + nO + " and all other nodes in the system" + newLine + shortestPaths.toString() + newLine);
        
        /*
         * As initially noted, this project was designed to be extensible. Many other types 
         * of nodes, edges, and graphs can be added to the current library by extending the
         * existing building blocks. Other graph theoretical algorithms can also easily be added
         * to this project.
         * 
         * Graph theory can be used in many different fields. It can be used by physical simulation 
         * engines to generate the equations used to model mechanical systems and systems of many 
         * other engineering domains. An example of how it can be used to find a mechanical model's
         * optimal modeling variables is shown in the MechanicalSystemModeling project.
         * 
         * Graph theory can also be used in numerical solvers. For example algorithms that find the
         * strongly connected components of a graph (such as Tarjan's algorithm) can be used to 
         * divide the equations to be solved into many smaller sets of equations that can be solved 
         * sequentially instead of having to solve the entire set of equations simultaneously at 
         * each time step.  
         */
    }
}
