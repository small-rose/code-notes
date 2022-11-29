package com.luguosong.asn1;

import org.bouncycastle.asn1.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @author luguosong
 * @date 2022/11/1
 */
public class Asn1Test {

    /**
     * Object Identifiers（OID）
     * 对象标识符
     */
    @Test
    public void testASN1ObjectIdentifier(){
        ASN1ObjectIdentifier sm_scheme = new ASN1ObjectIdentifier("1.2.156.10197.1");
        //返回一个在当前分支下创建分支的 OID。返回新创建的分支的 OID
        ASN1ObjectIdentifier sm2sign_with_sm3 = sm_scheme.branch("501");
        System.out.println(sm2sign_with_sm3.getId());
    }

    /**
     * 布尔类型
     */
    @Test
    public void testASN1Boolean(){
        ASN1Boolean b = ASN1Boolean.getInstance(true);
        System.out.println(b.isTrue());
    }


    /**
     * 枚举类型
     */
    @Test
    public void testASN1Enumerated(){
        ASN1Enumerated enum1 = new ASN1Enumerated(10);
        System.out.println(enum1.getValue());
    }


    /**
     * 整型
     */
    @Test
    public void testASN1Integer(){
        ASN1Integer i = new ASN1Integer(100);
        System.out.println(i.getValue());
    }


    /**
     * 空
     */
    @Test
    public void testASN1Null(){
        ASN1Null aNull = DERNull.INSTANCE;
        System.out.println(aNull);
    }

    /**
     * UTC时间类型
     */
    @Test
    public void testUTCTime() throws ParseException {
        ASN1UTCTime time1 = new ASN1UTCTime(new Date());
        System.out.println(time1);
        System.out.println("getTime():"+time1.getTime());
        System.out.println("getDate():"+time1.getDate());
        DERUTCTime time2 = new DERUTCTime(new Date());
        System.out.println(time2);
        System.out.println("getTime():"+time2.getTime());
        System.out.println("getDate():"+time2.getDate());
    }


    /**
     * GeneralizedTime
     */
    @Test
    public void testGeneralizedTime() throws ParseException {
        ASN1GeneralizedTime time1 = new ASN1GeneralizedTime(new Date());
        System.out.println(time1.getTime());
        System.out.println(time1.getDate());
        DERGeneralizedTime time2 = new DERGeneralizedTime(new Date());
        System.out.println(time2.getTime());
        System.out.println(time2.getDate());
    }

    /**
     *
     */
    @Test
    public void testBitString() throws IOException {

    }
}
