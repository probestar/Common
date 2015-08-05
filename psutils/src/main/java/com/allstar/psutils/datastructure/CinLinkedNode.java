package com.allstar.psutils.datastructure;


public class CinLinkedNode<K> {

	/**
	 * 
	 */
	CinLinkedNode<K> previous;
	CinLinkedNode<K> next;
	CinLinkedList<K> list;
	private K _object;

	public CinLinkedNode(K node) {
		_object = node;
	}

	public K object() {
		return _object;
	}
}
