package me.R3PTR.Blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BlockHeader header = new BlockHeader("This is the Genesisblock", 0, "");
        Block genesisBlock = new Block(header, new ArrayList<>());
        List<Block> genesisBlockchain = new ArrayList<>();
        genesisBlockchain.add(genesisBlock);
        Node genesisNode = new Node("GenesisNode", genesisBlockchain);
        Nodes.addNode(genesisNode.getName(), genesisNode);
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("Possible Inputs: NodeList, newNode, newTransactions, newBlock");
            input = scanner.next();
            if (input.equalsIgnoreCase("exit"))
                break;
            if (input.equalsIgnoreCase("NodeList")) {
                nodeList();
            } else if (input.equalsIgnoreCase("newNode")) {
                System.out.println("Which name should the Node have?");
                String name = scanner.next();
                Node node = new Node(genesisNode.getName(), name);
                Nodes.addNode(node.getName(), node);
            } else if (input.equalsIgnoreCase("newTransactions")) {
                System.out.println("How many Transactions should be generated?");
                int amount = scanner.nextInt();
                generateNewTransactions(amount);
            }
        }
    }

    private static void generateNewTransactions(int amount) {
        Random random = new Random();
        int inputs = random.nextInt(5) + 1;
        for (int i = 0; i < inputs; i++) {
            
        }
    }

    public static void nodeList() {
        System.out.println("NodeList:");
        for (String node : Nodes.getNodeList()) {
            System.out.println(node);
        }
    }
}