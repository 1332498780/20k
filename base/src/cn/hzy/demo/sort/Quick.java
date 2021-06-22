package cn.hzy.demo.sort;

import cn.hzy.demo.sort.common.GenerateData;
import cn.hzy.demo.sort.common.Sort;

import java.util.LinkedList;


/***
 * 优化过2次：
 *      第一次：由于在最坏的情况下（原数组倒叙，排为升序），如果还是默认数组第一个做为基准数，那么就变成
 *      斜树了，导致时间复杂度沦为n^2,更为致命的是压栈次数太多，直接stackOverFlow,所以改为基准数的选取
 *      改为取，第一个，中间，最后一个元素的中间值，降低最坏情况的出现概率。
 *      第二次：接着第一次改进，虽然降低概率了，但是如果待排序数组过大，还是会StackOverFlow。所以这次改为
 *      非递归遍历，使用栈做为容器，再大的数组也不会栈溢出了。
 *
 * 稳定性：不稳定。假定a,b 2个相同值在右侧，由于右侧是从后往前遍历，所以很肯定，b先交换到左侧，而后a又交换到左侧，这样顺序就变成
 * b,a了。
 * 时间复杂度：nlogn。每次待交换元素个数为n，需要跑logn趟(也就是二叉树的高度)
 */
public class Quick extends Sort<Integer> {


    public Quick(Integer[] array) {
        super("快速排序", array);
    }

    @Override
    public void asc() {
        ascNormal(0,array.length-1);
    }

    @Override
    public void desc() {
        descNormal(0,array.length-1);
    }

    @Override
    public void upgrade() {}

    public static void main(String[] args) throws InterruptedException {
        Integer[] array = GenerateData.da(GenerateData.w10);
//        Integer[] array = new Integer[]{10,9,8,7,6,5,4,3,2,1};
        Quick quick = new Quick(array);
        quick.printPre(20);
        quick.sortDesc();
        quick.printPre(20);
//        System.out.println(quick.pivot(0,9));
    }

    private int pivot(int start,int end){
        int mid = (end + start) >> 1;
        //三数取中
        if(compareTo(start,mid)==1){
            if(compareTo(start,end) == 1){
                if(compareTo(mid,end) == 1){
                    return mid;
                }else{
                    return end;
                }
            }else{
                return start;
            }
        }else{
            if(compareTo(start,end) == 1){
                return start;
            }else{
                if(compareTo(mid,end) == 1){
                    return end;
                }else{
                    return mid;
                }
            }
        }
    }

    /**
     * 递归进行升序
     * @param start
     * @param end
     */
    private void recursionAsc(int start,int end){
        int mid = pivot(start,end);
        int midVal = array[mid];
        boolean fromRight = true;
        int i = start,j = end;
        while(i<j){
            if(fromRight){
                if(j==mid){
                    fromRight = !fromRight;
                    continue;
                }
                if(compareValTo(array[j],midVal)==-1){
                    array[mid] = array[j];
                    mid = j;
                    fromRight = !fromRight;
                }
                j--;
            }else{
                if(i==mid){
                    fromRight = !fromRight;
                    continue;
                }
                if(compareValTo(array[i],midVal)==1){
                    array[mid] = array[i];
                    mid = i;
                    fromRight = !fromRight;
                }
                i++;
            }
        }
        //i==j时，上边没有做比较操作来确定mid值和i==j值的位置关系，所以下面补充了判断
        if(i<mid && compareTo(i,mid) ==  1){
            array[mid] = array[i];
            mid = i;
        }else if(j>mid && compareTo(j,mid) == -1){
            array[mid] = array[j];
            mid = j;
        }
        array[mid] = midVal;
        if((mid-1) - start > 0){
            recursionAsc(start,mid-1);
        }
        if(end - (mid+1) > 0){
            recursionAsc(mid+1,end);
        }
    }

    private void findBaseIndexAsc(LinkedList<Node> list,Node node){
        int start = node.start,end = node.end;
        int mid = pivot(start,end);
        int midVal = array[mid];
        boolean fromRight = true;
        int i = start,j = end;
        while(i<j){
            if(fromRight){
                if(j==mid){
                    fromRight = !fromRight;
                    continue;
                }
                if(compareValTo(array[j],midVal)==-1){
                    array[mid] = array[j];
                    mid = j;
                    fromRight = !fromRight;
                }
                j--;
            }else{
                if(i==mid){
                    fromRight = !fromRight;
                    continue;
                }
                if(compareValTo(array[i],midVal)==1){
                    array[mid] = array[i];
                    mid = i;
                    fromRight = !fromRight;
                }
                i++;
            }
        }
        //i==j时，上边没有做比较操作来确定mid值和i==j值的位置关系，所以下面补充了判断
        if(i<mid && compareTo(i,mid) ==  1){
            array[mid] = array[i];
            mid = i;
        }else if(j>mid && compareTo(j,mid) == -1){
            array[mid] = array[j];
            mid = j;
        }
        array[mid] = midVal;
        if((mid-1) - start > 0){
            list.offer(new Node(start,mid-1));
//            recursionAsc(start,mid-1);
        }
        if(end - (mid+1) > 0){
            list.offer(new Node(mid+1,end));
//            recursionAsc(mid+1,end);
        }
    }

    private void findBaseIndexDesc(LinkedList<Node> list,Node node){
        int start = node.start,end = node.end;
        int mid = pivot(start,end);
        int midVal = array[mid];
        boolean fromRight = true;
        int i = start,j = end;
        while(i<j){
            if(fromRight){
                if(j==mid){
                    fromRight = !fromRight;
                    continue;
                }
                if(compareValTo(array[j],midVal)==1){
                    array[mid] = array[j];
                    mid = j;
                    fromRight = !fromRight;
                }
                j--;
            }else{
                if(i==mid){
                    fromRight = !fromRight;
                    continue;
                }
                if(compareValTo(array[i],midVal)==-1){
                    array[mid] = array[i];
                    mid = i;
                    fromRight = !fromRight;
                }
                i++;
            }
        }
        //i==j时，上边没有做比较操作来确定mid值和i==j值的位置关系，所以下面补充了判断
        if(i<mid && compareTo(i,mid) ==  -1){
            array[mid] = array[i];
            mid = i;
        }else if(j>mid && compareTo(j,mid) == 1){
            array[mid] = array[j];
            mid = j;
        }
        array[mid] = midVal;
        if((mid-1) - start > 0){
            list.offer(new Node(start,mid-1));
        }
        if(end - (mid+1) > 0){
            list.offer(new Node(mid+1,end));
        }
    }

    class Node{
        int start;
        int end;
        public Node(int start,int end){
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 非递归进行升序
     * @param start
     * @param end
     */
    private void ascNormal(int start,int end){
        LinkedList<Node> list = new LinkedList<>();
        list.offer(new Node(start,end));
        while(!list.isEmpty()){
            findBaseIndexAsc(list,list.poll());
        }
    }

    /**
     * 非递归进行降序
     * @param start
     * @param end
     */
    private void descNormal(int start,int end){
        LinkedList<Node> list = new LinkedList<>();
        list.offer(new Node(start,end));
        while(!list.isEmpty()){
            findBaseIndexDesc(list,list.poll());
        }
    }
}
