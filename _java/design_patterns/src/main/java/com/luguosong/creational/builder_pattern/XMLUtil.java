package com.luguosong.creational.builder_pattern;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * 工具类，通过配置文件创建具体构造器
 *
 * @author luguosong
 * @date 2022/3/15 13:54
 */
public class XMLUtil {
    public static Object getBean() {
        try {
            //创建DOM文件对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("_java/design_patterns/src/main/java/com/luguosong/creational/builder_pattern/config.xml"));

            //获取包含类名的文本节点
            NodeList n1 = doc.getElementsByTagName("className");
            Node classNode = n1.item(0).getFirstChild();
            String cName = classNode.getNodeValue();


            Class<?> c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
