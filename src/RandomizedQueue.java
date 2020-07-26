import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	//Fields: 
	protected Item[] QArray;
	private int N = 0; 
	
	
	//API Helper Methods: 
	protected void resize(int N) {
		Item[] newArray = (Item[]) new Object[N];
		
		for (int i = 0; i < N; i++) {
			Item insertion = i < this.QArray.length ? this.QArray[i] : null; 
			newArray[i] = insertion; 
		}
		
		this.QArray = newArray; 
	}
	
	//API Methods:
    // construct an empty randomized queue
    public RandomizedQueue() {
    	this.QArray = (Item[]) new Object[1];
    }
    
    // is the randomized queue empty?
    public boolean isEmpty() {
    	return this.QArray.length == 1 && this.QArray[0] == null; 
    }
    
    // return the number of items on the randomized queue
    public int size() {
    	return this.N; 
    }

    // add the item
    public void enqueue(Item item) {
    	if (item == null) throw new IllegalArgumentException("Null value added, please add value");
    	if (this.N == this.QArray.length) this.resize(2*this.QArray.length);
    
    	this.QArray[N++] = item; 
    }

    
    
    // remove and return a random item
    public Item dequeue() {
    	QueueIterator<Item> iterator = this.iterator();    	
    	if (this.size() == 0) throw new NoSuchElementException(); 
    	
    	Item chosenItem = iterator.next();
    	int itemIndex = iterator.getSelectedIndex(); 
    	this.QArray[itemIndex] = null;
    	if (this.N == this.QArray.length / 4) this.resize(this.QArray.length / 2);  

    	this.N--;
    	return chosenItem; 
    } 

    
    // return a random item (but do not remove it)
    public Item sample() {
    	QueueIterator<Item> iterator = this.iterator(); 
    	return iterator.next(); 
    }
	
    // return an independent iterator over items in random order
    public QueueIterator<Item> iterator() {
    	return new QueueIterator<Item>(N, this.QArray); 
    }
    
}

//Other Classes: 
class QueueIterator<Item> implements Iterator<Item> {
	
	protected int i;
	protected Item[] Queue;
	protected int selectedIndex; 
	
	public QueueIterator(int capacity, Item[] Queue) {
		this.i = capacity; 
		this.Queue = Queue; 
	}
	
	public Item next() { //Refactor to include exception and randomization code.
		if (this.Queue[0] == null && this.Queue.length == 1) throw new NoSuchElementException("No items left to iterate over"); 
		int randomIndex = StdRandom.uniform(i);
		this.selectedIndex = randomIndex; 
		return this.Queue[randomIndex]; 
	}
	
	public int getSelectedIndex() {
		return this.selectedIndex; 
	}
	
	public boolean hasNext() {
		return i > 0; 
	}
}



//Planning for Randomized Queue Structure: 

	//Performance Analysis: 

		//RandomizedQueue operations require worst case O(1) in amortized time. 

			//Amortized time considers the average running time per operation over the sequence of operations done. 

			//While worst case scenario is N (ie linear), the amortized version is constant (because resizing only happens at specific powers of 2). 

			//Conclusion: Array implementation is best for RandomizedQueue. 


		//Iterator performance must be O(1) and constructor must be O(N). 

			//O(1): next() and hasNext() are constant time because they only need to do a constant number of array accessing operations. 

			//O(N): for loop filling of the iterator field in order to construct the iterator. 

			
	//Casting is needed for the case of generic arrays. 

	//Use wrapper classes for primitives when it comes to parameterizations. 

	//Resizing method is needed in order to adjust array size when overflow is created. 

		//Applied in enqueue and dequeue operations, once boundaries are reached. 



//Analysis: 

	//Independent Iterator Usage: 

		//It is a bad idea to use an iterator within a class, especially as a field. 

		//An iterator is a stateful object that maintains its own internal state. 

		//Therefore, in order to maintain method scope encapsulations and allow method behaviors to be independent from each other, it is important that independent iterators are used to individually carry out the method functions. 

			//If a single iterator is used, then there is a large risk of mutating the state of a RandomizedQueue instance, resulting in errors relating to keeping track of the number of non-null values in the queue. 

	

	//OVerall purpose of an iterator: 

		//An iterator represents a while loop or a for loop that allows for selective iteration to be done on a collection of objects. 

		//The iterator allows for less verbose code to be used compared to a normal loop iteration. 

		//Additionally, an iterator, as an object, takes advantage of the OOP principle of encapsulation, allowing for iteration to be either restricted to individual scopes of methods or, if designed properly, for methods to "keep track" of state in the larger Iterable object it is a part of. 

			//This usage would be carried out by making the larger class implementing the Iterable interface and to have the Iterator class as a separate helper class in the same file (to allow for encapsulation). 

			//The iterator is used as an instance field for the object instance. 