package Test;

import java.util.NoSuchElementException;

public class DoubleLinkedList <AnyType> {
	//Начало
	private DLList<AnyType> head;
	//Размер списка
	private int size;
	
	public int size () {
		
		return size;
		
	}
	
	public boolean isEmpty () {
		
		if (size == 0) {
			
			return true;
			
		}
		
		else {
			
			return false;
			
		}
		
	}
	
	public void addFirst (AnyType data) {
		
		if(isEmpty()) { //Если пусто, то просто в начало добавляем
			
			head = new DLList<AnyType> (data);
			
		}
		
		else { //Если непусто, то сдвигаем
			
			DLList<AnyType> temp = head; //Временно сохраняем
			head = new DLList <AnyType> (null, data, temp); 
			head.next.prev = head; //Указываем на предыдущий 
			
		}
		
		size ++;
		
	}
	
	public void addLast (AnyType data) {
		
		if(isEmpty()) { //Если пусто, то просто в начало добавляем
			
			head = new DLList<AnyType> (data);
			
		}
		
		else {
			
			DLList<AnyType> temp = head;
			while (temp.next != null) {
				
				temp = temp.next; //Перемещаем указатель до тех пор, пока следующий указатель существует
				
			}
			
			temp.next = new DLList <AnyType> (temp, data, null);
			
		}
		
		size ++;
	}
	
	public void addBefore (AnyType data, int i) {
		
		if (isEmpty()) {
			
			throw new NoSuchElementException("Element "  + i + " not found");
			
		}
		
		DLList <AnyType> current = head;
		int k = 0;
		while (current != null && k < i) {
			
			current = current.next;
			k ++;
			
		}
		
		if (current == null) {
			
			throw new NoSuchElementException("Element "  + i + " not found");
			
		}
		
		else {
			
			DLList <AnyType> addNode = new DLList <AnyType> (current.prev, data, current);
			
			if (current.prev != null) {
				
				current.prev.next = addNode;
				
			}
			
			else {
				
				head = addNode;
				
			}
			
			current.prev = addNode;
			
			size ++;
			
		}
		
		
	}
	
	public void addAfter (AnyType data, int i) {
		
		if (isEmpty()) {
			
			throw new NoSuchElementException("Element "  + i + " not found");
			
		}
		
		DLList <AnyType> current = head;
		int k = 0;
		while (current != null && k < i) {
			
			current = current.next;
			k ++;
			
		}
		
		if (current == null) {
			
			throw new NoSuchElementException("Element "  + i + " not found");
			
		}
		
		else {
			
			DLList <AnyType> addNode = new DLList <AnyType> (current, data, current.next);
			
			if (current.next != null) {
				
				current.next.prev = addNode;
				
			}
			
			
			
			current.next = addNode;
			
			size ++;
			
		}
		
		
	}
	
	public void remove (AnyType data) {
		
		if (isEmpty()) {
			
			throw new NoSuchElementException("Element "  + data + " not found");
		
		
		}
		
		if (head.data.equals(data)) {
			
			head = head.next;
			return;
			
		}
		
		DLList <AnyType> current = head;
		
		while (current != null && !current.data.equals(data)) {
			
			current = current.next;
			
		}
		
		if (current == null) {
			
			throw new NoSuchElementException("Element "  + data + " not found");
			
		}
		
		else {
			
			if (current.next != null) {
				
				current.next.prev = current.prev;
				
			}
			
			current.prev.next = current.next;
			
			 size --;
			
		}
		
	}
	
	public void print_list() {
		
		if (isEmpty()) {
			
			throw new NoSuchElementException("Element not found");
		
		
		}
		
		
		
		else {
			
			DLList <AnyType> current = head;
			
			System.out.println(current.data);
			
			while (current.next != null) {
				

				current = current.next;
				System.out.println(current.data);
			}
			
			
			
		}
		
	}
		
}
