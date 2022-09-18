package designpattern.behavior.statusmachine;

public class SuperMario implements IMario{

    private static final SuperMario instance = new SuperMario();
    private SuperMario(){}
    public static SuperMario getInstance() {
        return instance;
    }
    @Override
    public State getName() {
        return State.SUPER;
    }

    @Override
    public void obtainMushRoom(MarioStatusMachine statusMachine) {
        // do nothing
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
        statusMachine.setCurrentState(SmallMario.getInstance());
        statusMachine.setScore(statusMachine.getScore() - 100);
    }
}
