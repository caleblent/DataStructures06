package balancedSymbols;

import java.util.EmptyStackException;

public interface DSStack<T> {
	/**
	 * 
	 * @return true if stack is empty, false otherwise
	 */
	public boolean isEmpty();
	
	/**
	 * 
	 * @param element element to be added to top of stack
	 * @throws RuntimeException if stack is already full
	 */
	public void push(T element) throws RuntimeException;
	
	/**
	 * 
	 * @return top element of stack, which is then removed from stack
	 * @throws EmptyStackException if stack is empty
	 */
	public T pop() throws EmptyStackException;
	
	/**
	 * 
	 * @return top element of stack, which is not removed from stack
	 * @throws EmptyStackException if stack is empty
	 */
	public T top()throws EmptyStackException;
}