package daoo.encoder;

import com.sun.istack.internal.NotNull;
import daoo.ioc.MessageEncoder;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created with IntelliJ IDEA.
 * User: keevstessens
 * Date: 17/05/13
 * Time: 17:04
 * To change this template use File | Settings | File Templates.
 */
public class CypherEncoder implements MessageEncoder {

    public static final SecretKeySpec SECRET_KEY = generateKey();

    @Override
    public String encode(@NotNull String message) {
        try{
            Cipher aes = Cipher.getInstance("AES");
            aes.init(Cipher.ENCRYPT_MODE, SECRET_KEY);
            byte[] ciphertext = aes.doFinal(message.getBytes());
            return ciphertext.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String decode(@NotNull String message) {
        try{
            Cipher aes = Cipher.getInstance("AES");
            aes.init(Cipher.DECRYPT_MODE, SECRET_KEY);
            byte[] byteMessage = message.getBytes();
            return new String(aes.doFinal(byteMessage));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    private static SecretKeySpec generateKey()  {
        String passphrase = "correct horse battery staple";
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(passphrase.getBytes());
        SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
        return key;

    }

    public static void main(String[] args) {
        CypherEncoder c=new CypherEncoder();
        System.out.println(c.decode(c.encode("pizza party ")));

    }



}
