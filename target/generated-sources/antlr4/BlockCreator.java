
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public interface BlockCreator {

	//Returns the newly created tree with the proper CFG rules
	AST createTree(AST tree);
	
	
}
