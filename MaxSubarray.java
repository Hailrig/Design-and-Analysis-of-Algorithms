import java.io.File;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class MaxSubarray {
	public static void main(String[] args) throws IOException {
		String input = args[0];
		String output = args[1];
		File file = new File(input);
		Scanner scanner = new Scanner(file);
		FileWriter fileWriter = new FileWriter(output);
		int totalLoops = Integer.parseInt(scanner.nextLine());
		for (int x = 0; x < totalLoops; x++) {
			int subLoop = Integer.parseInt(scanner.nextLine());
			int[] inputArray = new int [subLoop];
			for (int i = 0; i < subLoop; i++) {
				inputArray[i] = Integer.parseInt(scanner.nextLine());
			}
			int[] array = findMaximumSubArray(inputArray, 0, inputArray.length-1);
			fileWriter.write(array[2] + "\r\n");
			
		}
		scanner.close();
		fileWriter.close();
	}
	
	public static int[] findMaximumSubArray(int[] array, int low, int high) {
		if (high == low) {
			int[] outArray = new int[] {low, high, array[low]};
			return outArray;
		} else {
			int mid = (low + high)/2;
			int[] lefts = findMaximumSubArray(array, low, mid);
			int[] rights = findMaximumSubArray(array, mid+1, high);
			int[] crosses = findMaximumCrossingSubArray(array, low, mid, high);
			if (lefts[2] >= rights[2] && lefts[2] >= crosses[2]) {
				return lefts;
			} else if (rights[2] >= lefts[2] && rights[2] >= crosses[2]) {
				return rights;
			} else {
				return crosses;
			}
		}
	}
	
	public static int[] findMaximumCrossingSubArray(int[] array, int low, int mid, int high) {
		int sum = 0;
		double leftSum = Double.NEGATIVE_INFINITY;
		double maxLeft = Double.NEGATIVE_INFINITY;
		for (int x = mid; x >= low; x--) {
			sum = sum + array[x];
			if(sum > leftSum){
				leftSum = sum;
				maxLeft = x;
			}
		}
		
		sum = 0;
		double rightSum = Double.NEGATIVE_INFINITY;
		double maxRight = Double.NEGATIVE_INFINITY;
		for (int x = mid + 1; x <= high; x++) {
			sum = sum + array[x];
			if(sum > rightSum){
				rightSum = sum;
				maxRight = x;
			}
		}
		int[] outArray = new int[] {(int) maxLeft, (int) maxRight, (int) (leftSum + rightSum)};
		return outArray;
	}
}
