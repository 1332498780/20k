package designpattern.behavior.statusmachine;

/**
 * 状态模式
 * 状态机核心：
 *      状态、事件、动作；事件驱动状态改变、执行响应动作
 *      状态模式：把逻辑放到相应状态类做
 * 实现方式：分支逻辑法、查表法、状态模式法
 */
public interface IMario {
    State getName();

    // 定义事件
    void obtainMushRoom(MarioStatusMachine statusMachine);
    void obtainCape(MarioStatusMachine statusMachine);
    void obtainFireFlower(MarioStatusMachine statusMachine);
    void obtainMonster(MarioStatusMachine statusMachine);
}
