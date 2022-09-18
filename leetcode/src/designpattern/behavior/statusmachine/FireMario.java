package designpattern.behavior.statusmachine;

public class FireMario implements IMario{
    private static final FireMario instance = new FireMario();
    private FireMario(){}
    public static FireMario getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.FIRE;
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
        statusMachine.setScore(statusMachine.getScore() - 300);
    }
}
