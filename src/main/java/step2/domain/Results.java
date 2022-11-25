package step2.domain;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class Results {
    private static final String COMMA = ",";

    private List<Result> results;

    private Results(List<Result> results) {
        this.results = results;
    }

    public static Results from(String values, int limit) {
        List<Result> results = convert(values);
        validate(results, limit);
        return new Results(results);
    }

    private static List<Result> convert(String values) {
        AtomicInteger index = new AtomicInteger();
        return Arrays.stream(values.split(COMMA))
                .map(value -> Result.of(index.getAndIncrement(), value))
                .collect(Collectors.toList());
    }

    private static void validate(List<Result> results, int limit) {
        if (results.size() > limit) {
            throw new IllegalArgumentException("결과값은 참여자보다 많을 수 없습니다.");
        }
    }
    public String getResults() {
        return results.stream()
                .map(result -> String.format("%6s", result.toString()))
                .collect(joining(""));
    }

    public Stream<Result> stream() {
        return results.stream();
    }
}
