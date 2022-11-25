package step2.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Line {
    private int index;
    private List<Point> line;

    private Line(int index, List<Point> line) {
        this.index = index;
        this.line = line;
    }

    public static Line of(int index, int countOfPerson, LineCreateStrategy strategy) {
        return new Line(index, toPoint(countOfPerson, strategy));
    }

    private static List<Point> toPoint(int countOfPerson, LineCreateStrategy strategy) {
        return createLine(countOfPerson, strategy)
                .stream()
                .map(Point::from)
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

    public Stream<Point> stream() {
        return line.stream();
    }
}
