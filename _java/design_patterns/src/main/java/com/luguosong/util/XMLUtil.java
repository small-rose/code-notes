package com.luguosong.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 工具类
 *
 * @author 10545
 * @date 2022/5/2 21:03
 */
public class XMLUtil {
    /**
     * 从xml配置文件中提取具体类的类名，并返回一个实例对象
     *
     * @return
     */
    public static List<Object> getBean(String xmlPath) {
        try {
            //创建DOM文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File(xmlPath));

            //获取包含类名的文件节点
            NodeList n1 = doc.getElementsByTagName("className");
            List<Object> objects = new ArrayList<>();
            for (int i = 0; i < n1.getLength(); i++) {
                Node classNode = n1.item(i).getFirstChild();
                String cName = classNode.getNodeValue();

                //通过类名创建实例对象并返回
                Class c = Class.forName(cName);
                Object obj = c.newInstance();
                objects.add(obj);
            }
            return objects;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
