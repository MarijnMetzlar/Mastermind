import java.util.Random;
import java.util.Scanner;

class Demo {
	int[] currentMastermindCode = {0, 0, 0, 0};
	String answer;
	int possibleTurns = 10;

	public static void main(String[] args)
	{
		new Demo().setupRandomCode();
	}

	void setupRandomCode()
	{
		//FD: Laat een random code tevoorschijn komen op het scherm.
		//TS: We moeten Random importeren, en dit in een int opslaan.
		//TS: We moeten een Array met ints hebben, die de random code opslaat.
		//TS: Deze lijst met ints moet als code worden weergeven op het scherm.
		for(int i = 0; i < currentMastermindCode.length; i++)
		{
			Random randomNumber = new Random();
			int x = randomNumber.nextInt(6) + 1;
			currentMastermindCode[i] = x;
		}
		
		answer = "" + currentMastermindCode[0] + currentMastermindCode[1] + currentMastermindCode[2] + currentMastermindCode[3];
		System.out.println("Random code is: " + answer);

		checkUserInput();
	}

	void checkUserInput()
	{
		//FD: Zorg ervoor dat de gebruiker een code kan invullen.
		//TS: We moeten een Scanner importeren, en initialiseren.
		//TS: We moeten een userinput ontvangen.
		//TS: De userinput moet gecheckt worden.
		//TS: Er moet meerdere keer gegokt kunnen worden.
		//TS: Het programma moet feedback geven gebasseerd op de input.
		Scanner keyboardInput = new Scanner(System.in);
		
		while(possibleTurns > 0)
		{
			String userInput = keyboardInput.nextLine();
			
			if(userInput.equals(answer))
			{
				System.out.println("Je hebt de code goed!");
				System.out.println("Bedankt voor het spelen!");
				possibleTurns = 0;
			}
			else
			{
				System.out.println("De code klopt niet!");
				giveFeedback();
				possibleTurns--;
			}
		}

		if(keyboardInput != null)
			keyboardInput.close();
	}

	void giveFeedback()
	{
		System.out.println("Hier komt de feedback :)");
	}
}