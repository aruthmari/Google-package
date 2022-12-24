package com.foobar.challenge;

import java.math.BigInteger;

public class FuelPelletDivider {
	public static int solution(String x) {
		int count = 0,condn = 0;
		BigInteger num = new BigInteger(x);
		BigInteger three = new BigInteger("3");
		BigInteger four = new BigInteger("4");
		while(num.compareTo(BigInteger.ONE) > 0) {
			if(num.equals(three)) {
				return count += 2;
			}
			if(num.remainder(BigInteger.TWO).equals(BigInteger.ONE)) {
				String currNum = num.toString();
				int len = currNum.length();
				if(len >= 2)
					condn = (Integer.parseInt(currNum.substring(len-2))-1)%4;
				else
					condn = (Integer.parseInt(currNum)-1)%4;
				num = (condn == 0)? num.subtract(BigInteger.ONE):num.add(BigInteger.ONE);
				if(count == 0) {
					if(num.toString().length() > 309)
						num = num.subtract(four);
				}					
				count++;
			}
			num = num.divide(BigInteger.TWO);
			count++;
		}
		return count;
	}
	public static void main(String[] args) {
		String s = "15";
		System.out.println(solution(s));
	}
}

/*	 	int count = 0;
		BigInteger num = new BigInteger(x);
		BigInteger one = BigInteger.ONE;
		BigInteger two = BigInteger.TWO;
		while(num.compareTo(one) > 0) {
			if(num.equals(one.add(two))) {
				num = two;
				count++;
			}
			if(num.remainder(two).equals(one)) {
				num = (num.subtract(one).remainder(two.add(two)).compareTo(one) == -1)? num.subtract(one):num.add(one);
				if(count == 0) {
					if(num.toString().length() > 309)
						num = num.subtract(two.add(two));
				}					
				count++;
			}
			num = num.divide(two);
			count++;
		}
		return count;
*/
