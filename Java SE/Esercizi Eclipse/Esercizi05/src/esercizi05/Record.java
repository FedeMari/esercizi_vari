package esercizi05;

public class Record<E> {
	E attuale;
    Record next;

//    public Record(E attuale) {
//        this.attuale = attuale;
//    }

    public Record(E attuale, Record next) {
        this.attuale = attuale;
        this.next = next;
    }

    @Override
    public String toString() {
        if (next == null)
            return "" + attuale;
        return "" + attuale + "," + next;
    }

    public E getAttuale() {
        return attuale;
    }

    public void setAttuale(E attuale) {
        this.attuale = attuale;
    }

    public Record getNext() {
        return next;
    }

    public void setNext(Record next) {
        this.next = next;
    }
}
