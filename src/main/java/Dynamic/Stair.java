/*
* Даны число 1 ≤ N ≤ 10^2 ступенек лестницы и N целых чисел, которыми помечены ступеньки.
* Найдите максимальную сумму, которую можно получить, идя по лестнице снизу вверх (от нулевой до Т-й ступеньки),
* каждый раз поднимаясь на одну или две ступеньки.
*/

import java.util.Scanner;

public class Stair {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        int[] step = new int[count];
        for (int i = 0; i < count; i++) {
            step[i] = in.nextInt();
        }

        int[] cost = new int[count + 1];
        cost[0] = 0;
        cost[1] = step[0];
        for (int i = 2; i <= count; i++) {
            cost[i] = Math.max(cost[i-1] + step[i - 1], cost[i-2] + step[i - 1]);
        }
        System.out.println(cost[count]);
    }



}
