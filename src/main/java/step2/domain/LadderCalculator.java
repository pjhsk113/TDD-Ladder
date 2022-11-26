package step2.domain;

public class LadderCalculator {
    private LadderCalculator() { }

    public static Result execute(Ladder ladder, Participant participant, Results results) {
        int resultIndex = ladder.execute(participant);
        return results.findResult(resultIndex);
    }
}
