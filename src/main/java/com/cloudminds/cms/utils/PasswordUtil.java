package com.cloudminds.cms.utils;

import com.cloudminds.cms.constant.ConstantBean;
import org.springframework.util.Assert;

/**
 * @Author: zhouchong
 * Created by 76409 on 13:49 2018/12/7.
 * @Description:
 */
public class PasswordUtil {
	// hash算法循环次数
	public static final int HASH_ITERATIONS = 1024;

	/**
	 * 密码hash
	 *
	 * @param plainText
	 * @param salt
	 * @return
	 */
	public static String digest(String plainText, String salt) {
		Assert.notNull(plainText,"digest can't be null");

		byte[] hashPasswords = Digests.sha1(plainText.getBytes(), salt.getBytes(), HASH_ITERATIONS);
		return Encodes.encodeHex(hashPasswords);
	}/**
	 * 密码hash
	 *
	 * @param plainText
	 * @return
	 */
	public static String digest(String plainText) {

		return digest(plainText,ConstantBean.PASSWORD_SALT);
	}

	public static void main(String[] args) {
		System.out.println("args = [" + PasswordUtil.digest("123456", ConstantBean.PASSWORD_SALT) + "]");
	}
}
