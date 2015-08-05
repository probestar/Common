/**
 *
 * Copyright (c) 2015
 * All rights reserved.
 *
 * @Title CinConsole.java
 * @Package com.allstar.cinutils
 * @author ProbeStar
 * @Email probestar@qq.com
 * @QQ 344137375
 * @date Jul 28, 2015 2:38:41 PM
 * @version V1.0
 * @Description 
 *
 */

package com.allstar.psutils;

import java.nio.ByteBuffer;

public class CinConsole {
	private static CinTracer _tracer = CinTracer.getInstance(CinConsole.class);

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
			_tracer.error("CinConsole.readLine error.", t);
		}
		return s;
	}
}
