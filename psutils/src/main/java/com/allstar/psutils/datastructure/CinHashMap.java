package com.allstar.psutils.datastructure;

import java.util.HashMap;
import java.util.Set;

public class CinHashMap<K, V> {
	private CinLinkedList<V> _list;
	private HashMap<K, CinLinkedNode<V>> _map;

	public CinHashMap() {
		_list = new CinLinkedList<V>();
		_map = new HashMap<K, CinLinkedNode<V>>();
	}

	public Set<K> KeySet() {
		return _map.keySet();
	}

	public synchronized void add(K key, V value) {
		_map.put(key, _list.put(value));
	}

	public synchronized CinLinkedNode<V> remove(K key) {
		CinLinkedNode<V> v = _map.remove(key);
		_list.remove(v);
		return v;
	}

	public synchronized V get(K key) {
		CinLinkedNode<V> node = _map.get(key);
		if (node != null && node.object() != null)
			return node.object();
		else
			return null;
	}

	public synchronized V get(K key, V defaultValue) {
		CinLinkedNode<V> node = _map.get(key);
		if (node != null && node.object() != null)
			return node.object();
		else
			return defaultValue;
	}

	public synchronized V takeAway(K key) {
		CinLinkedNode<V> node = _map.get(key);
		this.remove(key);
		if (node != null && node.object() != null)
			return node.object();
		else
			return null;
	}

	public void linkedListMoveToHead() {
		_list.moveToHead();
	}

	public V linkedListGet() {
		CinLinkedNode<V> node = _list.get();
		if (node != null && node.object() != null)
			return node.object();
		else
			return null;
	}

	public long length() {
		return _list.length();
	}
}
