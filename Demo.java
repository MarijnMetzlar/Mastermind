import java.util.Random;
import java.util.Scanner;

class Demo {
	char[] currentMastermindCode = {0, 0, 0, 0};
	
	String wrongCodeLetters = "";
	String correctedCodeLetters = "";
	String answer;
	
	int possibleTurns = 20;
	int correctAmount = 0;
	int inWrongOrderAmount = 0;

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
			currentMastermindCode[i] = getLetter(x);
		}
		answer = "" + currentMastermindCode[0] + currentMastermindCode[1] + currentMastermindCode[2] + currentMastermindCode[3];

		System.out.println("");
		System.out.println("Welkom bij Mastermind!");
		System.out.println("Vul vier letters in van a tot f, en raad de code.");
		//System.out.println("Random code is: " + answer);
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
			if(userInput.length() != currentMastermindCode.length) 
			{
				if(userInput.equals("q"))
				{
					System.out.println("Je hebt het spel verlaten.");
					System.exit(0);
				}
				else
					System.out.println("Je moet 4 cijfers invoeren!");
			}
			else 
			{
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
					resetAmounts();
					System.out.println("Je hebt " + getCorrectAmount(userInput) + " letters op de goeie plaats!");
					System.out.println("Je hebt " + getWrongPosAmount(userInput) + " letters op de verkeerde plaats!");
					possibleTurns--;
				}
			}
		}
	}

	//FD: De applicatie moet niks meer hoeven doen met de vorige inputs van de gebruiker.
	//TS: De variabelen die met de vorige inputs te maken heeft moeten worden reset.
	void resetAmounts()
	{
		correctAmount = 0;
		inWrongOrderAmount = 0;
		wrongCodeLetters = "";
		correctedCodeLetters = "";
	}

	//FD: Zet de willekeurig gegenereerde cijfercode om naar een lettercode.
	//TS: Een int moet omgezet worden naar een char.
	//TS: Deze char moet vervolgens via het ASCII tabel omgezet worden naar een letter.
	char getLetter(int i)
	{
		if(i < 0 || i > 6)
			return '?';
		else
			return (char)(96 + i);
	}

	//FD: De gebruiker moet te weten krijgen hoeveel cijfers correct zijn via tekst.
	//TS: De input moet vergeleken worden met het antwoord.
	//TS: Hierbij moet gekeken worden of het antwoord gelijke characters heeft als die van de input.
	int getCorrectAmount(String uInput)
	{
		for(int i = 0; i < currentMastermindCode.length; i++) {
			char charInput = uInput.charAt(i);
			char charCode = answer.charAt(i);
			if(charInput == charCode)
				correctAmount++;
			else {
				wrongCodeLetters = wrongCodeLetters + charInput;
				correctedCodeLetters = correctedCodeLetters + charCode;
			}
		}
		return correctAmount;
	}

	//FD: De gebruiker moet te weten krijgen welke cijfers niet op de goede plek zitten, maar wel in de code zitten.
	//TS: Check of de cijfers die niet kloppen wel in de code zitten.
	//TS: Sla de foute cijfers op, en check per cijfer in de code of die erin zit kijkend naar de overige cijfers die niet klopten.
	int getWrongPosAmount(String uInput)
	{
		int wrongAmount = currentMastermindCode.length - correctAmount;
		for(int i = 0; i < wrongAmount; i++) {
			char charWrongCodeNumber = wrongCodeLetters.charAt(i);
			String number = "" + charWrongCodeNumber;
			boolean inTheCode = correctedCodeLetters.contains(number);
			if(inTheCode)
				inWrongOrderAmount++;
		}
		return inWrongOrderAmount;
	}
}