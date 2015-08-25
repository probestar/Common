package com.probestar.psutils.datastructure;


public class PSLinkedList<K> {

	/**
	 * 
	 */
	private PSLinkedNode<K> _head;
	private PSLinkedNode<K> _tail;
	private PSLinkedNode<K> _current;
	private long _length;

	public PSLinkedList() {
		_head = new PSLinkedNode<K>(null);
		_tail = new PSLinkedNode<K>(null);
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

	public synchronized PSLinkedNode<K> put(K object) {
		PSLinkedNode<K> node = new PSLinkedNode<K>(object);
		_tail.previous.next = node;
		node.previous = _tail.previous;
		_tail.previous = node;
		node.next = _tail;
		node.list = this;
		_length++;
		return node;
	}

	private synchronized void put(PSLinkedNode<K> node) {
		_tail.previous.next = node;
		node.previous = _tail.previous;
		_tail.previous = node;
		node.next = _tail;
		_length++;
	}

	public synchronized void moveToHead() {
		_current = _head;
	}

	public synchronized PSLinkedNode<K> takeAwayFirst() {
		if (_head.next.equals(_tail))
			return null;
		else {
			PSLinkedNode<K> result = _head.next;
			remove(result);
			return result;
		}
	}

	public synchronized PSLinkedNode<K> takeAwayLast() {
		if (_tail.previous.equals(_head))
			return null;
		else {
			PSLinkedNode<K> result = _tail.previous;
			remove(result);
			return result;
		}
	}

	public synchronized PSLinkedNode<K> get() {
		if (_current.next.equals(_tail))
			return null;
		else {
			_current = _current.next;
			return _current;
		}
	}

	public synchronized void kick(PSLinkedNode<K> node) {
		if (node == null)
			return;
		remove(node);
		put(node);
	}

	public synchronized boolean remove(PSLinkedNode<K> node) {
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
