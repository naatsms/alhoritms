import java.util.Scanner;
import java.util.TreeMap;

/**
 * Восстановите строку по её коду и беспрефиксному коду символов.
 *
 * В первой строке входного файла заданы два целых числа kk и ll через пробел — количество различных букв,
 * встречающихся в строке, и размер получившейся закодированной строки, соответственно.
 * В следующих kk строках записаны коды букв в формате "letter: code". Ни один код не является префиксом другого.
 * Буквы могут быть перечислены в любом порядке. В качестве букв могут встречаться лишь строчные буквы латинского алфавита;
 * каждая из этих букв встречается в строке хотя бы один раз. Наконец, в последней строке записана закодированная строка.
 * Исходная строка и коды всех букв непусты. Заданный код таков, что закодированная строка имеет минимальный возможный размер.
 *
 *
 * В первой строке выходного файла выведите строку ss. Она должна состоять из строчных букв латинского алфавита.
 * Гарантируется, что длина правильного ответа не превосходит 104104 символов.
 */
public class HoffmanDecode {

    void go(){
        Scanner in = new Scanner(System.in);
        in.useDelimiter("(: *|\\s)");
        int charCount = in.nextInt();
        int stringSize = in.nextInt();

        TreeMap<String, Character> codeMap = new TreeMap<>();

        for (int i = 0; i < charCount; i++) {
            char a = in.next().charAt(0);
            String b = in.next();
            codeMap.put(b, a);
        }

        String codeString = in.next();

        StringBuilder result = new StringBuilder();

    for(int i = 0, j = 0; i < stringSize;) {
        while (!codeMap.containsKey(codeString.substring(i, j))) j++;
        result.append(codeMap.get(codeString.substring(i, j)));
        i = j;
        j = i;
    }
        System.out.println(result.toString());
    }

    public static void main(String[] args) {
        new HoffmanDecode().go();
    }

}
