package visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SimpleName;

import util.Config;
import util.Util;

public class FileVistor extends ASTVisitor {
	@Override
	public boolean visit(SimpleName node) {
		String id = node.resolveBinding().getKey();
		
		String temp = Config.projectName.replaceAll(",", "_");
		id = id.replaceAll(",", "_");
		Util.appendFile(temp + "," + id + "\n");

		return super.visit(node);
	}
}
