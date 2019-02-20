package visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SimpleName;

import util.Util;

public class NameVistor2 extends ASTVisitor {
	public String methodNameId;
	
	public NameVistor2(String methodNameId) {
		super();
		this.methodNameId = methodNameId;
		// TODO Auto-generated constructor stub
	}

	public NameVistor2(boolean visitDocTags) {
		super(visitDocTags);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean visit(SimpleName node) {
		String id = node.resolveBinding().getKey();
		String temp = this.methodNameId.replaceAll(",", "_");
		id = id.replaceAll(",", "_");
		
		Util.appendFile(temp + "," + id + "\n");

		return super.visit(node);
	}
}
