class Floatp extends Exp
{
	float floatElem;
	
	Floatp(float f)
	{
		floatElem = f;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <exp>");
		String indent1 = indent+" ";
		IO.displayln(indent1 + indent1.length() + " " + floatElem);
	}
	TypeVal typeEval(){
		IO.displayln("----------------floatp       " + floatElem);
		//OrE.expList = TypeVal.Float;
	 	//Exp.superExpTypeEval(TypeVal.Float);
	//return TypeVal.Float;;
		return TypeVal.Float;
	}


}
