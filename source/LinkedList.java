// implementation of a Singly Linked list taken from the textbook
public class LinkedList {
	protected Node head, tail, popped;
	protected long size; // number of nodes in the list
	
	public LinkedList()
	{
		head = null;
		tail = null;
		popped = null;
		size = 0;
	}
	
	public void addFirst(Node aNode)
	{
		aNode.setNext(head); // have aNode point to old head node
		head = aNode;
		size = size + 1;
	}
	public void addLast(Node endNode)
	{
		if(this.size == 0)
		{
			this.addFirst(endNode);
		}
		else
		{
			Node currentNode = head;
			while(currentNode.getNext() != null)
			{
				currentNode = currentNode.getNext();
			}
			tail = currentNode; // gets last node in list
			tail.setNext(endNode); // sets next node to new end node
			endNode.setNext(null); // sets next node of end node to null
			tail = endNode; // sets endNode as the new tail
			size = size + 1;
		}
	}
	
	public void removeLast()
	{
		if(this.size == 1)
		{
			this.removeFirst();
		}
		else
		{
			tail = null;
		}
	}

	public void removeFirst()
	{
		if(head == null)
		{
			System.out.println("Cannot remove from empty list!");
		}
		popped = head;
		head = head.getNext();
		popped.setNext(null);
		size = size - 1;
	}
	public void printList(String listName)
	{
		if(this.size!=0)
		{
		Node currentNode = head;
		System.out.println("");
		System.out.print(listName + ": "+ this.size + " contents: ");
		for(int i = 0; i < this.size; i++)

				{
					System.out.print(currentNode.getElement() + " ");
					currentNode = currentNode.getNext();
				}
				System.out.println("");
				
			}
	}
}
