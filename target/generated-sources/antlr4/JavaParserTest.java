import java.io.File;
import java.util.Scanner;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class JavaParserTest {
	public static void main(String[] args) throws Exception 
	{
	    String content = new Scanner(new File("target/generated-sources/antlr4/integers.java")).useDelimiter("\\Z").next();
	    System.out.println( "Java File:\n" + content + "\n\n");
	    
		ANTLRInputStream input = new ANTLRInputStream( content );
		
		JavaLexer lexer = new JavaLexer(input);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		System.out.println(tokens);
		
		JavaParser parser = new JavaParser(tokens);
		//Key Method this assists in extracting specific data from file 
		//TODO need to modify
		ParseTree tree = parser.compilationUnit();
		
		
		//ParseTree val = parser.compilationUnit().getRuleContext();
		
		ParseTree simple= tree;
		//simple=BlockSimpleGenerator.createTree(simple);
		
		ParseTree multiple= tree;
		//multiple=BlockMultipleGenerator.createTree(multiple);
		




		
		//System.out.println( "ParseTree:\n" + tree.toStringTree( parser ) + "\n"); 
		
		AST ast = new AST( tree );
		System.out.println( "Improved ParseTree:\n" + ast.toString() );
		System.out.println("testing code!");
		Object Payload= ast.getChild().get(0).getChild().get(1).getPayload();
		System.out.println("testing payload: "+ ast.getTypeFromTokenPayload(Payload));
		
		System.out.println("testing payload: "+ ast.getTextFromTokenPayload(Payload));
		
		System.out.println("Creating expanded CFG!");
		BlockSimpleGenerator generator = new BlockSimpleGenerator();
		BlockNode expandedCFG = generator.generateExpandedCFG(ast);
		expandedCFG.print();
		//System.out.println("Compressing CFG!");
		//BlockNode compressedCFG = expandedCFG.compressCFG();
		//System.out.println("Printing compressed CFG!");
		//compressedCFG.print();
		
		System.out.println("Getting Array!");
		String [][] array = expandedCFG.getArray();
		System.out.println("Printing Array");
		expandedCFG.printArray(array);
		
		//Create Simple CFG*************************************
		System.out.println("Compressing CFG");
		String[][] simpleCFG = expandedCFG.constructSimpleCFG(array);
		System.out.println("Printing simple CFG");
		expandedCFG.printArray(simpleCFG);
		
		Block[] table = XMLCreate.Testing(simpleCFG);
		new CreateNewXML(table, "simple");
		//Simple CFG Ends***************************************
		
		
		//Create Multiple CFG***********************************
		String[][] multipleCFG = expandedCFG.constructMultipleCFG(array);
		System.out.println("Printing multipleCFG");
		expandedCFG.printArray(multipleCFG);
		
		Block[] table2 = XMLCreate.Testing(multipleCFG);
		new CreateNewXML(table2, "multiple");
		//Multiple CFG Ends************************************
		
		//System.out.println("Collapsing curly brackets");
		//simpleCFG = expandedCFG.collapseCurlyBracketBlocks(simpleCFG);
		//expandedCFG.printArray(simpleCFG);
		//test vals
		//System.out.println("Testing values of tokens");
//System.out.println( "ParseTree:\n" + tree.toStringTree( val ) + "\n"); 
		
		//AST astval = new AST( val );
		//System.out.println( "Test ParseTree:\n" + astval.toString() );
	}

}
