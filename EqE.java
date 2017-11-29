class EqE extends FunExp {
	ExpList expList;

	EqE(ExpList e) {
		expList = e;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <exp>");

		String indent1 = indent + " ";
		String indent2 = indent1 + " ";

		IO.displayln(indent1 + indent1.length() + " <fun exp>");
		IO.displayln(indent2 + indent2.length() + " =");
		expList.printParseTree(indent2);
	}

	TypeVal typeEval() {
		if (expList.typeEval().isNumberType()
				|| TypeChecker.paramTypeMap.get(Exp.funName).get(expList).isNumberType()) {
			return TypeVal.Boolean;
		} else if (expList.typeEval() == TypeVal.Boolean
				|| TypeChecker.paramTypeMap.get(Exp.funName).get(expList) == TypeVal.Boolean)
			return TypeVal.Boolean;
		else {
			IO.displayln("Type Error: some arguments of " + "=" + " operator have incompatible types");
			return TypeVal.Error;
		}
	}
}