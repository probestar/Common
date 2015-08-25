/**
 *
 * Copyright (c) 2015
 * All rights reserved.
 *
 * @Title ResponsibilityChain.java
 * @Package com.probestar.psutils.designpattern.chain
 * @author WangYanwei
 * @Email wangyanwei@feinno.com
 * @QQ 344137375
 * @date 2015年6月11日 上午11:00:17
 * @version V1.0
 * @Description 
 *
 */

package com.probestar.psutils.designpattern.chain;

import java.util.Iterator;

import com.probestar.psutils.datastructure.PSDoublyLinkedList;

public class ResponsibilityChain {

	private PSDoublyLinkedList<ResponsibilityChainHandler> _handlers;
	private ResponsibilityChainContext _context;

	public ResponsibilityChain() {
		this(new ResponsibilityChainContext());
	}

	public ResponsibilityChain(ResponsibilityChainContext context) {
		_handlers = new PSDoublyLinkedList<ResponsibilityChainHandler>();
		_context = context;
		_context.setChain(this);
	}

	public ResponsibilityChainHandler getPrevHandler(ResponsibilityChainHandler handler) {
		ResponsibilityChainHandler prev = null;
		Iterator<ResponsibilityChainHandler> it = _handlers.iterator();
		while (it.hasNext()) {
			ResponsibilityChainHandler next = it.next();
			if (next == handler)
				return prev;
			prev = next;
		}
		return null;
	}

	public ResponsibilityChainHandler getNextHandler(ResponsibilityChainHandler handler) {
		Iterator<ResponsibilityChainHandler> it = _handlers.iterator();
		while (it.hasNext()) {
			if (it.next() == handler)
				return it.next();
		}
		return null;
	}

	public void addFirst(ResponsibilityChainHandler handler) {
		_handlers.addFirst(handler);
	}

	public void addLast(ResponsibilityChainHandler handler) {
		_handlers.addLast(handler);
	}

	public void fire() {
		_handlers.getFirst().handle(_context, null);
	}

	@Override
	public String toString() {
		Iterator<ResponsibilityChainHandler> it = _handlers.iterator();
		StringBuilder sb = new StringBuilder();
		while (it.hasNext()) {
			sb.append(it.next().getName());
			sb.append("\r\n");
		}
		return sb.toString();
	}
}
