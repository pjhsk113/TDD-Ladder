package step2.view;

import step2.domain.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class OutputView {
    private static final String DIVERGING_POINT_LINE = "-----|";
    private static final String LINE = "     |";
    private static final String NEW_LINE = System.lineSeparator();

    private OutputView() { }

    public static void printLadder(Participants participants, Ladder ladder, Results results) {
        System.out.println("사다리 결과");
        System.out.println(participants.getNames());
        System.out.println(ladderCreationResult(ladder));
        System.out.println(results.getResults());
    }

    private static String ladderCreationResult(Ladder ladder) {
        return ladder.stream()
                .map(line -> lineCreationResult(line))
                .collect(Collectors.joining(NEW_LINE));
    }

    private static String lineCreationResult(Line line) {
        return line.stream()
                .map(point -> point.isDivergingPoint() ? DIVERGING_POINT_LINE : LINE)
                .collect(Collectors.joining(""));
    }

    public static void printGameResult(Participants participants, List<Integer> resultsIndexes, Results results) {
        System.out.println("실행 결과");
        System.out.println(resultsToString(participants, resultsIndexes, results));
    }

    private static String resultsToString(Participants participants, List<Integer> resultsIndexes, Results results) {
        if (results.isSingle(resultsIndexes)) {
            return results.findResult(resultsIndexes.get(0)).toString();
        }

        return resultFormatting(participants, resultsIndexes, results);
    }

    private static String resultFormatting(Participants participants, List<Integer> resultsIndexes, Results results) {
        return IntStream.range(0, resultsIndexes.size())
                .mapToObj(i -> String.format("%s : %s",
                        participants.get(i),
                        results.findResult(resultsIndexes.get(i)).toString()))
                .collect(joining(System.lineSeparator()));
    }
}
