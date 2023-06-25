package Task04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RedBlack {

    private Node root;

    private enum NodeColor{
        R, B
    }

    class Node{
        int value;
        Node left;
        Node right;
        NodeColor color;
    }

    public boolean add(int value){

        if (root != null) {
            boolean result = addNode(root, value);
            root = rebalance(root);
            root.color = NodeColor.B;
            return result;
        }
        else {
            root = new Node();
            root.color = NodeColor.B;
            root.value = value;
            return true;
        }
    }

    private boolean addNode(Node node, int value){
        if (node.value == value){
            return false;
        }
        else {
            if (node.value > value){
                if (node.left != null){
                    boolean result = addNode(node.left, value);
                    node.left = rebalance(node.left);
                    return result;
                }
                else{
                    node.left = new Node();
                    node.left.color = NodeColor.R;
                    node.left.value = value;
                    return true;
                }
            }
            else {
                if (node.right != null){
                    boolean result = addNode(node.right, value);
                    node.right = rebalance(node.right);
                    return result;
                }
                else{
                    node.right = new Node();
                    node.right.color = NodeColor.R;
                    node.right.value = value;
                    return true;
                }
            }
        }
    }

    private Node rebalance(Node node){

        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.right != null && result.right.color == NodeColor.R &&
                (result.left == null || result.left.color == NodeColor.B)){
                    needRebalance = true;
                    result = rightSwap(result);
                }
            if (result.left != null && result.left.color == NodeColor.R &&
                result.left.left != null && result.left.left.color == NodeColor.R){
                    needRebalance = true;
                    result = leftSwap(result);
                }
            if (result.left != null && result.left.color == NodeColor.R && 
                result.right != null && result.right.color == NodeColor.R){
                    needRebalance = true;
                    colorSwap(result);
            }
        }
        while (needRebalance);
        return result;
    }

    private Node rightSwap(Node node){
        Node rightCh = node.right;
        Node temp = rightCh.left;
        rightCh.left = node;
        node.right = temp;
        rightCh.color = node.color;
        node.color = NodeColor.R;
        return rightCh;
    }

    private Node leftSwap(Node node){
        Node leftCh = node.left;
        Node temp = leftCh.right;
        leftCh.right = node;
        node.left = temp;
        leftCh.color = node.color;
        node.color = NodeColor.R;
        return leftCh;

    }

    private void colorSwap(Node node){
        node.right.color = NodeColor.B;
        node.left.color = NodeColor.B;
        node.color = NodeColor.R;
    }
// Блок вывода на экран
    class BTreePrinter {

    public static <T extends Comparable<?>> void printNode(Node root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<Node> newNodes = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node != null) {
                if (node.color == NodeColor.R){
                    System.out.print("\033[31m" + node.value + "\033[0m");
                }
                else {
                    System.out.print(node.value);
                }
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(Node node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
}
// Блок вывода на экран

    public static void main(String[] args) {
        final RedBlack tree = new RedBlack();
        for (int i = 0; i < 10; i++) {
            tree.add(i);
            BTreePrinter.printNode(tree.root);
        }
        
}
}

