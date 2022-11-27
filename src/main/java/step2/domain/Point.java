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

    public int getIndex() {
        return index;
    }

    public int isRightDiverge(Point nextPoint) {
        if (nextPoint.isDivergingPoint()) {
            return nextPoint.getIndex();
        }

        return index;
    }

    public int isLeftDiverge() {
        if (divergingPoint) {
            return index - 1;
        }

        return index;
    }

    public int findDirectionIndex(Point next) {
        if (divergingPoint) {
            return index - 1;
        }

        if (next.isDivergingPoint()) {
            return index + 1;
        }

        return index;
    }
}
