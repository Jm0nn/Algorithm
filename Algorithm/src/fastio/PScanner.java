package fastio;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PScanner {
	private final InputStreamReader in;
	private final char[] buf;
	private int len, ptr;

	public PScanner(InputStream input) {
		in = new InputStreamReader(input);
		buf = new char[8192];
	}

	public boolean hasNext() {
		consume();
		return ptr < len && buf[ptr] > ' ';
	}

	public String next() {
		consume();
		char[] cbuf = new char[16];
		char clen = 0;
		while ((cbuf[clen++] = read()) > ' ') {
			if (clen == cbuf.length)
				cbuf = Arrays.copyOf(cbuf, clen << 2);
		}
		return new String(cbuf, 0, clen - 1);
	}

	public int nextInt() {
		consume();
		int v = 0;
		char c = read();
		boolean neg = c == '-';
		if (neg)
			c = read();
		do {
			v = v * 10 + c - '0';
		} while ('0' <= (c = read()) && c <= '9');
		return neg ? -v : v;
	}

	public long nextLong() {
		consume();
		long v = 0;
		char c = read();
		boolean neg = c == '-';
		if (neg)
			c = read();
		do {
			v = v * 10 + c - '0';
		} while ('0' <= (c = read()) && c <= '9');
		return neg ? -v : v;
	}

	private char read() {
		if (ptr == len)
			fill();
		return ptr < len ? buf[ptr++] : 0;
	}

	private void fill() {
		try {
			len = in.read(buf);
			ptr = 0;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private void consume() {
		char c;
		while ((c = read()) <= ' ' && c != 0)
			;
		ptr--;
	}
}
