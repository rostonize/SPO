package Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class StackMachine {
	
	private LinkedList<String> notation = new LinkedList<>();
	private LinkedList<String> notation_tokens = new LinkedList<>();
	private LinkedList<String> stack = new LinkedList<>();
	private LinkedList<String> stack_tokens = new LinkedList<>();
	
	int i = 0;
	double result;
	Map<String,String> TableVar = new HashMap<String,String>();
	Map<String,String> TableVar_tokens = new HashMap<String,String>();
	Map<String,DoubleLinkedList<String>> TableList = new HashMap<String, DoubleLinkedList<String>>();
	
	public void add_notation(String elem) {
		
		notation.addLast(elem);
		
	}
	
	public void add_notation_tokens(String elem) {
		
		notation_tokens.addLast(elem);
		
	}
	
	public void stack() {
	
		//notation_copy = (LinkedList<String>) notation.clone();
		while (notation.size() > i) {
			stack_check();
			//i++;
		}
		
	}
	
	private void stack_check() {
		
			
			switch (notation.get(i)) {
			
			case("="):
				assign();
			break;
			
			case ("+"):
				operation();
			break;
				
			case ("-"):
				operation();
			break;
			
			case ("*"):
				operation();
			break;
			
			case ("/"):
				operation();
			break;
			
			case ("<"):
				operation();
			break;
			
			case ("<="):
				operation();
			break;
				
			case ("=="):
				operation();
			break;
			
			case (">"):
				operation();
			break;
			
			case (">="):
				operation();
			break;
			
			case ("&&"):
				operation();
			break;
			
			case ("||"):
				operation();
			break;
			
			/*case ("~"):
				operation();
			break;*/
			
			case ("var"):
				add_table();
			break;
			
			case (";"):
				i ++;
			break;
			
			case (","):
				i++;
			break;
			
			case ("if"):
				if_check();
			break;
			
			case ("while"):
				while_check();
			break;
			
			case ("do"):
				do_while_check();
			break;
			
			case ("{"):
				i ++;
			break;
			
			case ("}"):
				i++;
			break;
			
			case ("print"):
				print();
			break;
			
			case("LinkedList"):
				LinkedList();
			break;
			
			case("addLast"):
				addLast();
			break;
			
			case("addBefore"):
				addBefore();
			break;
			
			case("addAfter"):
				addAfter();
			break;
			
			case("PrintList"):
				PrintList();
			break;
			
			case("remove"):
				remove();
			break;
			
			case("addFirst"):
				addFirst();
			break;
			
			default:
				
				stack.addLast(notation.get(i));
				stack_tokens.addLast(notation_tokens.get(i));
				i++;
			break;
			
			
			}
			
		if (i == (notation.size()-1)) {
			
			i++;
			
		}
		
	}
	
	private void assign() {
		
		String value = stack.getLast();
		stack.removeLast();
		String lexema = stack.getLast();
		
		stack.removeLast();
		TableVar_tokens.put(lexema, stack_tokens.getLast());
		stack_tokens.removeLast();
		stack_tokens.removeLast();
		TableVar.put(lexema,value);
		
		
		i++;
		
	}
	
	private void operation() {
		String lexema1 = stack.getLast();
		
		stack.removeLast();
		String lexema2 = stack.getLast();
		stack.removeLast();
		String token1 = stack_tokens.getLast();
		stack_tokens.removeLast();
		String token2 = stack_tokens.getLast();
		stack_tokens.removeLast();
		double op1 = 0;
		double op2 = 0;
		double result;
		boolean Result;
		
		
		switch (notation.get(i)) {
		
		case ("+"):
			
			op1 = change_type_switch(lexema1, token1);
			op2 = change_type_switch(lexema2, token2);
			
			result = op1 + op2;
			lexema1 = Double.toString(result);
			stack.addLast(lexema1);
			stack_tokens.addLast("REAL");
			
		break;
			
		case ("-"):
			op1 = change_type_switch(lexema1, token1);
			op2 = change_type_switch(lexema2, token2);
			
			result = op2 - op1;
			lexema1 = Double.toString(result);
			stack.addLast(lexema1);
			stack_tokens.addLast("REAL");
		break;
		
		case ("*"):
			op1 = change_type_switch(lexema1, token1);
			op2 = change_type_switch(lexema2, token2);
			
			result = op1 * op2;
			lexema1 = Double.toString(result);
			stack.addLast(lexema1);
			stack_tokens.addLast("REAL");
		break;
		
		case ("/"):
			op1 = change_type_switch(lexema1, token1);
			op2 = change_type_switch(lexema2, token2);
			
			result = op2 / op1;
			lexema1 = Double.toString(result);
			stack.addLast(lexema1);
			stack_tokens.addLast("REAL");
		break;
		
		case ("<"):
			
			
			
		
			op1 = change_type_switch(lexema1, token1);
			op2 = change_type_switch(lexema2, token2);
		
			Result  = op1 > op2;
			
			stack.addLast(Boolean.toString(Result));
			stack_tokens.addLast("BOOLEAN");
		break;
		
		case ("<="):

		op1 = change_type_switch(lexema1, token1);
		op2 = change_type_switch(lexema2, token2);
	
		Result  = op1 >= op2;
		
		stack.addLast(Boolean.toString(Result));
		stack_tokens.addLast("BOOLEAN");
		break;
			
		case ("=="):
			
			op1 = change_type_switch(lexema1, token1);
			op2 = change_type_switch(lexema2, token2);
			;
			Result  = (op1 == op2);
			
			stack.addLast(Boolean.toString(Result));
			stack_tokens.addLast("BOOLEAN");
		
		break;
		
		case (">"):

			op1 = change_type_switch(lexema1, token1);
			op2 = change_type_switch(lexema2, token2);
			
			Result  = op1 < op2;
			
			stack.addLast(Boolean.toString(Result));
			stack_tokens.addLast("BOOLEAN");
			
		break;
		
		case (">="):
			
			op1 = change_type_switch(lexema1, token1);
			op2 = change_type_switch(lexema2, token2);
		
			Result  = op1 <= op2;
			
			stack.addLast(Boolean.toString(Result));
			stack_tokens.addLast("BOOLEAN");
			
		break;
		
		case ("&&"):
			boolean OP1;
			boolean OP2;
			
			if (token1 == "BOOLEAN") {
				
				OP1 = Boolean.parseBoolean(lexema1);
				if (token2 == "BOOLEAN") {
					
					OP2 = Boolean.parseBoolean(lexema2);
					
					Result = OP1 && OP2;
					
					stack.addLast(Boolean.toString(Result));
					stack_tokens.addLast("BOOLEAN");
				}
				
				else {
					
					System.out.println("Cant work with boolean & not boolean");
					System.exit(0);
					
				}
				
			}
			else {
				System.out.println("Cant work with boolean & not boolean");
				System.exit(0);
			}
			
			
		break;
		
		case ("||"):
			
			if (token1 == "BOOLEAN") {
				
				OP1 = Boolean.parseBoolean(lexema1);
				if (token2 == "BOOLEAN") {
					
					OP2 = Boolean.parseBoolean(lexema2);
					
					Result = OP1 && OP2;
					stack.addLast(Boolean.toString(Result));
					stack_tokens.addLast("BOOLEAN");
				}
				
				else {
					
					System.out.println("Cant work with boolean & not boolean");
					System.exit(0);
					
				}
				
			}
			else {
				System.out.println("Cant work with boolean & not boolean");
				System.exit(0);
			}
			
			
			
		break;
		
		/*case ("~"):
			operation();
		break;*/
		
		}
		
		i ++;
		return;
		
	}
	
	private void add_table() {
		
		if(stack.size() != 0) {
			
			TableVar.put(stack.getLast(), null);
			TableVar_tokens.put(stack.getLast(), "null");
			stack.remove();
			stack_tokens.remove();
			
		}
		
		i++;
		
	}
	
	private void if_check() {
		
		String op = stack.getLast();
		stack.removeLast();
		stack_tokens.removeLast();
		
		i++;
		if(op == "true") {


			while (notation_tokens.get(i) != "CLOSE_BRACKET") {
				//System.out.println(i);
				//System.out.println(notation.get(i));
				stack_check();
				//System.exit(0);
			}
			//System.exit(0);
			i ++;
			//System.out.println(notation_tokens.get(i));
			if (notation_tokens.get(i) == "TOK_KEY_ELSE") {

				while (notation_tokens.get(i) != "CLOSE_BRACKET") {
					
					change_i();
					
				}
				
				i ++; return;
				
			}
			
		}
		
		else {
			//System.out.println(i +"a");
			while (notation_tokens.get(i) != "CLOSE_BRACKET") {
				//System.out.println(i +"b");
				change_i();
				
			}
			
			if (notation.get(i) == "else") {
				
				while (notation.get(i) != "}") {
					
					stack_check();
					
				}
				
				i ++; return;
			
			}
		}
		
		
	}
	
	private void while_check() {
		int k = i;
		
		while ((notation_tokens.get(k) != "CLOSE_BRACKET") && (notation_tokens.get(k) != "TOK_SEMI")) {
			//System.out.println(notation_tokens.get(k));
			//System.out.println("5");
			k--;
			
		}
		//System.out.println(k + "B");
		k++;
		String op = stack.getLast();
		stack.removeLast();
		stack_tokens.removeLast();
		i++;
		//System.out.println(notation.get(k));
		//System.out.println(op);
		if(op == "true") {
			
			while (notation_tokens.get(i) != "CLOSE_BRACKET") {
				//System.out.println(notation.get(i));
				stack_check();
				
			}
			i = k;
			return;
		}
		
		else { 
			
			while (notation_tokens.get(i) != "CLOSE_BRACKET") {
				
				change_i();
				
			}
			
			i++; return;
			
		}
		
	}
	
	private void do_while_check() {
		
		int k = i;
		i++;
		;
		while (notation_tokens.get(i) != "CLOSE_BRACKET") {
			//System.out.println(no);
			stack_check();
			//System.out.println("BB");
		}
		int l = i;
		int m = i;
		//System.out.println(m + "c");

		while  (notation_tokens.get(i+1) != "TOK_KEY_WHILE") {
			System.out.println(notation.get(i));
			stack_check();
			//i++;
			//m ++;
			//.out.println("BB");
			
			
		}
		//System.out.println(m);
		 i = l;
		 //System.out.println(notation.get(m));
		 
		 //System.exit(0);
		/*for(; i < m; i ++) {
			
			if(notation_tokens.get(i) == "IDENTIFIER" || notation_tokens.get(i) == "REAL" || notation_tokens.get(i) == "INTEGER") {
				//System.out.println(notation.get(i) + "a");
				stack.addLast(notation.get(i));
				stack_tokens.addLast(notation_tokens.get(i));
				
			}
			
			else {
				//System.out.println(notation.get(i));
				operation();
				
			}
			
			
		}*/

		String op = stack.getLast();
		stack.removeLast();
		stack_tokens.removeLast();
		if(op == "true") {
			
			i = k;
			
		}
		
		else {

			
			i ++;
			return;
			
		}
		
		
	}
	
	private void print() {
		
		switch (stack_tokens.getLast()) {
		
		case("IDENTIFIER"):
			System.out.print(TableVar.get(stack.getLast()));
			System.out.println();
		break;
		
		default:
			System.out.print(stack.getLast());
			System.out.println();
			break;
			
			
		
		}
		i++;
		stack.removeLast();
		stack_tokens.removeLast();
	}
	
	
	
	private double change_type_double(String lexema, String token) {
		double op;
		return  op = Double.parseDouble(lexema);
		
	}
	
	private double change_type_switch (String lexema, String token) {
		String Op;
		String Op_token;
		double op = 0;
		switch (token) {
		
		case("INTEGER"):
			op = change_type_double(lexema, token);
		break;
			
			
		case("REAL"):
			op = change_type_double(lexema, token);
		break;
		
			
			
		case("IDENTIFIER"):
			
			Op = TableVar.get(lexema);
			
			Op_token = TableVar_tokens.get(lexema);
			
			switch (Op_token) {
			
			case("INTEGER"):
				op = change_type_double(Op, token);
			break;
				
			case("REAL"):
				op = change_type_double(Op, token);
			break;
			
			
			
				default:
					break;
			}
			
			default:
				break;
		
		}
		
		return op;
		
	}
	
	
	private void change_i() {
		
			
			if (notation.get(i) == "else" || notation.get(i) == "while" || notation.get(i) == "if") {
				
				while (notation.get(i) != "}") {
					
					change_i();
					
				}
				
				i ++;
				return;
				
			}
			
			else {
			
				if (notation.get(i) == "do") {
				
				while (notation.get(i) != "while" && (notation.get(i+1)) != ";") {
					
					change_i();
					
				}
				
				i = i + 2;
				return;
				
				}
			}
			

		
		i ++; return;
		
	}
		
	private void LinkedList() {
		String name = stack.getLast();
		DoubleLinkedList <String> UserList = new DoubleLinkedList<>();
 		
		TableList.put(name, UserList);
		stack.removeLast();
		stack_tokens.removeLast();
		i++;
	}
	
	private void addLast() {
		
		
		DoubleLinkedList <String> UserList = TableList.get(stack.getLast());
		stack.removeLast();
		stack_tokens.removeLast();
		
		UserList.addLast(stack.getLast());
		
		stack.removeLast();
		stack_tokens.removeLast();
		i++;
		
	}
	
	private void addBefore() {
		
		DoubleLinkedList <String> UserList = TableList.get(stack.getLast());
		
		stack.removeLast();
		stack_tokens.removeLast();
		
		int k = Integer.parseInt(stack.getLast());
		stack.removeLast();
		stack_tokens.removeLast();
		
		UserList.addBefore(stack.getLast(), k);
		stack.removeLast();
		stack_tokens.removeLast();
		i++;
		
	}
	
	private void addAfter() {
		
		DoubleLinkedList <String> UserList = TableList.get(stack.getLast());
		stack.removeLast();
		stack_tokens.removeLast();
		//System.out.println(stack.getLast());
		int k = Integer.parseInt(stack.getLast());
		stack.removeLast();
		stack_tokens.removeLast();
		UserList.addAfter(stack.getLast(), k);
		stack.removeLast();
		stack_tokens.removeLast();
		i++;
		
	}

	private void PrintList() {
		
		DoubleLinkedList <String> UserList = TableList.get(stack.getLast());
		stack.removeLast();
		stack_tokens.removeLast();
		UserList.print_list();
		i++;
		
	}
	
	private void remove() {
		
		DoubleLinkedList <String> UserList = TableList.get(stack.getLast());
		stack.removeLast();
		stack_tokens.removeLast();
		UserList.remove(stack.getLast());
		stack.removeLast();
		stack_tokens.removeLast();
		i++;
		
	}
	
	private void addFirst() {
		
		DoubleLinkedList <String> UserList = TableList.get(stack.getLast());
		stack.removeLast();
		stack_tokens.removeLast();
		
		UserList.addFirst(stack.getLast());
		
		stack.removeLast();
		stack_tokens.removeLast();
		i++;
		
	}
	
}
