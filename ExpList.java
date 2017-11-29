class ExpList
{
	Exp exp;
	ExpList expList; // expList is null at the end of the list.
	
	ExpList(Exp e, ExpList el)
	{
		exp = e;
		expList = el;
	}
		
	void printParseTree(String indent)
	{
		exp.printParseTree(indent);
		if ( expList != null )
			expList.printParseTree(indent);	
	}
	TypeVal typeEval(){
//		if(exp.typeEval() == TypeVal.Error)
//			return TypeVal.Error;
//		else{if(expList != null)
//			return expList.typeEval();}
//		return null;
		IO.displayln("------------------ExpList" + this.exp.typeEval());
		Exp.superExpTypeEval(this.exp.typeEval());
		if ( expList != null )
		return this.expList.typeEval();
		else return this.exp.typeEval();
//		if(expList!= null)
//		Exp.superExpTypeEval(expList.typeEval());
//		if(exp!=null)
//			return exp.typeEval();
//		else return exp.typeEval();

	}
}