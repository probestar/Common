/**
 *
 * Copyright (c) 2015
 * All rights reserved.
 *
 * @Title ResponsibilityChainContext.java
 * @Package com.probestar.psutils.designpattern.chain
 * @author WangYanwei
 * @Email wangyanwei@feinno.com
 * @QQ 344137375
 * @date 2015年6月11日 上午11:07:08
 * @version V1.0
 * @Description 
 *
 */

package com.probestar.psutils.designpattern.chain;

public class ResponsibilityChainContext {
	private ResponsibilityChain _chain;

	public void setChain(ResponsibilityChain chain) {
		_chain = chain;
	}

	public ResponsibilityChain getChain() {
		return _chain;
	}
}
