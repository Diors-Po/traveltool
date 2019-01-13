package cn.edu.nju.traveltool.util;

import cn.edu.nju.traveltool.controller.vo.UserVO;
import com.google.gson.Gson;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-11 23:03
 **/
public class AESUtil {
    public static String ecodes(String content, String key) {
        if (content == null || content.length() < 1) {
            return null;
        }
        try {
            SecretKeySpec secretKeySpec = genSecretKeySpec(key);
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] byteRresult = cipher.doFinal(byteContent);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteRresult.length; i++) {
                String hex = Integer.toHexString(byteRresult[i] & 0xFF);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                sb.append(hex.toUpperCase());
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException |
                BadPaddingException |
                NoSuchPaddingException |
                InvalidKeyException |
                IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String dcodes(String content, String key) {
        if (content == null || content.length() < 1) {
            return null;
        }
        if (content.trim().length() < 19) {
            return content;
        }
        byte[] byteRresult = new byte[content.length() / 2];
        for (int i = 0; i < content.length() / 2; i++) {
            int high = Integer.parseInt(content.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(content.substring(i * 2 + 1, i * 2 + 2), 16);
            byteRresult[i] = (byte) (high * 16 + low);
        }
        try {
            SecretKeySpec secretKeySpec = genSecretKeySpec(key);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] result = cipher.doFinal(byteRresult);
            return new String(result);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static SecretKeySpec genSecretKeySpec(String key) throws NoSuchAlgorithmException, NoSuchPaddingException{
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key.getBytes());
        kgen.init(128, random);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        return new SecretKeySpec(enCodeFormat, "AES");

    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        UserVO userVO = new UserVO();
        userVO.setEmail("hello@mooctest.net");
        userVO.setUser("你好");
        String x = gson.toJson(userVO);
        System.out.println(ecodes(x,"happy-bird"));
        System.out.println(dcodes("E9AAF3339DA7C0D34A6053C08E3B3713753632EDD6590221DDBA0360E5D744BA9D0F037453947185502B38B7BE14E681","happy-bird"));
    }

}
