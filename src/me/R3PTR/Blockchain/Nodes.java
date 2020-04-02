package me.R3PTR.Blockchain;

import java.util.HashMap;

public class Nodes {
    private static HashMap<String, Node> nodeList = new HashMap<>();

    public static Node getNode(String node) {
        return nodeList.get(node);
    }

    public static void addNode(String name, Node node) {
        nodeList.put(name, node);
    }

    public static java.util.Set<String> getNodeList() {
        return nodeList.keySet();
    }
}
