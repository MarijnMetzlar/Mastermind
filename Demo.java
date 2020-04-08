import java.util.Random;
import java.util.Scanner;

class Demo {
	public static int[] currentMastermindCode = {0, 0, 0, 0};

	public static void main(String[] args)
	{
		new Demo().setupRandomCode();
	}

	void setupRandomCode()
	{
		for(int i = 0; i < currentMastermindCode.length; i++)
		{
			Random randomNumber = new Random();
			int x = randomNumber.nextInt(6) + 1;
			currentMastermindCode[i] = x;
		}

		System.out.println("Random code is: " + currentMastermindCode[0] + currentMastermindCode[1] 
		+ currentMastermindCode[2] + currentMastermindCode[3]);
	}

	void checkUserInput()
	{
		Scanner keyboardInput = new Scanner(System.in);

		if(keyboardInput != null)
			keyboardInput.close();
	}
}