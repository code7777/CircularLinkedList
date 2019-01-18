/*CS275 extra credit optional assignment
 * Author : Preston Porter
 * modified code from the book
 *
 */

import java.util.LinkedList;
import java.util.Queue;


import java.util.NoSuchElementException;
//import edu.colorado.nodes.Node;
public class CircularLinkedList<E> implements Cloneable {
  
   // File: LinkedQueue.java from the package edu.colorado.collections
// Complete documentation is available from the LinkedQueue link in:
//   http://www.cs.colorado.edu/~main/docs/



   // Invariant of the LinkedQueue class:
   //   1. The number of items in the queue is stored in the instance variable
   //      manyNodes.
   //   2. The items in the queue are stored in a linked list, with the front 
   //      of the queue stored at the head node, and the rear of the queue at 
   //      the final node.
   //   3. For a non-empty queue, the instance variable front is the head 
   //      reference of the linked list of items and the instance variable rear
   //      is the tail reference of the linked list. For an empty queue, both 
   //      front and rear are the null reference.
  
   private int manyNodes=0; //numberOf Nodes in list initialized to 0
   //private Node<E> front;
   private Node<E> rear;


   /**
   * Initialize an empty queue.
   * <b>Postcondition:</b>
   *   This queue is empty.
   **/   
   public <Node>CircularLinkedList( )
   {
      //front = null;
      rear = null;
      
   }

   
   /**
   * Put a new a new item in this queue. 
   * @param item
   *   the item to be pushed onto this queue 
   * <b>Postcondition:</b>
   *   The item has been pushed onto this queue.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for increasing the queue's capacity.
   * <b>Note:</b>
   *   An attempt to increase the capacity beyond
   *   <CODE>Integer.MAX_VALUE</CODE> will cause the queue to fail with an
   *   arithmetic overflow.
   **/    
   public void add(E item)
   {
     Node cursor;
       if (isEmpty( ))
       {  // Insert first item.
         rear = new Node<E>(item, null);
          rear.setLink(rear);// <=== add a link to itself to make it circular
   //rear = front;
       }
       else
       {  // Insert an item that is not the first.
          rear.addNodeAfter(item);
          rear = rear.getLink( );  
          //for(cursor =rear; curs
         // rear.setLink(CircularLinkedList.listposition(0)); //make link to front creating a circular linked List
         
       }
       manyNodes++;
   }
              
   
   /**
   * Generate a copy of this queue.
   * @return
   *   The return value is a copy of this queue. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to an <CODE>LinkedQueue</CODE> before it can be used.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   @SuppressWarnings("unchecked")
   public CircularLinkedList<E> clone( )       
   {  // Clone a LinkedQueue<E>.
      CircularLinkedList<E> answer;
      Object[ ] cloneInfo;
      
      try
      {
         answer = (CircularLinkedList<E>) super.clone( );
      }
      catch (CloneNotSupportedException e)
      { 
         // This exception should not occur. But if it does, it would probably indicate a
         // programming error that made super.clone unavailable. The most comon error
         // The most common error would be forgetting the "Implements Cloneable"
         // clause at the start of this class.
         throw new RuntimeException
         ("This class does not implement Cloneable");
      }
      
      cloneInfo = Node.listCopyWithTail(rear);
     // answer.front = (Node<E>) cloneInfo[0];
      answer.rear = (Node<E>) cloneInfo[1];
      
      return answer;
   }        

 
   /**
   * Determine whether this queue is empty.
   * @return
   *   <CODE>true</CODE> if this queue is empty;
   *   <CODE>false</CODE> otherwise. 
   **/
   public boolean isEmpty( )
   {
      return (manyNodes == 0);
   }


   /**
   * Get the front item, removing it from this queue.
   * <b>Precondition:</b>
   *   This queue is not empty.
   * @return
   *   The return value is the front item of this queue, and the item has
   *   been removed.
   * @exception NoSuchElementException
   *   Indicates that this queue is empty.
   **/    
   public E remove( )
   {
     
      if (rear == null) {
        // NoSuchElementException is from java.util and its constructor has no argument.
        throw new NoSuchElementException("Queue underflow");
    }
    Node<E> link = rear.getLink();

    E answer = link.getData();

    if (link == rear) {
        // this is the last item in the list
        rear = null;
    }
    else {
        // point rear to the one after front so that front is removed
        rear.setLink(link.getLink());
    }
    manyNodes--;
    return answer;
     
     /*
      E answer;

      if (manyNodes == 0)
         // NoSuchElementException is from java.util and its constructor has no argument.
         throw new NoSuchElementException("Queue underflow");
     answer = rear.getData( );
     // front = front.getLink( );
      manyNodes--;
      if (manyNodes == 0)
         rear = null;
      return answer;
       //rear.setLink(front);*/
   }
   
   
   /**
   * Accessor method to determine the number of itaems in this queue.
   * @return
   *   the number of items in this queue
   **/ 
   public int size( )   
   {
      return manyNodes;
   }

   public static void main(String[] args)
   {
     CircularLinkedList c1 = new CircularLinkedList();
     c1.add(1);
     c1.add(2);
     c1.add(3);
     System.out.println("the data in the rear is " + c1.rear.getData());
     System.out.println("the data in the link from the rear is " + c1.rear.getLink().getData());
      System.out.println("the size of the list is " + c1.size());
       System.out.print("\n");
     c1.remove();
     c1.remove();
     c1.remove();
     c1.add(5);
      c1.add(7);
     System.out.println("the data in the rear is " + c1.rear.getData());
     //System.out.println("the data in the front is " + c1.front.getData());
    System.out.println("the data in the link from the rear is " + c1.rear.getLink().getData());
    System.out.println("the size of the list is " + c1.size());
     //System.out.println("this is because the rear links to the front making the Linked List circular");
   }
}
 class Node<E>
{
   // Invariant of the Node class:
   //   1. Each node has one reference to an E Object, stored in the instance
   //      variable data.
   //   2. For the final node of a list, the link part is null.
   //      Otherwise, the  link part is a reference to the
   //      next node of the list.
   private E data;
   private Node<E> link;

   /**
   * Initialize a node with a specified initial data and link to the next
   * node. Note that the initialLink may be the null reference,
   * which indicates that the new node has nothing after it.
   * @param initialData
   *   the initial data of this new node
   * @param initialLink
   *   a reference to the node after this new node--this reference may be null
   *   to indicate that there is no node after this new node.
   * @postcondition
   *   This node contains the specified data and link to the next node.
   **/
   public Node(E initialData, Node<E> initialLink)
   {
      data = initialData;
      link = initialLink;
   }


   /**
   * Modification method to add a new node after this node.
   * @param element
   *   the data to place in the new node
   * @postcondition
   *   A new node has been created and placed after this node.
   *   The data for the new node is element. Any other nodes
   *   that used to be after this node are now after the new node.
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for a new
   *   Node.
   **/
   public void addNodeAfter(E element)
   {
      link = new Node<E>(element, link);
   }


   /**
   * Accessor method to get the data from this node.
   * @param - none
   * @return
   *   the data from this node
   **/
   public E getData( )
   {
      return data;
   }


   /**
   * Accessor method to get a reference to the next node after this node.
   * @param - none
   * @return
   *   a reference to the node after this node (or the null reference if there
   *   is nothing after this node)
   **/
   public Node<E> getLink( )
   {
      return link;
   }


   /**
   * Copy a list.
   * @param source
   *   the head of a linked list that will be copied (which may be
   *   an empty list in where source is null)
   * @return
   *   The method has made a copy of the linked list starting at
   *   source. The return value is the head reference for the
   *   copy.
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for the new list.
   **/
   public static <E> Node<E> listCopy(Node<E> source)
   {
      Node<E> copyHead;
      Node<E> copyTail;

      // Handle the special case of the empty list.
      if (source == null)
         return null;

      // Make the first node for the newly created list.
      copyHead = new Node<E>(source.data, null);
      copyTail = copyHead;

      // Make the rest of the nodes for the newly created list.
      while (source.link != null)
      {
         source = source.link;
         copyTail.addNodeAfter(source.data);
         copyTail = copyTail.link;
      }

      // Return the head reference for the new list.
      return copyHead;
   }


   /**
   * Copy a list, returning both a head and tail reference for the copy.
   * @param source
   *   the head of a linked list that will be copied (which may be
   *   an empty list in where source is null)
   * @return
   *   The method has made a copy of the linked list starting at
   *   source.  The return value is an
   *   array where the [0] element is a head reference for the copy and the [1]
   *   element is a tail reference for the copy.
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for the new list.
   **/
   public static <E> Node<E>[ ] listCopyWithTail(Node<E> source)
   {
      Node<E> copyHead;
      Node<E> copyTail;
    //Node<E>[ ] answer = (Node<E>[]) new Object[2]; Causes ClassCastException!
      Node<E>[ ] answer = createArray(null, null);

      // Handle the special case of the empty list.
      if (source == null)
         return answer; // The answer has two null references .

      // Make the first node for the newly created list.
      copyHead = new Node<E>(source.data, null);
      copyTail = copyHead;

      // Make the rest of the nodes for the newly created list.
      while (source.link != null)
      {
         source = source.link;
         copyTail.addNodeAfter(source.data);
         copyTail = copyTail.link;
      }

      // Return the head and tail references.
      answer[0] = copyHead;
      answer[1] = copyTail;
      return answer;
   }


   /**
   * Compute the number of nodes in a linked list.
   * @param head
   *   the head reference for a linked list (which may be an empty list
   *   with a null head)
   * @return
   *   the number of nodes in the list with the given head
   * @note
   *   A wrong answer occurs for lists longer than Int.MAX_VALUE.
   **/
   public static <E> int listLength(Node<E> head)
   {
      Node<E> cursor;
      int answer;

      answer = 0;
      for (cursor = head; cursor != null; cursor = cursor.link)
         answer++;

      return answer;
   }


   /**
   * Copy part of a list, providing a head and tail reference for the new copy.
   * @param start/end
   *   references to two nodes of a linked list
   * @param copyHead/copyTail
   *   the method sets these to refer to the head and tail node of the new
   *   list that is created
   * @precondition
   *   start and end are non-null references to nodes
   *   on the same linked list,
   *   with the start node at or before the end node.
   * @return
   *   The method has made a copy of the part of a linked list, from the
   *   specified start node to the specified end node. The return value is an
   *   array where the [0] component is a head reference for the copy and the
   *   [1] component is a tail reference for the copy.
   * @exception IllegalArgumentException
   *   Indicates that start and end do not satisfy
   *   the precondition.
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for the new list.
   **/
   public static <E> Node<E>[ ] listPart(Node<E> start, Node<E> end)
   {
      Node<E> copyHead;
      Node<E> copyTail;
      Node<E> cursor;
    //Node<E>[ ] answer = (Node<E>[]) new Object[2]; Causes ClassCastException!
      Node<E>[ ] answer = createArray(null, null);

      // Check for illegal null at start or end.
      if (start == null)
         throw new IllegalArgumentException("start is null");
      if (end == null)
         throw new IllegalArgumentException("end is null");

      // Make the first node for the newly created list.
      copyHead = new Node<E>(start.data, null);
      copyTail = copyHead;
      cursor = start;

      // Make the rest of the nodes for the newly created list.
      while (cursor != end)
      {
         cursor = cursor.link;
         if (cursor == null)
            throw new IllegalArgumentException
            ("end node was not found on the list");
         copyTail.addNodeAfter(cursor.data);
         copyTail = copyTail.link;
      }

      // Return the head and tail references
      answer[0] = copyHead;
      answer[1] = copyTail;
      return answer;
   }


   /**
   * Find a node at a specified position in a linked list.
   * @param head
   *   the head reference for a linked list (which may be an empty list in
   *   which case the head is null)
   * @param position
   *   a node number
   * @precondition
   *   position > 0.
   * @return
   *   The return value is a reference to the node at the specified position in
   *   the list. (The head node is position 1, the next node is position 2, and
   *   so on.) If there is no such position (because the list is too short),
   *   then the null reference is returned.
   * @exception IllegalArgumentException
   *   Indicates that position is zero.
   **/
   public static <E> Node<E> listPosition(Node<E> head, int position)
   {
      Node<E> cursor;
      int i;

      if (position == 0)
           throw new IllegalArgumentException("position is zero");

      cursor = head;
      for (i = 1; (i < position) && (cursor != null); i++)
         cursor = cursor.link;

      return cursor;
   }


   /**
   * Search for a particular piece of data in a linked list.
   * @param head
   *   the head reference for a linked list (which may be an empty list in
   *   which case the head is null)
   * @param target
   *   a target to search for
   * @return
   *   The return value is a reference to the first node that contains the
   *   specified target. If the target is non-null, then the
   *   target.equals method is used to find such a node.
   *   The target may also be null, in which case the return value is a
   *   reference to the first node that contains a null reference for its
   *   data. If there is no node that contains the target, then the null
   *   reference is returned.
   **/
   public static <E> Node<E> listSearch(Node<E> head, E target)
   {
      Node<E> cursor;

      if (target == null)
      {  // Search for a node in which the data is the null reference.
         for (cursor = head; cursor != null; cursor = cursor.link)
            if (cursor.data == null)
               return cursor;
      }
      else
      {  // Search for a node that contains the non-null target.
         for (cursor = head; cursor != null; cursor = cursor.link)
            if (target.equals(cursor.data))
               return cursor;
      }

      return null;
   }


   /**
   * Modification method to remove the node after this node.
   * @param - none
   * @precondition
   *   This node must not be the tail node of the list.
   * @postcondition
   *   The node after this node has been removed from the linked list.
   *   If there were further nodes after that one, they are still
   *   present on the list.
   * @exception NullPointerException
   *   Indicates that this was the tail node of the list, so there is nothing
   *   after it to remove.
   **/
   public void removeNodeAfter( )
   {
      link = link.link;
   }


   /**
   * Modification method to set the data in this node.
   * @param newData
   *   the new data to place in this node
   * @postcondition
   *   The data of this node has been set to newData.
   *   This data is allowed to be null.
   **/
   public void setData(E newData)
   {
      data = newData;
   }


   /**
   * Modification method to set the link to the next node after this node.
   * @param newLink
   *   a reference to the node that should appear after this node in the linked
   *   list (or the null reference if there is no node after this node)
   * @postcondition
   *   The link to the node after this node has been set to newLink.
   *   Any other node (that used to be in this link) is no longer connected to
   *   this node.
   **/
   public void setLink(Node<E> newLink)
   {
      link = newLink;
   }



   /**
   * Create an array of Node<E> objects.  This is the only way that
   * I've found to create such an array that doesn't cause a
   * ClassCastException at run time.  In the textbook, I used:
   *  // Node<E>[ ] answer = (Node<E>[]) new Object[2];
   * but this approach now seems to fail.  I'll keep looking for
   * other solutions!
   **/
   private static <E> Node<E>[ ] createArray(Node<E>... nodes)
   {
      return nodes;
   }

}
