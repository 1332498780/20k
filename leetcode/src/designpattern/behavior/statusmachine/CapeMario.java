package designpattern.behavior.statusmachine;

public class CapeMario implements IMario {
    private static final CapeMario instance = new CapeMario();
    private CapeMario(){};
    public static CapeMario getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.CAPE;
    }

    @Override
    public void obtainMushRoom(MarioStatusMachine statusMachine) {
        // do nothing
    }

    @Override
    public void obtainCape(MarioStatusMachine statusMachine) {
        // do nothing
    }

    @Override
    public void obtainFireFlower(MarioStatusMachine statusMachine) {
        // do nothing
    }

    @Override
    public void obtainMonster(MarioStatusMachine statusMachine) {
        statusMachine.setCurrentState(SmallMario.getInstance());
        statusMachine.setScore(statusMachine.getScore() - 200);
    }
}
