package cn.hzy.demo.sort.common;

public abstract class Sort<T extends Comparable>{

    private Marker marker;

    private T[] array;

    public Sort(String sortName){
        marker = new Marker(sortName);
    }

    public abstract void asc(T[] array);

    public abstract void desc(T[] array);

    public int compareTo(int a,int b){
        marker.compareCount++;
        return array[a].compareTo(array[b]);
    }

    public void swap(int a,int b){
        marker.swapCount++;
        T temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public void sortAsc(T[] array){
        this.array = array;
        long start = System.currentTimeMillis();
        asc(array);
        long end = System.currentTimeMillis();
        marker.calc(start,end);
        marker.print();
    }

    public void sortDesc(T[] array){
        this.array = array;
        long start = System.currentTimeMillis();
        desc(array);
        long end = System.currentTimeMillis();
        marker.calc(start,end);
        marker.print();
    }


    private static class Marker{

        String sortName;
        Marker(String sortName){
            this.sortName = sortName;
        }

        long compareCount;

        long swapCount;

        double cost;

        void print(){
            StringBuilder sb = new StringBuilder();
            sb.append(sortName).append("\n");
            sb.append("耗时：").append(cost).append(" s").append("\n");
            sb.append("比较次数：").append(compareCount).append("\n");
            sb.append("交换次数：").append(swapCount).append("\n");
            System.out.print(sb.toString());
        }

        void calc(long start,long end){
            cost = (end - start)/1000.0d;
        }
    }


}
