package cn.genlei.ydms.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

public class PathUtilTest {

    @Test
    public void join() {
        String a = PathUtil.join("/","/abc");
        Assert.assertEquals("/abc",a);

        String b = PathUtil.join("","/abc");
        Assert.assertEquals("/abc",b);

        String c = PathUtil.join("/","abc");
        Assert.assertEquals("/abc",c);

    }

    @Test


    public void sha(){
        System.out.println(DigestUtils.sha1Hex("11111104bcbe1972f78a57e5801f3f6748cd27"));
    }
}