package graph.components;

import graph.elements.Node;

/**
 * This class creates a basic node that edges in a graph can connect to.
 * 
 * @author Mathieu LŽger
 * @since Mar 16, 2014
 */
public class BasicNode implements Node {

    /**
     * Name of the edge
     */
    private String name;

    /**
     * Creates a basic node.
     * 
     * @param name of the node.
     * 
     * @throws IllegalArgumentException if name is null.
     */
    public BasicNode(String name){
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Node name is null or empty");
        }

        this.name = name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
