class MultipleFunDef extends FunDefList
{
	FunDef funDef;
	FunDefList funDefList;
	
	MultipleFunDef(FunDef fdef, FunDefList fdeflist)
	{
		funDef = fdef;
		funDefList = fdeflist;
	}
	
	void printParseTree(String indent)
	{
		funDef.printParseTree(indent);
		IO.displayln("\n--------------------\n");
		funDefList.printParseTree(indent);
	}
	void buildTypeMaps(){
		funDef.buildTypeMaps();
		funDefList.buildTypeMaps();
	}

	@Override
	TypeVal typeEval() {
		if(funDef.typeEval() != TypeVal.Error)
			return funDefList.typeEval();
		else return TypeVal.Error;
	}
}