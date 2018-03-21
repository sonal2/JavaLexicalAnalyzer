
public class Record {
	
	private String token = null;
	private String lexeme = null;
	private int i = 0;
	private boolean isSeparator;
	private boolean isOperator;
	private boolean isDigit;// = false;
	private boolean isReal;
	private boolean isIdentifier;
	private boolean isKeyword;
	private boolean invalid = false;
	private String digit;
	
	private int startingInvalidNum;
	private int endingInvalidNum;
	private boolean invalidDigit = false;
	
	
	public Record()
	{
		
	}
	
	public void setStartingInvalidNum(int coming)
	{
		startingInvalidNum = coming;
	}
	public int getStartingInvalidNum()
	{
		return startingInvalidNum;
	}
	public void setEndingInvalidNum(int coming)
	{
		startingInvalidNum = coming;
	}
	public int getEndingInvalidNum()
	{
		return endingInvalidNum;
	}
	public void setInvalidDigit(boolean coming)
	{
		invalidDigit = coming;
	}
	public boolean getInvalidDigit()
	{
		return invalidDigit;
	}
	
	public void setLexeme(String lexeme2)
	{
		lexeme = lexeme2;
	}
	public String getLexeme()
	{
		return lexeme;
	}
	public void setToken(String token2)
	{	
		token = token2;		
	}
	public String getToken()
	{
		return token;
	}
	public void setI(int IComing)
	{
		//System.out.println(IComing);
		i = IComing;
	}
	public int getI()
	{
		return i;
	}
	public void setIsSeparator(boolean value)
	{
		isSeparator = value;
	}
	public boolean getIsSeparator()
	{
		return isSeparator;
	}
	public void setIsOperator(boolean value)
	{
		isOperator = value;		
	}
	public boolean getIsOperator()
	{
		return isOperator;
	}
	public void setIsDigit(boolean value)
	{
		isDigit = value;
	}
	public boolean getIsDigit()
	{
		return isDigit;
	}
	public void setDigit(String digitComing)
	{
		digit = digitComing;
	}
	public String getDigit()
	{
		return digit;
	}
	public void setIsReal(boolean coming)
	{
		isReal = coming;
	}
	public boolean getIsReal()
	{
		return isReal;
	}
	public void setIsIdentifier(boolean coming)
	{
		isIdentifier = coming;
	}
	public boolean getIsIdentifier()
	{
		return isIdentifier;
	}
	public void setIsKeyword(boolean coming)
	{
		isKeyword = coming;
	}
	public boolean getIsKeyword()
	{
		return isKeyword;
	}
	public void setInvalid(boolean setInvalidVar)
	{
		invalid = setInvalidVar;
	}
	public boolean getInvalid()
	{
		return invalid;
	}
	
}
