package step2.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Line {
    private List<Point> line;

    private Line(List<Point> line) {
        this.line = line;
    }

    public static Line of(int countOfPerson, LineCreateStrategy strategy) {
        return new Line(toPoint(countOfPerson, strategy));
    }

    private static List<Point> toPoint(int countOfPerson, LineCreateStrategy strategy) {
        AtomicInteger index = new AtomicInteger();
        return createLine(countOfPerson, strategy)
                .stream()
                .map(divergingPoint -> Point.of(index.getAndIncrement(), divergingPoint))
                .collect(Collectors.toList());
    }

    private static List<Boolean> createLine(int countOfPerson, LineCreateStrategy strategy) {
        List<Boolean> points = new ArrayList<>();
        boolean prev = false;
        points.add(prev);
        for (int i = 1; i < countOfPerson; i++) {
            prev = strategy.createLine(prev);
            points.add(prev);
        }

        return points;
    }

    public int move(int targetIndex) {
        int prevIndex = targetIndex - 1;
        int nextIndex = targetIndex + 1;

        if (prevIndex < 0) {
            return getPoint(targetIndex).isRightDiverge(getPoint(nextIndex));
        }

        if (nextIndex > line.size() - 1) {
            return getPoint(targetIndex).isLeftDiverge();
        }

        return getPoint(targetIndex).findDirectionIndex(getPoint(nextIndex));
    }

    private Point getPoint(int targetIndex) {
        return line.get(targetIndex);
    }

    public Stream<Point> stream() {
        return line.stream();
    }
}
