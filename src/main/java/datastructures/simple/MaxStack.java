package DataStructures.SimpleStructures;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

/* Стек — абстрактная структура данных, поддерживающая операции push и pop.
 *       Несложно реализовать стек так, чтобы обе эти операции работали за константное
 *       время. В данной задача ваша цель — расширить интерфейс стека так, чтобы он дополнительно поддерживал операцию max
 *       и при этом чтобы время работы всех операций по-прежнему было константным.
 */
public class MaxStack {

    private Stack<Integer> input = new Stack<>();
    private Stack<Integer> max = new Stack<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = Integer.parseInt(in.nextLine());

        MaxStack stack = new MaxStack();

        for (int i = 0; i < count; i++) {
            String command = in.nextLine();
            if (command.startsWith("push")) {
                stack.push(Integer.parseInt(command.split(" ")[1]));
            }
            if (command.equals("pop")) {
                stack.pop();
            }
            if (command.equals("max")) {
                System.out.println(stack.max());
            }
        }

    }

    public void push(int i) {
        input.push(i);
        if (max.isEmpty() || max() < i) max.push(i);
        else max.push(max.peek());
    }

    public int pop() {
        max.pop();
        return input.pop();
    }

    public int max() {
        if (max.isEmpty()) throw new EmptyStackException();
        return max.peek();
    }

    public boolean isEmpty() {
        return input.isEmpty();
    }
}
