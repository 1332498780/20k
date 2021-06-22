package cn.hzy.demo.question;

/***
 * 使用双栈模拟队列操作
 *
 * 说明：如果第一次有大量数据写入，那么第一次从写数据转换为读数据时，会慢。因为需要把之前写的所有数据写入到另外一个栈。
 */
public class DoubleStackQueue {
    private static Stack<Integer> stackA; //写栈
    private static Stack<Integer> stackB; //读栈

    static {
        stackA = new Stack<>();
        stackB = new Stack<>();
    }
    /*

    */

    public static void appendTail(int val){
        stackA.push(val);
    }
    public static int deleteHead(){
        if(!stackB.isEmpty()){
            return stackB.pop();
        }
        while(!stackA.isEmpty()){
            stackB.push(stackA.pop());
        }
        if(!stackB.isEmpty()){
            return stackB.pop();
        }
        return -1;
    }

}
