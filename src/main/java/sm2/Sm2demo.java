package sm2;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.SM2;

import java.nio.charset.StandardCharsets;

/*
国密sm2算法验证

msg:hello-sse-sm2-msg
msgBase64:aGVsbG8tc3NlLXNtMi1tc2c=
//注意:椭圆曲线加密算法每次签名运算得出的结果都不一样
sign:MEUCIQCn5hiGmnJE5yFWj8zq2qnLBQQD2xKx2SjfiifvJ74jEwIgCjI4dVS6liMU0fhY6zXJ5VBNSSmD2uHI5xT2z1cyANs=
verifyResult:true
*/

public class Sm2demo {

    public static String pulickey = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEO+1C/z6iEnvWlao4sPDY3/A97d749JExBkmNv11hbOgNkZxWWq+DhPd82X1oUV/hPw9rmIXPs7QAcAz7uEAZFw==";
    public static String privateKey = "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQgt0q4ZrybdEHV+4MEqg2xylCsgXmGWOpNeLjAmk1hyMqgCgYIKoEcz1UBgi2hRANCAAQ77UL/PqISe9aVqjiw8Njf8D3t3vj0kTEGSY2/XWFs6A2RnFZar4OE93zZfWhRX+E/D2uYhc+ztABwDPu4QBkX";

    public static String message = "hello-sse-sm2-msg";


    public static void main(String[] args) {

        System.out.println("message：" + message);
        System.out.println("msgBase64：" + Base64.encode(message));
        System.out.println("sign：" + sign(message, privateKey));
        System.out.println("verifyResult：" + verify(message, sign(message, privateKey), pulickey));

    }

    /*
    message:网络获取的消息； privateKey：本地的私钥,已经被base64编码；
    sign: 返回值，要网络传输，所以还是要base64编码
    */
    public static String sign(String message, String privateKey) {
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] privateKeyBytes = Base64.decode(privateKey);

        //公钥签名
        SM2 sm2Singer = SmUtil.sm2(privateKeyBytes, null);
        byte[] sign = sm2Singer.sign(messageBytes);
        return Base64.encode(sign);
    }

    /*
    message:网络获取的消息，经过了base64编码； privateKey：本地的私钥，没有编码；
    sign: 返回值，要网络传输，所以还是要base64编码
   */
    public static Boolean verify(String message, String signature, String publicKey) {
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] signatureBytes = Base64.decode(signature);
        byte[] publicKeyBytes = Base64.decode(publicKey);

        //公钥验证
        SM2 sm2Verifier = SmUtil.sm2(null, publicKeyBytes);
        return sm2Verifier.verify(messageBytes, signatureBytes);
    }

}
