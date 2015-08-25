/**
 *
 * Copyright (c) 2015
 * All rights reserved.
 *
 * @Title PSConsole.java
 * @Package com.probestar.psutils
 * @author ProbeStar
 * @Email probestar@qq.com
 * @QQ 344137375
 * @date Jul 28, 2015 2:38:41 PM
 * @version V1.0
 * @Description 
 *
 */

package com.probestar.psutils;

import java.nio.ByteBuffer;

public class PSConsole {
	private static PSTracer _tracer = PSTracer.getInstance(PSConsole.class);

	public static String readLine() {
		String s = null;
		try {
			ByteBuffer buf = ByteBuffer.allocate(1024 * 1024);
			byte b;
			do {
				b = (byte) System.in.read();
				buf.put(b);
			} while (b != 10);
			s = new String(buf.array(), 0, buf.position() - 1);
		} catch (Throwable t) {
			_tracer.error("PSConsole.readLine error.", t);
		}
		return s;
	}
}
