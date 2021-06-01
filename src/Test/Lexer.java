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
	 private StringBuilder input = new StringBuilder(); //Входной поток с файла
	    private Token token; //Токен
	    private String lexema; //Полученная лексема
	    private boolean exhausted = false; //Ошибка
	    private String errorMessage = "";
	    private Set<Character> blankChars = new HashSet<Character>(); //таблице хэшей
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

	    public Lexer(String filePath) { //Добавляем во входной поток содержимое файла, если нет файла, то ссылаемся на ошибку
	        
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
	        blankChars.add((char) 32); //В таблицу хэшев добавляем пробел, табуляцию, перенос на новую строчку и т.д.
	        
	        moveAhead(); //Двигаемся вперед
	    }

	    public void moveAhead() {
	        if (exhausted) {
	            return;
	        }

	        if (input.length() == 0) { //Длина содержимого файла равна 0, то ошибка
	        	exhausted = true;
	            return;
	        }

	        ignoreWhiteSpaces(); //Удаляем пробелы/иные неопознанные символы, которые можно проигнорировать

	        if (findNextToken()) { //Проверяем на существование следующего токена
	            return;
	        }

	        exhausted = true;

	        if (input.length() > 0) { //Файл не пустой, но встречен был неясный символ, вывод на ошибку
	            errorMessage = "Unexpected symbol: '" + input.charAt(0) + "'";
	        }
	    }

	    private void ignoreWhiteSpaces() { //Сверяемся с таблицей хэшей, если есть такой символ, то передвигаем указатель дальше, если нет - удаляем
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
	        for (Token t : Token.values()) { //Получаем весь список возможных токенов и проходимся по нему
	        	
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

	    public Token currentToken() { //Возвращает текущий токен
	        return token;
	    }

	    public String currentLexema() { //Возвращает текущую лексему
	        return lexema;
	    }

	    public boolean isSuccessful() {
	        return errorMessage.isEmpty();
	    }

	    public String errorMessage() { //Возвращает найденную ошибку в качестве сообщения
	        return errorMessage;
	    }

	    public boolean isExhausted() { //Возвращает существование ошибки 
	        return exhausted;
	    }
}
