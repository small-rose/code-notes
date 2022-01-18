package cn.com.lgs.asn1;

import org.bouncycastle.asn1.*;

import java.io.IOException;
import java.util.Date;

/**
 * 数据类型
 *
 * @author luguosong
 * @date 2022/1/4 9:48
 */
public class Types {
    public static void main(String[] args) throws IOException {

        System.out.println("====简单数据类型====");
        //BOOLEAN类型
        ASN1Boolean asn1Boolean = ASN1Boolean.TRUE;
        System.out.println("BOOLEAN类型:" + asn1Boolean);
        //ENUMERATED类型
        ASN1Enumerated asn1Enumerated = new ASN1Enumerated(1);
        System.out.println("ENUMERATED类型:" + asn1Enumerated.getValue());
        //INTEGER类型
        ASN1Integer asn1Integer = new ASN1Integer(1);
        System.out.println("INTEGER类型:" + asn1Integer);
        //NULL类型
        ASN1Null asn1Null1 = DERNull.INSTANCE;
        System.out.println("NULL类型:" + asn1Null1);
        //OBJECT IDENTIFIER类型
        ASN1ObjectIdentifier asn1ObjectIdentifier = new ASN1ObjectIdentifier("2.16.840.1.101.3.4.3.12");
        System.out.println("OBJECT IDENTIFIER类型:" + asn1ObjectIdentifier);
        //UTCTime类型
        ASN1UTCTime asn1UTCTime = new ASN1UTCTime(new Date());
        System.out.println("UTCTime类型:" + asn1UTCTime.getTime());
        //GeneralizedTime类型
        ASN1GeneralizedTime asn1GeneralizedTime = new DERGeneralizedTime(new Date());
        System.out.println("GeneralizedTime类型:" + asn1GeneralizedTime.getTime());


        System.out.println("====位字符串类型====");
        //BIG STRING类型
        ASN1BitString asn1BitString = new DERBitString("hello world 你好".getBytes());
        System.out.println("BIG STRING类型:" + asn1BitString);
        //OCTET STRING类型
        ASN1OctetString asn1OctetString = new DEROctetString("hello world 你好".getBytes());
        System.out.println("OCTET STRING类型:" + asn1OctetString);

        System.out.println("====字符串类型====");
        //BMPString类型
        ASN1BMPString asn1BMPString = new DERBMPString("hello world 你好");
        System.out.println("BMPString类型:" + asn1BMPString);
        //GeneralString类型
        ASN1GeneralString asn1GeneralString = new DERGeneralString("hello world 你好");
        System.out.println("GeneralString类型：" + asn1GeneralString);
        //GraphicString类型
        ASN1GraphicString asn1GraphicString = new DERGraphicString("hello world 你好".getBytes());
        System.out.println("GraphicString类型：" + asn1GraphicString.getString());
        //IA5String类型
        ASN1IA5String asn1IA5String = new DERIA5String("hello world 你好");
        System.out.println("IA5String类型：" + asn1IA5String);
        //NumericString类型
        ASN1NumericString asn1NumericString = new DERNumericString("hello world 你好");
        System.out.println("NumericString类型:" + asn1NumericString);
        //PrintableString类型
        ASN1PrintableString asn1PrintableString = new DERPrintableString("hello world 你好");
        System.out.println("PrintableString类型:" + asn1PrintableString);
        //TeletexString类型
        ASN1T61String asn1T61String = new DERT61String("hello world 你好");
        System.out.println("TeletexString类型：" + asn1T61String);
        //UniversalString类型
        ASN1UniversalString asn1UniversalString = new DERUniversalString("hello world 你好".getBytes());
        System.out.println("UniversalString类型:" + asn1UniversalString);
        //UTF8String类型
        ASN1UTF8String asn1UTF8String = new DERUTF8String("hello world 你好");
        System.out.println("UTF8String类型:" + asn1UTF8String);
        //VideotexString类型
        ASN1VideotexString asn1VideotexString = new DERVideotexString("hello world 你好".getBytes());
        System.out.println("VideotexString类型:" + asn1VideotexString.getString());
        //VisibleString类型
        ASN1VisibleString asn1VisibleString = new DERVisibleString("hello world 你好");
        System.out.println("VisibleString类型:"+asn1VisibleString);

    }
}
