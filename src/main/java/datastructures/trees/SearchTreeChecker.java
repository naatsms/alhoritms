package DataStructures.Trees;

import java.util.Scanner;

/**
 * @author mikhail.goncharenko@masterdata.ru on 16.05.18.
 */
public class SearchTreeChecker {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();

        if (count == 0) {
            System.out.println("CORRECT");
            return;
        }

        Node[] tree = processInputTree(in, count);
        if (checkTree(tree, 0, Integer.MIN_VALUE, Integer.MAX_VALUE)) System.out.println("CORRECT");
        else System.out.println("INCORRECT");
    }

    private static boolean checkTree(Node[] tree, int pivot, int min, int max) {
        Node node = tree[pivot];
        if (node.key > max || node.key < min) return false;
        return checkLow(tree, node, min, node.key) && checkHigh(tree, node, node.key, max);
    }

    private static boolean checkLow(Node[] tree, Node parent, int min, int max) {
        if (parent.left == -1) return true;
        return tree[parent.left].key <= max && checkTree(tree, parent.left, min, max);
    }

    private static boolean checkHigh(Node[] tree, Node parent, int min, int max) {
        if (parent.right == -1) return true;
        return tree[parent.right].key > min && checkTree(tree, parent.right, min, max);
    }


    private static Node[] processInputTree(Scanner in, int count) {
        Node[] tree = new Node[count];
        for (int i = 0; i < count; i++) {
            int key = in.nextInt();
            int left = in.nextInt();
            int right = in.nextInt();
            tree[i] = new Node(key, left, right);
        }
        return tree;
    }

    static class Node {
        int key;
        int left;
        int right;

        public Node(int key, int left, int right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

}
