package com.nttuyen.android.base.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author nttuyen266@gmail.com
 */
public class BinaryResponse extends Response<byte[]> {
	private byte[] result = new byte[0];
	@Override
	public void parse(InputStream input) {
		try {
			byte[] b = new byte[1024];
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			int nRead = -1;
			while ((nRead = input.read(b, 0, b.length)) != -1) {
				buffer.write(b, 0, nRead);
			}
			this.result = buffer.toByteArray();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public byte[] getResult() {
		return this.result;
	}
}
