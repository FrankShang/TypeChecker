class If extends Exp
{
	Exp exp1;
	Exp exp2;
	Exp exp3;
	
	If(Exp exp1, Exp exp2, Exp exp3)
	{
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.exp3 = exp3;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <exp>");
		
		String indent1 = indent+" ";
		String indent2 = indent1+" ";
		
		IO.displayln(indent1 + indent1.length() + " if");		
		exp1.printParseTree(indent2);
		IO.displayln(indent1 + indent1.length() + " then");
		exp2.printParseTree(indent2);
		IO.displayln(indent1 + indent1.length() + " else");
		exp3.printParseTree(indent2);
	}
	
	TypeVal typeEval(){
		if (exp1.typeEval() == TypeVal.Boolean)
		{
			if (exp2.typeEval() == TypeVal.Int && exp3.typeEval() == TypeVal.Int)
				return TypeVal.Int;
			else if (exp2.typeEval() == TypeVal.Float && exp3.typeEval() == TypeVal.Float)
				return TypeVal.Float;
			else if (exp2.typeEval() == TypeVal.Boolean && exp3.typeEval() == TypeVal.Boolean)
				return TypeVal.Boolean;
			else
			{
				IO.displayln("Type Error: " + "incompatible types found in conditional expression " + Exp.funName);
				return TypeVal.Error;
			}
		} else{
			IO.displayln("Type Error: " + "incompatible types found in conditional expression " + Exp.funName);
			return TypeVal.Error;
		}
	}
}