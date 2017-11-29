import java.util.HashMap;

class ParameterList {
	Parameters parameters;
	static HashMap<String, TypeVal> param;
	static HashMap<Integer, TypeVal> paramNum;

	ParameterList(Parameters ps) {
		parameters = ps;
	}

	void printParseTree(String indent) {
		parameters.printParseTree(indent);
	}

	void buildTypeMaps(String funName) {
		this.param = new HashMap<String, TypeVal>();
		this.paramNum = new HashMap<Integer, TypeVal>();
		if (parameters != null)
			parameters.buildTypeMaps(funName);
	}
}