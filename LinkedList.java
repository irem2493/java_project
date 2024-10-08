package c1008;

public class LinkedList<T> {
	Node<T> head;
	public LinkedList() {
		head = null;
	}

	public void append(T data) {
		if(head == null) {
			head = new Node<T>(data);
			return;
		}
		Node<T> current = head;
		while(current.next != null) {
			current = current.next;
		}
		current.next = new Node<T>(data);
	}
	
	public void printList() {
		Node<T> current = head;
		while(current != null) {
			System.out.print(current.data + " -> ");
			current = current.next;
		}
		System.out.println("null");
	}
	
	//데이터 삭제
	public void delete(T data) {
		//1.연결리스트가 비어있으면 종료
		if(head == null) {
			System.out.println("연결리스트가 비어있습니다.");
			return;
		}
		
		//2. 삭제하려는 데이터가 첫번째 노드에 있으면 첫 번째 노드 삭제
		if(head.data == data) {
			/*current = head.next; 
			head.next = null;
			head = current;*/
			head = head.next;
			return;
		}
		Node<T> current = head;
		while(current.next != null && current.next.data != data) {
			current = current.next;
		}
		
		//3.삭제할 노드를 찾았으면...
		if(current.next != null) current.next = current.next.next;
		else System.out.println("해당 데이터가 존재하지 않습니다.");
	}
}
