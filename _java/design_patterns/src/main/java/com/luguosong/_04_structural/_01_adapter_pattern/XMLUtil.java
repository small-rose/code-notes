package com.luguosong._04_structural._01_adapter_pattern;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

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
    public static Object getBean() {
        try {
            //创建DOM文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("_java/design_patterns/src/main/java/com/luguosong/_04_structural/_01_adapter_pattern/config.xml"));

            //获取包含类名的文件节点
            NodeList n1 = doc.getElementsByTagName("className");
            Node classNode = n1.item(0).getFirstChild();
            String cName = classNode.getNodeValue();

            //通过类名创建实例对象并返回
            Class c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
