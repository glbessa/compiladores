class CodeGen
{
	String geraCodigo (ArvoreSintatica arv)
	{
		return (geraCodigo2(arv) + "PRINT");
	}

	String geraCodigo2 (ArvoreSintatica arv)
	{
		if (arv instanceof Mult)
			return (geraCodigo2(((Mult) arv).arg1) + 
				geraCodigo2(((Mult) arv).arg2) +
				"MULT\n");

		if (arv instanceof Div)
			return (geraCodigo2(((Div) arv).arg1) + 
				geraCodigo2(((Div) arv).arg2) +
				"DIV\n");

		if (arv instanceof Soma)
			return (geraCodigo2(((Soma) arv).arg1) + 
				geraCodigo2(((Soma) arv).arg2) +
				"SUM\n");

		if (arv instanceof Sub)
			return (geraCodigo2(((Sub) arv).arg1) + 
				geraCodigo2(((Sub) arv).arg2) +
				"SUB\n");

		if (arv instanceof Int)
			return ("PUSH "  + ((Int) arv).num + "\n");

		if (arv instanceof Flo)
			return ("PUSH "  + ((Flo) arv).num + "\n");


		return "";
	}

	float executeCode(ArvoreSintatica tree)
	{
		return executeStep(tree, 0);
	}

	private float executeStep(ArvoreSintatica tree, float partial)
	{
		if (tree instanceof Mult)
			return (executeStep(((Mult) tree).arg1, partial) * executeStep(((Mult) tree).arg2, partial));

		if (tree instanceof Div)
			return (executeStep(((Div) tree).arg1, partial) / executeStep(((Div) tree).arg2, partial));

		if (tree instanceof Soma)
			return (executeStep(((Soma) tree).arg1, partial) + executeStep(((Soma) tree).arg2, partial));
		
		if (tree instanceof Sub)
			return (executeStep(((Sub) tree).arg1, partial) - executeStep(((Sub) tree).arg2, partial));

		if (tree instanceof Int)
			return ((Int) tree).num;
		if (tree instanceof Flo)
			return ((Flo) tree).num;

		return 0;
	}
}
