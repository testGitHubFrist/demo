package com.nilo.utils;


/**
 * 处理字节流工具
 * 
 * @author will
 * 
 */
public final class ByteUtils {

	/**
	 * 合并两个byte数组，产生新的byte 返回。
	 * @param byte_1 pre
	 * @param byte_2 last
	 * @return
	 */
	public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
		byte[] byte_3 = new byte[byte_1.length + byte_2.length];
		System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
		System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
		return byte_3;
	}
	
	public static byte[] objectToByte(Object a){
		return CarSerializer.serialize(a);
	}
	
	public static Object byteToObject(byte [] is) throws Exception {
		return CarSerializer.unserialize(is);
	}

	public static int byteArrayToInt(byte[] b) {
		return   b[3] & 0xFF |
				(b[2] & 0xFF) << 8 |
				(b[1] & 0xFF) << 16 |
				(b[0] & 0xFF) << 24;
	}
	public static byte[] intToByteArray(int a) {
		return new byte[] {
				(byte) ((a >> 24) & 0xFF),
				(byte) ((a >> 16) & 0xFF),
				(byte) ((a >> 8) & 0xFF),
				(byte) (a & 0xFF)
		};
	}

}
