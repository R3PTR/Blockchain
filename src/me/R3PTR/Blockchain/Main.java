package me.R3PTR.Blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    //Main method, creating GenesisBlock and the first Node, then waiting for input.
    public static void main(String[] args) {
        BlockHeader header = new BlockHeader("This is the Genesisblock", 0, "");
        Block genesisBlock = new Block(header, new ArrayList<>());
        List<Block> genesisBlockchain = new ArrayList<>();
        genesisBlockchain.add(genesisBlock);
        Node genesisNode = new Node("Genesis", genesisBlockchain);
        Nodes.addNode(genesisNode.getName(), genesisNode);
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("Possible Inputs: a: NodeList, b: newNode, c: newTransactions, d: newBlock, e: printBlockchain, f: getBlockHashes");
            input = scanner.next();
            if (input.equalsIgnoreCase("exit"))
                break;
            if (input.equalsIgnoreCase("NodeList") || input.equalsIgnoreCase("a")) {
                nodeList();
            } else if (input.equalsIgnoreCase("newNode") || input.equalsIgnoreCase("b")) {
                System.out.println("Which name should the Node have?");
                String name = scanner.next();
                Node node = new Node(genesisNode.getName(), name);
                Nodes.addNode(node.getName(), node);
            } else if (input.equalsIgnoreCase("newTransactions") || input.equalsIgnoreCase("c")) {
                System.out.println("How many Transactions should be generated?");
                int amount = scanner.nextInt();
                generateNewTransactions(amount);
            } else if (input.equalsIgnoreCase("newBlock") || input.equalsIgnoreCase("d")) {
                System.out.println("Which Node should mine a Block?");
                String nodeName = scanner.next();
                Nodes.getNode(nodeName).mine();
            } else if (input.equalsIgnoreCase("printBlockchain") || input.equalsIgnoreCase("e")) {
                System.out.println("Which Node should print the Blockchain?");
                String nodeName = scanner.next();
                Nodes.getNode(nodeName).printBlockchain();
            } else if (input.equalsIgnoreCase("printBlockHashes") || input.equalsIgnoreCase("f")) {
                System.out.println("Which Node should print the Blockhashes?");
                String nodeName = scanner.next();
                Nodes.getNode(nodeName).printBlockHashes();
            }
        }
    }

    //Method used to generate an given amount of random Transactions.
    private static void generateNewTransactions(int amount) {
        Random random = new Random();
        for (int i1 = 0; i1 < amount; i1++) {
            int input = random.nextInt(5) + 1;
            List<String> inputs = new ArrayList<>();
            List<String> outputs = new ArrayList<>();
            int sum = 0;
            for (int i2 = 0; i2 < input; i2++) {
                int randomInt = random.nextInt(1000000) + 1;
                inputs.add(String.valueOf(randomInt));
                sum += randomInt;
            }
            int send = random.nextInt(sum) + 1;
            int recieve = sum - send;
            outputs.add(String.valueOf(send));
            outputs.add(String.valueOf(recieve));
            Transaction transaction = new Transaction(inputs, outputs);
            Nodes.getNode("Genesis").newTransaction(transaction);
        }
    }

    //Method to print the List of all Nodes.
    public static void nodeList() {
        System.out.println("NodeList:");
        for (String node : Nodes.getNodeList()) {
            System.out.println(node);
        }
    }
}