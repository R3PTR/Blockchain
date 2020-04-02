package me.R3PTR.Blockchain;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BlockHeader header = new BlockHeader("This is the Genesisblock", 0, "");
        Block genesisBlock = new Block(header, new ArrayList<>());
        List<Block> genesisBlockchain = new ArrayList<>();
        genesisBlockchain.add(genesisBlock);
        Node node = new Node("GenesisNode", genesisBlockchain);
        Nodes.addNode(node.getName(), node);
        node.addTransaction(new Transaction(new ArrayList<>(), new ArrayList<>()));
        node.mine();
        node.printBlockchain();
    }

    public void nodeList() {
        System.out.println();
        for (String node : Nodes.getNodeList()) {

        }
    }
}