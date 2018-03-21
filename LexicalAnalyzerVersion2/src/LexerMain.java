import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
 

public class LexerMain {
static StringBuilder buffer = new StringBuilder();
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		
		/*
		 * while not finished (i.e. not end of the source file) do
          		call the lexer for a token 
          		print the token and lexeme
     		endwhile

		 */
		
		//First, read in a file that contains the source code
		Scanner keyboard = new Scanner(System.in);
		System.out.println( "Please enter the name or path of the text file: ");
		String fileName = keyboard.next();
		
		//Creates the file called inputFile
		File file = new File(fileName);
		Scanner inputFile = new Scanner(file);
		
		//Loop through inputFile until the end of file is reached
		String word;
		while(inputFile.hasNext())
		{
		
			//Create an instance of the Record class
			Record objRecReturned; 
			
			//System.out.println(inputFile.next());
			
			//Call the lexer function for a token
			word = inputFile.next();
			//System.out.println(word + "sonal");
			for(int i = 0; i < word.length(); i++)
			{
				objRecReturned = lexer(i, word.charAt(i), word);
				i = objRecReturned.getI();
				
				if(objRecReturned.getInvalid() == false)
				{
					System.out.println(objRecReturned.getToken() + " " + objRecReturned.getLexeme());
				}
			}
			//break;
		
			//Print the token and lexeme
			//System.out.println(objRecReturned.getToken() + " " + objRecReturned.getLexeme());
			
			
		}

	}
	
	
	
	/*
	A major component of your assignment will be to write a procedure 
	(Function) – lexer () -  that returns a  token when it is needed.  
	Your lexer()  should return a record, one field for the token and 
	another field the actual "value" of the token (lexeme), i.e. the 
	instance of a token
	*/

	public static Record lexer(int i, char letter, String word) //change void to type Record
	{
		//We need to read each character from the passed word
		//and send each character to the FSMs
		char letterFromWord = letter;
		
		Record nullObject = new Record();
		boolean wentToDigit = false;
		//for(int i = 0; i < word.length(); i++)
		//{
			//letterFromWord = word.charAt(i);
			
			Record returnedObj;// = new Record();
			//returnedObj = isSeparator(i, letterFromWord, word);
			//i = returnedObj.getI();
			//returnedObj = isOperator(i, letterFromWord, word);
			//i = returnedObj.getI();
			//System.out.println("Hello: " + returnedObj.getLexeme());
			if((returnedObj = isSeparator(i, letterFromWord, word)).getIsSeparator() == true)
			{
				//i = returnedObj.getI();
				//System.out.println("Sonal Rocks");
				return returnedObj;
				//System.out.println("Token: " + returnedObj.getToken() + " " + "Lexeme: " + returnedObj.getLexeme());
				//System.out.println(i);
			}
			else if((returnedObj = isOperator(i, letterFromWord, word)).getIsOperator() == true)
			{
				//i = returnedObj.getI();
				//System.out.println("Token: " + returnedObj.getToken() + " " + "Lexeme: " + returnedObj.getLexeme());
				return returnedObj;
			}
			else if((returnedObj = isDigit(i, letterFromWord, word)).getIsDigit() == true && (wentToDigit = true))
			{
				return returnedObj;
			}
			else if((returnedObj = isReal(i, letterFromWord, word)).getIsReal() == true)
			{
				return returnedObj;
			}
			else if((returnedObj = isIdentifier(i, letterFromWord, word)).getIsIdentifier() == true)
			{
				//return returnedObj;
				if((returnedObj = isKeyword(returnedObj)).getIsKeyword() == true)
				{
					//System.out.println("here: " + returnedObj.getToken());
					return returnedObj;
				}
				else
				{
					//System.out.println("lala: " + returnedObj.getToken());
					return returnedObj;
				}
				
			}
			else
			{
				/*if(returnedObj.getIsDigit() == false && wentToDigit == true)
				{
					nullObject.setI(returnedObj.getI());
				}
				else
				{
					nullObject.setI(i);
				}*/
				if(returnedObj.getInvalidDigit() == true)
				{
					/*System.out.println("sonal");
					int a = returnedObj.getStartingInvalidNum();
					int b = returnedObj.getEndingInvalidNum();
					while(a < b)
					{
						System.out.println("Invalid input: " + word.charAt(a));
						a++;
					}*/
				}
				else
				{
					nullObject.setI(i);
					nullObject.setInvalid(true);
					System.out.println("Invalid input: " + word.charAt(i));
					return nullObject;
				}
			}
		
		//}
		
		return null;
	}
	
	
	public static Record isSeparator(int i, char letterFromWord, String word)
	{
		//System.out.println("test");
		Record obj = new Record();
		
		boolean isSeparator = false;
		String lexeme;
		char separators[] = {':', '%', '@', '{', '}', '[', ']', '(', ')', ',', ';' };
		/*
		 * %% (this one)
		 * := (this one)
		 * ( )
		 * @
		 * ,
		 * :
		 * { }
		 * [ ]
		 */
		
		for(int iSep = 0; iSep < separators.length; iSep++)
		{
			//System.out.println("test2");
			if(letterFromWord == separators[iSep])
			{//System.out.println("test3");
				if(letterFromWord == '%')
				{
					//System.out.println("test4");
					//then we need to check if the next letter is '%'
					if((i+1) < word.length())
					{
						if(word.charAt(i+1) == '%')
						{
							/*if(word.charAt(i+1+1) == '%' && Character.toString(word.charAt(i+1)) != null && word.charAt(i+1) != ' ')
							{
								lexeme = Character.toString(letterFromWord) + Character.toString(word.charAt(i+1));
								obj.setIsSeparator(true);
								obj.setToken("Separator");
								obj.setLexeme(lexeme);
								obj.setI(i+1);
							}*/
							
						//}
						//else
						//{
							//System.out.println("sonal");
							lexeme = Character.toString(letterFromWord) + Character.toString(word.charAt(i+1));
							obj.setIsSeparator(true);
							obj.setToken("Separator");
							obj.setLexeme(lexeme);
							obj.setI(i+1);
							//System.out.println("This one: " + obj.getI());
							break;
						}
						else
						{
							lexeme = Character.toString(letterFromWord);
							obj.setIsSeparator(true);
							obj.setToken("Separator");
							obj.setLexeme(lexeme);
							obj.setI(i);			
							break;
						}
					}
					else
					{
						lexeme = Character.toString(letterFromWord);
						obj.setIsSeparator(true);
						obj.setToken("Separator");
						obj.setLexeme(lexeme);
						obj.setI(i);
						//System.out.println("This one: " + obj.getI());
						break;
					}
				}
				else if(letterFromWord == ':')
				{
					//System.out.println("test5");
					//then we need to check if the next letter is '='
					//System.out.println("lala " + i);
					if((i+1) < word.length())
					{
						if(word.charAt(i+1) == '=')
						{
							//System.out.println("sonal" );
							//System.out.println("Checking: " + " " + word.charAt(i));
							lexeme = Character.toString(letterFromWord) + Character.toString(word.charAt(i+1));
							obj.setIsSeparator(true);
							obj.setToken("Separator");
							obj.setLexeme(lexeme);
							//System.out.println("lala: " + obj.getLexeme() + " " + obj.getToken());
							//we want to set the pointer of the character to the character following '=' since we don't want to read the '=' sign 
							//again. 
							obj.setI(i+1);
							break;
							
						}
						else
						{
							//System.out.println("Sonal Sarin: " + letterFromWord);
							lexeme = Character.toString(letterFromWord);
							obj.setIsSeparator(true);
							obj.setToken("Separator");
							obj.setLexeme(lexeme);
							obj.setI(i);
							break;
						}
					}
					else
					{
						lexeme = Character.toString(letterFromWord);
						obj.setIsSeparator(true);
						obj.setToken("Separator");
						obj.setLexeme(lexeme);
						obj.setI(i);
						break;
						//complete here
					}
				}
				else
				{
					lexeme = Character.toString(letterFromWord);
					obj.setIsSeparator(true);
					obj.setToken("Separator");
					obj.setLexeme(lexeme);
					obj.setI(i);			
					break;
				}
			}
			else
			{
				obj.setI(i);
			}
		}
		
		
		
		return obj; 
	}
	
	
	public static Record isOperator(int i, char letterFromWord, String word)
	{
		Record obj = new Record();
		char operators[] = {'=', '/', '>', '<', '=', '+', '-', '*'};
		String lexeme;
		
		/*
		 * =
		 * (/=) (this one)
		 * >
		 * <
		 * => (this one)
		 * <= (this one)
		 * *
		 * +
		 * -
		 *(/)
		 * 
		 * 
		 */
		
		
		for(int iOp = 0; iOp < operators.length; iOp++)
		{
			if(letterFromWord == operators[iOp])
			{
				if(letterFromWord == '/')
				{
					System.out.println("hello");
					if((i+1) < word.length())
					{
						if(word.charAt(i+1) == '=')
						{
							System.out.println("sonal");
							lexeme = Character.toString(letterFromWord) + Character.toString(word.charAt(i+1)); //here
							obj.setIsOperator(true);
							obj.setToken("Operator");
							obj.setLexeme(lexeme);
							obj.setI(i+1);			
							break;
						}
						else
						{
							lexeme = Character.toString(letterFromWord);
							obj.setIsOperator(true);
							obj.setToken("Operator");
							obj.setLexeme(lexeme);
							obj.setI(i);			
							break;
						}
					}
					else
					{
						lexeme = Character.toString(letterFromWord);
						obj.setIsOperator(true);
						obj.setToken("Operator");
						obj.setLexeme(lexeme);
						obj.setI(i);			
						break;
					}
				}
				else if(letterFromWord == '=')
				{
					if((i+1) < word.length())
					{
						if(word.charAt(i+1) == '>')
						{
							lexeme = Character.toString(letterFromWord) + Character.toString(word.charAt(i+1)); //here
							obj.setIsOperator(true);
							obj.setToken("Operator");
							obj.setLexeme(lexeme);
							obj.setI(i+1);			
							break;
						}
						else
						{
							lexeme = Character.toString(letterFromWord);
							obj.setIsOperator(true);
							obj.setToken("Operator");
							obj.setLexeme(lexeme);
							obj.setI(i);			
							break;
						}
					}
					else
					{
						lexeme = Character.toString(letterFromWord);
						obj.setIsOperator(true);
						obj.setToken("Operator");
						obj.setLexeme(lexeme);
						obj.setI(i);			
						break;
					}
				}
				else if(letterFromWord == '<')
				{
					//if(word.charAt(i+1) == '=')
					if((i+1) < word.length())
					{
						if(word.charAt(i+1) == '=')
						{
							lexeme = Character.toString(letterFromWord) + Character.toString(word.charAt(i+1)); //here
							obj.setIsOperator(true);
							obj.setToken("Operator");
							obj.setLexeme(lexeme);
							obj.setI(i+1);			
							break;
						}
					}
					else
					{
						lexeme = Character.toString(letterFromWord);
						obj.setIsOperator(true);
						obj.setToken("Operator");
						obj.setLexeme(lexeme);
						obj.setI(i);			
						break;
					}
				}
				else
				{
					lexeme = Character.toString(letterFromWord);
					obj.setIsOperator(true);
					obj.setToken("Operator");
					obj.setLexeme(lexeme);
					obj.setI(i);			
					break;
				}
			}
			else
			{
				obj.setI(i);
			}
		}
		
		return obj; //change accordingly, once function is implemented.
	}
	
	public static Record isDigit(int i, char letter, String word)
	{
		//static String buffer;
		/*
		 * Read each letter one by one. If the letter is a digit, add it to the buffer.
		 * If the next letter is a '.' or If the next letter is a '.' and the following letter is a digit then clear the buffer 
		 * and If the next letter is not a digit or a '.', then
		 * check whether the last state
		 * was an accepting state. If it was, then good, the entire buffer (i.e. arraylist) will be the 
		 * lexeme and the token is a digit
		 */
		//StringBuilder string = new StringBuilder();
		Record obj = new Record();
		String buffer2;
		boolean thereIsDigit = false;
		
		for(int j = i; j < word.length(); j++) //j = 0
		{
			if(Character.isDigit(word.charAt(j)))
			{
				buffer.append(word.charAt(j));
				thereIsDigit = true;
				if(j+1 < word.length())
				{
					if(word.charAt(j+1) == '.')
					{
						//System.out.println(j+1 + "lala");
						//System.out.println("hello");
						/*int x = j+1;
						while( x < word.length())
						{
							if(Character.isDigit(word.charAt(x)))
							{
								x++;
							}
							else
							{
								break;
							}
						}
						obj.setStartingInvalidNum(i);
						obj.setEndingInvalidNum(x);*/
						buffer.setLength(0);
						obj.setIsDigit(false);
						//obj.setI(x);
						obj.setInvalidDigit(true);
						return obj;
					}
				}
			}
			else
			{
				if(thereIsDigit == true)
				{
					buffer2 = buffer.toString();
					obj.setLexeme(buffer2);
					obj.setI(j-1);
					buffer.setLength(0);
					obj.setIsDigit(true);
					obj.setToken("Integer");
					return obj;
				}
				else
				{
					obj.setIsDigit(false);
					return obj;
				}
			}
			
			
			/*if(Character.isDigit(word.charAt(j))) //letter to word.charAt(j)
			{
				buffer.append(word.charAt(j)); //letter to word.charAt(j)
				
				
				if(j+1 < word.length())
				{
					if(word.charAt(j+1) == '.')
					{
						
						if(j+2 < word.length())
						{
							if(Character.isDigit(word.charAt(j+2)))
							{
								
								int x = j+1;
								while( x < word.length())
								{
									if(Character.isDigit(word.charAt(x)))
									{
										x++;
									}
									else
									{
										break;
									}
								}
								obj.setStartingInvalidNum(i);
								obj.setEndingInvalidNum(x);
								buffer.setLength(0);
								obj.setIsDigit(false);
								obj.setI(j+1);
								obj.setInvalidDigit(true);
								return obj;
								
							}
						}
						else
						{
							obj.setIsDigit(true);
							//buffer2 = buffer.toString();
							//obj.setDigit(buffer2);
							obj.setToken("Integer");
							//obj.setLexeme(buffer2);
							//buffer.setLength(0);
							//obj.setI(j+2);
							j = j+2; //changed minus 1 - back
						}
					}
					else
					{
						if(Character.isDigit(word.charAt(j+1)))
						{
							buffer.append(word.charAt(j+1));
							obj.setToken("Integer");
							obj.setIsDigit(true);
							//buffer2 = buffer.toString();
							//obj.setLexeme(buffer2);
							//obj.setI(j+1);
							j = j + 1; //changed minus 1 -back
							//buffer.setLength(0);
						}
						else
						{
							buffer2 = buffer.toString();
							obj.setI(j);
							obj.setToken("Integer");
							obj.setLexeme(buffer2);
							buffer.setLength(0);
							return obj;
						}
					}
				}
				else
				{
					obj.setToken("Integer");
					//obj.setLexeme(lexeme2);
					obj.setIsDigit(true);
					//buffer2 = buffer.toString();
					//obj.setDigit(buffer2);
					//obj.setLexeme(buffer2);
					//obj.setI(j);
					j = j;
					//buffer.setLength(0);
				}
			}
			else
			{
				if(word.charAt(j) == '.')
				{
					
					if(j+1 < word.length())
					{
						if(Character.isDigit(word.charAt(j+1)))
						{
							
							
							int x = j+1;
							while( x < word.length())
							{
								if(Character.isDigit(word.charAt(x)))
								{
									x++;
								}
								else
								{
									break;
								}
							}
							obj.setStartingInvalidNum(i);
							obj.setEndingInvalidNum(x);
							buffer.setLength(0);
							obj.setIsDigit(false);
							obj.setI(j+1);
							obj.setInvalidDigit(true);
							return obj;
							
						}
					}
					else
					{
						obj.setIsDigit(true);
						//buffer2 = buffer.toString();
						//obj.setDigit(buffer2);
						obj.setToken("Integer");
						//obj.setLexeme(buffer2);
						//buffer.setLength(0);
						//obj.setI(j+2);
						j = j+1; //changed minus 1 - back
					}
				}
			}*/
		}
		buffer2 = buffer.toString();
		//System.out.println("lala: " + buffer2);
		obj.setLexeme(buffer2);
		obj.setI(word.length());
		buffer.setLength(0);
		obj.setIsDigit(true);
		obj.setToken("Integer");
		return obj;
		/*buffer2 = buffer.toString();
		//System.out.println("lala: " + buffer2);
		obj.setLexeme(buffer2);
		obj.setI(word.length());
		buffer.setLength(0);
		return obj; //change accordingly, once function is implemented.*/
	}
	public static Record isReal(int i, char letter, String word)
	{
		
		/*
		 * Read each letter one by one. If the letter is a digit, then add it to the buffer.
		 * If next letter is a digit, then add the letter to the buffer.
		 * If the next letter is a '.', check if the following letter is a digit, if it is not, then
		 * clear the buffer because then the inputs was not a real number, it was an integer. 
		 */
		
		Record obj = new Record();
		String buffer2;
		boolean thereIsDigit = false;
		boolean thereIsDot = true;
		
		for(int j = 0; j < word.length(); j++) //j = 0
		{
			if(Character.isDigit(word.charAt(j)))
			{
				buffer.append(word.charAt(j));
				thereIsDigit = true;
				if(j+1 < word.length())
				{
					if(word.charAt(j+1) == '.')
					{
						buffer.append(word.charAt(j+1));
						obj.setIsReal(true);
						j+=1;
						//obj.setIsDigit(false);
						//obj.setI(x);
						//obj.setInvalidDigit(true);
						//return obj;
					}
				}
			}
			else
			{
				if(thereIsDot == true)
				{
					buffer2 = buffer.toString();
					obj.setLexeme(buffer2);
					obj.setI(j-1);
					buffer.setLength(0);
					obj.setIsDigit(true);
					obj.setToken("Real");
					return obj;
				}
				else
				{
					obj.setIsReal(false);
					return obj;
				}
			}
			
		}
		buffer2 = buffer.toString();
		//System.out.println("lala: " + buffer2);
		obj.setLexeme(buffer2);
		obj.setI(word.length());
		buffer.setLength(0);
		obj.setIsDigit(true);
		obj.setToken("Real");
		return obj;
		/*buffer2 = buffer.toString();
		//System.out.println("lala: " + buffer2);
		obj.setLexeme(buffer2);
		obj.setI(word.length());
		buffer.setLength(0);
		return obj; //change accordingly, once function is implemented.*/
	}
	public static Record isIdentifier(int i, char letter, String word)
	{
		Record obj = new Record();
		String buffer2;
		boolean thereIsIdentifier = false;
		//boolean thereIsDot = true;
		//System.out.println("lala: " + word);
		//System.out.println("lala: " + letter);
		//System.out.println("lala: " + i);
		for(int j = i; j < word.length(); j++) //j = 0
		{
			if(Character.isLetter(word.charAt(j)))
			{
				//System.out.println("sonal: " + (word.charAt(j)));
				buffer.append(word.charAt(j));
				thereIsIdentifier = true;
				if(j+1 < word.length())
				{
					if(word.charAt(j+1) == '#')
					{
						buffer.append(word.charAt(j+1));
						obj.setIsIdentifier(true);
						j+=1;
						//obj.setIsDigit(false);
						//obj.setI(x);
						//obj.setInvalidDigit(true);
						//return obj;
					}
				}
			}
			else
			{
				if(thereIsIdentifier == true)
				{
					buffer2 = buffer.toString();
					obj.setLexeme(buffer2);
					obj.setI(j-1);
					buffer.setLength(0);
					//obj.setIsDigit(true);
					obj.setIsIdentifier(true);
					obj.setToken("Identifier");
					return obj;
				}
				else
				{
					obj.setIsIdentifier(false);
					return obj;
				}
			}
			
		}
		buffer2 = buffer.toString();
		//System.out.println("lala: " + buffer2);
		obj.setLexeme(buffer2);
		obj.setI(word.length());
		buffer.setLength(0);
		obj.setIsIdentifier(true);
		obj.setToken("Identifier");
		return obj;
	}
	public static Record isKeyword(Record returnedObjComing)
	{
		//System.out.println("sonal: " + returnedObjComing.getLexeme());
		String keywords[] = {"integer", "boolean", "floating", "if", "fi", "else", 
				"return", "write", "true", "false", "while"};

		//System.out.println(keywords.length + " length");
		//System.out.println(returnedObjComing.getLexeme() + " aselh");
		for(int i = 0; i < keywords.length; i++)
		{
			//System.out.println("coco: " + keywords[i]);
			if(returnedObjComing.getLexeme().equals(keywords[i]))
			{
				//System.out.println(returnedObjComing.getLexeme() + " " + keywords[i] + " blah");
				returnedObjComing.setToken("Keyword");
				returnedObjComing.setIsKeyword(true);
				break;
			}
		}
		
		return returnedObjComing; //change accordingly, once function is implemented.
	}

}


