package step2.domain;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

    public String resultToString(Participants participants, List<Integer> resultsIndexes) {
        if (isSingle(resultsIndexes)) {
            return findResult(resultsIndexes.get(0)).toString();
        }

        return resultFormatting(participants, resultsIndexes);
    }

    private String resultFormatting(Participants participants, List<Integer> resultsIndexes) {
        return IntStream.range(0, resultsIndexes.size())
                .mapToObj(i -> String.format("%s : %s",
                        participants.get(i),
                        findResult(resultsIndexes.get(i)).toString()))
                .collect(joining(System.lineSeparator()));
    }

    private Result findResult(int resultIndex) {
        return results.stream()
                .filter(result -> result.isSameIndex(resultIndex))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("매칭된 결과가 없습니다."));
    }

    private boolean isSingle(List<Integer> resultsIndexes) {
        return resultsIndexes.size() == 1;
    }
    public Stream<Result> stream() {
        return results.stream();
    }
}
