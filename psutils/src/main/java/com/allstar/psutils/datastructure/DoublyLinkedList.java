/**
 *
 * Copyright (c) 2015
 * All rights reserved.
 *
 * @Title DoublyLinkedList.java
 * @Package com.allstar.cinutils.datastructure
 * @author WangYanwei
 * @Email wangyanwei@feinno.com
 * @QQ 344137375
 * @date 2015年6月12日 上午9:54:09
 * @version V1.0
 * @Description 
 *
 */

package com.allstar.psutils.datastructure;

import java.util.Iterator;

public class DoublyLinkedList<E> implements Iterable<E> {
	private int _count;
	private DoublyLinkedListNode<E> _head;
	private DoublyLinkedListNode<E> _tail;

	public DoublyLinkedList() {
		_count = 0;
		_head = new DoublyLinkedListNode<E>(null);
		_tail = new DoublyLinkedListNode<E>(null);
		_head._next = _tail;
		_tail._prev = _head;
	}

	private DoublyLinkedListNode<E> getNode(E value) {
		checkValue(value);
		DoublyLinkedListNode<E> node = _head;
		while (node._next != _tail) {
			node = node._next;
			if (node._value.equals(value))
				return node;
		}
		return null;
	}

	private void checkValue(E value) {
		if (value == null)
			throw new NullPointerException("value can NOT be null.");
	}

	private void checkNode(DoublyLinkedListNode<E> node) {
		if (node == null)
			throw new NullPointerException("Can NOT find node.");
		if (node != _head && node != _tail)
			checkValue(node._value);
	}

	private void addBeforeNode(DoublyLinkedListNode<E> baseNode, E value) {
		checkNode(baseNode);
		checkValue(value);
		DoublyLinkedListNode<E> node = new DoublyLinkedListNode<E>(value);
		node._prev = baseNode._prev;
		node._next = baseNode;
		baseNode._prev._next = node;
		baseNode._prev = node;
		_count++;
	}

	private void addAfterNode(DoublyLinkedListNode<E> baseNode, E value) {
		checkNode(baseNode);
		checkValue(value);
		DoublyLinkedListNode<E> node = new DoublyLinkedListNode<E>(value);
		node._prev = baseNode;
		node._next = baseNode._next;
		baseNode._next = node;
		node._next._prev = node;
		_count++;
	}

	public E getPrevious(E value) {
		DoublyLinkedListNode<E> node = getNode(value);
		if (node == null || node == _head)
			return null;
		return node._prev._value;
	}

	public E getNext(E value) {
		DoublyLinkedListNode<E> node = getNode(value);
		if (node == null || node == _tail)
			return null;
		return node._next._value;
	}

	public E getFirst() {
		return _head._next._value;
	}

	public E getLast() {
		return _tail._prev._value;
	}

	public boolean isEmpty() {
		return _head == _tail;
	}

	public void addBefore(E base, E value) {
		DoublyLinkedListNode<E> baseNode = getNode(base);
		addBeforeNode(baseNode, value);
	}

	public void addAfter(E base, E value) {
		DoublyLinkedListNode<E> baseNode = getNode(base);
		addAfterNode(baseNode, value);
	}

	public void addFirst(E value) {
		addAfterNode(_head, value);
	}

	public void addLast(E value) {
		addBeforeNode(_tail, value);
	}

	private void removeNode(DoublyLinkedListNode<E> node) {
		checkNode(node);
		node._prev._next = node._next;
		node._next._prev = node._prev;
		node.release();
		_count--;
	}

	public boolean remove(E value) {
		DoublyLinkedListNode<E> baseNode = getNode(value);
		if (baseNode == null)
			return false;
		removeNode(baseNode);
		return true;
	}

	public void removeAll(E value) {
		while (remove(value))
			;
	}

	public void clear() {
		_head._next._prev = null;
		_tail._prev._next = null;
		_head._next = _tail;
		_tail._prev = _head;
		_count = 0;
	}

	public int getCount() {
		return _count;
	}

	private class DoublyLinkedListNode<T> {
		private T _value;
		private DoublyLinkedListNode<T> _prev;
		private DoublyLinkedListNode<T> _next;

		public DoublyLinkedListNode(T value) {
			_value = value;
		}

		public void release() {
			_value = null;
			_prev = null;
			_next = null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			if (!(obj instanceof DoublyLinkedListNode))
				return false;
			DoublyLinkedListNode<T> node = (DoublyLinkedListNode<T>) obj;
			if (node._value.equals(_value))
				return true;
			return false;
		}

		@Override
		public int hashCode() {
			return _value.hashCode();
		}

		@Override
		public String toString() {
			if (_value == null)
				return "[Null]";
			return _value.toString();
		}
	}

	private class DoublyLinkedListIterator<T> implements Iterator<T> {
		private DoublyLinkedListNode<T> _curr;

		public DoublyLinkedListIterator(DoublyLinkedListNode<T> node) {
			_curr = node;
		}

		public boolean hasNext() {
			return _curr._next != _tail;
		}

		public T next() {
			_curr = _curr._next;
			return _curr._value;
		}

		public void remove() {
		}

	}

	public Iterator<E> iterator() {
		return new DoublyLinkedListIterator<E>(_head);
	}

}
