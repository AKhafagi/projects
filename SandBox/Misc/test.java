package data_structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class test {
	public static ArrayList<Integer> spiralOrder(final List<ArrayList<Integer>> a) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int t, b, r, l, dir, i;
		t = 0;
		b = a.size() - 1;
		r = a.get(0).size() - 1;
		l = 0;
		dir = 0;
		while (t <= b && l <= r) {
			switch (dir) {
			case 0:
				for (i = l; i <= r; i++)
					result.add(a.get(t).get(i));
				t++;
				dir++;
				System.out.println();
				break;
			case 1:
				for (i = t; i <= b; i++)
					result.add(a.get(i).get(r));
				r--;
				dir++;
				System.out.println();
				break;
			case 2:
				for (i = r; i >= l; i--)
					result.add(a.get(b).get(i));
				b--;
				dir++;
				System.out.println();
				break;
			case 3:
				for (i = b; i >= t; i--)
					result.add(a.get(i).get(l));
				l++;
				dir = 0;
				System.out.println();
				break;
			}

		}
		return result;
	}

	public static void printSpiral(int[][] A, int row, int col) {
		int t, b, l, r, i, dir;
		t = 0;
		b = row - 1;
		l = 0;
		r = col - 1;
		dir = 0;

		while (t <= b && l <= r) {
			switch (dir) {
			case 0:
				for (i = l; i <= r; i++)
					System.out.println(A[t][i]);
				t++;
				dir++;
				System.out.println();
				break;
			case 1:
				for (i = t; i <= b; i++)
					System.out.println(A[i][r]);
				r--;
				dir++;
				System.out.println();
				break;
			case 2:
				for (i = r; i >= l; i--)
					System.out.println(A[b][i]);
				b--;
				dir++;
				System.out.println();
				break;
			case 3:
				for (i = b; i >= t; i--)
					System.out.println(A[i][l]);
				l++;
				dir = 0;
				System.out.println();
				break;
			}

		}
	}

	public static boolean isPrime(int n) {
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static boolean isUnique(String k) {
		HashSet<Character> hs = new HashSet<Character>();
		for (int i = 0; i < k.length(); i++) {
			if (!hs.add(k.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	public static boolean isUniqueNo(String k) {
		
		for (int i = 0; i < k.length(); i++) {
			for(int j= i+1; j< k.length(); j++){
				if(k.charAt(i) == k.charAt(j))
					return false;
			}
		}
		return true;
	}
	public static char[] urlfy(char[] k,int trueLength){
		int spaceCount=0;
		int index, i =0;
		for(i =0; i < trueLength; i++){
			if(k[i] == ' '){
				spaceCount++;
			}
		}
		index = trueLength + spaceCount*2;
		if(trueLength < k.length)
			k[trueLength] = '\0';
		for(i = trueLength -1; i >= 0; i--){
			if(k[i] == ' '){
				k[index-1] = '0';
				k[index-2] = '2';
				k[index-3] = '%';
				index -=3;
			}
			else{
				k[index-1] = k[i];
				index --;
			}
		}
		return k;
	}
	

	public static void main(String[] args) {
		char[] k ={'h','e','l','l','o',' ','W','o','r','l','d',' ','I',' ',' ',' ',' '};
		int length = 13;
		System.out.println(urlfy(k,length));
	}

}
