class OrE extends FunExp
{
     ExpList expList;
	
	OrE(ExpList e)
	{
		expList = e;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <exp>");
		
		String indent1 = indent+" ";
		String indent2 = indent1+" ";
		
		IO.displayln(indent1 + indent1.length() + " <fun exp>");
		IO.displayln(indent2 + indent2.length() + " or");
		expList.printParseTree(indent2);	
	}
	
	TypeVal typeEval() {
           IO.displayln("--------------OrE        " + expList.typeEval());
//		if (TypeVal.Boolean == this.expList.typeEval()) {
//			// IO.displayln("inside " + this.f + "-------" + e.typeEval());
//			return TypeVal.Boolean;
//
//		} else {
//			// IO.displayln("error " + this.f + "----LolOLOLLO---" +
//			// e.typeEval());
//			IO.displayln("Type Error: some arguments of " + "or" + " operator have incompatible types");
//
//			return TypeVal.Error;
//
//		}
		if(this.expList.typeEval() != TypeVal.Boolean ){//   || expList.typeEval() == TypeVal.Float){
			IO.displayln("Type Error: some arguments of " + "And" + " operator have incompatible types");
						return TypeVal.Error;
		}else{
			return TypeVal.Boolean;
		}
		
//		if(expList.typeEval() == TypeVal.Float || expList.typeEval() == TypeVal.Int){
//			IO.displayln("Type Error: some arguments of " + "or" + " operator have incompatible types");
//			return TypeVal.Error;
//		}else {
//			return TypeVal.Boolean;
//		}

	}
}