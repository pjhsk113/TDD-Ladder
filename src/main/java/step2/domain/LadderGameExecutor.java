package step2.domain;

import step2.view.InputView;
import step2.view.OutputView;

public class LadderGameExecutor {
    public void play() {
        Participants participants = InputView.inputParticipant();
        Results expectResult = InputView.inputResult(participants.getSize());
        int height = InputView.inputHeight();

        Ladder ladder = Ladder.of(participants, height, new LadderLineCreateStrategy());
        OutputView.printLadder(participants, ladder, expectResult);

        Participant target = InputView.inputCheckGameResult(participants);
        Result executeResult = LadderCalculator.execute(ladder, target, expectResult);
        OutputView.printGameResult(executeResult);
    }
}
