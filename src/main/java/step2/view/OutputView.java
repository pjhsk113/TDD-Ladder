package step2.view;

import step2.domain.*;

import java.util.stream.Collectors;

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

    public static void printGameResult(Result executeResult) {
        System.out.println("실행 결과");
        System.out.println(executeResult.toString());
    }
}
