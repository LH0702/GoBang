package model.algorithms;

public class Step {
    private int x;
    private int y;
    private int score;

    public Step(int x, int y, int score) {
        this.x = x;
        this.y = y;
        this.score = score;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getScore() {
        return score;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
