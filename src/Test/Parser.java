package Test;

import java.util.LinkedList;

/*
 
 Грамматика языка:
 
 lang -> OPEN_BRACKET expr+ CLOSE_BRACKET
 expr -> assign_expr | if_expr | while_expr | do_while_expr | assign_expr_idn | print_expr | dll_expr | addLast_expr | addBefore_expr | addAfter_expr | PrintList_expr | remove_expr | add_first_expr
 assign_expr -> TOK_KEY_VAR IDENTIFIER (TOK_ASSIGN value_expr)? TOK_SEMI
 if_expr -> if_head body (TOK_KEY_ELSE body)?
 while_expr -> while_head body
 do_while_expr -> TOK_KEY_DO body while_head
 value_expr -> (value_brakets | value) (math_op value_expr)?
 if_head -> TOK_KEY_IF logic_expr 
 body -> OPEN_BRACKET expr+ CLOSE_BRACKET
 while_head -> TOK_KEY_WHILE logic_expr
 value -> REAL | INTEGER | IDENTIFIER | STRING | INTEGER_MINUS | REAL_MINUS
 logic_expr -> TOK_OPEN value (logic_op value)* TOK_CLOSE
 assign_expr_idn -> IDENTIFIER (TOK_ASSIGN value_expr*)? TOK_SEMI
 value_brakets ->TOK_OPEN value_expr TOK_CLOSE
 math_op -> TOK_MINUS_OP | TOK_PLUS_OP | TOK_MUL_OP | TOK_DIV_OP 
 logic_op -> TOK_LEG | TOK_LESS | TOK_GEQ | TOK_GT | TOK_EQ
 print_expr -> TOK_KEY_PRINT TOK_OPEN value TOK_CLOSE
 dll_expr -> TOK_KEY_LINKED_LIST  IDENTIFIER TOK_SEMI
 addLast_expr -> TOK_KEY_ADD_LAST TOK_OPEN value TOK_COMMA INTEGER TOK COMMA IDENTIFIER TOK_CLOSE TOK_SEMI
 addBefore_expr -> TOK_KEY_ADD_BEFORE TOK_OPEN value TOK_COMMA INTEGER TOK COMMA IDENTIFIER TOK_CLOSE TOK_SEMI
 addAfter_expr -> TOK_KEY_ADD_AFTER TOK_OPEN value TOK_COMMA INTEGER TOK COMMA IDENTIFIER TOK_CLOSE TOK_SEMI
 PrintList_expr -> TOK_KEY_PRINT_LIST TOK_OPEN IDENTIFIER TOK_CLOSE TOK_SEMI
 remove_expr -> TOK_KEY_REMOVE_LIST TOK_OPEN value TOK_COMMA IDENTIFIER TOK_CLOSE TOK_SEMI
 add_first_expr -> TOK_KEY_ADD_LAST TOK_OPEN value TOK_COMMA INTEGER TOK COMMA IDENTIFIER TOK_CLOSE TOK_SEMI
 
 */

public class Parser {
	//private LinkedList<String> mass_values = new LinkedList<>();
    private LinkedList<Token> mass_tok = new LinkedList<>();

    
    public void add_elem_tok(Token token) {
    	
    	mass_tok.addLast(token);
    	
    }
    
    public void Error() {
    	if (mass_tok.size() != 0) {
    	System.out.println("You have error with " + mass_tok.getFirst().toString());
    	}
    	else {
    		System.out.println("You have error with null");
    	}
		System.exit(0);
    	
    }
	
	public void lang() { //Точка вхождения
		switch (mass_tok.getFirst().toString()){
		case("OPEN_BRACKET"):
			mass_tok.remove();
		
			while (mass_tok.getFirst().toString() != "CLOSE_BRACKET") {
			
				expr();
			
			}
			
			switch (mass_tok.getFirst().toString()){
			
			case("CLOSE_BRACKET"):
			
				mass_tok.remove();
				
				if (!mass_tok.isEmpty()) {
					
					Error();
					
				}
				break;
			
			default:
				
				Error();
				break;
				
			}
			
			break;
			
			default:
				Error();
				break;
				
		}
		
	}
	
	private void expr() {
		
		switch (mass_tok.getFirst().toString()){
			
			case ("TOK_KEY_VAR"):
				assign_expr();
			break;
			
			case ("IDENTIFIER"):
				assign_expr_idn();
			break;
			
			case ("TOK_KEY_IF"):
				if_expr();
			break;
			
			case ("TOK_KEY_WHILE"):
				while_expr();
			break;
			
			case("TOK_KEY_DO"):
				do_while_expr();
			break;
			
			case("TOK_KEY_PRINT"):
				print_expr();
			break;
			
			case("TOK_KEY_LINKED_LIST"):
				dll_expr();
			break;
			
			case("TOK_KEY_ADD_LAST"):
				addLast_expr();
			break;
			
			case("TOK_KEY_ADD_FIRST"):
				addFirst_expr();
			break;
			
			case("TOK_KEY_ADD_BEFORE"):
				addBefore_expr();
			break;
			
			case("TOK_KEY_ADD_AFTER"):
				addAfter_expr();
			break;
			
			case("TOK_KEY_PRINT_LIST"):
				PrintList_expr();
			break;
			
			case("TOK_KEY_REMOVE_LIST"):
				remove_expr();
			break;
			
			default:
				Error();
			break;
			
		}
		
	}
	
	
	private void assign_expr() {
		
		if (mass_tok.getFirst().toString() == "TOK_KEY_VAR") {
			
				mass_tok.remove();
				
				if (mass_tok.getFirst().toString() == "IDENTIFIER") {
					
					mass_tok.remove();
					
					if (mass_tok.getFirst().toString() == "TOK_ASSIGN") {
						
						mass_tok.remove();
						
						while (mass_tok.getFirst().toString() != "TOK_SEMI") {
							
							value_expr();
							
							if (mass_tok.getFirst().toString() == "TOK_SEMI") {
								
								mass_tok.remove();
								return;
								
							}
							
						}
						
						
					}
					
					else {
						
						if (mass_tok.getFirst().toString() == "TOK_SEMI") {
							
							mass_tok.remove();
							return;
							
						}
						
						else {
							
							Error();
							
						}
						
					}
					
				}
				
				else {
				
					Error();
			
				}
			
		}
		
		else {
			
			Error();
			
		}
		
	}
	
	private void assign_expr_idn() {
		
		
			
			switch (mass_tok.getFirst().toString()) {
			
				case("IDENTIFIER"):
					mass_tok.remove();
					switch (mass_tok.getFirst().toString()) {
						case ("TOK_ASSIGN"):
							mass_tok.remove();
							while (mass_tok.getFirst().toString() != "TOK_SEMI") {
							value_expr();
							}
							mass_tok.remove();
					
						break;
						
						default:
							Error();
							break;
							
					}
					break;
				default:
					Error();
					break;
			
		}
		
	}
	
	private void if_expr() {
		
		if_head();
		body();
		switch (mass_tok.getFirst().toString()){
		
		case("TOK_KEY_ELSE"):
			mass_tok.remove();
			body();
			break;
		
		default:
			return;
		
		
		}
		
		
		
	}
	
	private void while_expr() {
		
		while_head();
		body();
		
	}
	
	private void do_while_expr() {
		
		switch (mass_tok.getFirst().toString()){
		
		case("TOK_KEY_DO"):
			mass_tok.remove();
			body();
			while_head();
			if (mass_tok.getFirst().toString() == "TOK_SEMI") {
				mass_tok.remove();
			}
			else {
				Error();
				break;
			}
		break;
		
		default:
			Error();
			break;
		
		}
		
	}
	
	private void value_expr() {
		// value_expr -> (value_brakets | value) (math_op value_expr)?
		switch (mass_tok.getFirst().toString()){
		
		case("TOK_OPEN"):
			value_brakets();
			if (mass_tok.getFirst().toString() != "TOK_SEMI" && mass_tok.getFirst().toString() != "TOK_CLOSE") {
				math_op();
				value_expr();
			}
			break;
			//REAL | INTEGER | IDENTIFIER | STRING
		
		default: 
			value();
			if (mass_tok.getFirst().toString() != "TOK_SEMI" && mass_tok.getFirst().toString() != "TOK_CLOSE") {
				math_op();
				value_expr();
			}
			
			break;

		
		}
		
		
		
		
	}
	
	private void if_head() {
		// if_head -> TOK_KEY_IF logic_expr 
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_KEY_IF"):
			mass_tok.remove();
			logic_expr();
			break;
			
		default:
			Error();
			break;
		
		}
		
	}	
	
	private void body() {
		//body -> OPEN_BRACKET expr* CLOSE_BRACKET
		switch (mass_tok.getFirst().toString()) {
		
		case("OPEN_BRACKET"):
			mass_tok.remove();
			while (mass_tok.getFirst().toString() != "CLOSE_BRACKET") {
				
				expr();
				
			}
			mass_tok.remove();
			break;
		default:
			Error();
			break;
		
		}
		
	}
	
	private void while_head() {
		//while_head -> TOK_KEY_WHILE logic_expr
		switch (mass_tok.getFirst().toString()) {
		
		case ("TOK_KEY_WHILE"):
			mass_tok.remove();
			logic_expr();
			break;
			
		default:
			Error();
			break;
		
		
		}
		
	}
	
	private void value_brakets() {
		//value_brakets ->TOK_OPEN value_expr TOK_CLOSE
		switch (mass_tok.getFirst().toString()) {
		
		case ("TOK_OPEN"):
			mass_tok.remove();
			value_expr();
			switch (mass_tok.getFirst().toString()) {
			
			case ("TOK_CLOSE"):
				mass_tok.remove();
				break;
			
			default:
				Error();
				break;
			
			}
			break;
			
		default:
			Error();
			break;
		
		}
		
	}
	
	private void value() {
		//value -> REAL | INTEGER | IDENTIFIER | STRING | INTEGER_MINUS | REAL_MINUS
		
		switch (mass_tok.getFirst().toString()) {
		
		case("REAL"):
			mass_tok.remove();
		break;
		
		case("INTEGER"):
			mass_tok.remove();
		break;
		
		case("IDENTIFIER"):
			mass_tok.remove();
		break;
		
		case("STRING"):
			mass_tok.remove();
		break;
		
		case("INTEGER_MINUS"):
			mass_tok.remove();
		break;
		
		case("REAL_MINUS"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
			
		
		}
		
		
	}
	
	private void math_op() {
		//math_op -> TOK_MINUS_OP | TOK_PLUS_OP | TOK_MUL_OP | TOK_DIV_OP 
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_MINUS_OP"):
			mass_tok.remove();
		break;
		
		case("TOK_PLUS_OP"):
			mass_tok.remove();
		break;
		
		case("TOK_MUL_OP"):
			mass_tok.remove();
		break;

		case("TOK_DIV_OP"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		}
		
		
	}
	
	private void logic_expr() {
		//logic_expr -> TOK_OPEN value (logic_op value)* TOK_CLOSE
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_OPEN"):
			mass_tok.remove();
			value();
			while (mass_tok.getFirst().toString() != "TOK_CLOSE") {
			logic_op();
			value();
			}
			mass_tok.remove();
			
		break;
		
		default:
			System.out.print("Hui2");
			Error();
			break;
		
		}
		
	}
	
	private void logic_op() {
		//logic_op -> TOK_LEG | TOK_LESS | TOK_GEQ | TOK_GT | TOK_EQ | TOK_NOT | TOK_AND | TOK_OR
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_LEG"):
			mass_tok.remove();
		break;
		
		case("TOK_LESS"):
			mass_tok.remove();
		break;
		
		case("TOK_GEQ"):
			mass_tok.remove();
		break;
		
		case("TOK_GT"):
			mass_tok.remove();
		break;
		
		case("TOK_EQ"):
			mass_tok.remove();
		break;
		
		case("TOK_NOT"):
			mass_tok.remove();
		break;
		
		case("TOK_AND"):
			mass_tok.remove();
		break;
		
		case("TOK_OR"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		
		}
		
	}
	
	private void print_expr() {
		
		mass_tok.remove();
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_OPEN"):
			mass_tok.remove();
			value();
			switch (mass_tok.getFirst().toString()) {
			
			case("TOK_CLOSE"):
				mass_tok.remove();
			switch (mass_tok.getFirst().toString()) {
			
			case("TOK_SEMI"):
				mass_tok.remove();
			break;
			 
			default:
				Error();
				break;
			
			}
			break;
			
			default:
				Error();
				break;
			}
		break;
		
		default:
			Error();
			break;
		
		}
		
	}
	
	private void dll_expr() {
		
		mass_tok.remove();
		
		
		
		switch (mass_tok.getFirst().toString()) {
		
		case("IDENTIFIER"):
			
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		}
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_SEMI"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		}
		
	}
	
	private void addLast_expr() {
		
		mass_tok.remove();
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_OPEN"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		
		}
		
		value();
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_COMMA"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		}
	
		switch (mass_tok.getFirst().toString()) {
		
		case("IDENTIFIER"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		}
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_CLOSE"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		
		}
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_SEMI"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		
		}
		
		
	}
	
	private void addBefore_expr() {
		
		mass_tok.remove();
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_OPEN"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		
		}
		
		value();
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_COMMA"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
	}
		
		switch (mass_tok.getFirst().toString()) {
		
		case("INTEGER"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
			
		}
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_COMMA"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
	}
	
		switch (mass_tok.getFirst().toString()) {
		
		case("IDENTIFIER"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
	}
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_CLOSE"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		
		}
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_SEMI"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		
		}
		
	}
	
	private void addAfter_expr() {
		
		mass_tok.remove();
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_OPEN"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		
		}
		
		value();
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_COMMA"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
	}
		
		switch (mass_tok.getFirst().toString()) {
		
		case("INTEGER"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
			
		}
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_COMMA"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
	}
	
		switch (mass_tok.getFirst().toString()) {
		
		case("IDENTIFIER"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
	}
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_CLOSE"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		
		}
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_SEMI"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		
		}
		
	}
	
	private void PrintList_expr() {
		
		mass_tok.remove();
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_OPEN"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		
		}
		
		
		switch (mass_tok.getFirst().toString()) {
		
		case("IDENTIFIER"):
			mass_tok.remove();
		break;
		
		default:
			
			Error();
			break;
		
	}
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_CLOSE"):
			mass_tok.remove();
			//System.out.println("1");
		break;
		
		default:
			
			Error();
			break;
			
		}
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_SEMI"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
	}
		
	}

	private void remove_expr() {
		
		mass_tok.remove();
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_OPEN"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		
		}
		
		value();
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_COMMA"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
			
		}
		
		switch (mass_tok.getFirst().toString()) {
		
		case("IDENTIFIER"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
			
		}

		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_CLOSE"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
			
		}
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_SEMI"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
	}
		
	}

	private void addFirst_expr() {
		

		mass_tok.remove();
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_OPEN"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		
		}
		
		value();
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_COMMA"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		}
	
		switch (mass_tok.getFirst().toString()) {
		
		case("IDENTIFIER"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		}
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_CLOSE"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		
		}
		
		switch (mass_tok.getFirst().toString()) {
		
		case("TOK_SEMI"):
			mass_tok.remove();
		break;
		
		default:
			Error();
			break;
		
		
		}
		
		
	}
		
	
}
