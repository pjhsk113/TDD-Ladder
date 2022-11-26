package step2.domain;

import step2.view.InputView;
import step2.view.OutputView;

import java.util.List;

public class LadderGameExecutor {
    public void play() {
        Participants participants = InputView.inputParticipant();
        Results expectResult = InputView.inputResult(participants.getSize());
        int height = InputView.inputHeight();

        Ladder ladder = Ladder.of(participants, height, new LadderLineCreateStrategy());
        OutputView.printLadder(participants, ladder, expectResult);

        String target = "";
        while(!target.equalsIgnoreCase("all")) {
            target = InputView.inputCheckGameResult();
            List<Integer> resultIndexes = LadderCalculator.execute(ladder, participants, target);
            OutputView.printGameResult(participants, resultIndexes, expectResult);
        }
    }
}
