import java.io.*;

enum TokenType 
{ 
	INT,
	FLOAT,
	SUM, 
	SUB,
	MULT,
	DIV,
	APar,
	FPar,
	EOF
}

class Token 
{
  	String lexema;
  	TokenType token;

	Token (String l, TokenType t) 
	{ 
		lexema = l;
		token = t;
	}	
}

class AnaliseLexica 
{
	PushbackReader file;

	AnaliseLexica(String a) throws Exception
	{
	 	file = new PushbackReader(new FileReader(a));	
	}

	Token getNextToken() throws Exception
	{	
		Token token;
		int eof = -1;
		char currchar;
		int currchar1;

		do {
			currchar1 =  file.read();
			currchar = (char) currchar1;
		} while (currchar == '\n' || currchar == ' ' || currchar =='\t' || currchar == '\r');
		
		if (currchar1 != eof && currchar1 != 10) {
			if (currchar >= '0' && currchar <= '9' || currchar == '.') {
				StringBuffer buffer = new StringBuffer("");
				buffer.append(currchar);
				boolean isFloat = currchar == '.';
				
				do {
					currchar1 = file.read();
					currchar = (char) currchar1;
					buffer.append(currchar);
				} while (currchar1 != eof && currchar1 != 10 && currchar >= '0' && currchar <= '9' || currchar == '.');

				file.unread(currchar1);
				buffer.deleteCharAt(buffer.length() - 1);
				
				if (isFloat)
					return (new Token (buffer.toString(), TokenType.FLOAT));
				
				return (new Token (buffer.toString(), TokenType.INT));
			}

			switch (currchar) {
				case '(':
					return (new Token(String.valueOf(currchar), TokenType.APar));
				case ')':
					return (new Token(String.valueOf(currchar), TokenType.FPar));
				case '+':
					return (new Token(String.valueOf(currchar), TokenType.SUM));
				case '-':
					return (new Token(String.valueOf(currchar), TokenType.SUB));
				case '*':
					return (new Token(String.valueOf(currchar), TokenType.MULT));
				case '/':
					return (new Token(String.valueOf(currchar), TokenType.DIV));
				default: 
					throw (new Exception("Caractere inválido: " + ((int) currchar)));
			}
		}

		file.close();
			
		return (new Token(String.valueOf(currchar), TokenType.EOF));
	}
}
