import java.io.File;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class RodCutting {

	public static void main(String[] args) throws IOException {
		String input = args[0];
		String output = args[1];
		File file = new File(input);
		Scanner scanner = new Scanner(file);
		FileWriter fileWriter = new FileWriter(output);
		int totalLoops = Integer.parseInt(scanner.nextLine());
		for (int x = 0; x < totalLoops; x++) {
			int subLoop = Integer.parseInt(scanner.nextLine());
			int[] inputArray = new int [subLoop+1];
			inputArray[0] = 0;
			for (int i = 1; i < subLoop+1; i++) {
				inputArray[i] = Integer.parseInt(scanner.nextLine());
			}
			int answer = rodCutting(inputArray);
			fileWriter.write(answer + "\r\n");
			
		}
		scanner.close();
		fileWriter.close();
	}
	
	public static int rodCutting(int[] priceArray) {
		int[] arrayR = new int [priceArray.length];
		int[] arrayS = new int [priceArray.length];
		arrayR[0] = 0;
		arrayS[0] = 0;
		for (int i = 1; i < priceArray.length; i++) {
			int[] answer = loopDown(arrayR, arrayS, priceArray, i);
			arrayR[i] = answer[0];
			arrayS[i] = answer[1];
		}
		return computeValue(arrayR, arrayS);
	}
	
	public static int[] loopDown(int[] arrayR, int[] arrayS, int[] priceArray, int x) {
		int previous = 0;
		int index = 0;
		for (int i = x; i > 0; i--) {
			if (priceArray[i] + arrayR[x-i] > previous) {
				previous = priceArray[i] + arrayR[x-i];
				index = i;
			}
		}
		int[] answer = new int [2];
		answer [0] = previous;
		answer [1] = index;
		return answer;
	}
	
	public static int computeValue(int[] arrayR, int[] arrayS) {
		int length = arrayS.length-1;
		int cut, value = 0;
		
		while(length != 0) {
			cut = arrayS[length];
			value += arrayR[cut];
			length -= cut;
		}
		return value;
	}
}















