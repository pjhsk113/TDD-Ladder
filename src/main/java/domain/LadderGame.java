package domain;

import view.InputView;
import view.OutputView;

public class LadderGame {
    public void play() {
        Participants participants = InputView.inputParticipant();
        int height = InputView.inputHeight();

        Ladder ladder = Ladder.of(participants, height, new LadderLineCreateStrategy());
        OutputView.printLadder(participants, ladder);
    }
}
