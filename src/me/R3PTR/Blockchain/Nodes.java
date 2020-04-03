package me.R3PTR.Blockchain;

import java.util.HashMap;

//General Overview of all Nodes, used to simulate a network.
public class Nodes {

    //List of all Nodes
    private static HashMap<String, Node> nodeList = new HashMap<>();

    //Method to get a node from the List, by its name.
    public static Node getNode(String node) {
        return nodeList.get(node);
    }

    //Method to add new Nodes
    public static void addNode(String name, Node node) {
        nodeList.put(name, node);
    }

    //Method to get all Nodes.
    public static java.util.Set<String> getNodeList() {
        return nodeList.keySet();
    }
}
