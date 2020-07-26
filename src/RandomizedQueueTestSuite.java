import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;




public class RandomizedQueueTestSuite {
	
	private RandomizedQueue<Integer> testQueue; 
	private QueueIterator<Integer> iterator; 
	
	@Before
	public void setUp() throws Exception {
		//1. Arrange Object: Instantiate 
		testQueue = new RandomizedQueue<Integer>();
	}
	
	
	//Test for Queue Creation: 
	@Test
	public void testForQueueCreation() throws Exception {
		assertNotNull(testQueue.QArray); 
	}
	
	
	//resize() tests: 	
	@Test
	public void testForDoublingQueueSize() throws Exception {
		int expectedSize = 2*testQueue.getSize(); 
		testQueue.resize(2); 
		int newSize = testQueue.getSize(); 
		
		assertEquals(expectedSize, newSize); 
	}
		
	@Test 
	public void testForHalvingQueueSize() throws Exception {
		testQueue.resize(2); 
		int expectedSize = testQueue.getSize() / 2; 
		testQueue.resize(1); 
		int newSize = testQueue.getSize(); 
		
		assertEquals(expectedSize, newSize); 
	}
	
	
	//isEmpty() tests: 
	@Test 
	public void testForEmptyQueue() throws Exception {
		boolean isEmpty = testQueue.isEmpty(); 
		assertEquals(true, isEmpty); 
	}
	
	@Test 
	public void testForNonEmptyQueue() throws Exception {
		testQueue.enqueue(1);
		testQueue.enqueue(2);
		boolean isEmpty = testQueue.isEmpty(); 
		
		assertEquals(false, isEmpty); 
	}
	
	
	//enqueue() tests: 
	@Test
	public void testEnqueueforZeroIndex() throws Exception {
		testQueue.enqueue(1);
		int value = testQueue.peekArrayValue(0); //Note: Getting around generics class casting issue => Work within the class and simply use a wrapper method to do type conversions rather than outside of class scope. 
		
		assertEquals(1, value); 
	}
	
	@Test
	public void testEnqueueForSizeDoubling() throws Exception {
		int oldSize = testQueue.getSize(); 
		testQueue.enqueue(1);
		testQueue.enqueue(2);
		int newSize = testQueue.getSize(); 
		
		assertEquals(2*oldSize, newSize); 
	}
	
	@Test
	public void testEnqueueForNthAddition() throws Exception {
		for (int i = 0; i < 4; i++) {
			testQueue.enqueue(i);
		}
		
		for (int i = 0; i < 4; i++) {
			int value = testQueue.peekArrayValue(i); 
			assertEquals(i, value); 
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEnqueueForIllegalArgument() throws Exception {
		testQueue.enqueue(null);
	}
	
	
	
	//Iterator Tests: 	
	@Test
	public void verifyHasNextForTrue() throws Exception {
		testQueue.enqueue(1);
		testQueue.enqueue(2);
		iterator = testQueue.iterator();   
		boolean hasNext = iterator.hasNext(); 
		
		assertEquals(true, hasNext);
	}
		
	@Test
	public void verifyHasNextForFalse() throws Exception {
		iterator = testQueue.iterator();   
		boolean hasNext = iterator.hasNext(); 
		
		assertEquals(false, hasNext);
	}
	
	@Test
	public void testNextForRandomValue() throws Exception {
		for (int i = 0; i < 100; i++) {
			testQueue.enqueue(i);
		}
		
		iterator = testQueue.iterator(); 
		
		
		int value = iterator.next(); 
		assertNotEquals(0, value); 
	}
		
	@Test(expected = NoSuchElementException.class)
	public void testForNoSuchElementNext() throws Exception {
		iterator = testQueue.iterator(); 
		iterator.next(); 
	}
	
	
	
	//size() tests: 	
	@Test
	public void testSizeOfNonZeroQueue() throws Exception {
		for (int i = 0; i < 50; i++) {
			testQueue.enqueue(i);
		}
		int size = testQueue.size(); 
		
		assertEquals(50, size); 
	}
		
	@Test
	public void testSizeOfEmptyQueue() throws Exception {
		int size = testQueue.size(); 
		
		assertEquals(0, size); 
	}
	
	
	
	//dequeue() tests: 	
	@Test
	public void testDequeueForSizeChange() throws Exception {
		for (int i = 0; i < 10; i++) {
			testQueue.enqueue(i);
		}
		int oldSize = testQueue.size();
		testQueue.dequeue(); 
		int newSize = testQueue.size(); 
		
		assertEquals(oldSize - 1, newSize); 
	}
		
	@Test
	public void testDequeueForItemRemoval() throws Exception {
		testQueue.enqueue(1);
		testQueue.dequeue(); 
		testQueue.enqueue(2);		
		int dequeueTimeValue = testQueue.peekArrayValue(0); 
		
		assertEquals(2, dequeueTimeValue); 
	}
		
	@Test
	public void verifyItemRemovalFromQueue() throws Exception {
		testQueue.enqueue(1);
		int testValue = testQueue.dequeue(); 
		
		assertEquals(1, testValue); 
	}
	
	@Test(expected = NoSuchElementException.class)
	public void verifyNoSuchElementException() throws Exception {
		testQueue.dequeue(); 
	}

	
	//sample() tests: 
	//Case 1: Verify Item return from method. 
	@Test
	public void testForItemReturnFromSample() throws Exception {
		for (int i = 0; i < 10; i++) {
			testQueue.enqueue(i);
		}
		
		assertNotNull(testQueue.sample());
	}
	
	
	//Case 2: Verify size says the same after calling method. 
	@Test
	public void testForSizeMaintenance() throws Exception {
		for (int i = 0; i < 10; i++) {
			testQueue.enqueue(i);
		}
		int oldSize = testQueue.size(); 
		testQueue.sample(); 
		int newSize = testQueue.size(); 
		
		assertEquals(oldSize, newSize); 
	}
	
	
	//Case 3: Verify NoSuchElementException for case of empty Queue. 
	@Test(expected = NoSuchElementException.class)
	public void testForNoSuchElementExceptionSample() throws Exception {
		testQueue.sample(); 
	}
}
