package com.zking.zkingedu.common.utils;

import java.security.MessageDigest;

public class MD5Util2 {
    /**
     * Title: MD5加密 生成32位md5码
     * Description: TestDemo
     *
     * @param inStr
     * @return 返回32位md5码
     * @throws Exception
     * @author lu
     * @date 2016年6月23日 下午2:36:07
     */
    public static String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * Title: MD5加密
     * Description: TestDemo
     *
     * @param inStr
     * @return
     * @author lu
     * @date 2016年6月23日 下午2:43:31
     */
    public static String md5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * Title: 加密解密算法 执行一次加密，两次解密
     * Description: TestDemo
     *
     * @param inStr
     * @return
     * @author lu
     * @date 2016年6月23日 下午2:37:29
     */
    public static String convertMD5(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

    public static String md5Decode(String str) {
        return convertMD5(convertMD5(str));
    }

    public static void main(String[] args) {
        String s = new String("13917114404");
//        System.out.println(md5Decode("a6aeb3ffa55fc7d664406af9c3bd0f1b"));
//        System.out.println("原始：" + s);
//        System.out.println("MD5后：" + md5("order_no=1319886901&subject=????&pay_type=43&money=10.00&app_id=10835&extra=&c40da4c40a14406eb3a0e6fb51c18d7c"));
//        System.out.println("加密的：" + convertMD5(s));
//        System.out.println("解密的：" + convertMD5(convertMD5(s)));
//        System.out.println(md5("13917114404"));
    }
}