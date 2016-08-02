
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

public class BlockSimpleGenerator implements BlockCreator {

	
	public BlockSimpleGenerator(){}
	
	@override
	public AST createTree(AST tree){
		
		
		
		
		
		return tree;
	}
	
	
	public BlockNode generateExpandedCFG(AST ast) {
		 
		StringBuilder builder = new StringBuilder();
		
		int tokenType = -1;
		String tokenContent = "";
		BlockNode expandedCFG = new BlockNode ("0", "", null);
 
		//AST ast = this;
		List<AST> firstStack = new ArrayList<>();
		firstStack.add(ast);
 
		List<List<AST>> childListStack = new ArrayList<>();
		childListStack.add(firstStack);
 
		while (!childListStack.isEmpty()) {
 
			List<AST> childStack = childListStack
					.get(childListStack.size() - 1);
 
			if (childStack.isEmpty()) {
				childListStack.remove(childListStack.size() - 1);
			} else {
				ast = childStack.remove(0);
				String caption;
 
				if (ast.getPayload() instanceof Token) {
					Token token = (Token) ast.getPayload();
					caption = String.format("TOKEN[type: %s, text: %s]",
							token.getType(),
							token.getText().replace("\n", "\\n"));
					tokenType = token.getType();
					tokenContent = token.getText();
					expandedCFG.getTail().setExit1(new BlockNode(""+tokenType, tokenContent, null));
					
				} else {
					caption = String.valueOf(ast.getPayload());
				}
 
				String indent = "";
 
				for (int i = 0; i < childListStack.size() - 1; i++) {
					indent += (childListStack.get(i).size() > 0) ? "| " : " ";
				}
 
				//this is what appends to builder
				builder.append(indent)
						.append(childStack.isEmpty() ? "'- " : "|- ")
						.append(caption).append("\n");
 
				if (ast.children.size() > 0) {
					List<AST> children = new ArrayList<>();
					for (int i = 0; i < ast.children.size(); i++) {
						children.add(ast.children.get(i));
					}
					childListStack.add(children);
				}
			}
		}
 
		//return builder.toString();
		return expandedCFG;
	}

}
