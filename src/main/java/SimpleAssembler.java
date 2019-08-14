import static org.junit.Assert.assertEquals;

import java.util.*;

public class SimpleAssembler {

  public static Map<String, Integer> interpret(String[] program){
    Map<String, Integer> register = new HashMap<>();
    for (int i = 0; i < program.length; i++) {
      i = executeCommand(program, register, i);
    }
    return register;
  }

  private static int executeCommand(String[] program, Map<String, Integer> register,
      int i) {
    String[] args = program[i].split(" ");
    switch (args[0]) {
      case "mov":
        if (args[2].matches("[a-z]")) {
          register.put(args[1], register.get(args[2]));
        }
        else {
          register.put(args[1], Integer.parseInt(args[2]));
        }
        break;
      case "inc":
        register.put(args[1], register.get(args[1]) + 1);
        break;
      case "dec":
        register.put(args[1], register.get(args[1]) - 1);
        break;
      case "jnz":
        if ((args[1].matches("[a-z]") && register.get(args[1]) != 0) || args[1].matches("[1-9]")) {
          i = --i + Integer.parseInt(args[2]);
        }
    }
    return i;
  }

  public static void main(String[] args) {
    String[] program = new String[]{"mov a 5","inc a","dec a","dec a","jnz a -1","inc a"};
    Map<String, Integer> out = new HashMap<>();
    out.put("a", 1);
    assertEquals(out, SimpleAssembler.interpret(program));
  }

}