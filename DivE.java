class DivE extends FunExp {
	ExpList expList;

	DivE(ExpList e) {
		expList = e;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <exp>");

		String indent1 = indent + " ";
		String indent2 = indent1 + " ";

		IO.displayln(indent1 + indent1.length() + " <fun exp>");
		IO.displayln(indent2 + indent2.length() + " /");
		expList.printParseTree(indent2);
	}

	TypeVal typeEval() {

		if (this.expList.typeEval() == TypeVal.Int)// ||
													// TypeChecker.paramTypeMap.get(Exp.funName).get(e)
													// == TypeVal.Int)
			return TypeVal.Int;
		else if (expList.typeEval() == TypeVal.Float)// ||
														// TypeChecker.paramTypeMap.get(Exp.funName).get(e)
														// == TypeVal.Float)
			return TypeVal.Float;
		else {
			IO.displayln("Type Error: some arguments of " + "/" + " operator have incompatible types");
			return TypeVal.Error;
			// return null;
		}

	}
}