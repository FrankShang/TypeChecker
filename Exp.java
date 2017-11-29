abstract class Exp {
	static String funName;

	abstract void printParseTree(String indent);
	abstract TypeVal typeEval();
	static TypeVal superExpTypeEval(TypeVal t){
		IO.displayln("-------------------Exp     " + t);
		return t;
	}
}