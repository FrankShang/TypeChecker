class FunCall extends FunExp
{
	String funName;
	ExpList expList; // expList is null if <exp list> is empty.
	
	FunCall(String s, ExpList e)
	{
		funName = s;
		expList = e;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent+" ";
		String indent2 = indent1+" ";
		
		IO.displayln(indent + indent.length() + " <exp>");
		IO.displayln(indent1 + indent1.length() + " <fun exp>");
		IO.displayln(indent2 + indent2.length() + " " + funName);
		if ( expList != null )
			expList.printParseTree(indent2);
	}
	TypeVal typeEval(){
		if(expList != null && funName != null){
		if(TypeChecker.funTypeMap.containsKey(funName)){//if(TypeChecker.funTypeMap.get(f) == e.typeEval()){
//			for(int i = 1; i <= TypeChecker.paramNumTypeMap.get(f).size();i++){
//				if(TypeChecker.paramNumTypeMap.get(f).get(i) == e.typeEval() || TypeChecker.paramNumTypeMap.get(f).get(i) == TypeChecker.paramTypeMap.get(Exp.funName).get(e)){
//					return TypeChecker.funTypeMap.get(f);
//				}
//		}
		if(TypeChecker.paramTypeMap.get(funName).containsValue(expList.typeEval())){
			return TypeChecker.funTypeMap.get(funName);
		}
		else{
			IO.displayln("Type Error: some arguments of " + funName + " operator have incompatible types");
			return TypeVal.Error;
		}
	}else{
		IO.displayln("Type Error: some arguments of " + funName + " operator have incompatible types");
		return TypeVal.Error;
	}}
		return TypeVal.Error;
	}
}