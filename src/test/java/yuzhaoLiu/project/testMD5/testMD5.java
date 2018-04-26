package yuzhaoLiu.project.testMD5;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class testMD5 {

    @Test
    public void testDigestUtils(){
        String dataMD5 = DigestUtils.md5Hex("6667303");
        System.out.println(dataMD5);
    }

}
