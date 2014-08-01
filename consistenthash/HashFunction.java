package consistenthash;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFunction {

	public Integer hash(Object key) {
		// TODO Auto-generated method stub
		return (int) (hashToInteger(computeMd5(key), 0) % Integer.MAX_VALUE);
	}

	public byte[] computeMd5(Object k) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 not supported", e);
		}
		md5.reset();
		byte[] keyBytes = null;
		try {
			keyBytes = k.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Unknown string :" + k, e);
		}
		md5.update(keyBytes);
		return md5.digest();
	}
	
	public long hashToInteger(byte[] digest, int nTime) {
		long rv = ((long) (digest[3+nTime*4] & 0xFF) << 24)
		| ((long) (digest[2+nTime*4] & 0xFF) << 16)
		| ((long) (digest[1+nTime*4] & 0xFF) << 8)
		| (digest[0+nTime*4] & 0xFF);

		return rv & 0xffffffffL; /* Truncate to 32-bits */
	}
}
