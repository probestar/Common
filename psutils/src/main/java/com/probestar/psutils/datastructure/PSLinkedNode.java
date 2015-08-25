package com.probestar.psutils.datastructure;


public class PSLinkedNode<K> {

	/**
	 * 
	 */
	PSLinkedNode<K> previous;
	PSLinkedNode<K> next;
	PSLinkedList<K> list;
	private K _object;

	public PSLinkedNode(K node) {
		_object = node;
	}

	public K object() {
		return _object;
	}
}
