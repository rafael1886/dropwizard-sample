package org.pl.dropwizard.config;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Validation<T> {
  private Set<Pair<Predicate<T>, String>> pairs = new HashSet<>();

  public void from(Predicate<T> predicate, String errorMessage) {
    pairs.add(new Pair<>(predicate, errorMessage));
  }

  public Set<String> result(final T param) {
    return pairs.stream()
        .filter(f -> !f.first.test(param))
        .map(m -> m.second)
        .collect(Collectors.toSet());
  }

  private class Pair<T, R> {
    private T first;
    private R second;

    public Pair(T first, R second) {
      this.first = first;
      this.second = second;
    }
  }
}
