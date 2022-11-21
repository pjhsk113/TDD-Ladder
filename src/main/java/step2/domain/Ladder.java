package step2.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ladder {
    private List<Line> ladder;

    private Ladder(List<Line> ladder) {
        this.ladder = ladder;
    }

    public static Ladder of(Participants participants, int height, LineCreateStrategy strategy) {
        return new Ladder(toLine(participants.getSize(), height, strategy));
    }

    private static List<Line> toLine(int countOfPerson, int height, LineCreateStrategy strategy) {
        return IntStream.range(0, height)
                .mapToObj(value -> Line.of(countOfPerson, strategy))
                .collect(Collectors.toList());
    }

    public Stream<Line> stream() {
        return ladder.stream();
    }
}
