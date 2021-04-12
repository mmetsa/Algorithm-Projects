package mmetsa.nodetrees;

import java.util.*;

public class Node {

    private String name;
    private Node firstChild;
    private Node nextSibling;


    public Node (String n, Node d, Node r) {
        this.name = n;
        this.firstChild = d;
        this.nextSibling = r;
    }

    public static void errorCheck(String s){
        if(s.length() == 0)
            throw new RuntimeException("Error: string is empty: " + s);
        if(!s.matches("[\\w(),+--/ *]+"))
            throw new RuntimeException("String contains illegal symbols: " + s );
        if(s.contains(" "))
            throw new RuntimeException("String contains whitespace: " + s);
        if(s.contains(",,"))
            throw new RuntimeException("Double commas in string: " + s);
        if(s.contains("()"))
            throw new RuntimeException("Empty Node in string: " + s);
        if(s.contains(",") && !s.contains("(") && !s.contains(")"))
            throw new RuntimeException("More than 1 root node in string: " + s);
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(' && s.charAt(i+1) == ',')
                throw new RuntimeException("Comma can't be right after '(':  " + s);
            if(s.charAt(i) == ')' && (s.charAt(i+1) == ',' || s.charAt(i+1) == ')' || s.charAt(i+1) == '('))
                throw new RuntimeException("Double bracket error " + s);
        }
    }

    public static Node parsePostfix (String s) {
        errorCheck(s);
        Stack<Node> nodes = new Stack<>();
        Node node = new Node("", null, null);
        StringTokenizer tokenizer = new StringTokenizer(s, "(),", true);
        while(tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken().trim();
            if(token.equals("(")){
                nodes.push(node);
                node.firstChild = new Node("", null, null);
                node = node.firstChild;
            }else if( token.equals(")")){
                node = nodes.pop();
            }else if(token.equals(",")){
                if(nodes.empty())
                    throw new RuntimeException("Comma exception" + s);
                node.nextSibling = new Node("", null, null);
                node = node.nextSibling;
            }else{
                node.name = token;
            }
        }
        return node;
    }

    public String leftParentheticRepresentation() {
        StringBuilder str = new StringBuilder();
        str.append(this.name);
        if(this.firstChild != null){
            str.append("(");
            str.append(this.firstChild.leftParentheticRepresentation());
            str.append(")");
        }
        if(this.nextSibling != null){
            str.append(",");
            str.append(this.nextSibling.leftParentheticRepresentation());
        }
        return str.toString();
    }

    public String pseudoXMLRepresentation() {
        return pseudoXMLRepresentation(1);
    }

    public String pseudoXMLRepresentation(int level) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t".repeat(level - 1));
        sb.append("<L").append(level).append("> ").append(this.name).append(" ");
        if (this.firstChild != null) {
            sb.append("\n").append(this.firstChild.pseudoXMLRepresentation(level + 1)).append("\n").append("\t".repeat(level - 1));
        }
        sb.append("</L").append(level).append(">");
        if (this.nextSibling != null) {
            sb.append("\n").append(this.nextSibling.pseudoXMLRepresentation(level));
        }
        return sb.toString();
    }


    public static void main (String[] param) {
        String s = "((C)B,(E,F)D,G)A";
        Node n = Node.parsePostfix(s);
        System.out.println(n.pseudoXMLRepresentation());
    }
}