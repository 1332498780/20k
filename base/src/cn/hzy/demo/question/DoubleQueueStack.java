package cn.hzy.demo.question;

/***
 * 双队列栈
 *
 * 说明：效率低。每次出栈都需要把其中一个队列的数据转移到另一个队列中，并返回最后一个元素
 */
public class DoubleQueueStack {

    private  Queue<Integer> queueA;
    private  Queue<Integer> queueB;
    private  boolean queueAFlag;
    {
        queueA = new Queue<>();
        queueB = new Queue<>();
    }

    public void push(Integer val){
        if(queueAFlag){
            queueA.push(val);
        }else{
            queueB.push(val);
        }
    }
    public Integer pop(){
        if(queueAFlag){
            while(!queueA.isEmpty()){
                Integer temp = queueA.pop();
                if(queueA.isEmpty()){
                    //队列的最后一个元素
                    queueAFlag = false;
                    return temp;
                }else{
                    queueB.push(temp);
                }
            }
        }else{
            while(!queueB.isEmpty()){
                Integer temp = queueB.pop();
                if(queueB.isEmpty()){
                    //队列的最后一个元素
                    queueAFlag = true;
                    return temp;
                }else{
                    queueA.push(temp);
                }
            }
        }
        return null;
    }
}
