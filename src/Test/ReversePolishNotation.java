package Test;
import java.util.LinkedList;
import java.util.Stack;

class ReversePolishNotation {
	
	private LinkedList<String> mass_tokens = new LinkedList<>();
	private LinkedList<String> mass_values = new LinkedList<>();
	private LinkedList<String> notation = new LinkedList<>();
	private LinkedList<String> operators = new LinkedList<>();
	private LinkedList<String> operators_tokens = new LinkedList<>();
	private LinkedList<String> notation_tokens = new LinkedList<>();
	
	public void add_values(String value) {
		
		mass_values.addLast(value);
		return;
		
	}
	
	public String return_rpn(int i) {
		
		return notation.get(i);
		
	}
	
	public String return_rpn_tokens(int i) {
		
		return notation_tokens.get(i);
		
	}
	
	public int return_size () {
		
		return notation.size();
		
	}
	
	
	public void add_tokens(Token token) {
		
		mass_tokens.addLast(token.toString());
		return;
		
	}
	
	public void print_rpn() {
		int i = 0;
		while (notation.size() > i ) {
			
			System.out.print(notation.get(i));
			i++;
			
		}
		
	}
	
	public void print_rpn_tok() {
		int i = 0;
		while (notation_tokens.size() > i ) {
			
			System.out.print(notation_tokens.get(i) + " ");
			
			i++;
			
		}
		
	}
	
	
	
	private int get_priority(String Token) {
		
		int priority = 0;
		switch (Token) {
		
		case("TOK_KEY_VAR"):
			priority = 0;
		break;
		
		case("TOK_KEY_IF"):
			priority = 0;
		break;
		
		case("TOK_KEY_WHILE"):
			priority = 0;
		break;
		
		case("TOK_KEY_DO"):
			priority = 10;
		break;
		
		case("TOK_KEY_ELSE"):
			priority = 11;
		break;
		
		case("OPEN_BRACKET"):
			priority = 0;
		break;
		
		case("CLOSE_BRACKET"):
			priority = 1;
		break;
		
		case("REAL"):
			priority = 0;
		break;
		
		case("STRING"):
			priority = 0;
		break;
		
		case("INTEGER"):
			priority = 0;
		break;
		
		case("IDENTIFIER"):
			priority = 0;
		break;
		
		case("TOK_KEY_PRINT"):
			priority = 10;
		break;
		
		case("REAL_MINUS"):
			priority = 0;
		break;
		
		case("INTEGER_MINUS"):
			priority = 0;
		break;
		
		case("TOK_MINUS_OP"):
			priority = 7;
		break;
		
		case("TOK_PLUS_OP"):
			priority = 7;
		break;
		
		case("TOK_MUL_OP"):
			priority = 8;
		break;
		
		case("TOK_DIV_OP"):
			priority = 8;
		break;
		
		case("TOK_NOT"):
			priority = 5;
		break;
		
		case("TOK_AND"):
			priority = 4;
		break;
		
		case("TOK_OR"):
			priority = 3;
		break;
		
		case("TOK_LEG"):
			priority = 6;
		break;
		
		case("TOK_LESS"):
			priority = 6;
		break;
		
		case("TOK_GEQ"):
			priority = 6;
		break;
		
		case("TOK_GT"):
			priority = 6;
		break;
		
		case("TOK_EQ"):
			priority = 6;
		break;
		
		case("TOK_ASSIGN"):
			priority = 2;
		break;
		
		case("TOK_OPEN"):
			priority = 0;
		break;
		
		case("TOK_CLOSE"):
			priority = 1;
		break;
		
		case("TOK_SEMI"):
			priority = 1;
		break;
		
		case("TOK_COMMA"):
			priority = 1;
		break;
		
		
		}
		
		
		
		return priority;
	}
	
	public void rpn() {
		
		while (!mass_values.isEmpty()) {
			
			switch (mass_tokens.getFirst()) {
				
			case("TOK_KEY_VAR"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_KEY_IF"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_KEY_WHILE"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_KEY_DO"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_KEY_ELSE"):
				add_op(mass_values.getFirst());
			break;
			
			case("OPEN_BRACKET"):
				add_op(mass_values.getFirst());
			break;
			
			case("CLOSE_BRACKET"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_KEY_PRINT"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_MINUS_OP"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_PLUS_OP"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_MUL_OP"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_DIV_OP"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_NOT"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_AND"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_OR"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_LEG"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_LESS"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_GEQ"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_GT"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_ASSIGN"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_EQ"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_OPEN"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_CLOSE"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_SEMI"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_COMMA"):
				add_op(mass_values.getFirst());
			break;
			
			case("TOK_KEY_LINKED_LIST"):
				add_op(mass_values.getFirst());
			break;
			
			case ("TOK_KEY_ADD_LAST"):
				add_op(mass_values.getFirst());
			break;
			
			case ("TOK_KEY_ADD_FIRST"):
				add_op(mass_values.getFirst());
			break;
			
			case ("TOK_KEY_ADD_BEFORE"):
				add_op(mass_values.getFirst());
			break;
			
			case ("TOK_KEY_ADD_AFTER"):
				add_op(mass_values.getFirst());
			break;
			
			case ("TOK_KEY_PRINT_LIST"):
				add_op(mass_values.getFirst());
			break;
			
			case ("TOK_KEY_REMOVE_LIST"):
				add_op(mass_values.getFirst());
			break;
			
			
			default:
				//if (operators.getLast() != "(" && operators.getLast() != ")") {
					notation.add(mass_values.getFirst());
					notation_tokens.add(mass_tokens.getFirst());
				//}
				mass_values.remove();
				mass_tokens.remove();
					
				break;
			
			}
			
		}
		

		
		while(operators.size() != 0) {

			notation.addLast(operators.getLast());
			operators.remove();
			operators_tokens.remove();
			
		}
		
		
	}
	
	private void add_op (String lexema) {
		
		String token = mass_tokens.getFirst();
		
		if ((operators.size() == 0 || token == "TOK_OPEN") && (token != "TOK_SEMI") && (token != "OPEN_BRACKET") && (token != "CLOSE_BRACKET")) {
			operators.addLast(lexema);
			operators_tokens.addLast(token);
			mass_values.remove();
			mass_tokens.remove();
			
		}
		
		else {

			if (token == "TOK_CLOSE") {
				//System.out.println(mass_tokens.getFirst());
				//for(int i = 0; i <operators.size();i ++) {System.out.println(operators.get(i));}
				while (operators_tokens.getLast() != "TOK_OPEN") {
					//System.out.println(operators.getLast());
					//System.out.println(operators.size());
					notation_tokens.addLast(operators_tokens.getLast());
					notation.addLast(operators.getLast());
					operators.removeLast();
					operators_tokens.removeLast();
					
					
				}
				
				mass_values.removeFirst();
				mass_tokens.removeFirst();
				operators.removeLast();
				operators_tokens.removeLast();
				
			
				
			}
			
			else {
				
				if ( (token == "TOK_SEMI") || (token == "OPEN_BRACKET") || (token == "CLOSE_BRACKET") ) {
				//System.out.println(operators.getLast());
					while (operators.size() != 0) {
						//System.out.println(operators.getLast());
						//System.out.println("Check1");
						notation_tokens.addLast(operators_tokens.getLast());
						notation.addLast(operators.getLast());
						operators.removeLast();
						operators_tokens.removeLast();
						
					}
					notation_tokens.addLast(mass_tokens.getFirst());
					notation.addLast(mass_values.getFirst());
					mass_values.removeFirst();
					mass_tokens.removeFirst();
				}
				
				else {
					if (operators.size() != 0 ) {
						//System.out.println(operators.getLast());
						while (get_priority(operators_tokens.getLast()) >= get_priority(token)) {

							//System.out.println(operators.size());
							notation_tokens.addLast(operators_tokens.getLast());
							notation.addLast(operators.getLast());
							operators.removeLast();
							operators_tokens.removeLast();
							
							if(operators.size() == 0) {
								
								break;
								
							}
							
						}
					}
					
					operators.addLast(lexema);
					operators_tokens.addLast(token);
					mass_values.removeFirst();
					mass_tokens.removeFirst();
					
					
				}
				
			}
			
		}
			
		
		
	}
	
}
	
	
	//private void add
	
	
	//}
