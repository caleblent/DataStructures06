package balancedSymbols;

import java.util.EmptyStackException;

public class LinkedListStack<T> implements DSStack<T> {
	
	private ListNode top;
	private ListNode bottom;
	
	public LinkedListStack() {
		top = new ListNode(null, null);
		bottom = new ListNode(null, null);
	}
	
	/**
	 * 
	 * @return true if stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		if (top.next == null)
			return true;
		return false;
	}
	
	/**
	 * 
	 * @param element element to be added to top of stack
	 * @throws RuntimeException if stack is already full
	 */
	public void push(T element) throws RuntimeException {
		
		ListNode temp = new ListNode(element, null);
		
		if (isEmpty()) {
			bottom.next = temp;
			top.next = temp;
		} else {
			temp.next = top.next;
			top.next = temp;
		}
		
	}
	/**
	 * 
	 * @return top element of stack, which is then removed from stack
	 * @throws EmptyStackException if stack is empty
	 */
	public T pop() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();
		ListNode temp = top.next;
		top.next = top.next.next; // = temp.next
		return temp.data;
	}
	
	/**
	 * 
	 * @return top element of stack, which is not removed from stack
	 * @throws EmptyStackException if stack is empty
	 */
	public T top()throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();
		return top.next.data;
	}
	
	@Override
	public String toString() {
		String str = "";
		
		
		if (isEmpty()) {
			return "Empty";
		}
		
		ListNode curr = top.next;
		
		boolean flag = true;
		while(flag) {
			
			str += curr.data;
			
			if (curr.next == null)
				flag = false;
			else 
				str += " -> ";
//				str += " ";
			
			curr = curr.next;
		}
		
		return str;
	}
	
	public String toStringReverse() {
		String str = "";
		
		
		if (isEmpty()) {
			return "Empty";
		}
		
		ListNode curr = top.next;
		
		boolean flag = true;
		while(flag) {
			
			str = curr.data + str;
			
			if (curr.next == null)
				flag = false;
			else 
				str = " <- " + str;
//				str += " ";
			
			curr = curr.next;
		}
		
		return str;
	}
	
	/**
	 * A private class for our ListNodes
	 * Since this class is private, only code in the LinkedList class can reference it
	 * We can safely make the attributes here public because no one else can get to the private class
	 * This allows for simpler code in our LinkedList class as long as we can trust ourselves
	 * not to supply illegal values
	 * Note that since this is a private class, we're still using the same type T as the LinkedList
	 * class that we are inside of, so no need to re-declare that type parameter here.
	 * @author gosnat
	 *
	 */
	public class ListNode {
		/** The data to store in this node */
		T data;
		/** A pointer to the next node in the list */
		ListNode next;

		/**
		 * Constructor
		 * @param data The actual data item to store
		 * @param next A pointer to the node that should go next in the list
		 */
		public ListNode(T input, ListNode nextNode) {
			data = input;
			next = nextNode;
		}

	}
	
}
