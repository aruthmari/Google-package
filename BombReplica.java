package com.foobar.challenge;

import java.math.BigInteger;

public class BombReplica {
	public static String solution(String m, String f) {
		BigInteger mach = new BigInteger(m);
		BigInteger facula = new BigInteger(f);
		BigInteger count = BigInteger.ZERO;
		if (mach.compareTo(facula) > 0)
			count = replica(mach,facula,count);
		else
			count = replica(facula,mach,count);
		if (count.compareTo(BigInteger.ZERO) > 0)
			return count.toString();
		else
			return "impossible";
	}
	public static BigInteger replica(BigInteger b1,BigInteger b2,BigInteger count) {
		if (b1.equals(BigInteger.ONE) && b2.equals(BigInteger.ONE))
			return count;
		if (b2.equals(BigInteger.ONE))
			return count = count.add(b1).subtract(b2);
		if (b1.remainder(b2).equals(BigInteger.ZERO) || b1.equals(b2))
			return BigInteger.ZERO;
		else {
			count = count.add(b1.divide(b2));
			b1 = b1.remainder(b2);
			return count = replica(b2,b1,count);
		}
	}
	public static void main(String[] args) {
		System.out.println(solution("1234556447","123456789"));
	}
}
