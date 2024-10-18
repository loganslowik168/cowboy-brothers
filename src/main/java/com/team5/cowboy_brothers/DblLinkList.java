/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team5.cowboy_brothers;

/**
 *
 * @author matth
 */
public class DblLinkList {
    PathNode head;
    PathNode tail;
    
    public DblLinkList(){
        head=null;
        tail=null;
    }
    // Traversing from head to the end of the list 
public void traverseForward() 
{ 
	PathNode current = head; 
	while (current != null) { 
		System.out.print(current.data + " "); 
		current = current.next; 
	} 
} 
// Traversing from tail to the head 
public void traverseBackward() 
{ 
	PathNode current = tail; 
	while (current != null) { 
		System.out.print(current.data + " "); 
		current = current.prev; 
	} 
} 

    public void insertAtEnd(int data) 
{ 
	PathNode temp = new PathNode(data); 
	if (tail == null) { 
		head = temp; 
		tail = temp; 
	} 
	else { 
		tail.next = temp; 
		temp.prev = tail; 
		tail = temp; 
	} 
} 

}
