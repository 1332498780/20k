package cn.hzy.demo.sort.common;

public abstract class Sort<T extends Comparable>{

    private Marker marker;

    public T[] array;

    public Sort(String sortName,T[] array){
        marker = new Marker(sortName);
        this.array = array;
    }

    public abstract void asc();

    public abstract void desc();

    public abstract void upgrade();

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

    public void printPre(int count){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<count;i++){
            sb.append(array[i]).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
    }

    public void printPost(int count){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<count;i++){
            sb.append(array[array.length-1-i]).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
    }

    public void sortAsc(){
        long start = System.currentTimeMillis();
        asc();
        long end = System.currentTimeMillis();
        marker.calc(start,end);
        marker.print();
    }

    public void sortDesc(){
        long start = System.currentTimeMillis();
        desc();
        long end = System.currentTimeMillis();
        marker.calc(start,end);
        marker.print();
    }

    public void sortUpgrade(){
        long start = System.currentTimeMillis();
        upgrade();
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
