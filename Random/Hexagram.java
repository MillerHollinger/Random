import java.util.ArrayList;
import java.util.List;

public class Hexagram {
	static ArrayList<List<Integer>> permutations = new ArrayList<List<Integer>>();
	
	public static void main(String[] args)
	{
		ArrayList<Integer> testArr = new ArrayList<Integer>();
		for (int i = 0; i < 6; i++)
			testArr.add(i);
		Permute.permute(testArr, 0);
	}
	
	public static class Permute{
	    static void permute(java.util.List<Integer> arr, int k){
	        for(int i = k; i < arr.size(); i++){
	            java.util.Collections.swap(arr, i, k);
	            permute(arr, k+1);
	            java.util.Collections.swap(arr, k, i);
	        }
	        if (k == arr.size() -1){
	            System.out.println(java.util.Arrays.toString(arr.toArray()));
	            permutations.add(arr);
	        }
	    }
	    public static void main(String[] args){
	        //Permute.permute(java.util.Arrays.asList(3,4,6,2,1), 0);
	        System.out.println("------");
	        System.out.println(permutations);
	    }
	}
}
