package step2.domain;

import java.util.Arrays;
import java.util.List;

public class LadderCalculator {
    private LadderCalculator() { }

    public static List<Integer> execute(Ladder ladder, Participants participants, String target) {
        if (target.equalsIgnoreCase("all")) {
            return ladder.executeAll(participants);
        }

        return Arrays.asList(
                ladder.execute(participants.findParticipant(target))
        );
    }
}
