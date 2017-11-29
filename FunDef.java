class FunDef extends FunDefList {
	Header header;
	Exp exp;

	FunDef(Header h, Exp e) {
		header = h;
		exp = e;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";

		IO.displayln(indent + indent.length() + " <fun def>");
		header.printParseTree(indent1);
		exp.printParseTree(indent1);
	}

	void buildTypeMaps() {
		header.buildTypeMaps();
	}


	TypeVal typeEval() {
		if (exp != null) {
			Exp.funName = header.funName;
			IO.displayln("----------------return type " +exp.typeEval()+ "  "+ TypeVal.toTypeVal(header.type));
			if(this.exp.typeEval() != TypeVal.Error && this.exp.typeEval() == TypeVal.toTypeVal(header.type)){
				
				return TypeVal.Correct;
			}
			else{
				IO.displayln("Type Error: incompatible return type and body expression type in function " + header.funName);
				return TypeVal.Error;
			}
			//return exp.typeEval();
		}
		return null;
	}

}