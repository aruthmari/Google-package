package com.foobar.challenge;

public class IdCreating {
	public static String solution(int n) {
		String prime = " 235";
		int re, de = 0;
		for (int i = 7; prime.length() <= 10005; i += 2) {
			re = i % 10;
			if (re == 2 || re == 4 || re == 5 || re == 6 || re == 8)
				continue;
			else {
				int di = 3;
				while (di <= i / 2) {
					if (i % di == 0) {
						de = 0;
						break;
					} else
						de = 1;
					di += 2;
				}
			}
			if (de == 1)
				prime += i;
		}
		String id = prime.substring(n + 1, n + 6);
		return id;
	}
	public static void main(String[] args) {
		System.out.println(solution(0));
	}
}
