package step2.domain;

import step2.view.InputView;
import step2.view.OutputView;

public class LadderGame {
    public void play() {
        Participants participants = InputView.inputParticipant();
        int height = InputView.inputHeight();

        Ladder ladder = Ladder.of(participants, height, new LadderLineCreateStrategy());
        OutputView.printLadder(participants, ladder);
    }
}
