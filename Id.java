class Id extends Exp
{
	String id;
	
	Id(String s)
	{
		id = s;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <exp>");
		String indent1 = indent+" ";
		IO.displayln(indent1 + indent1.length() + " " + id);
	}
	TypeVal  typeEval(){
		if(id != null){
			if(TypeChecker.paramTypeMap.get(Exp.funName) != null){
				if(TypeChecker.paramTypeMap.get(Exp.funName).get(this.id) != null){
					return TypeChecker.paramTypeMap.get(Exp.funName).get(this.id);
				}
				else
					return TypeVal.Error;
			}
			else 
				return TypeVal.Error;
		}else
		return null;
	}
	
}