package assignment1;
//Robert Beaudenon
//260725065


public class Message {

	public String message; 
	public int lengthOfMessage;

	public Message (String m){
		message = m;
		lengthOfMessage = m.length();
		this.makeValid();
	}

	public Message (String m, boolean b){
		message = m;
		lengthOfMessage = m.length();
	}

	/**
	 * makeValid modifies message to remove any character that is not a letter and turn Upper Case into Lower Case
	 */
	public void makeValid(){

		this.message = message.replaceAll("[^a-zA-Z]", "").toLowerCase(); //remove all characters that are not letters and convert to letters to lowercase
		this.lengthOfMessage = message.length();//by doing this action the size of the message will change so we should readjust it


	}

	/**
	 * prints the string message
	 */
	public void print(){
		System.out.println(message);
	}

	/**
	 * tests if two Messages are equal
	 */
	public boolean equals(Message m){
		if (message.equals(m.message) && lengthOfMessage == m.lengthOfMessage){
			return true;
		}
		return false;
	}

	/**
	 * caesarCipher implements the Caesar cipher : it shifts all letter by the number 'key' given as a parameter.
	 * @param key
	 */
	public void caesarCipher(int key){
		// INSERT YOUR CODE HERE

		for (int i = 0; i < message.length(); i++) {
			char letter = message.charAt(i);

			// shift only letters (leave other characters alone)

			letter = (char) (letter + key);

			// may need to wrap around
			if (letter > 'z') {
				letter = (char) (letter - 26);
			} else if (letter < 'a') {
				letter = (char) (letter + 26);

			}
			//store 
			char[] messageChar = message.toCharArray();
			messageChar[i] = letter;

			this.message = String.valueOf(messageChar);
		}



	}

	public void caesarDecipher(int key){
		this.caesarCipher(- key);
	}

	/**
	 * caesarAnalysis breaks the Caesar cipher
	 * you will implement the following algorithm :
	 * - compute how often each letter appear in the message
	 * - compute a shift (key) such that the letter that happens the most was originally an 'e'
	 * - decipher the message using the key you have just computed
	 */
	public void caesarAnalysis(){
		// INSERT YOUR CODE HERE


		char maxchar = ' ';
		int maxcount = 0;

		int[] chararray = new int[Character.MAX_VALUE + 1];
		for (int i = message.length() - 1; i >= 0; i--) {
			char ch = message.charAt(i);
			// increase this character's count and compare it to our max
			if (++chararray[ch] >= maxcount) {
				maxcount = chararray[ch];
				maxchar = ch;
			}
		}
		//find the key 
		int key = ((int)maxchar - (int)'e') ;
		//if character that is repeated the most is positioned after the 'e' than take negative key to go backward else take positive key 
		if ( ((int)maxchar > (int)'e')) {

			this.caesarCipher(-key);}
		else {

			this.caesarCipher(key);
		}

	}






	/**
	 * vigenereCipher implements the Vigenere Cipher : it shifts all letter from message by the corresponding shift in the 'key'
	 * @param key
	 */
	public void vigenereCipher (int[] key){
		// INSERT YOUR CODE HERE

		int counter=0;
		for (int i = 0; i < message.length(); i++) {
			char letter = message.charAt(i);

			// shift only letters (leave other characters alone)


			letter = (char) (letter + key[counter]);

			counter++;
			if (counter>=key.length) {
				counter=0;
			}



			// may need to wrap around
			if (letter > 'z') {
				letter = (char) (letter - 26);
			} else if (letter < 'a') {
				letter = (char) (letter + 26);

			}
			char[] messageChar = message.toCharArray();
			messageChar[i] = letter;

			this.message = String.valueOf(messageChar);
		}

	}

	/**
	 * vigenereDecipher deciphers the message given the 'key' according to the Vigenere Cipher
	 * @param key
	 */
	public void vigenereDecipher (int[] key){
		// INSERT YOUR CODE HERE
		for ( int j=0; j<key.length;j++) {
			key[j]=-key[j];
		}
		int counter=0;
		for (int i = 0; i < message.length(); i++) {
			char letter = message.charAt(i);

			// shift only letters (leave other characters alone)

			letter = (char) (letter + key[counter]);


			counter++;
			if (counter>=key.length) {
				counter=0;
			}



			// may need to wrap around
			if (letter > 'z') {
				letter = (char) (letter - 26);
			} else if (letter < 'a') {
				letter = (char) (letter + 26);

			}
			char[] messageChar = message.toCharArray();
			messageChar[i] = letter;

			this.message = String.valueOf(messageChar);
		}




	}
	/**
	 * 
	 */

	/**
	 * transpositionCipher performs the transition cipher on the message by reorganizing the letters and eventually adding characters
	 * @param key
	 */
	public void transpositionCipher (int key){
		// INSERT YOUR CODE HERE
		//determine the number of row of the array
		int length = (message.length())/key;
		if ((length % key )!=0) {
			length = length+1;


		}
		//2d array
		char[][] chararray = new char[key][length];
		int counter=0;
		//filling in the array with the message
		// if the message is done and array stilll not complete addd stars
		for (int i = 0; i < length; i++) {

			for (int j=0; j<key;j++) {
				if (counter == ((message.length()))) {

					for (j=j; j<key; j++) {

						chararray[j][i]= '*';

					}	 

				}
				else {

					char letter = message.charAt(counter);

					chararray[j][i]= letter;


				}

				counter =counter+1;



			}



		}

		String s = "";
		for(int k = 0; k < chararray.length; k++) {
			for(int l = 0; l < chararray[0].length; l++) {
				s += String.valueOf(chararray[k][l]);


			}
		}
		this.message = s;
		this.lengthOfMessage = message.length();


	}





	/**
	 * transpositionDecipher deciphers the message given the 'key'  according to the transition cipher.
	 * @param key
	 */
	public void transpositionDecipher (int key){
		// INSERT YOUR CODE HERE
		//to determine the number of rows of the array
		int length = (message.length())/key;

		char[][] chararray = new char[length][key];
		int counter=0;

		for (int i = 0; i < chararray[0].length; i++) {

			for (int j=0; j<chararray.length;j++) {

				char letter = message.charAt(counter);

				chararray[j][i]= letter;

				//System.out.print(chararray[j][i]);

				counter =counter+1;
			}

			//System.out.println();	
		}

		String s = "";
		for(int k = 0; k < chararray.length; k++) {
			for(int l = 0; l < chararray[0].length; l++) {
				s += String.valueOf(chararray[k][l]);


			}
		}
		this.message = s;
		this.message = message.replaceAll("[^a-zA-Z]", "").toLowerCase();
		this.lengthOfMessage = message.length();


	}




}
