public class MaxArea {
    public static void main(String[] args){
        System.out.println(new MaxArea().maxArea1(new int[]{1,8,6,2,5,4,8,3,7}));
    }
    //莽夫解法
    public int maxArea(int[] height) {
        int maxRes = 0;
        for(int i=0; i<height.length;i++){
            for(int j=i+1; j<height.length;j++){
                int tempMax = Math.min(height[i],height[j])*(j-i);
                if(tempMax>maxRes)
                    maxRes = tempMax;
            }
        }
        return maxRes;
    }

    //谋士解法
    // 谋士特点：
        // 饱读兵书：见过世面，见过的题多
        // 仔细分析战场形势：根据题型仔细分析
    public int maxArea1(int[] height) {
        int l=0,r=height.length-1;
        int maxRes = 0;
        while(l<r){
            int tempVal = Math.min(height[l],height[r])*(r-l);
            maxRes = Math.max(tempVal,maxRes);
            if(height[l]<height[r])
                l++;
            else
                r--;
        }
        return maxRes;
    }
}
