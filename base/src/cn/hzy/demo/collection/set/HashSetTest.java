package cn.hzy.demo.collection.set;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.HashSet;

public class HashSetTest {
    public static void main(String[] args) {
        HashSet<R> hashSet = new HashSet<>();

        R r1 = new R(3);
        R r2 = new R(5);
        R r3 = new R(8);
        R r4 = new R(9);
        hashSet.add(r1);
        hashSet.add(r2);
        hashSet.add(r3);
        hashSet.add(r4);
        System.out.println(hashSet);

        //修改3元素变成5，此时set中存在相同值5
        r1.count = 5;
        System.out.println(hashSet);

        //删除元素5，其实删除的原来的5
        hashSet.remove(new R(5));

        //false,由于原来的5被删除，而新修改的5的hashcode不会重新计算，还是3
        System.out.println(hashSet.contains(new R(5)));
        //由于值已经变成5，hashcode还是3
        System.out.println(hashSet.contains(new R(3)));
    }

    static class R{
        int count;
        public R(int count){
            this.count = count;
        }

        @Override
        public int hashCode(){
            return count;
        }

        @Override
        public boolean equals(Object object){
            if(this == object){
                return true;
            }
            if(object != null){
                if(object instanceof R){
                    R r = (R)object;
                    if(r.hashCode() == hashCode() && r.count == count){
                        return true;
                    }
                }
            }
            return false;
        }
        @Override
        public String toString(){
            return count+"";
        }
    }
}
