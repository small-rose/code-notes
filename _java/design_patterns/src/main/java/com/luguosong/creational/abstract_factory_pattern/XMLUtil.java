package com.luguosong.creational.abstract_factory_pattern;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * 从xml配置文件中提取具体工厂类的类名，并返回一个实例对象
 *
 * @author 10545
 * @date 2022/2/28 21:47
 */
public class XMLUtil {
    public static Object getBean() {
        try {
            //创建DOM对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc = builder.parse("_java/design_patterns/src/main/java/com/luguosong/creational/abstract_factory_pattern/config.xml");

            //获取包含类名的文本节点
            NodeList nl = doc.getElementsByTagName("className");
            Node classNode = nl.item(0).getFirstChild();
            String cName = classNode.getNodeValue();

            //通过类名生成实例对象并将其返回
            Class<?> c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
