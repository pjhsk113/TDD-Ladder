package domain;

public class Point {
    private boolean point;

    private Point(boolean point) {
        this.point = point;
    }

    public static Point from(boolean point) {
        return new Point(point);
    }

    public boolean isDivergingPoint() {
        return point;
    }
}
