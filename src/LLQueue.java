package data_structure_project_;

import java.util.Scanner;

public class LLQueue<E> {

    public Scanner input = new Scanner(System.in);

    static class Node<E> {

        private E element; // reference to the element stored at this node
        private Node<E> next; // reference to the subsequent node in the list

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }

        public void setelement(E element) {
            this.element = element;
        }
    }
    protected Node<E> front;
    protected Node<E> rear;
    protected int size;

    public LLQueue() { // constructor
        front = null;
        rear = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() { // returns (but does not remove) the first element
        if (isEmpty()) {
            return null;
        }
        return front.getElement();
    }

    public E last() { // returns (but does not remove) the last element
        if (isEmpty()) {
            return null;
        }
        return rear.getElement();
    }

    public void enqueue(E elem) {
        Node<E> newest = new Node<E>(elem, null);
        if (isEmpty()) {
            front = newest; // special case of a previously empty queue 
        } else {
            rear.setNext(newest); // add node at the tail of the list 
        }
        rear = newest; // update the reference to the tail node 
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E answer = front.getElement();
        front = front.getNext();
        size--;
        if (size == 0) {
            rear = null; // the queue is now empty
        }
        return answer;
    }

    public void Display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            E ele = dequeue();
            System.out.println(ele);

            enqueue(ele);
        }
    }

    public void deleteOrder() {
        System.out.print("Enter order ID to delete: ");
        int orderID = input.nextInt();

        int s = size();
        boolean found = false;

        for (int i = 0; i < s; i++) {
            Order ele = (Order) dequeue();

            if (ele.getOrderID() == orderID) {
                found = true;
            } else {
                enqueue((E) ele);
            }
        }

        if (found) {
            System.out.println("Order deleted successfully!");
        } else {
            System.out.println("Order ID not found!");
        }
    }

    public void deliverNextOrder() {
        if (isEmpty()) {
            System.out.println("There are no orders ready for delivery.");
        } else {
            Order order = (Order) dequeue();
            System.out.println("Delivering order now...");
            System.out.println(order);

            order.markAsDelivered();

            System.out.println("The delivery line has been updated!");
        }

    }

    public void deliveryLine() {
        if (isEmpty()) {
            System.out.println("No orders in the delivery line");
        } else {
            int s = size();
            System.out.print("[First]  ");
            for (int i = 0; i < s; i++) {
                Order order = (Order) dequeue();
                System.out.print("Order #" + order.getOrderID() + " (" + order.getCustomerName() + ") <---- ");
                enqueue((E) order);
            }
            System.out.println("[End]");
        }
    }

}
