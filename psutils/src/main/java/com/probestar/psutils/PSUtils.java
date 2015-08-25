/**
 *
 * Copyright (c) 2015
 * All rights reserved.
 *
 * @Title PSUtils.java
 * @Package com.probestar.psutils
 * @author WangYanwei
 * @Email wangyanwei@feinno.com
 * @QQ 344137375
 * @date 2015年6月24日 下午6:07:44
 * @version V1.0
 * @Description 
 *
 */

package com.probestar.psutils;

import java.nio.ByteBuffer;
import java.util.UUID;

public class PSUtils {
	public static ByteBuffer getUUIDBuffer() {
		ByteBuffer buffer = ByteBuffer.allocate(16);
		UUID uuid = UUID.randomUUID();
		buffer.putLong(uuid.getLeastSignificantBits());
		buffer.putLong(uuid.getMostSignificantBits());
		return buffer;
	}
	
	public static byte[] getUUIDArray() {
		return getUUIDBuffer().array();
	}
}
