class Parameter {
	String type;
	String ident;

	Parameter(String t, String id) {
		type = t;
		ident = id;
	}

	void printParseTree(String indent) {
		IO.display(indent + indent.length() + " <parameter> " + type + " ");
		IO.displayln(ident);
	}

	void buildTypeMaps(String funName) {
		// ParameterList.param = new HashMap<String, TypeVal>();
		// Parameters.paramNum = new HashMap<Integer , TypeVal>();
		ParameterList.param.put(ident, TypeVal.toTypeVal(type));
		TypeChecker.paramTypeMap.put(funName, ParameterList.param);
		ParameterList.paramNum.put((ParameterList.paramNum.size() + 1), TypeVal.toTypeVal(type));
		TypeChecker.paramNumTypeMap.put(funName, ParameterList.paramNum);

	}
}