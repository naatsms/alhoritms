import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Created by Mikhail Goncharenko on 07.08.19.
 */
public class MorseCodeDecoder {

  public static String decodeBitsAdvanced(String bits) {
    String trimmed = bits.replaceAll("(^0+)|(0+$)", "");
    if (trimmed.length() == 0) {
      return "";
    }
    List[] rates = findRate(trimmed);
    if (!rates[2].isEmpty()) {
      trimmed = trimmed.replaceAll(String.format("0{%d,%d}", rates[2].get(0), rates[2].get(rates[2].size() - 1)), "   ");
    }
    if (!rates[1].isEmpty()) {
      trimmed = trimmed.replaceAll(String.format("1{%d,%d}", rates[1].get(0), rates[1].get(rates[1].size() - 1)), "-")
          .replaceAll(String.format("0{%d,%d}", rates[1].get(0), rates[1].get(rates[1].size() - 1)), " ");
    }
    return trimmed.replaceAll(String.format("1{%d,%d}", rates[0].get(0), rates[0].get(rates[0].size() - 1)), ".")
        .replaceAll(String.format("0{%d,%d}", rates[0].get(0), rates[0].get(rates[0].size() - 1)), "");
  }

  private static List[] findRate(String bits) {
    TreeSet<Integer> wholeSet = new TreeSet<>();
    Matcher matcher = Pattern.compile("1+|0+").matcher(bits);
    while (matcher.find()) {
      wholeSet.add(matcher.group().length());
    }
    return getSubSets(new LinkedList<>(wholeSet));
  }

  private static List[] getSubSets(List<Integer> whole) {
    List<Integer>[] lists = new List[]{new ArrayList<Integer>(),new ArrayList<Integer>(),new ArrayList<Integer>()};
    double sum = 0;
    double newSum = 0;
    double[] medians = getInitialMeans(whole);

    do {
      sum = newSum;
      Arrays.stream(lists).forEach(List::clear);
      rearrange(whole, lists, medians);
      newSum = getTotalSqrtDeviations(lists);
      medians = Arrays.stream(lists).mapToDouble(MorseCodeDecoder::getMean).toArray();
    } while (sum != newSum);

    return lists;
  }

  private static double[] getInitialMeans(List<Integer> whole) {
    double[] result = new double[3];
    result[0] = whole.get(0);
    result[2] = Math.max(whole.get(whole.size() - 1), result[0] * 7.0);
    // awful brutforcing stuff
    result[1] = whole.size() >= 6 ? (result[0] + result[2] / 7) * 1.95 : result[0] * 3.0;
    return result;
  }

  private static void rearrange(List<Integer> list, List<Integer>[] subLists, double means[]) {
    for (Integer integer : list) {
      double distance1 = Math.abs(integer - means[0]);
      double distance2 = Math.abs(integer - means[1]);
      double distance3 = Math.abs(integer - means[2]);
      if (distance1 <= distance2 && distance1 < distance3) {
        subLists[0].add(integer);
      } else if (distance2 <= distance3) {
        subLists[1].add(integer);
      } else {
        subLists[2].add(integer);
      }
    }
  }

  private static double getMean(List<Integer> list) {
    return list.stream().mapToInt(Integer::valueOf).average().orElse(0);
  }

  private static double getTotalSqrtDeviations(List<Integer>[] lists) {
    return Arrays.stream(lists).mapToDouble(list -> {
      double mean = getMean(list);
      return list.stream().mapToDouble(integer -> Math.pow(integer - mean, 2)).sum();
    }).sum();
  }

  public static String decodeMorse(String morseCode) {
    if (morseCode.length() == 0) {
      return "";
    }
    return Stream.of(morseCode.split("\\s{3}"))
        .map(MorseCodeDecoder::getSymbol)
        .collect(Collectors.joining(" "));
  }

  private static String getSymbol(String word) {
    return Stream.of(word.split(" ")).map(MorseCode::get).collect(Collectors.joining());
  }

  static class MorseCode {

    private MorseCode() {
    }

    static String get(String symbol) {
      return "Implement dictionary" + symbol;

    }
  }

}