/**
 *
 * Copyright (c) 2015
 * All rights reserved.
 *
 * @Title ResponsibilityChainHandler.java
 * @Package com.allstar.cinutils.designpattern.chain
 * @author WangYanwei
 * @Email wangyanwei@feinno.com
 * @QQ 344137375
 * @date 2015年6月11日 上午11:01:39
 * @version V1.0
 * @Description 
 *
 */

package com.allstar.psutils.designpattern.chain;

public class ResponsibilityChainHandler {

	public String getName() {
		return ResponsibilityChainHandler.class.getName();
	}

	public void handle(ResponsibilityChainContext ctx, Object msg) {
		handleNext(ctx, msg);
	}

	public void handleNext(ResponsibilityChainContext ctx, Object msg) {
		ResponsibilityChainHandler next = ctx.getChain().getNextHandler(this);
		if (next != null)
			next.handle(ctx, msg);
	}

	@Override
	public String toString() {
		return getName();
	}
}