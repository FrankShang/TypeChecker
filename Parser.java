/**

This class is a top-down, recursive-descent parser for the following syntactic categories:

<fun def list> --> <fun def> | <fun def> <fun def list>
<fun def> --> <header> = <exp> 
<header> --> <type> <fun name> <parameter list>
<type> --> "int" | "float" | "boolean"
<fun name> --> <id>
<parameter list> --> epsilon | "(" <parameters> ")"
<parameters> --> <parameter> | <parameter> "," <parameters>
<parameter> --> <type> <id>
<exp> --> <id> | <int> | <float> | <floatE> | <boolLiteral> | "(" <fun exp> ")" | "if" <exp> "then" <exp> "else" <exp>
<boolLiteral> --> "false" | "true"
<fun exp> --> <fun op> <exp list>
<fun op> --> <fun name> | <arith op> | <bool op> | <comp op>
<arith op> --> + | - | * | /
<bool op> --> "and" | "or" | "not"
<comp op> --> "<" | "<=" | ">" | ">=" | "="
<exp list> --> epsilon | <exp> <exp list>
 
Note: "epsilon" denotes the empty string.
 
The definitions of the tokens are given in the lexical analyzer class file "LexAnalyzer.java". 

The following variables and functions of the "LexAnalyzer" class are used:

static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token
static void display(String s)
static void displayln(String s)
static void setIO(String inFile, String outFile)
static void closeIO()

The program will display the parse tree in linearly indented form.
Each syntactic category name labeling a node is displayed on a separate line, 
prefixed with the integer i representing the node's depth and indented by i blanks. 
The string variable "indent" will keep track of the correct number of blanks for indentation and
will be passed to parse functions corresponding to syntactic categories.

**/


public abstract class Parser extends LexAnalyzer
{	
	static boolean syntaxErrorFound = false;
	
	public static FunDefList funDefList()
	
	// <fun def list> --> <fun def> | <fun def> <fun def list>
		
	{
		FunDef funDef = funDef();
		if ( state.isType() )
		{
			FunDefList funDefList = funDefList();
			return new MultipleFunDef(funDef, funDefList);
		}
		else
			return funDef;
	}
	
	public static FunDef funDef()
	
	// <fun def> --> <header> = <exp>
	
	{
		Header header = header();
		if (state == State.Eq )
		{
			getToken();
			Exp exp = exp();
			return new FunDef(header, exp);
		}
		else
		{
			errorMsg(1);
			return null;
		}
	}

	public static Header header()
	
	// <header> --> <type> <fun name> <parameter list>
	// <type> --> "int" | "float" | "boolean"
	// <fun name> --> <id>
	
	{
		if ( state.isType() )
		{
			String type = t;
			getToken();
			if ( state == State.Id )
			{
				String funName = t;
				getToken();
				ParameterList parameterList = parameterList();
				return new Header(type, funName, parameterList);
			}
			else 
			{
				errorMsg(2);
				return null;
			}
		}
		else
		{
			errorMsg(8);
			return null;
		}
	}

	public static ParameterList parameterList()
	
	// <parameter list> --> epsilon | "(" <parameters> ")
	
	{
		if ( state == State.LParen )
		{
			getToken();
			Parameters parameters = parameters();
			if ( state == State.RParen )
			{
				getToken();
				return new ParameterList(parameters);
			}
			else
			{
				errorMsg(3);
				return null;
			}	
		}
		else // <parameter list> is epsilon
			return null;
	}

	public static Parameters parameters()

	// <parameters> --> <parameter> | <parameter> "," <parameters>

	{
		Parameter parameter = parameter();
		if ( state == State.Comma )
		{
			getToken();
			Parameters parameters = parameters();
			return new Parameters(parameter, parameters);
		}
		else
			return new Parameters(parameter, null);
	}

	public static Parameter parameter()

	// <parameter> --> <type> <id>

	{
		if ( state.isType() )
		{
			String type = t;
			getToken();
			if ( state == State.Id )
			{
				String id = t;
				getToken();
				return new Parameter(type, id);
			}
			else
			{
				errorMsg(9);
				return null;
			}
		}
		else
		{
			errorMsg(8);
			return null;
		}
	}

	public static Exp exp()
	
	// <exp> --> <id> | <int> | <float> | <floatE> | <boolLiteral> | "(" <fun exp> ")" | "if" <exp> "then" <exp> "else" <exp>
	// <boolLiteral> --> "false" | "true"
	
	{
		switch ( state )
		{
			case Id:

				Id id = new Id(t);
				getToken();				
				return id;
				
			case Int:
				
				Int intElem = new Int(Integer.parseInt(t));
				getToken();
				return intElem;
				
			case Float: case FloatE:
				
				Floatp floatElem = new Floatp(Float.parseFloat(t));
				getToken();
				return floatElem;

			case Keyword_false:

				Bool boolElem = new Bool(false);
				getToken();
				return boolElem;

			case Keyword_true:

				boolElem = new Bool(true);
				getToken();
				return boolElem;
				
			case LParen:
				
				getToken();
				FunExp funExp = funExp();
				if ( state == State.RParen )
				{
					getToken();
					return funExp;
				}
				else 
				{
					errorMsg(3);
					return null;
				}
				
			case Keyword_if:
				
				getToken();
				Exp exp1 = exp();
				if ( state == State.Keyword_then )
				{
					getToken();
					Exp exp2 = exp();
					if ( state == State.Keyword_else )
					{
						getToken();
						Exp exp3 = exp();
						return new If(exp1, exp2, exp3);
					}
					else
						errorMsg(4);
				}
				else
					errorMsg(5);
				return null;
			
			default:
				
				errorMsg(6);
				return null;
		}
	}
	
	public static FunExp funExp()
	
	// <fun exp> --> <fun op> <exp list>
	// <fun op> --> <fun name> | <arith op> | <bool op> | <comp op>
	// <fun name> --> <id>
	// <arith op> --> + | - | * | /
	// <bool op> --> "and" | "or" | "not"
	// <comp op> --> "<" | "<=" | ">" | ">=" | "="
	
	{		
		switch ( state )
		{
			case Id:
					
				String funName = t;
				getToken();
				ExpList expList = expList();
				return new FunCall(funName, expList);
				
			case Add:
				
				getToken();
				expList = expList();
				return new AddE(expList);
				
			case Sub:
				
				getToken();
				expList = expList();
				return new SubE(expList);
				
			case Mul:
				
				getToken();
				expList = expList();
				return new MulE(expList);
				
			case Div:
				
				getToken();
				expList = expList();
				return new DivE(expList);
				
			case Keyword_and:
				
				getToken();
				expList = expList();
				return new AndE(expList);
				
			case Keyword_or:
				
				getToken();
				expList = expList();
				return new OrE(expList);
				
			case Keyword_not:
				
				getToken();
				expList = expList();
				return new NotE(expList);
				
			case Lt:
				
				getToken();
				expList = expList();
				return new LtE(expList);
				
			case Le:
				
				getToken();
				expList = expList();
				return new LeE(expList);
				
			case Gt:
				
				getToken();
				expList = expList();
				return new GtE(expList);
				
			case Ge:
				
				getToken();
				expList = expList();
				return new GeE(expList);
				
			case Eq:
				
				getToken();
				expList = expList();
				return new EqE(expList);
				
			default:
				
				errorMsg(7);
				return null;
		}
	}

	public static boolean beginsExp(State state)
	{
		return
		( state.compareTo(State.Id) >= 0 && state.compareTo(State.LParen) <= 0 ) ||
		state == State.Keyword_if || 
		state == State.Keyword_false || state == State.Keyword_true
		;
	}
	
	public static ExpList expList()
	
	// <exp list> --> epsilon | <exp> <exp list>
	
	{
		if ( beginsExp(state) )
		{
			Exp exp = exp();
			ExpList expList = expList();
			return new ExpList(exp, expList);
		}
		else // <exp list> is epsilon
			return null;
	}
	
	public static void errorMsg(int i)
	{
		syntaxErrorFound = true;
		
		display(t + " : Syntax Error, unexpected symbol where");

		switch( i )
		{
		case 1:	displayln(" = expected"); return;							
		case 2: displayln(" fun name expected"); return;
		case 3: displayln(" ) expected"); return;
		case 4:	displayln(" else expected"); return;				
		case 5:	displayln(" then expected"); return;				
		case 6:	displayln(" id, int, float, (, or if expected"); return;
		case 7:	displayln(" fun name, arith op, bool op, or comp op expected"); return;
		case 8: displayln(" type name expected"); return;
		case 9: displayln(" parameter id expected"); return;
		case 10: displayln(" ( expected"); return;
		}
	}

	public static void main(String argv[])
	{
		// argv[0]: input file containing function definitions
		// argv[1]: output file displaying the parse tree or error messages

		setIO( argv[0], argv[1] );
		setLex();

		getToken();

		FunDefList funDefList = funDefList(); // build a parse tree
		if ( ! t.isEmpty() )
			displayln(t + " : Syntax Error, unexpected symbol");
		else if ( ! syntaxErrorFound )
			funDefList.printParseTree("");
		
		closeIO();
	}
}
