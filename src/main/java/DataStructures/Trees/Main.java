package DataStructures.Trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Goncharenko Mikhail, created on 24.07.2018.
 * 
 */
public class Main {

  Node root;
  private long lastResult = 0;

  public static void main(String[] args) throws IOException {
    Main tree = new Main();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int steps = Integer.parseInt(reader.readLine());
    for (int i = 0; i < steps; i++) {
      String line = reader.readLine();
      switch (line.charAt(0)) {
        case '?': 
            tree.check(Long.parseLong(line.substring(2)));
            break;
        case '+': 
            tree.add(Long.parseLong(line.substring(2)));
            break;
        case '-': 
            tree.delete(Long.parseLong(line.substring(2)));
            break;
        case 's':
            tree.calc(Long.parseLong(line.substring(line.indexOf(' ') + 1, line.lastIndexOf(' '))), Long.parseLong(line.substring(line.lastIndexOf(' ') + 1)));
            break;
      }
    }
    reader.close();
  }

  public void add(long num) {
    long number = getNumber(num);
    if (root == null) {
      root = new Node(0, number);
    }
    else {
      root = addInternal(number, root);
    }
    fixSum(root);
  }
  
  public void delete(long num) {
    long number = getNumber(num);
    root = deleteInternal(root, number);
    fixSum(root);
  }

  public void check(long num) {
    long number = getNumber(num);
    if (checkInternal(number)) {
      System.out.println("Found");
    }
    else System.out.println("Not found");
  }

  private boolean checkInternal(long number) {
    Node node = find(number);
    return node != null && node.value == number;
  }

  public void calc(long left, long right) {
    long l = getNumber(left);
    long r = getNumber(right);
    long result = calcInternal(root, l, r);
    lastResult = result;
    System.out.println(result);
  }

  private long getNumber(long num) {
    return (lastResult + num) % 1_000_000_001;
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
    return rebalance(head);
  }

  private Node rebalance(Node head) {
    int lHeight = head.left != null ? head.left.height : 0;
    int rHeight = head.right != null ? head.right.height : 0;
    if (lHeight - rHeight == 2) {
      int llHeight = (head.left != null && head.left.left != null) ? head.left.left.height : 0;
      int lrHeight = (head.left != null && head.left.right != null) ? head.left.right.height : 0;
      if (llHeight >= lrHeight) {
        return smallRightRotation(head);
      }
      else return largeRightRotation(head);
    }
    else if (rHeight - lHeight == 2) {
      int rlHeight = (head.right != null && head.right.left != null) ? head.right.left.height : 0;
      int rrHeight = (head.right != null && head.right.right != null) ? head.right.right.height : 0;
      if (rlHeight > rrHeight) {
        return largeLeftRotation(head);
      }
      else return smallLeftRotation(head);
    }
    return head;
  }

  private Node largeLeftRotation(Node head) {
    Node newHead = head.right.left;
    Node newRight = head.right;
    newRight.left = newHead.right;
    head.right = newHead.left;
    newHead.right = newRight;
    newHead.left = head;
    fixHeight(head);
    fixHeight(newRight);
    fixHeight(newHead);
    return newHead;
  }

  private Node largeRightRotation(Node head) {
    Node newHead = head.left.right;
    Node newLeft = head.left;
    newLeft.right = newHead.left;
    head.left = newHead.right;
    newHead.left = newLeft;
    newHead.right = head;
    fixHeight(head);
    fixHeight(newLeft);
    fixHeight(newHead);

    return newHead;
  }

  private Node smallLeftRotation(Node head) {
    Node newHead = head.right;
    head.right = newHead.left;
    newHead.left = head;
    fixHeight(head);
    fixHeight(newHead);
    return newHead;
  }

  private Node smallRightRotation(Node head) {
    Node newHead = head.left;
    head.left = newHead.right;
    newHead.right = head;
    fixHeight(head);
    fixHeight(newHead);
    return newHead;
  }

  private void fixHeight(Node head) {
    if (head == null) return;
    int hl = 0;
    int hr = 0;
    if (head.left != null) {
      hl = head.left.height;
    }
    if (head.right != null) {
      hr = head.right.height;
    }
    head.height = Math.max(hl, hr) + 1;
  }

  private long fixSum(Node head) {
    if (head == null) return 0;
    long hl = 0;
    long hr = 0;
    if (head.left != null) {
      hl = fixSum(head.left);
    }
    if (head.right != null) {
      hr = fixSum(head.right);
    }
    head.sum = head.value + hl + hr;
    return head.sum;
  }

  private Node deleteInternal(Node current, long number) {
    if (current == null) {
      return null;
    }
    if (number == current.value) {
      if (current.right != null) {
        Node newHead = findNewHead(current.right);
        newHead.right = removeMin(current.right);
        newHead.left = current.left;
        fixHeight(newHead.left);
        fixHeight(newHead.right);
        fixHeight(newHead);
        return rebalance(newHead);
      }
      else if (current.left != null) {
        return current.left;
      }
      else return null;
    }

    if (number < current.value) {
      current.left = deleteInternal(current.left, number);
    }
    else {
      current.right = deleteInternal(current.right, number);
    }
    fixHeight(current);
    return rebalance(current);
  }

  private Node removeMin(Node node) {
    if (node.left != null) {
      node.left = removeMin(node.left);
      return node;
    }
    else if (node.right != null) {
      return node.right;
    }
    return null;
  }

  private Node findNewHead(Node node) {
    if (node.left != null) {
      return findNewHead(node.left);
    }
    return node;
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
      return calculate(head, left, right);
    }
  }

  private long calculate(Node head, long left, long right) {
    long l = calcLower(head.left, left);
    long r = calcHigher(head.right, right);
    return head.sum - l - r;
  }

  private long calcLower(Node head, long left) {
    long result = 0;
    while (head != null) {
      if (head.value < left) {
        result += head.value;
        if (head.left != null) {
          result += head.left.sum;
        }
        head = head.right;
      }
      else {
        head = head.left;
      }
    }
    return result;
  }

  private long calcHigher(Node head, long right) {
    long result = 0;
    while (head != null) {
      if (head.value > right) {
        result += head.value;
        if (head.right != null) {
          result += head.right.sum;
        }
        head = head.left;
      }
      else {
        head = head.right;
      }
    }
    return result;
  }

  private Node find(long number) {
    return findInternal(number, root);
  }

  private Node findInternal(long number, Node currentNode) {
    if (currentNode == null) return null;
    if (currentNode.value == number) {
      return currentNode;
    }
    if (currentNode.left != null && number < currentNode.value) {
      return findInternal(number, currentNode.left);
    }
    else if (currentNode.right != null && number > currentNode.value ) {
      return findInternal(number, currentNode.right);
    }
    return currentNode;
  }

  class Node {

    public Node(int height, long value) {
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
}