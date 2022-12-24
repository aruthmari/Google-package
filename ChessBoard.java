package com.foobar.challenge;

import java.util.LinkedList;
import java.util.Queue;

public class ChessBoard{

	static int len = 8;
	
	static class chip {
		int x,y,moves;
		
		public chip(int x, int y, int moves) {
			this.x = x;
			this.y = y;
			this.moves = moves;
		}
	}
	static boolean boundary(int x, int y, int db, int ub, int lb, int rb) {
		if (x >= ub && x <= db && y >= lb && y <= rb)
			return true;
		return false;
	}
	public static int solution(int src, int dest) {
		if (src == dest)
			return 0;
		
		int[] sInd = findIndex(src), tInd = findIndex(dest);
		int[] xSet = {-1, 1,-2,-2,2, 2,1,-1};
		int[] ySet = {-2,-2,-1, 1,1,-1,2, 2};
		int x = sInd[0],y = sInd[1],dx = tInd[0],dy = tInd[1];chip fi;
		
		Queue<chip> que = new LinkedList<>();
		que.add(new chip(x,y,0));
		
		boolean visited[][] = new boolean[len][len];
		visited[x][y] = true;
		
		int db=0,ub=0,rb=0,lb=0;
		if (x > dx && y > dy){
			db=(x==7)? x:x+1; rb =(y==7)? y:y+1; ub = 0; lb = 0;
		}
		else if (x < dx && y < dy) {
			ub=(x==0)? x:x-1; lb=(y==0)? y:y-1; rb = 7; db = 7;
		}
		else if (x < dx && y > dy) {
			ub=(x==0)? x:x-1; rb=(y==7)? y:y+1; db = 7; lb = 0;
		}
		else if (x > dx && y < dy) {
			db=(x==7)?x:x+1; lb =(y==0)? y:y-1; ub = 0; rb = 7;
		}
		else {
			db = 7;ub = 0;lb = 0; rb = 7;
		}
		while (!que.isEmpty()) {
			fi = que.poll();
			if (fi.x == dx && fi.y == dy)
				return fi.moves;
			for(int j = 0;j<len;j++) {
				x = fi.x + xSet[j];
				y = fi.y + ySet[j];
				if (boundary(x, y,db,ub,lb,rb) && !visited[x][y]) {
					visited[x][y] = true;
					que.add(new chip(x, y, fi.moves + 1));
				}
			}
		}		
		return 4;
	}
	public static int[] findIndex(int num) {
		int x = 0, y = 0;
		for (int i = 1; i <= len; i++) {
			if (num < len * i) {
				x = i - 1;
				break;
			}
		}
		for (int j = 0; j < len; j++) {
			if (num == len * x + j) {
				y = j;
				break;
			}
		}
		int[] arr = { x, y };
		return arr;
	}
	public static void main(String [] args) {
		System.out.println(solution(9,0));
	}
}