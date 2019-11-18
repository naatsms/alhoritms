import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Created by Mikhail Goncharenko on 14.11.2019.
 */
class HowManyNumbers {

  private int[] arr;
  private Set<Long> results;
  private int sum;

  private HowManyNumbers(int sum, int length) {
    this.arr = getArray(length);
    this.sum = sum;
    this.results = new LinkedHashSet<>();
  }

  private static List<Long> findAll(final int sumDigits, final int numDigits) {
    return new HowManyNumbers(sumDigits, numDigits).findAll();
  }

  private List<Long> findAll() {
    for (int i = arr.length - 1; i >= 0; i--) {
      reccurciveCategoryUp(i, 1);
    }
    LongSummaryStatistics stats = results.stream().mapToLong(Long::valueOf).summaryStatistics();

    List<Long> result = new ArrayList<>();
    if (results.isEmpty()) {
      return result;
    }

    result.add(stats.getCount());
    result.add(stats.getMin());
    result.add(stats.getMax());
    return result;
  }

  private void reccurciveCategoryUp(int position, int prevValue) {
    for (int d = prevValue; d<=9; d++) {
      arr[position] = d;
      checkSum();
      if (position < arr.length - 1) {
        reccurciveCategoryUp(position + 1, arr[position]);
      }
    }
  }

  private void checkSum() {
    int total = IntStream.of(arr).sum();
    if (sum == total) {
      results.add(getLong());
    }
  }

  private Long getLong() {
    return Long.valueOf(IntStream.of(arr).
        mapToObj(String::valueOf)
        .collect(Collectors.joining()));
  }

  private static int[] getArray(int num) {
    return IntStream.generate(() -> 1).limit(num).toArray();
  }

}
