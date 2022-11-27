package step2.domain;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class Results {
    private static final String INPUT_EXCEEDED_EXCEPTION_MESSAGE = "결과값은 참여자보다 많을 수 없습니다.";
    private static final String NOT_MATCHED_RESULT_EXCEPTION_MESSAGE = "매칭된 결과가 없습니다.";
    private static final String COMMA = ",";

    private List<Result> results;

    private Results(List<Result> results) {
        this.results = results;
    }

    public static Results of(String values, int limit) {
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
            throw new IllegalArgumentException(INPUT_EXCEEDED_EXCEPTION_MESSAGE);
        }
    }

    public String getResults() {
        return results.stream()
                .map(result -> String.format("%6s", result.toString()))
                .collect(joining(""));
    }

    public Result findResult(int resultIndex) {
        return results.stream()
                .filter(result -> result.isSameIndex(resultIndex))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_MATCHED_RESULT_EXCEPTION_MESSAGE));
    }

    public boolean isSingle(List<Integer> resultsIndexes) {
        return resultsIndexes.size() == 1;
    }

    public Stream<Result> stream() {
        return results.stream();
    }
}
