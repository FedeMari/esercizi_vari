package main;

public class Stack2 {
	private int[] stack; // array che fa da stack
	private int top; // indice dell'elemento in cima allo stack
	// private int i; //indice di appoggio
	// costruttore

	public Stack2(int size) {
        stack = new int[size];
        top = -1;
        // i = 0;
    }

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean isFull() {
		return top == stack.length - 1;
	}

	public boolean push(int n) throws StackFullException{
		 if (isFull()) {
	            throw new StackFullException();
	     }
		 /*if (isFull()) {
			return false;
		}*/
		stack[++top] = n;
		return true;
	}

	public int pop() throws StackEmptyException{
        if (isEmpty()) {
        	throw new StackEmptyException();
        }
        int num = stack[top];
        top--;
        return num;
    }

}
