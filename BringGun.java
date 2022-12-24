package com.foobar.challenge;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BringGun {
	public static void main(String[] args) {
		System.out.println(solution(new int[] {3,2}, new int[] {1,1},new int[] {2,1}, 4));
	}
	private static class Loc {
		int x;
		int y;

		public Loc(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int dist() {
			return x * x + y * y;
		}

		public Loc barry() {
			int c = gcd((x<0)?	x*-1:x, (y<0)?	y*-1:y);
			return new Loc(x / c, y / c);
		}

		private static int gcd(int a, int b) {
			if (b == 0)
				return a;
			return gcd(b, a % b);
		}
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Loc locn = (Loc) o;
			return x == locn.x && y == locn.y;
		}
		public int hashCode() {
			return Objects.hash(x, y);
		}
		public Loc rln(int[] pos) {
			return new Loc(this.x - pos[0], this.y - pos[1]);
		}
	}
	public static int solution(int[] dimensions, int[] your_position, int[] trainer_position, int distance) {
		int dx = distance / dimensions[0] + 1;
		int dy = distance / dimensions[1] + 1;

		Map<Loc, Integer> target = new HashMap<>();
		int dis2 = distance * distance;
		for (int i = -dx; i <= dx; i++) {
			for (int j = -dy; j <= dy; j++) {
				Loc bunny = getLoc(dimensions, trainer_position, i, j).rln(your_position);
				if (bunny.dist() <= dis2) { 
					Loc barry = bunny.barry();
					if (!target.containsKey(barry) || target.get(barry) > bunny.dist()) {
						target.put(barry, bunny.dist());
					}
				}
			}
		}
		for (int i = -dx; i <= dx; i++) {
			for (int j = -dy; j <= dy; j++) {
				Loc hero = getLoc(dimensions, your_position, i, j).rln(your_position);
				if (hero.dist() > 0 && hero.dist() <= dis2) {
					Loc barry = hero.barry();
					if (target.containsKey(barry) && target.get(barry) > hero.dist()) {
						target.remove(barry);
					}
				}
			}
		}
		return target.size();
	}

	private static Loc getLoc(int[] dim, int[] pos, int xpath, int ypath) {
		return new Loc(project(dim[0], pos[0], xpath),project(dim[1], pos[1], ypath));
	}

	private static int project(int size, int coord, int box) {
		return box * size + ((box % 2 == 0)? coord:size - coord);
	}
}