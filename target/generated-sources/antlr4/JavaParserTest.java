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
		
		JavaParser parser = new JavaParser(tokens);
		//Key Method this assists in extracting specific data from file 
		//TODO need to modify
		ParseTree tree = parser.compilationUnit();
		ParseTree val = parser.compilationUnit().getRuleContext();





		
		System.out.println( "ParseTree:\n" + tree.toStringTree( parser ) + "\n"); 
		
		AST ast = new AST( tree );
		System.out.println( "Improved ParseTree:\n" + ast.toString() );
		//test vals
		System.out.println("Testing values of tokens");
//System.out.println( "ParseTree:\n" + tree.toStringTree( val ) + "\n"); 
		
		AST astval = new AST( val );
		System.out.println( "Test ParseTree:\n" + astval.toString() );
	}

}
