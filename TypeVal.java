public enum TypeVal

/* This class is used to record the declared types of functions and their parameters in 
   
      HashMap<String,TypeVal> funTypeMap 
      HashMap<String,HashMap<String,TypeVal>> paramTypeMap
      HashMap<String,HashMap<Integer,TypeVal>> paramNumTypeMap

   which are included in the class "TypeChecker.java".
   The enumerated values are also returned from typeEval() function.
*/

{ 
	Int,
	Float,
	Boolean,
	Correct,  // represents type correctness of a function definition
	Error;    // represents the type error value

	boolean isNumberType()
	{
		return this == Int || this == Float;
	}

	public String toString()
	{
		switch (this)
		{
		case Int: return "int";
		case Float: return "float";
		case Boolean: return "boolean";
		case Correct: return "Correct";
		default: return "Error";
		}
	}

	public static TypeVal toTypeVal(String type)
	{
		if ( type.equals("int") )
			return Int;
		else if ( type.equals("float") )
			return Float;
		else if ( type.equals("boolean") )
			return Boolean;
		else
			return null;
	}
}