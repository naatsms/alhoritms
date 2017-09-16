import java.util.Arrays;
import java.util.Scanner;

/**
 * Первая строка содержит количество предметов 1 ≤ n ≤ 10^3 и вместимость рюкзака 0 ≤ W ≤ 2⋅10^6.
 * Каждая из следующих n строк задаёт стоимость 0 ≤ ci ≤ 2⋅10^6 и объём 0 < wi ≤ 2⋅10^6 предмета (n, W, ci, wi — целые числа).
 * Выведите максимальную стоимость частей предметов (от каждого предмета можно отделить любую часть, стоимость и объём
 * при этом пропорционально уменьшатся), помещающихся в данный рюкзак, с точностью не менее трёх знаков после запятой.
 */
public class Backpack {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cnt = in.nextInt();
        double backpack = in.nextInt();
        double totalCost = 0.000d;

        Item[] arr = new Item[cnt];

        for (int i = 0; i < cnt; i++) {
            arr[i] = new Item(in.nextInt(), in.nextInt());
        }
        Arrays.sort(arr);

        for(Item item : arr) {
            if (item.space < backpack) {
                backpack -= item.space;
                totalCost += item.cost;
                continue;
            }
            else if (backpack != 0) {
                totalCost += (backpack / item.space) * item.cost;
                break;
            }
            break;
        }

        System.out.printf("%.3f", totalCost);
    }
}

class Item implements Comparable<Item>{
    double space;
    double cost;

    Item (int a, int b) {
        cost = a;
        space = b;
    }

    @Override
    public int compareTo(Item b) {
        if (cost * 1.0f / space == b.cost * 1.0f / b.space) return 0;
        return (cost * 1.0f / space < b.cost * 1.0f / b.space) ? 1 : -1;
    }
}