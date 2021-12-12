package test;

import java.util.*;

public class MI {
    public static void main(String[] args) {
//        int[][] nums = {
//                {1,7,11,15},
//                {2,8,17,20},
//                {3,9,22,25},
//                {4,10,26,30},
//                {5,15,32,35},
//        };
//        System.out.println(searchMatrix0(nums, 35));
        HashMap<String, Object> map = new HashMap<>();
        HashMap<Object,  Object> subMap = new HashMap<>();
        HashMap<Object, Object> c = new HashMap<>();
        c.put("c",0);
        subMap.put("b", new Object[]{"v",2,c});
        map.put("a", subMap);
        map.put("d",new Object[]{1,null,3});

        Set<String> strings = showMap(map);
        System.out.println("strings = " + strings);
    }

    public static Set<String> showMap(Map<String, Object> map) {
        //TODO your code goes here...
        Set<String> set = new LinkedHashSet<>();
        return recMap(map, set);
    }

    public static  Set<String> recMap(Object obj, Set<String> set) {
        StringBuilder builder = new StringBuilder();
        String str = null;
        if (set.size() > 0) {
            str = (String) set.toArray()[set.size()-1];
        }
        if (obj instanceof Object[]) {
            if (str != null && str.endsWith("]")) {
                set.remove(str);
                builder.append(str);
            }
            Object[] objArrs = (Object[]) obj;
            for (int i = 0; i < objArrs.length; i++) {
                Object o = objArrs[i];
                StringBuilder bu = new StringBuilder(builder);
                if (o == null || o instanceof Number || o instanceof String) {
                    bu.append("[").append(i).append("]").append(" = ").append(objArrs[i]);
                    set.add(bu.toString());
                } else if (o instanceof Object[]) {
                    bu.append("[").append(i).append("]");
                    set.add(bu.toString());
                    recMap(o,set);
                } else {
                    set.add(bu.append(".").toString());
                    recMap(o,set);
                }
            }
        } else {
            Map<String, Object> map  = (Map<String, Object>)obj;
            for (String key : map.keySet()) {
                Object v = map.get(key);
                if (str != null && str.endsWith(".")) {
                    set.remove(str);
                    builder.append(str);
                }
                builder.append(key);
                if (v instanceof Number || v instanceof String) {
                    builder.append(" = ").append(v);
                    set.add(builder.toString());
                } else if (v instanceof Object[]) {
                    Object[] objArrs = (Object[]) v;
                    for (int i = 0; i < objArrs.length; i++) {
                        Object o = objArrs[i];
                        StringBuilder bu = new StringBuilder(builder);
                        if (o == null ||o instanceof Number || o instanceof String) {
                            bu.append("[").append(i).append("]").append(" = ").append(objArrs[i]);
                            set.add(bu.toString());
                        } else if (o instanceof Object[]) {
                            bu.append("[").append(i).append("]");
                            set.add(bu.toString());
                            recMap(o,set);
                        } else {
                            set.add(bu.append("[").append(i).append("]").append(".").toString());
                            recMap(o,set);
                        }
                    }
                } else {
                    set.add(builder.append(".").toString());
                    recMap(v,set);
                }
            }
        }
        return set;
    }



//    public static boolean searchMatrix(int[][] nums, int x) {
//        // Todo your code goes here...
//        return recurvise(nums,x,0,nums[0].length-1);
//    }
//    public static boolean recurvise(int[][] nums, int x, int row, int col) {
//        int rowLen = nums.length;
//        for (int i = row; i < rowLen; i++) {
//            if (x <= nums[i][col]) {
//                // 确定了某一行
//                for (int j = col; j >= 0; j--) {
//                    if (x == nums[i][j]) {
//                        return true;
//                    } else if (x > nums[i][j]) {
//                        return recurvise(nums,x,i+1,j);
//                    }
//                }
//                break;
//            }
//        }
//        return false;
//    }

    public static boolean searchMatrix0(int[][] nums, int x) {
        // Todo your code goes here...
        if (nums == null ||  nums.length == 0 || nums[0] == null) {
            return  false;
        }
        int rows = nums.length;
        // 先用二分比较最后一列，找到x大于某个值
        int rlow = 0;
        int rhigh = rows;
        while (rlow <= rhigh) {
            int mid = (rlow + rhigh) / 2;
            int[] midArr = nums[mid];
            if (x > midArr[midArr.length - 1]) {
                rlow = mid + 1;
            } else if (x < midArr[midArr.length - 1]) {
                rhigh = mid - 1;
            } else {
                return true;
            }
        }
        // 比最后一列，最后一个值还大，则无值false
        if (rlow > rows) {
            return false;
        }
        // 再用二分比较第一列的值，找到x小于某个值
        int llow = rhigh+1;
        int lhigh = rows;
        while (llow <= lhigh) {
            int mid = (llow + lhigh) / 2;
            int[] midArr = nums[mid];
            if (x > midArr[0]) {
                llow = mid + 1;
            } else if (x < midArr[0]) {
                lhigh = mid - 1;
            } else {
                return true;
            }
        }
        if (lhigh < rhigh+1) {
            return false;
        }
        // 初步筛选是区间[rhigh+1,rows-1]
        // 锁定一个行范围，在该范围内，遍历每行，然后二分查找
        for (int i = rhigh; i <= llow - 1; i++) {
            int[] tmpArrs = nums[i];
            int innerLow = tmpArrs[0];
            int innerHigh =tmpArrs[tmpArrs.length-1];
            while (innerLow <= innerHigh) {
                int mid = (innerLow + innerHigh) / 2;
                if (x > tmpArrs[mid]) {
                    innerLow = mid + 1;
                } else if (x < tmpArrs[mid]) {
                    innerHigh = mid - 1;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
    //    public void recursion(int[] arrs, int left, int n) {
//        if (left > arrs.length - 1 || n <= 0) {
//            return;
//        }
//        if (arrs[left] == n) {
//            count++;
//            return;
//        }
//        for (int i = left; i < arrs.length-1; i++) {
//            recursion(arrs,i+1, n-arrs[i]);
//        }
//    }
}
