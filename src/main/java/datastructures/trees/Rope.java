package datastructures.trees;

import static java.lang.String.format;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author mikhail.goncharenko 25.05.18.
 *
 * The AVL-tree with the support the sum on the ranges
 */

public class Rope {

    private static final Node EMPTY_NODE = new Node(0, ' ', null);

    private Node root;

    public void setRoot(Node node) {
            node.parent = null;
            root = node;
    }

    Rope(String str) {
        for (char c : str.toCharArray()) {
            if (root == null) {
                root = new Node(1, str.charAt(0), null);
            }
            else {
                Node node = new Node(1, c, null);
                root = merge(root, node);
            }
        }
    }

    void moveSubstring(int left, int right, int position) {
        Node[] firstSplit = splitTree(root, left);
        Node[] secondSplit = splitTree(firstSplit[1], right - left + 1);
        if (firstSplit[0] == null && secondSplit[1] == null) {
            setRoot(firstSplit[1]);
            return;
        }
        setRoot(merge(firstSplit[0], secondSplit[1]));
        Node[] thirdSplit = splitTree(root, position);
        setRoot(merge(thirdSplit[0], secondSplit[0]));
        setRoot(merge(root, thirdSplit[1]));
    }

    private Node[] splitTree(Node node, int after) {
        if (node == null) {
            return new Node[]{null, null};
        }
        if (after == 0) {
            return new Node[]{null, node};
        }
        if (after <= node.getLeftWeight()) {
            Node[] split = splitTree(node.left, after);
            split[1] = mergeAVLWithRoot(split[1], node.right, node);
            return new Node[]{split[0], split[1]};
        }
        else {
            Node[] split = splitTree(node.right, after - (node.getLeftWeight() + 1));
            split[0] = mergeAVLWithRoot(node.left, split[0], node);
            return new Node[]{split[0], split[1]};
        }
    }

    public Node mergeAVLWithRoot(Node left, Node right, Node newRoot) {
        if (left == null || right == null || Math.abs(getHeight(left) - getHeight(right)) <= 1) {
            return mergeWithRoot(left, right, newRoot);
        }
        else if (getHeight(left) > getHeight(right)) {
            Node r = mergeAVLWithRoot(left.right, right, newRoot);
            left.right = r;
            r.parent = left;
            fixState(r);
            fixState(left);
            return rebalance(left);
        }
        else {
            Node l = mergeAVLWithRoot(left, right.left, newRoot);
            right.left = l;
            l.parent = right;
            fixState(l);
            fixState(right);
            return rebalance(right);
        }
    }

    private Node mergeWithRoot(Node left, Node right, Node newRoot) {
        newRoot.left = left;
        newRoot.right = right;
        if (left != null) {
            left.parent = newRoot;
        }
        if (right != null) {
            right.parent = newRoot;
        }
        fixState(newRoot);
        return newRoot;
    }

    public Node merge(Node left, Node right) {
        if (left == null) return right;
        else if (right == null) return left;
        else {
            Node newRoot;
            if (left.weight >= right.weight) {
                newRoot = findMax(left);
                left = removeMax(left);
            } else {
                newRoot = findMin(right);
                right = removeMin(right);
            }
            return mergeAVLWithRoot(left, right, newRoot);
        }
    }

    private Node removeMax(Node node) {
        if (node.right != null) {
            node.right = removeMax(node.right);
            fixState(node);
            return node;
        }
        else return node.left;
    }

    private Node findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private Node removeMin(Node node) {
        if (node.left != null) {
            node.left = removeMin(node.left);
            fixState(node);
            return node;
        }
        else return node.right;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node rebalance(Node head) {
        if (getFactor(head) == 2) {
            if (getFactor(head.right) < 0) {
                head.right = rotateRight(head.right);
            }
            return rotateLeft(head);
        }
        else if (getFactor(head) == -2) {
            if (getFactor(head.left) > 0) {
                head.left = rotateLeft(head.left);
            }
            return rotateRight(head);
        }
        return head;
    }

    private Node rotateLeft(Node head) {
        Node newHead = head.right;
        head.right = newHead.left;
        if (head.right != null) {
            head.right.parent = head;
        }
        newHead.left = head;
        if (head.parent == null) {
            root = newHead;
        }
        head.parent = newHead;
        fixState(head);
        fixState(newHead);
        return newHead;
    }

    private Node rotateRight(Node head) {
        Node newHead = head.left;
        head.left = newHead.right;
        if (head.left != null) {
            head.left.parent = head;
        }
        newHead.right = head;
        if (head.parent == null) {
            root = newHead;
        }
        fixState(head);
        fixState(newHead);
        return newHead;
    }

    private static class Node {

        Node(int height, char value, Node parent) {
            this.height = height;
            this.value = value;
            this.weight = 1;
            this.parent = parent;
        }

        Node parent;
        Node left;
        Node right;
        int weight;
        int height;
        char value;

        void setValue(char value) {
            this.value = value;
            this.weight = 1;
        }

        int getLeftWeight() {
            return left != null ? left.weight : 0;
        }

        int getRightWeight() {
            return right != null ? right.weight : 0;
        }

        @Override
        public String toString() {
            return value == ' ' ? "{   }" : format("{%d|%s}", weight, value);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Deque<Node> que = new LinkedList<>();
        Node node = root;
        while (true) {
            while (node != null) {
                que.push(node);
                node = node.left;
            }
            if (que.isEmpty()) break;
            node = que.pop();
            result.append(node.value);
            if (node.right == null) {
                node = null;
            }
            else {
                node = node.right;
            }
        }
        return result.toString();
    }

    public void print() {
        StringBuilder result = new StringBuilder();
        Queue<Node> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()) {
            if (isLastLayer(que)) {
                break;
            }
            int count = que.size();
            for (int i = 0; i < count; i++) {
                Node node = que.poll();
                result.append(node + " ");
                que.add(node.left != null ? node.left : EMPTY_NODE);
                que.add(node.right != null ? node.right : EMPTY_NODE);
            }
            result.append("\n");
        }
        System.out.println(result.toString());
    }

    private boolean isLastLayer(Queue<Node> que) {
        return que.stream().allMatch(node -> node.height == 0);
    }

    private static int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private static void fixState(Node head) {
        if (head == null) return;
        head.weight = head.getLeftWeight() + head.getRightWeight() + 1;
        head.height = Math.max(getHeight(head.left), getHeight(head.right)) + 1;
    }

    private static int getFactor(Node node) {
        return getHeight(node.right) - getHeight(node.left);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        Rope rope = new Rope(s);
        int count = Integer.parseInt(in.readLine());
        for (int i = 0; i < count; i++) {
                String[] numbers = in.readLine().split(" ");
                rope.moveSubstring(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]),
                        Integer.parseInt(numbers[2]));
        }
        System.out.println(rope);
    }

}