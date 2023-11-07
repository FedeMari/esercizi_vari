

import java.util.*;

public class ArrayListCustom<E> extends AbstractList<E> implements List<E> {

	private static final int DEF_CAP = 10;
	private Object[] content;
	private int size = 0;

	public ArrayListCustom(int capacity_start) {

		if (capacity_start <= 0) {
			capacity_start = DEF_CAP;
		}
		content = new Object[capacity_start];
	}

	public ArrayListCustom() {
		content = new Object[DEF_CAP];
	}

	public ArrayListCustom(Collection<? extends E> c) {
		int cap = c.size();
		content = new Object[cap];
		Object[] s = c.toArray();
		if ((cap = s.length) != 0) {
			if (c instanceof ArrayListCustom) {
				content = s;
			} else {
				content = Arrays.copyOf(content, size, Object[].class);
			}
		}
	}

	private int newCapacity(int newCap) {
		int newSize = content.length;
		while (this.size + newCap > newSize) {
			newSize += content.length;
		}
		return newSize;
	}

	private void grow(int dim) {
		if (size + dim > content.length) {
			content = Arrays.copyOf(content, newCapacity(dim));
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		Object[] source = c.toArray();
		grow(source.length);
		System.arraycopy(source, 0, content, size, source.length);
		size += source.length;
		return true;
	}

	public boolean add(E element) {
		grow(1);
		content[size] = element;
		size++;
		return true;
	}

	public boolean addAt(E element, int index) {
		if (index < 0 || index > content.length) {
			return false;
		}
		grow(1);
		System.arraycopy(content, index, content, index + 1, size - index);
		size++;
		content[index] = element;
		return true;
	}

	public boolean removeElement(E elem) {
		int index = Arrays.asList(content).indexOf(elem);
		if (index == -1) {
			return false;
		}
		System.arraycopy(content, index + 1, content, index, content.length - index - 1);
		size--;
		return true;
	}

	public boolean removeAT(int index) {
		if (index < 0 || index > content.length) {
			return false;
		}
		System.arraycopy(content, index + 1, content, index, content.length - index - 1);
		size--;
		shrink();
		return true;
	}

	public boolean setElem(E elem, int index) {
		if (index < 0 || index > content.length) {
			return false;
		}
		content[index] = elem;
		return true;
	}

	private void shrink() {
		if (size < content.length / 2) {
			content = Arrays.copyOf(content, content.length / 2);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		return (E) content[index];
	}
	
	public int capacity() {
		return content.length;
	}
}
