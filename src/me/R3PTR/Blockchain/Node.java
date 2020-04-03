package me.R3PTR.Blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Blokchain Node, that stores the Blockchain, new Transactions and can mine Blocks
public class Node {

    //A name for the Node to find in Nodes.nodeList(), used for Simulating a network.
    private String nodeName;

    //List of all names from known nodes.
    private List<String> nodes = new ArrayList<>();

    //Copy of the blockchain.
    private List<Block> blockchain;

    //List of all Opentransactions, used when mining a block.
    private List<Transaction> openTransactions = new ArrayList<>();

    //Normal Constructor for Nodes, with one NodeName as reference node, for getting the blockchain.
    public Node(String firstNode, String nodeName) {
        nodes.add(firstNode);
        blockchain = Nodes.getNode(nodes.get(0)).getBlockchain(nodeName, this);
        this.nodeName = nodeName;
    }

    //Used to initialise the first(Genesis) Node
    public Node(String nodeName, List<Block> blockchain) {
        this.nodeName = nodeName;
        this.blockchain = blockchain;
    }

    //Method to addTransaction externally
    public void addTransaction(Transaction transaction) {
        openTransactions.add(transaction);
    }

    //Method used to print the current Blockchain of the Node.
    public void printBlockchain() {
        for (Block block : blockchain) {
            System.out.println(block.toString());
        }
    }

    //Method used to get the full Blockchain from an other node.
    public List<Block> getBlockchain(String name, Node node) {
        nodes.add(name);
        sendAllTransactions(node);
        return blockchain;
    }

    //Method used to send all openTransactions to a given node, used at get Blockchain.
    public void sendAllTransactions(Node node) {
        for (Transaction transaction : openTransactions) {
            node.newTransaction(transaction);
        }
    }

    //Method used to send new Blocks to all Nodes.
    public void sendBlock(Block block) {
        for (String node : nodes) {
            Nodes.getNode(node).newBlock(block);
        }
    }

    //Method to receive new Blocks from other nodes.
    public void newBlock(Block block) {
        if (block.getHash().charAt(0) == '0' && !blockchain.contains(block)) {
            blockchain.add(block);
            for (Transaction transaction : block.getTransactions()) {
                openTransactions.remove(transaction);
            }
            sendBlock(block);
        }
    }

    //Method used to send new Transactions to all nodes.
    public void sendTransaction(Transaction transaction) {
        for (String node : nodes) {
            Nodes.getNode(node).newTransaction(transaction);
        }
    }

    //Method to receive new Transactions from other nodes.
    public void newTransaction(Transaction transaction) {
        if (!openTransactions.contains(transaction)) {
            openTransactions.add(transaction);
            sendTransaction(transaction);
        }
    }

    //Method to mine new Blocks.
    public void mine() {
        System.out.println(String.format("%s: Mining a block.", nodeName));
        Random random = new Random();
        Block block = initBlock();
        while (!block.getHash().startsWith("00000")) {
            block.setNonce(random.nextInt());
        }
        blockchain.add(block);
        sendBlock(block);
        System.out.println(String.format("%s: Finished mining a block.", nodeName));
    }

    //Method to initialise a new Block.
    public Block initBlock() {
        Random random = new Random();
        String previousHash = blockchain.get(blockchain.size() - 1).getHash();
        List<Transaction> blockTransactions = new ArrayList<>();
        for (int i = 0; i < 10 && i < openTransactions.size(); i++) {
            blockTransactions.add(openTransactions.get(i));
        }
        String merkelTree = getMerkelTree(blockTransactions);
        int nonce = random.nextInt();
        BlockHeader header = new BlockHeader(previousHash, nonce, merkelTree);
        return new Block(header, blockTransactions);
    }

    //Method use to calculate the MerkelTree, used in initBlock().
    public String getMerkelTree(List<Transaction> blockTransactions) {
        List<String> hashedTransactions = new ArrayList<>();
        for (Transaction transaction : blockTransactions) {
            hashedTransactions.add(transaction.getHash());
        }
        while (hashedTransactions.size() > 1) {
            List<String> newHashes = new ArrayList<>();
            if (hashedTransactions.size() % 2 != 0) {
                hashedTransactions.add(hashedTransactions.get(hashedTransactions.size() - 1));
            }
            int i1 = 0;
            int i2 = 1;
            while (i2 < hashedTransactions.size()) {
                newHashes.add(Sha256.getHash(hashedTransactions.get(i1) + hashedTransactions.get(i2)));
                i1 += 2;
                i2 += 2;
            }
            hashedTransactions = newHashes;
        }
        return hashedTransactions.get(0);
    }

    //Method that return the name of the node.
    public String getName() {
        return nodeName;
    }

    //Method used to print all BlockHashes.
    public void printBlockHashes() {
        for (Block block : blockchain) {
            System.out.println(block.getHash());
        }
    }
}
