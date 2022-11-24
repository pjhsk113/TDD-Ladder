package step2.domain;

public class Point {
    private boolean divergingPoint;

    private Point(boolean divergingPoint) {
        this.divergingPoint = divergingPoint;
    }

    public static Point from(boolean divergingPoint) {
        return new Point(divergingPoint);
    }

    public boolean isDivergingPoint() {
        return divergingPoint;
    }
}
