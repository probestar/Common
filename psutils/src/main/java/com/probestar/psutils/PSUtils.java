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

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;

public class PSUtils {
	private static PSTracer _tracer = PSTracer.getInstance(PSUtils.class);

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

	public static HashMap<String, String> loadProperties(String fileName) {
		HashMap<String, String> map = new HashMap<>();
		try {
			Properties p = new Properties();
			p.load(new FileInputStream(fileName));
			for (Object obj : p.keySet()) {
				String key = obj.toString();
				String value = p.getProperty(key);
				map.put(key, value);
			}
		} catch (Throwable t) {
			_tracer.error("PSUtils.loadProperties error.", t);
		}
		return map;
	}
}
