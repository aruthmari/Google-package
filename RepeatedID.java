package com.foobar.challenge;

import java.util.ArrayList;

public class RepeatedID {
	
	static ArrayList<String> al = new ArrayList<String>();
	
	public static int solution(String n, int b) {
		if(al.contains(n)) {
			int res = al.size() - al.indexOf(n);
			return res;
		}
		else {
			al.add(n);
			char[] nChar = n.toCharArray();
			int len = nChar.length;
			return formNewID(sorting(nChar,len),len, b);
		}
	}
	static char[] sorting(char[] nChar,int len) {
		int i,j; char key;		
		for(i=1;i<len;i++) {
			key = nChar[i];
			j = i-1;			
			while(j>=0 && nChar[j]>key) {
				nChar[j+1] = nChar[j];
				--j;
			}
			nChar[j+1] = key;
		}
		return nChar;
	}
	static int formNewID(char[] nChar,int len,int b) {
		String y = new StringBuilder().append(nChar).toString();
		String x = new StringBuilder().append(nChar).reverse().toString();
		
		int xBase = Integer.parseInt(x, b);
		int yBase = Integer.parseInt(y, b);
		String str = Integer.toUnsignedString(xBase - yBase, b);
		
		int diff = len - str.length();
		StringBuilder sb = new StringBuilder();
		if(diff != 0) {
			while (diff >= 1) {
				sb.append(0);
				diff--;
			}
		}
		String newN = sb.append(str).toString();
		return solution(newN,b);
	}
	public static void main(String[] args) {
		System.out.println(solution("210022", 3));
	}
}