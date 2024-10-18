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


//Returns the Node at count g
public PathNode getvalue(int g){
    int count=1;
    PathNode current = head;
    if(g==1){
        return head;
    }else{
        while(count<g){
            current= current.next;
            count++;
        }
        return current;
    }

}


//returns size of linked list
public int sizeL(){
    int count = 1;
    PathNode current = head;
    while((current.next)!=null){
        current = current.next;
        count++;
    }
    return count;
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
