package step1.domain;

import step1.view.InputView;
import step1.view.OutputView;

public class LadderGame {
    public void play() {
        Participants participants = InputView.inputParticipant();
        int height = InputView.inputHeight();

        Ladder ladder = Ladder.of(participants, height, new LadderLineCreateStrategy());
        OutputView.printLadder(participants, ladder);
    }
}
