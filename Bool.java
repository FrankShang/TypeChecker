class Bool extends Exp
{
	boolean boolElem;
	
	Bool(boolean b)
	{
		boolElem = b;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <exp>");
		String indent1 = indent+" ";
		IO.displayln(indent1 + indent1.length() + " " + boolElem);
	}

	@Override
	TypeVal typeEval() {
		// TODO Auto-generated method stub
		return TypeVal.Boolean;
	}
	
}