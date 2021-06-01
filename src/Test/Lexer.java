package Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Stream;
import java.util.List;


public class Lexer {
	 private StringBuilder input = new StringBuilder(); //������� ����� � �����
	    private Token token; //�����
	    private String lexema; //���������� �������
	    private boolean exhausted = false; //������
	    private String errorMessage = "";
	    private Set<Character> blankChars = new HashSet<Character>(); //������� �����
	    private LinkedList<String> mass_values = new LinkedList<>();
	    private LinkedList<Token> mass_tok = new LinkedList<>();
	    private void add_elem(String lexema, Token token) {
	    	
	    	mass_values.addLast(lexema);
	    	mass_tok.addLast(token);
	    	
	    }
	    public String return_values(int i) {
	    	
	    	return mass_values.get(i);
	    	
	    }
	    
	    public int return_quantity() {
	    	
	    	return mass_values.size();
	    	
	    }
	    
	    public Token return_token (int i) {
	    	
	    	return mass_tok.get(i);
	    	
	    }

	    public Lexer(String filePath) { //��������� �� ������� ����� ���������� �����, ���� ��� �����, �� ��������� �� ������
	        
	    	try (Stream<String> st = Files.lines(Paths.get(filePath))) {
	            st.forEach(input::append);
	        } 
	    	
	    	catch (IOException ex) {
	        	exhausted = true;
	            errorMessage = "Could not read file: " + filePath;
	            return;
	        }
	    	input.append(" ");
	        blankChars.add('\r');
	        blankChars.add('\n');
	        blankChars.add((char) 8);
	        blankChars.add((char) 9);
	        blankChars.add((char) 11);
	        blankChars.add((char) 12);
	        blankChars.add((char) 32); //� ������� ����� ��������� ������, ���������, ������� �� ����� ������� � �.�.
	        
	        moveAhead(); //��������� ������
	    }

	    public void moveAhead() {
	        if (exhausted) {
	            return;
	        }

	        if (input.length() == 0) { //����� ����������� ����� ����� 0, �� ������
	        	exhausted = true;
	            return;
	        }

	        ignoreWhiteSpaces(); //������� �������/���� ������������ �������, ������� ����� ���������������

	        if (findNextToken()) { //��������� �� ������������� ���������� ������
	            return;
	        }

	        exhausted = true;

	        if (input.length() > 0) { //���� �� ������, �� �������� ��� ������� ������, ����� �� ������
	            errorMessage = "Unexpected symbol: '" + input.charAt(0) + "'";
	        }
	    }

	    private void ignoreWhiteSpaces() { //��������� � �������� �����, ���� ���� ����� ������, �� ����������� ��������� ������, ���� ��� - �������
	        int charsToDelete = 0;

	        while ((blankChars.contains(input.charAt(charsToDelete) ) && (input.length() > charsToDelete+1))) {
	            charsToDelete++; 
	        }
	        
	        if (input.length() == charsToDelete+1) {
	        	
	        	charsToDelete++;
	        	
	        }
	        
	        if (charsToDelete > 0) {
	            input.delete(0, charsToDelete); 
	        }
	        
	    }

	    private boolean findNextToken() {
	        for (Token t : Token.values()) { //�������� ���� ������ ��������� ������� � ���������� �� ����
	        	
	        	 int end = t.endOfMatch(input.toString());
	            
	            if (end != -1) {
			          
	            		token = t;
	                	lexema = input.substring(0, end);
	                	add_elem(lexema, token);
	                	input.delete(0, end);
	                	return true;
	            	
	            }
	        }

	        return false;
	    }

	    public Token currentToken() { //���������� ������� �����
	        return token;
	    }

	    public String currentLexema() { //���������� ������� �������
	        return lexema;
	    }

	    public boolean isSuccessful() {
	        return errorMessage.isEmpty();
	    }

	    public String errorMessage() { //���������� ��������� ������ � �������� ���������
	        return errorMessage;
	    }

	    public boolean isExhausted() { //���������� ������������� ������ 
	        return exhausted;
	    }
}
