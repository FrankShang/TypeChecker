class Parameters {
	Parameter parameter;
	Parameters parameters; // parameters is null at the end of the list.

	Parameters(Parameter p, Parameters ps) {
		parameter = p;
		parameters = ps;
	}

	void printParseTree(String indent) {
		parameter.printParseTree(indent);
		if (parameters != null)
			parameters.printParseTree(indent);
	}

	void buildTypeMaps(String funName) {
		// param = new HashMap<String, TypeVal>();
		// paramNum = new HashMap<Integer , TypeVal>();
		parameter.buildTypeMaps(funName);
		if (parameters != null)
			parameters.buildTypeMaps(funName);
	}
}