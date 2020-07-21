package DayOne20200721;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

	// 常规思路
	public int[] twoSum(int[] nums, int target) {

		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[] { i, j };
				}
			}

		}
		throw new IllegalArgumentException("no result");
	}

	//大神解题
	public int[] TwoSum(int[] nums, int target) {

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				return new int[] { map.get(target - nums[i]), i };
			}
			map.put(nums[i], i);
		}
		throw new IllegalArgumentException("no result");
	}
}
