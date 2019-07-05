package datastructures.trees;

import java.util.Scanner;

/**
 * @author mikhail.goncharenko@masterdata.ru on 16.05.18.
 */
public class TreeTraverses {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int count = in.nextInt();

        Node[] tree = processInputTree(in, count);
        System.out.println(preOrderTraversal(new StringBuilder(), tree, 0).toString());
        System.out.println(inOrderTraversal(new StringBuilder(), tree, 0).toString());
        System.out.println(postOrderTraversal(new StringBuilder(), tree, 0).toString());

    }

    private static StringBuilder preOrderTraversal(StringBuilder sb, Node[] tree, int pivot) {
        Node node = tree[pivot];
        if (node.left != -1) {
            preOrderTraversal(sb, tree, node.left);
        }
        sb.append(node.key);
        sb.append(" ");
        if (node.right != -1) {
            preOrderTraversal(sb, tree, node.right);
        }
        return sb;
    }

    private static StringBuilder inOrderTraversal(StringBuilder sb, Node[] tree, int pivot) {
        Node node = tree[pivot];
        sb.append(node.key);
        sb.append(" ");
        if (node.left != -1) {
            inOrderTraversal(sb, tree, node.left);
        }
        if (node.right != -1) {
            inOrderTraversal(sb, tree, node.right);
        }
        return sb;
    }

    private static StringBuilder postOrderTraversal(StringBuilder sb, Node[] tree, int pivot) {
        Node node = tree[pivot];
        if (node.left != -1) {
            postOrderTraversal(sb, tree, node.left);
        }
        if (node.right != -1) {
            postOrderTraversal(sb, tree, node.right);
        }
        sb.append(node.key);
        sb.append(" ");
        return sb;
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
