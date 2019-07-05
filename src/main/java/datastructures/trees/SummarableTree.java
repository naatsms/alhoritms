package datastructures.trees;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mikhail.goncharenko 25.05.18.
 *
 * The AVL-tree with the support the sum on the ranges
 */

public class SummarableTree {

    private static final Logger LOG = Logger.getLogger(SummarableTree.class.getSimpleName());

    private static final int INT = 1_000_000_001;
    private Node root;
    private long lastResult = 0;

    private long getNumber(long num) {
      return (lastResult + num) % INT;
    }

    public void add(long num) {
      long number = getNumber(num);
      if (root == null) {
        root = new Node(0, number);
      }
      else {
        root = addInternal(number, root);
      }
    }

    private Node addInternal(long number, Node head) {
      if (head == null) {
        return new Node(1, number);
      }
      if (number == head.value) {
        return head;
      }
      if (number > head.value) {
        head.right = addInternal(number, head.right);
      }
      else {
        head.left = addInternal(number, head.left);
      }
      fixHeight(head);
      fixSum(head);
      return rebalance(head);
    }

    public void delete(long num) {
      root = deleteInternal(root, getNumber(num));
    }

    private Node deleteInternal(Node current, long number) {
      if (current == null) {
        return null;
      }
      if (number == current.value) {
        return removeNode(current);
      }
      if (number < current.value) {
        current.left = deleteInternal(current.left, number);
      }
      else {
        current.right = deleteInternal(current.right, number);
      }

      fixHeight(current);
      fixSum(current);
      return rebalance(current);
    }

    private Node removeNode(Node current) {
      if (current.right != null) {
        Node newHead = findNewHead(current.right);
        newHead.right = removeMin(current.right);
        newHead.left = current.left;
        fixHeight(newHead.right);
        fixSum(newHead.right);
        fixHeight(newHead);
        fixSum(newHead);
        return rebalance(newHead);
      }
      return current.left;
    }

    private Node removeMin(Node node) {
      if (node.left != null) {
        node.left = removeMin(node.left);
        fixSum(node);
        return node;
      }
      else return node.right;
    }

    private Node findNewHead(Node node) {
      while (node.left != null) {
        node = node.left;
      }
      return node;
    }

    public void calc(long left, long right) {
      lastResult = calcInternal(root, getNumber(left), getNumber(right));
      if (LOG.isLoggable(Level.INFO)) {
        LOG.info(Long.toString(lastResult));
      }
    }

    private long calcInternal(Node head, long left, long right) {
      if (head == null) {
        return 0;
      }
      if (head.value < left) {
        return calcInternal(head.right, left, right);
      }
      else if (head.value > right) {
        return calcInternal(head.left, left, right);
      }
      else {
        return head.sum - getLeft(head, left) - getRight(head, right);
      }
    }

    private long getRight(Node node, long right) {
      long result = 0;
      while (node != null) {
        if (node.value > right) {
          result += node.value;
          if (node.right != null) {
            result += node.right.sum;
          }
          node = node.left;
        }
        else {
          node = node.right;
        }
      }
      return result;
    }

    private long getLeft(Node node, long left) {
      long result = 0;
      while (node != null) {
        if (node.value < left) {
          result += node.value;
          if (node.left != null) {
            result += node.left.sum;
          }
          node = node.right;
        }
        else {
          node = node.left;
        }
      }
      return result;
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
      newHead.left = head;
      fixHeight(head);
      fixSum(head);
      fixHeight(newHead);
      fixSum(newHead);
      return newHead;
    }

    private Node rotateRight(Node head) {
      Node newHead = head.left;
      head.left = newHead.right;
      newHead.right = head;
      fixHeight(head);
      fixSum(head);
      fixHeight(newHead);
      fixSum(newHead);
      return newHead;
    }

    public void check(long num) {
      if (findInternal(getNumber(num), root) != null) {
        LOG.info("Found");
      }
      else LOG.info("Not found");
    }

    private Node findInternal(long number, Node currentNode) {
      if (currentNode == null) return null;
      if (currentNode.value == number) {
        return currentNode;
      }
      if (currentNode.left != null && number < currentNode.value) {
        return findInternal(number, currentNode.left);
      }
      else {
        return findInternal(number, currentNode.right);
      }
    }

    private class Node {

      Node(int height, long value) {
        this.height = height;
        this.value = value;
        this.sum = value;
      }

      long sum;
      int height;
      long value;
      Node right;
      Node left;

    }

    private static int getHeight(Node node) {
      if (node == null) {
        return 0;
      }
      return node.height;
    }

    private static void fixHeight(Node head) {
      if (head == null) return;
      head.height = Math.max(getHeight(head.left), getHeight(head.right)) + 1;
    }

    private static void fixSum(Node head) {
      if (head == null) return;
      head.sum = (head.left != null ? head.left.sum : 0)
          + (head.right != null ? head.right.sum : 0)
          + head.value;
    }

    private static int getFactor(Node node) {
      return getHeight(node.right) - getHeight(node.left);
    }

}