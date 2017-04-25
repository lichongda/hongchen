package com.hongchen.util.redis;

import java.io.*;

/**
 * @author lu.jianhao
 * 将相关的对象进行序列化
 */
public class SerializeUtils {
	public static byte[] serialize(Object o) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream outo = null;
		try {
			outo = new ObjectOutputStream(out);
			outo.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				outo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return out.toByteArray();
	}

	public static Object deserialize(byte[] b) {
		ObjectInputStream oin = null;
		try {
			
			if(b == null){
				return null;
			}
			oin = new ObjectInputStream(new ByteArrayInputStream(b));
			try {
				return oin.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				if(oin!=null){
					oin.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}