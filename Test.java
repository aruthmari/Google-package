package com.foobar.challenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {
	public static void main(String args[]) {
		List<Integer> ar = Arrays.asList(4, 1, 2, 1, 5, 4, 1, 5, 3);
		Map<Integer, Long> mp = ar.stream().collect(Collectors.groupingBy(s -> s, HashMap::new, Collectors.counting()));
		int count = 0;
		for (Long pairs : mp.values()) {
			int rem = (int) (pairs%2);
			if(rem == 0)
				count += pairs;
			else
				count += (pairs-rem);
		}
		System.out.println(count/2);
	}
}