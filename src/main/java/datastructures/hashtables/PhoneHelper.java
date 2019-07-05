package datastructures.hashtables;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PhoneHelper {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<String, String> map = new HashMap<>();

        int count = Integer.parseInt(in.nextLine());

        for (int i = 0; i < count; i++) {
            String[] s = in.nextLine().split(" ");
            if (s[0].equals("add")) {
                map.put(s[1], s[2]);
            }
            if (s[0].equals("del")) {
                map.remove(s[1]);
            }
            if (s[0].equals("find")) {
                String it = map.get(s[1]);
                if (it != null) System.out.println(it);
                else System.out.println("not found");
            }
        }

    }
}
