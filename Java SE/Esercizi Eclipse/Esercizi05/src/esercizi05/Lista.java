package esercizi05;

public class Lista<E> {
	Record <E> head = null;
    Record <E> tail = null;

    public void add (E e) {
        if(head == null) {
            head = new Record(e, null);
            tail = head;
            return;
        }
        var last = new Record(e, null);
        tail.setNext(last);
        tail = last;
    }

    @Override
    public String toString() {
        return "Lista [" + head + "]";
    }
}
