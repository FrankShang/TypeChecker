import java.util.*;

public abstract class TypeChecker extends Parser
{
	public static HashMap<String,TypeVal> funTypeMap = new HashMap<String,TypeVal>(); 
		// records declared return types of functions
		
	public static HashMap<String,HashMap<String,TypeVal>> paramTypeMap = new HashMap<String,HashMap<String,TypeVal>>();
		// for each function name, records declared types of formal parameters

	public static HashMap<String,HashMap<Integer,TypeVal>> paramNumTypeMap = new HashMap<String,HashMap<Integer,TypeVal>>();
		// for each function name, records declared types of i-th formal parameters, indexed by i


	public static void main(String argv[])
	{
		// argv[0]: input file containing function definitions
		// argv[1]: output file displaying type information or error messages

		setIO( argv[0], argv[1] );
		setLex();

		getToken();

		FunDefList funDefList = funDefList(); // build a parse tree		                    
		if ( ! t.isEmpty() )
			displayln(t + " : Syntax Error, unexpected symbol");
		else if ( ! syntaxErrorFound )
		{
			funDefList.buildTypeMaps(); // build the three type maps

			{
				displayln("Display return types of functions:");
				displayln("");
				displayln(funTypeMap.toString());
				displayln("");
				displayln("Display parameter types of functions:");
				displayln("");
				displayln(paramTypeMap.toString());
				displayln("");
				displayln("Display parameter types of functions by position:");
				displayln("");
				displayln(paramNumTypeMap.toString());
				displayln("");

				TypeVal funDefListType = funDefList.typeEval(); // perform type checking
				if ( funDefListType == TypeVal.Correct )
					displayln("All function definitions have passed type checking.");
			}
		}

		closeIO();
	}

}