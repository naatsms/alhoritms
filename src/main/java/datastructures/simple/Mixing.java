package datastructures.simple;

import java.util.*;
import java.util.stream.*;

/**
 * @author Created by Mikhail Goncharenko on 05.08.19.
 */
public class Mixing {

  static Map<String, Entry> dictionary;

  public static String mix(String s1, String s2) {
    dictionary = new HashMap<>();
    s1.replaceAll("[^a-z]","").chars().mapToObj(ch -> String.valueOf((char)ch)).forEach(ch -> dictionary.merge(ch, new Entry(ch,1, 0), Entry::merge));
    s2.replaceAll("[^a-z]","").chars().mapToObj(ch -> String.valueOf((char)ch)).forEach(ch -> dictionary.merge(ch, new Entry(ch,0, 1), Entry::merge));
    return dictionary.values().stream().filter(a -> a.getValue() > 1)
        .sorted()
        .map(Entry::toString)
        .collect(Collectors.joining("/"));
  }

  static class Entry implements Comparable<Entry> {
    String ch;
    int countFirst;
    int countSecond;

    @Override
    public String toString() {
      if (countFirst > countSecond) {
        return "1:" + getStr(countFirst);
      }
      else if (countSecond > countFirst) {
        return "2:" + getStr(countSecond);
      }
      else return "=:" + getStr(countFirst);
    }

    private String getStr(int number) {
      return Stream.generate(() -> ch).limit(number).collect(Collectors.joining());
    }

    public Entry(String ch, int a, int b) {
      countFirst = a;
      countSecond = b;
      this.ch = ch;
    }

    public static Entry merge(Entry f, Entry s) {
      return new Entry(f.ch,f.countFirst + s.countFirst, f.countSecond + s.countSecond);
    }

    public int getValue() {
      return Math.max(countFirst, countSecond);
    }

    public int getPriority() {
      return countFirst > countSecond ? -1 : countSecond > countFirst ? 0 : 1;
    }

    @Override
    public int compareTo(Entry o) {
      return Comparator.comparing(Entry::getValue)
          .reversed()
          .thenComparing(Entry::getPriority)
          .thenComparing(entry -> entry.ch).compare(this, o);
    }
  }

}