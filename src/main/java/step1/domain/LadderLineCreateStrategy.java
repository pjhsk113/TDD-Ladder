package step1.domain;

import java.util.Random;

public class LadderLineCreateStrategy implements LineCreateStrategy {
    private static final Random random = new Random();

    @Override
    public boolean createLine(boolean prev) {
        if (prev) {
            return false;
        }

        return random.nextBoolean();
    }
}
