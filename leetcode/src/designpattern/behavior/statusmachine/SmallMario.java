package designpattern.behavior.statusmachine;

public class SmallMario implements IMario{

    private static final SmallMario instance = new SmallMario();
    private SmallMario() {}
    public static SmallMario getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.SMALL;
    }

    @Override
    public void obtainMushRoom(MarioStatusMachine statusMachine) {
        statusMachine.setCurrentState(SuperMario.getInstance());
        statusMachine.setScore(statusMachine.getScore() + 100);
    }

    @Override
    public void obtainCape(MarioStatusMachine statusMachine) {
        statusMachine.setCurrentState(CapeMario.getInstance());
        statusMachine.setScore(statusMachine.getScore() + 200);
    }

    @Override
    public void obtainFireFlower(MarioStatusMachine statusMachine) {
        statusMachine.setCurrentState(FireMario.getInstance());
        statusMachine.setScore(statusMachine.getScore() + 300);
    }

    @Override
    public void obtainMonster(MarioStatusMachine statusMachine) {
        // do nothing
    }
}
