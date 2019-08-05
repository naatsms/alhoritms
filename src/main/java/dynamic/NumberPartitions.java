package dynamic;

import java.util.*;
import java.util.stream.*;

public class NumberPartitions {

  static Map<Long, Set<Long>> partitions = new TreeMap<>();

  public static String part(long n) {
    List<Long> products = getPartitions(n).stream()
                                          .sorted()
                                          .collect(Collectors.toList());

    LongSummaryStatistics stats = products.stream().mapToLong(Long::longValue).summaryStatistics();
    return String.format("Range: %d Average: %.2f Median: %.2f",
        stats.getMax() - stats.getMin(),
        stats.getAverage(),
        getMedian(products, (int) stats.getCount()));
  }

  private static float getMedian(List<Long> products, int count) {
    if (count % 2 == 0) {
      return (products.get(count / 2) + products.get(count / 2 - 1)) / 2.0f;
    }
    else return products.get(count / 2);
  }

  static Set<Long> getPartitions(long number) {
    if (partitions.containsKey(number)) {
      return partitions.get(number);
    }

    Set<Long> result = new HashSet<>();
    for (int i = (int) number; i >= number / 2; i--) {
      if (number - i > i) break;
      if (i == number) {
        result.add((long) i);
      }
      else {
        long j = number - i;
        result.addAll(getPartitions(i).stream()
              .flatMap(a -> getPartitions(j).stream().map(b -> a * b))
              .collect(Collectors.toSet()));
      }
    }
    partitions.put(number, result);
    return partitions.get(number);
  }

}