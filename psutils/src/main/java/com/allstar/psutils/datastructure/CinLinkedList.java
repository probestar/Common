package com.allstar.psutils.datastructure;


public class CinLinkedList<K> {

	/**
	 * 
	 */
	private CinLinkedNode<K> _head;
	private CinLinkedNode<K> _tail;
	private CinLinkedNode<K> _current;
	private long _length;

	public CinLinkedList() {
		_head = new CinLinkedNode<K>(null);
		_tail = new CinLinkedNode<K>(null);
		_head.next = _tail;
		_tail.previous = _head;
		_current = _head;
		_length = 0;
	}

	public synchronized long length() {
		return _length;
	}

	public synchronized boolean hasMore() {
		return _length > 0;
	}

	public synchronized CinLinkedNode<K> put(K object) {
		CinLinkedNode<K> node = new CinLinkedNode<K>(object);
		_tail.previous.next = node;
		node.previous = _tail.previous;
		_tail.previous = node;
		node.next = _tail;
		node.list = this;
		_length++;
		return node;
	}

	private synchronized void put(CinLinkedNode<K> node) {
		_tail.previous.next = node;
		node.previous = _tail.previous;
		_tail.previous = node;
		node.next = _tail;
		_length++;
	}

	public synchronized void moveToHead() {
		_current = _head;
	}

	public synchronized CinLinkedNode<K> takeAwayFirst() {
		if (_head.next.equals(_tail))
			return null;
		else {
			CinLinkedNode<K> result = _head.next;
			remove(result);
			return result;
		}
	}

	public synchronized CinLinkedNode<K> takeAwayLast() {
		if (_tail.previous.equals(_head))
			return null;
		else {
			CinLinkedNode<K> result = _tail.previous;
			remove(result);
			return result;
		}
	}

	public synchronized CinLinkedNode<K> get() {
		if (_current.next.equals(_tail))
			return null;
		else {
			_current = _current.next;
			return _current;
		}
	}

	public synchronized void kick(CinLinkedNode<K> node) {
		if (node == null)
			return;
		remove(node);
		put(node);
	}

	public synchronized boolean remove(CinLinkedNode<K> node) {
		if (node == null)
			return false;
		if (node.next == null)
			return false;
		if (node.list != this)
			return false;
		if (_current.equals(node))
			_current = node.previous;
		node.previous.next = node.next;
		node.next.previous = node.previous;
		node.previous = null;
		node.next = null;
		_length--;
		return true;
	}
}
