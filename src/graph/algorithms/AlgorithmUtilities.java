package graph.algorithms;

import graph.elements.Edge;
import graph.elements.Node;

import java.util.Set;

/**
 * This class contains a set of utility methods that can be used
 * to simplify common tasks performed by graph algorithms.
 * 
 * @author Mathieu LŽger
 * @since Mar 20, 2014
 *
 */
public final class AlgorithmUtilities {

	/*
	 * This class contains only static utility methods. Therefore the constructor is private
	 * so that nobody can call it.
	 */
	private AlgorithmUtilities() {
	}
	
    /**
     * Creates a String that prints out each edge on a specific line. The String starts with a
     * container name, followed by "[" to indicate the start of the container. The String ends
     * with "]" to indicate the end of the container. The string describing each edge is indented
     * with as many spaces as the length of the container name plus 1 (to accommodate the "[" in
     * the initial string.
     * 
     * @param containerName name of the container that encompasses the edges
     * @param edgeSet set of edges found in the container
     * @return a formated String of the edges in the container
     */
    public static <E extends Edge<? extends Node>> String printEdgeSetToString(String containerName, Set<E> edgeSet) {
        String newLine = System.getProperty("line.separator");
        String indent = getEmptyStringOfLength(containerName.length() + 1);

        StringBuilder sb = new StringBuilder();
        sb.append(containerName);
        sb.append("[");
        boolean firstEdge = true;
        for(E edge : edgeSet){
            if(!firstEdge){
                sb.append(indent);
            }
            sb.append(edge.toString());
            sb.append(newLine);
            firstEdge = false;
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * Creates a String of spaces of the specified length
     * 
     * @param length of the string
     * @return string of empty spaces of the specified length
     */
    private static String getEmptyStringOfLength(int length) {
    	StringBuilder sb= new StringBuilder(length);
        for (int i = 0; i < length; i++){
            sb.append(' ');
        }
        return sb.toString();
    }
}
