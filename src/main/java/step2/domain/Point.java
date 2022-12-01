package step2.domain;

public class Point {
    private int index;
    private boolean divergingPoint;

    private Point(int index, boolean divergingPoint) {
        this.index = index;
        this.divergingPoint = divergingPoint;
    }

    public static Point of(int index, boolean divergingPoint) {
        return new Point(index, divergingPoint);
    }

    public boolean isDivergingPoint() {
        return divergingPoint;
    }

    public int moveStartingIndexDirection(Point nextPoint) {
        if (nextPoint.isDivergingPoint()) {
            return moveRight();
        }

        return notMove();
    }

    public int moveEndIndexDirection() {
        if (this.isDivergingPoint()) {
            return moveLeft();
        }

        return notMove();
    }

    public int moveIndexDirection(Point nextPoint) {
        if (this.isDivergingPoint()) {
            return moveLeft();
        }

        if (nextPoint.isDivergingPoint()) {
            return moveRight();
        }

        return notMove();
    }

    private int moveLeft() {
        return index - 1;
    }

    private int moveRight() {
        return index + 1;
    }

    private int notMove() {
        return index;
    }
}
