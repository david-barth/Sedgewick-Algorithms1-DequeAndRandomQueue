import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
	
	//Members: 
	private Node head = null; 
	private Node tail = null; 
	private int size;  
	
	
	//Inner Classes: 
	private class Node {
		Item data; 
		Node next;
		Node previous; 
		
		Node(Item data) {
			this.data = data; 
			this.next = null;
			this.previous = null; 
		}
	}
	
	
	private class DIterator implements Iterator<Item> {
		
		Node currentFromHead; 
		
		public DIterator() {
			this.currentFromHead = head; 
		}
		
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException(); 
			Item data = currentFromHead.data;
			this.currentFromHead = currentFromHead.next; 
			return data; 
		}
		
		public boolean hasNext() {
			return this.currentFromHead != null; 
		}
	}
	
	
	
	//API Methods: Methods seen by client. 
	
	 // construct an empty deque
    public Deque() {
    	this.size = 0; 
    	System.out.println("Deque Initialized");
    }
    
    
    // is the deque empty?
    public boolean isEmpty() {
    	if (this.head == null) return true;
    	else return false; 
    } 

    
    // return the number of items on the deque
    public int size() {
    	return this.size; 
    }

    // add the item to the front
    public void addFirst(Item item) {
    	if (item == null) throw new IllegalArgumentException("No data added, please add data"); 
    
    	
    	Node newNode = new Node(item); 
    	if (isEmpty()) {
    		this.head = newNode; 
    		this.tail = newNode;
    		this.size++;
    	}
    	
    	else { 
    		Node oldHead = this.head; 
    		this.head = newNode; 
    		this.head.next = oldHead;
    		oldHead.previous = this.head;
    		this.size++;
    	}
    }

    
    
    
    // add the item to the back
    public void addLast(Item item) {
    	if (item == null) throw new IllegalArgumentException("No value added, please add value");
    	
    	Node newNode = new Node(item);
    	if (isEmpty()) {
    		this.head = newNode; 
    		this.tail = newNode;
    		this.size++; 
    	}
    	
    	else {
    		Node oldTail = this.tail; 
        	oldTail.next = newNode;
        	this.tail = newNode;
        	this.tail.previous = oldTail;
        	this.size++; 
    	}
    }

    
    
    // remove and return the item from the front
    public Item removeFirst() {
    	if (this.size() > 1) {
    		Node currentHead = this.head; 
        	this.head = currentHead.next; 
        	this.head.previous = null; 
        	this.size--; 
        	return currentHead.data; 
    	} 
    	
    	else if (this.size() == 1) {
    		Node currentHead = this.head; 
    		this.head = null;
    		this.size--; 
    		return currentHead.data; 
    	}
    	
    	else throw new NoSuchElementException(); 
    	
    }

    
    // remove and return the item from the back
    public Item removeLast() {    	
    	if (this.size() > 1) {
    		Node currentTail = this.tail; 
    		Node newTail = currentTail.previous; 
    		this.tail = newTail; 
    		this.tail.next = null;
    		this.size--; 
    		return currentTail.data; 
    	}
    	
    	else if (this.size() == 1) {
    		Node currentTail = this.tail; 
    		this.tail = null;
    		this.size--; 
    		return currentTail.data; 
    	}
    	
    	
    	else throw new NoSuchElementException(); 
    }
    

    // return an iterator over items in order from front to back
    public DIterator iterator() {
    	return new DIterator(); 
    }
}


//Lessons and Analysis: 


	//Implementation is based on a double linked list, but likely can be reduced down to a single linked list. 

	//As long as the add() methods assign head and tail designations, in the case of empty deques, and the proper references are changed in the remove methods, the Deque operations can exist in worst case O(1). 

		//size() being a possible exception since iteration / recursion is needed through the list to count the node totals. 

		//However, using an instance variable of int size and integrating increments into the add methods can allow for O(1) in size(), turning it into a simple getter method. 


	//TDD Reflections: 

		//Current (Ideal) Method: 

			//1. Analyze method to extract all possible test cases (general use, boundary conditions, exception cases, etc). 

			//2. Write unit tests for all analyzed cases.  

			//3. Write the method and then run tests. 

			//4. Refactor as needed. 


		//Assuming test cases are properly considered, the test suite for a class will allow for more systematic and rapid identification of issues. 

			//These can be traced back into the methods themselves, using the stack traces and failure traces.  

			//From here, the code can be refactored so that the tests pass.  


		//Current Detailed Method: 

			//1. Extract test cases from method analysis. 

			//2. Write tests for each case. 

			//3. Run tests and analyze failure or error traces for defects in method code. 

			//4. Copy and paste test codes into the main() method of class. 

			//5. Use print statements to analyze outputs in each test case to allow for precise understanding of code defects.  

			//6. Refactor. 

			//This method allows for a more exhaustive consideration of possible bugs and defects. 

			//If used with an extensive testing suite, this will allow for a systematic and rapid response to dealing with bugs.  



