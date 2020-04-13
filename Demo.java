import java.util.Random;
import java.util.Scanner;

class Demo {
	int[] currentMastermindCode = {0, 0, 0, 0};
	String wrongCodeNumbers = "";
	String correctCodeNumbers = "";
	String answer;
	int possibleTurns = 10;

	int correctAmount = 0;
	int inWrongOrder = 0;

	public static void main(String[] args)
	{
		new Demo().setupRandomCode();
	}

	//FD: Laat een random code tevoorschijn komen op het scherm.
	//TS: We moeten Random importeren, en dit in een int opslaan.
	//TS: We moeten een Array met ints hebben, die de random code opslaat.
	//TS: Deze lijst met ints moet als code worden weergeven op het scherm.
	void setupRandomCode()
	{
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

	//FD: Zorg ervoor dat de gebruiker een code kan invullen.
	//TS: We moeten een Scanner importeren, en initialiseren.
	//TS: We moeten een userinput ontvangen.
	//TS: De userinput moet gecheckt worden.
	//TS: Er moet meerdere keer gegokt kunnen worden.
	//TS: Het programma moet feedback geven gebasseerd op de input.
	void checkUserInput()
	{
		
		Scanner keyboardInput = new Scanner(System.in);
		
		while(possibleTurns > 0)
		{
			String userInput = keyboardInput.nextLine();
			
			if(userInput.equals(answer))
			{
				System.out.println("Je hebt de code goed!");
				System.out.println("Bedankt voor het spelen!");
				possibleTurns = 0;
				
				if(keyboardInput != null)
				keyboardInput.close();
			}
			else
			{
				System.out.println("De code klopt niet!");
				giveFeedback(userInput);
				possibleTurns--;
			}
		}
	}

	void giveFeedback(String uInput)
	{
		//FD: De gebruiker moet te weten krijgen hoeveel cijfers correct zijn via tekst.
		//TS: De input moet vergeleken worden het antwoord.
		//TS: Hierbij moet gekeken worden of het antwoord gelijke characters heeft als die van de input.
		correctAmount = 0;
		inWrongOrder = 0;
		wrongCodeNumbers = "";
		correctCodeNumbers = "";

		for(int i = 0; i < currentMastermindCode.length; i++)
		{
			char charInput = uInput.charAt(i);
			char charCode = answer.charAt(i);

			if(charInput == charCode)
			{
				correctAmount++;
			}
			else
			{
				wrongCodeNumbers = wrongCodeNumbers + charInput;
				correctCodeNumbers = correctCodeNumbers + charCode;
			}
		}
		System.out.println("Je hebt " + correctAmount + " cijfers op de goeie plaats!");

		//FD: De gebruiker moet te weten krijgen welke cijfers niet op de goede plek zitten, maar wel in de code zitten.
		//TS: Check of de cijfers die niet kloppen wel in de code zitten.
		//TS: Sla de foute cijfers op in een array, en check per cijfer in de code of die erin zit.
		int wrongAmount = currentMastermindCode.length - correctAmount;
		for(int j = 0; j < wrongAmount; j++)
		{
			char charWrongCodeNumber = wrongCodeNumbers.charAt(j);
			String number = "" + charWrongCodeNumber;
			boolean inTheCode = correctCodeNumbers.contains(number);
			if(inTheCode)
				inWrongOrder++;
		}
		System.out.println("Je hebt " + inWrongOrder + " cijfers op de verkeerde plaats!");
	}
}