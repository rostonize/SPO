package Test;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedList;


public class Test {
	
	public static void main (String args[]) {
		
		final String dir = System.getProperty("user.dir");
		Lexer lexer = new Lexer(dir + "\\Input.txt");
		
       System.out.println("-----------------");
        
        while (!lexer.isExhausted()) {
         System.out.printf("%-10s : %s \n", lexer.currentLexema(), lexer.currentToken());
            lexer.moveAhead();
        }
        
      System.out.println("-----------------");
        
        
        
        if (!lexer.isSuccessful()) { 
        	System.out.println(lexer.errorMessage());
            return;
        } 
        
        LinkedList<Token> mass_tok = new LinkedList<>();
        LinkedList<String> mass_values = new LinkedList<>();
        Parser parser = new Parser ();
        
        for (int i = 0; i < lexer.return_quantity(); i++) {
        	
        	mass_tok.addLast(lexer.return_token(i));
        	mass_values.addLast(lexer.return_values(i));
        	parser.add_elem_tok(lexer.return_token(i));
        	//System.out.println(mass_tok.get(i));
        	
        }
        
        parser.lang();
        
        ReversePolishNotation rpn = new ReversePolishNotation();
        
       for (int i = 0; i < lexer.return_quantity(); i++) {
        	
    	    rpn.add_tokens(lexer.return_token(i));
        	rpn.add_values(lexer.return_values(i));
        	
        }
        
        rpn.rpn();
       rpn.print_rpn();
        System.out.println();
        //rpn.print_rpn_tok();
        //System.out.println();
        LinkedList<String> notation = new LinkedList<>();
        LinkedList<String> notation_tokens = new LinkedList<>();
        StackMachine stack = new StackMachine();
        
        for (int i = 0; i < rpn.return_size(); i++) {
        	
        	notation_tokens.addLast(rpn.return_rpn_tokens(i));
    	    notation.addLast(rpn.return_rpn(i));
    	    stack.add_notation(rpn.return_rpn(i));
    	    stack.add_notation_tokens(rpn.return_rpn_tokens(i));
        	
        }
        stack.stack();
        System.out.println("Таблица переменных:");
		stack.print_table_var();
		System.out.println("Таблица списков:");
		stack.print_table_list();
        System.out.println("Done");
        
        //DoubleLinkedList <String> check_list = new DoubleLinkedList<>();
        /*check_list.addFirst("Um");
        check_list.addLast("1");
        check_list.addLast("2");
        check_list.addLast("3");
        check_list.addBefore("B", 2);
        check_list.addAfter("C", 3);
        check_list.print_list(); 
        check_list.remove("C");
        check_list.remove("B");
        check_list.print_list(); */
        
        
        
        
}
        
	
}
