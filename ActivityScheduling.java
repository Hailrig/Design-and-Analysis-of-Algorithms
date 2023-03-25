import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ActivityScheduling {

	public static void main(String[] args) throws IOException {
		String input = args[0];
		String output = args[1];
		File file = new File(input);
		Scanner scanner = new Scanner(file);
		FileWriter fileWriter = new FileWriter(output);
		int totalLoops = Integer.parseInt(scanner.nextLine());
		for (int x = 0; x < totalLoops; x++) {
			int subLoop = Integer.parseInt(scanner.nextLine());
			int[][] inputArray = new int[subLoop][2];
			for (int i = 0; i < subLoop; i++) {
				inputArray[i][0] = scanner.nextInt();
				inputArray[i][1] = scanner.nextInt();
				scanner.nextLine();
			}
			int[][] sortedArray = sortFinishTime(inputArray, subLoop);
			int answer = (scheduler(sortedArray, subLoop));
			fileWriter.write(answer + "\r\n");
			
		}
		scanner.close();
		fileWriter.close();
	}
	
	public static int[][] sortFinishTime(int inputArray[][], int length){ //I chose to use insertion sort, as our N is probably pretty small for activity scheduling, but merge sort might be better if there is quite a lot of activities
		for (int i = 0; i < length; i++) {
			int startValue = inputArray[i][0];
			int endValue = inputArray[i][1];
			int compareInt = i-1;
			
			while (compareInt >= 0 && inputArray[compareInt][1] > endValue) {
				inputArray[compareInt+1][0] = inputArray[compareInt][0];
				inputArray[compareInt+1][1] = inputArray[compareInt][1];
				compareInt--;
			}
			inputArray[compareInt+1][0] = startValue;
			inputArray[compareInt+1][1] = endValue;
		}
		return inputArray;
	}
	
	public static int scheduler(int[][] sortedArray, int length) { //As per provided pseudocode
		int activites = 1;
		int lastFinish = sortedArray[0][1];
		for (int i = 1; i < length; i++) {
			if (sortedArray[i][0] >= lastFinish) {
				activites++;
				lastFinish = sortedArray[i][1];
			}
		}
		return activites;
	}

}
