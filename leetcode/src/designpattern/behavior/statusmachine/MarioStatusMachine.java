package designpattern.behavior.statusmachine;

public class MarioStatusMachine {
    private int score;
    private IMario currentState;

    MarioStatusMachine() {
        this.score = 0;
        this.currentState = SmallMario.getInstance();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public IMario getCurrentState() {
        return currentState;
    }

    public void setCurrentState(IMario currentState) {
        this.currentState = currentState;
    }

    public void obtainMushRoom() {
        this.currentState.obtainMushRoom(this);
    }

    public void obtainCape() {
        this.currentState.obtainCape(this);
    }

    public void obtainFireFlower() {
        this.currentState.obtainFireFlower(this);
    }

    public void obtainMonster() {
        this.currentState.obtainMonster(this);
    }

}
