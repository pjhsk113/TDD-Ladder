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
                .mapToObj(index -> Line.of(countOfPerson, strategy))
                .collect(Collectors.toList());
    }

    public Stream<Line> stream() {
        return ladder.stream();
    }

    public int execute(Participant participant) {
        int targetIndex = participant.getParticipationNumber();

        for (int i = 0; i < ladder.size(); i++) {
            targetIndex = ladder.get(i).move(targetIndex);
        }

        return targetIndex;
    }

    public List<Integer> executeAll(Participants participants) {
        return participants.stream()
                .map(participant -> execute(participant))
                .collect(Collectors.toUnmodifiableList());
    }
}
