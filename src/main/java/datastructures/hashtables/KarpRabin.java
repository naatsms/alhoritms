package datastructures.hashtables;

import java.util.Scanner;

/**
 * @author mikhail.goncharenko@masterdata.ru on 07.05.18.
 */
public class KarpRabin {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String pattern = in.nextLine();
        String text = in.nextLine();

        int l = pattern.length();
        int patternHash = initialHash(pattern, 0, l);
        int rollingHash = 0;
        
        for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
            if (i == 0) rollingHash = initialHash(text, 0, l);
            else {
                rollingHash += text.charAt(i + l - 1);
                rollingHash -= text.charAt(i - 1);
            }
            if (rollingHash == patternHash && text.regionMatches(i, pattern, 0, l)) {
                System.out.print(i + " ");
            }
        }
    }

    public static int initialHash(String text, int first, int last) {
        int result = 0;
        for (int i = first; i < last; i++) {
            result += text.charAt(i);
        }
        return result;
    }

    public static int hash(String s) {
        int hash = 0;
        for (char ch : s.toCharArray()) {
            hash += ch;
        }
        return hash;
    }

}
