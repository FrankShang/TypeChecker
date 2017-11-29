class Header
{
    String type;
	String funName;
	ParameterList parameterList; // parameterList is null if <parameter list> is empty.
	
	Header(String t, String f, ParameterList p)
	{
		type = t;
		funName = f;
		parameterList = p;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <header>");
							  
		String indent1 = indent+" ";

		IO.displayln(indent1 + indent1.length() + " <type> " + type);
		IO.displayln(indent1 + indent1.length() + " <fun name> " + funName);
		if ( parameterList != null )
		{
			IO.displayln(indent1 + indent1.length() + " <parameter list>");
			parameterList.printParseTree(indent1+" ");
		}
	}
	
	void buildTypeMaps(){
		TypeChecker.funTypeMap.put(funName, TypeVal.toTypeVal(type));
		if(parameterList != null)
		parameterList.buildTypeMaps(funName);
		//parameterList.buildTypeMaps();
		
	}
}
