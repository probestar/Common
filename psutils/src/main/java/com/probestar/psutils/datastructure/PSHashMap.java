package com.probestar.psutils.datastructure;

import java.util.HashMap;
import java.util.Set;

public class PSHashMap<K, V> {
	private PSLinkedList<V> _list;
	private HashMap<K, PSLinkedNode<V>> _map;

	public PSHashMap() {
		_list = new PSLinkedList<V>();
		_map = new HashMap<K, PSLinkedNode<V>>();
	}

	public Set<K> KeySet() {
		return _map.keySet();
	}

	public synchronized void add(K key, V value) {
		_map.put(key, _list.put(value));
	}

	public synchronized PSLinkedNode<V> remove(K key) {
		PSLinkedNode<V> v = _map.remove(key);
		_list.remove(v);
		return v;
	}

	public synchronized V get(K key) {
		PSLinkedNode<V> node = _map.get(key);
		if (node != null && node.object() != null)
			return node.object();
		else
			return null;
	}

	public synchronized V get(K key, V defaultValue) {
		PSLinkedNode<V> node = _map.get(key);
		if (node != null && node.object() != null)
			return node.object();
		else
			return defaultValue;
	}

	public synchronized V takeAway(K key) {
		PSLinkedNode<V> node = _map.get(key);
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
		PSLinkedNode<V> node = _list.get();
		if (node != null && node.object() != null)
			return node.object();
		else
			return null;
	}

	public long length() {
		return _list.length();
	}
}
