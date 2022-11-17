package domain;

import view.InputView;
import view.OutputView;

public class LadderGame {
    public void play() {
        Participants participants = Participants.from(InputView.inputParticipant());
        OutputView.printParticipant(participants);
    }
}
