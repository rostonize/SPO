package Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Token { //Перечисления токенов
	TOK_KEY_PRINT("print"),
	TOK_KEY_LINKED_LIST("LinkedList"),
	TOK_KEY_ADD_FIRST("addFirst"),
	TOK_KEY_ADD_LAST("addLast"),
	TOK_KEY_ADD_BEFORE("addBefore"),
	TOK_KEY_ADD_AFTER("addAfter"),
	TOK_KEY_PRINT_LIST("PrintList"),
	TOK_KEY_REMOVE_LIST("remove"),
	REAL_MINUS ("\\(\\-(\\d+)\\.(\\d+)\\)"),
	INTEGER_MINUS ("\\(\\-\\d+\\)"), 
	TOK_MINUS_OP ("-"),
    TOK_PLUS_OP ("\\+"), 
    TOK_MUL_OP ("\\*"), 
    TOK_DIV_OP ("/"), 
    TOK_NOT ("~"), 
    TOK_AND("&&"),  
    TOK_OR("\\|\\|"),  
    TOK_LEG("<="),
    TOK_LESS("\\<"),
    TOK_GEQ("\\>="), 
    TOK_GT("\\>"),
    TOK_EQ("=="),
    TOK_ASSIGN("="),
    TOK_OPEN("\\("),
    TOK_CLOSE("\\)"), 
    TOK_SEMI(";"), 
    TOK_COMMA(","), 
    TOK_KEY_VAR("var"), 
    TOK_KEY_IF("if"), 
    TOK_KEY_WHILE("while"),
    //TOK_KEY_FOR("for"),
    //TOK_KEY_DO ("do"),
    TOK_KEY_ELSE("else"),
    OPEN_BRACKET("\\{"),
    CLOSE_BRACKET("\\}"),
    REAL("(\\d+)\\.(\\d+)"),
    STRING("'[^']*'"),
    INTEGER("\\d+"), 
    IDENTIFIER("\\w+");

    private final Pattern pattern;

    Token(String regex) {
        pattern = Pattern.compile("^" + regex);
    }

    int endOfMatch(String s) {
        Matcher m = pattern.matcher(s); //Передаем Matcher входной input

        if (m.find()) { //Ищем совпадение подстроки внутри РВ
            return m.end(); //Возвращаем последнее совпашее значение 
        }
        return -1; //Иначе -1
    }

}
