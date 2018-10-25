import java.util.*;

public class Swish {
	public static void main(String[] args) {

	}

	public static class SwishCard {
		public ArrayList<Integer> dots;
		public ArrayList<Integer> hoops;

		public SwishCard(ArrayList<Integer> d, ArrayList<Integer> h) {
			dots = d;
			hoops = h;
		}
		
		public void combine(SwishCard add) {
			for (int i = 0; i < add.dots.size(); i++)
				if (!dots.contains(add.dots.get(i)))
					dots.add(add.dots.get(i));
			for (int i = 0; i < add.hoops.size(); i++)
				if (!hoops.contains(add.hoops.get(i)))
					hoops.add(add.hoops.get(i));
		}
		
		public void rotate() {
			for (int i = 0; i < dots.size(); i++)
				dots.set(i, 11 - dots.get(i));
			for (int i = 0; i < hoops.size(); i++)
				hoops.set(i, 11 - hoops.get(i));
		}

		public void flip() {
			for (int i = 0; i < dots.size(); i++) {
				int set = 0;
				switch (dots.get(i)) {
				case 0:
					set = 9;
					break;
				case 1:
					set = 10;
					break;
				case 2:
					set = 11;
					break;
				case 3:
					set = 6;
					break;
				case 4:
					set = 7;
					break;
				case 5:
					set = 8;
					break;
				case 6:
					set = 3;
					break;
				case 7:
					set = 4;
					break;
				case 8:
					set = 5;
					break;
				case 9:
					set = 0;
					break;
				case 10:
					set = 1;
					break;
				case 11:
					set = 2;
					break;
				}
				dots.set(i, set);
			}
			for (int i = 0; i < hoops.size(); i++) {
				int set = 0;
				switch (hoops.get(i)) {
				case 0:
					set = 9;
					break;
				case 1:
					set = 10;
					break;
				case 2:
					set = 11;
					break;
				case 3:
					set = 6;
					break;
				case 4:
					set = 7;
					break;
				case 5:
					set = 8;
					break;
				case 6:
					set = 3;
					break;
				case 7:
					set = 4;
					break;
				case 8:
					set = 5;
					break;
				case 9:
					set = 0;
					break;
				case 10:
					set = 1;
					break;
				case 11:
					set = 2;
					break;
				}
				hoops.set(i, set);
			}
		}
	}

	// Checks to see if two cards currently can swish
	public static boolean swishNow(SwishCard a, SwishCard b) {
		// Check to see if A's dots match B's hoops
		for (int i = 0; i < a.dots.size(); i++) {
			if (!b.hoops.contains(a.dots.get(i))) {
				return false;
			}
		}
		// Check to see if B's dots match A's hoops
		for (int i = 0; i < a.dots.size(); i++) {
			if (!a.hoops.contains(b.dots.get(i))) {
				return false;
			}
		}
		return true;
	}

	// Checks to see if two cards can swish in any orientation-- card a is rotated
	// and turned, but never b
	public static boolean canSwish(SwishCard a, SwishCard b) {
		if (swishNow(a, b))
			return true;
		a.rotate();
		if (swishNow(a, b))
			return true;
		a.flip();
		if (swishNow(a, b))
			return true;
		a.rotate();
		if (swishNow(a, b))
			return true;
		return false;
	}

	// Checks to see if a pile of cards can swish together
	public static boolean swishDeck(ArrayList<SwishCard> deck) {
		
	}
}
