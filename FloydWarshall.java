package com.foobar.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FloydWarshall {
	static int size;
	static List <Integer> res;
	public static void main(String[] args) {
		int[][] times = {{0,-1,3,2,1,4}, {1,0,2,1,2,1}, {1,2,0,3,5,2}, {5,4,1,0,2,-1}, {1,1,2,2,0,1}, {1,2,-1,3,1,0}};
		System.out.println(Arrays.toString(solution(times, 2)));
	}
	public static int[] solution(int[][] times, int times_limit) {
	    int len = times.length;
	    if (len <= 2 || (len != times[0].length)) {
	        return new int[] {};
	    }
	    boolean negativeWeigh = checking(times, len);
	    if (negativeWeigh) {
	        int[] res = new int[len - 2];
	        for (int i = 0; i < len - 2; i++) {
	            res[i] = i;
	        }
	        return res;
	    } else {
	        size = 0;
	        res = new ArrayList < >();
	        boolean[] visit = new boolean[len];
	        visit[0] = true;
	        for (int i = 1; i < len - 1; i++) {
	            depthpath(i, times_limit - times[0][i], times, new ArrayList < >(), visit);
	        }
	        if (res.size() == 0) {
	            return new int[] {};
	        }
	        int[] ret = new int[res.size()];
	        for (int i = 0; i < ret.length; i++) {
	            ret[i] = res.get(i);
	        }
	        Arrays.sort(ret);
	        return ret;
	    }
	}
	public static void depthpath(int i, int time, int[][] times, List < Integer > list, boolean[] visit) {
	
	    int len = times.length;
	    if (time <= -999 || (i == len - 1 && time < 0) || (size == len - 2)) {
	        return;
	    }
	    if (time >= 0 && i == len - 1) {
	        if (list.size() > size) {
	            res = new ArrayList < >(list);
	            size = list.size();
	        }
	        return;
	    }
	    if (visit[i]) {
	        return;
	    }
	    visit[i] = true;
	    list.add(i - 1);
	    for (int v = 1; v < len; ++v) {
	        if (v == i) {
	            continue;
	        }
	        depthpath(v, time - times[i][v], times, list, visit);
	    }
	    list.remove(list.size() - 1);
	    visit[i] = false;
	}
	public static boolean checking(int[][] times, int n) {
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            for (int k = 0; k < n; k++) {
	                if (times[i][k] + times[k][j] < times[i][j]) {
	                    times[i][j] = times[i][k] + times[k][j];
	                }
	            }
	        }
	    }
	    for (int i = 0; i < n; i++) {
	        if (times[i][i] < 0) {
	            return true;
	        }
	    }
	    return false;
	}
}