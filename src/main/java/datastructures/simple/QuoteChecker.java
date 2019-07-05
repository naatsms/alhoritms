package datastructures.simple;

import java.util.*;

public class QuoteChecker {
    final static Map<Character, Character> map = new HashMap<>();

    static {
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
    }

    public static void main(String[] args) {
        Scanner in = new Scanner("([](){([])})");
        String input = in.nextLine();
        in.close();

        int result = check(input);
        if (result == 0) System.out.println("Success");
        else System.out.println(result);
    }

    public static int check(String input) {
        Stack<Node> stack = new Stack<>();
        char[] arr = input.toCharArray();
        int result = 0;
        for (char c : arr) {
            result++;
            if (map.containsKey(c)) {
                stack.push(new Node(c, result));
            }
            else {
                if (stack.isEmpty()) {
                    if (map.containsValue(c)) return result;
                    else continue;
                }
                else if (c == map.get(stack.peek().value)) stack.pop();
                else if (map.containsValue(c)) return result;
            }
        }
        if (stack.isEmpty()) return 0;
        else return stack.peek().position;
    }

    static class Node {
        Character value;
        int position;

        public Node(Character value, int position) {
            this.value = value;
            this.position = position;
        }
    }
}
