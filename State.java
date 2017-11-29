public enum State 
{ 
// final states    ordinal number  token accepted 

	Add,             // 0          +
	Sub,             // 1          -
	Mul,             // 2          *
	Div,             // 3          /
	Lt,              // 4          <
	Le,              // 5          <=
	Gt,              // 6          >
	Ge,              // 7          >=
	Eq,              // 8          =
	Id,              // 9          identifiers
	Int,             // 10         integers
	Float,           // 11         floats without exponentiation part
	FloatE,          // 12         floats with exponentiation part
	LParen,          // 13         (
	RParen,          // 14         )
	Comma,           // 15         ,

// non-final states                string recognized    

	Start,           // 16      the empty string
	Period,          // 17      ".", "+.", "-."
	E,               // 18      float parts ending with E or e
	EPlusMinus,      // 19      float parts ending with + or - in exponentiation part
	Underscore,      // 20      identifier parts ending with "_"

// keyword states

	Keyword_int,
	Keyword_float,
	Keyword_boolean,
	Keyword_if,
	Keyword_then,
	Keyword_else,
	Keyword_and,
	Keyword_or,
	Keyword_not,
	Keyword_false,
	Keyword_true,

	UNDEF;

	boolean isFinal()
	{
		return this.compareTo(State.Comma) <= 0;  
	}

	boolean isType()
	{
		return ( this == State.Keyword_int || this == State.Keyword_float || this == State.Keyword_boolean );
	}

	boolean isCompOp()
	{
		return ( this.compareTo(Lt) >= 0 && this.compareTo(Eq) <= 0 );
	}
}

// By enumerating the final states first and then the non-final states,
// test for a final state can be done by testing if the state's ordinal number
// is less than or equal to that of Comma.
