package mmetsa.stackoperations;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class LongStack {

    private final LinkedList<Long> longStack;

    public static void main (String[] args) {
        System.out.println(LongStack.interpret("2 5 SWAP -"));
    }

    LongStack() {
        longStack = new LinkedList<>();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        LongStack tmp = new LongStack();
        for (int i = longStack.size(); i > 0; i--) {
            tmp.push(longStack.get(i - 1));
        }
        return tmp;
    }

    public boolean stEmpty() {
        return longStack.isEmpty();
    }

    public void push (long a) {
        longStack.push(a);
    }

    public long pop() {
        if (stEmpty()) {
            throw new IndexOutOfBoundsException("Error: Tried to pop from an empty stack");
        }
        return longStack.pop();
    } // pop

    public void op (String s) {
        if (longStack.size() < 2) {
            throw new IndexOutOfBoundsException("Error: Too few elements for operation: " + s);
        }
        if (s.equals("ROT") && longStack.size() < 3) {
            throw new IndexOutOfBoundsException("Error: Too few elements for operation: " + s);

        }
        long op1 = longStack.pop();
        long op2 = longStack.pop();
        switch (s) {
            case "+":
                push(op1 + op2);
                break;
            case "-":
                push(op2 - op1);
                break;
            case "*":
                push(op1 * op2);
                break;
            case "/":
                push(op2 / op1);
                break;
            case "SWAP":
                push(op1);
                push(op2);
                break;
            case "ROT":
                long op3 = longStack.pop();
                longStack.push(op2);
                longStack.push(op1);
                longStack.push(op3);
                break;
            default:
                throw new IllegalArgumentException("Error: Invalid operation: " + s);
        }
    }

    public long tos() {
        if (stEmpty()) {
            throw new IndexOutOfBoundsException("Error: Stack is empty!");
        }
        return longStack.peek();
    }

    @Override
    public boolean equals (Object o) {
        if(!(o instanceof LongStack)) {
            return false;
        }
        LongStack temp = (LongStack)o;
        if (this.stEmpty() && temp.stEmpty()) {
            return true;
        }
        if (this.stEmpty() && !temp.stEmpty() || temp.stEmpty() && !this.stEmpty()) {
            return false;
        }
        for (int i = 0; i < temp.longStack.size(); i++) {
            if (!temp.longStack.get(i).equals(this.longStack.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        if (stEmpty()) {
            return "empty";
        }
        StringBuilder str = new StringBuilder();
        for (int i = longStack.size(); i > 0; i--) {
            str.append(longStack.get(i - 1));
            str.append(" ");
        }
        return str.toString();
    }

    private static boolean isValidToken(String token) {
        String[] validTokens = new String[] {"+", "-", "*", "/", "SWAP", "ROT"};
        for(String tok : validTokens) {
            if (token.equals(tok)) {
                return true;
            }
        }
        try {
            Long.parseLong(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static boolean isNumber(String token) {
        try {
            Long.parseLong(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static long interpret (String pol) {
        LongStack ls = new LongStack();
        if (pol.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: String is empty!");
        }
        StringTokenizer st = new StringTokenizer(pol);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (!isValidToken(token)) {
                throw new IllegalArgumentException("Error: Illegal symbol " + "token" + " in expression: " + '"' + pol + '"');
            }
            if (isNumber(token)) {
                ls.push((Long.parseLong(token)));
            } else {
                try {
                    ls.op(token);
                } catch (IndexOutOfBoundsException e) {
                    throw new IndexOutOfBoundsException("Error: Cannot perform " + token + " in expression: " + '"' + pol + '"');
                } catch (IllegalArgumentException e) {

                    throw new IllegalArgumentException("Error: Invalid operation: " + '"' + pol + '"');
                }
            }
        }
        long result = ls.pop();
        if (!ls.stEmpty()) {
            throw new IllegalArgumentException("Error: Too many numbers in expression: " + '"' + pol + '"');
        }
        return result;
    }

}

