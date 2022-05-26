package com.luguosong._04_structural._02_bridge_pattern;

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
 * @date 2022/5/8 20:53
 */
public class XMLUtil {
    /**
     * 该方法用于从XML配置文件中提取具体类的类名，并返回一个实例对象
     *
     * @param args
     * @return
     */
    public static Object getBean(String args) {
        try {
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("_java/design_patterns/src/main/java/com/luguosong/_04_structural/_02_bridge_pattern/config.xml"));
            NodeList n1 = null;
            Node classNode = null;
            String cName = null;
            n1 = doc.getElementsByTagName("className");

            //获取第一个包含类名的结点，即扩充抽象类
            if (args.equals("image")) {
                classNode = n1.item(0).getFirstChild();
            }

            //获取第二个包含类名的结点，即具体实现类
            if (args.equals("os")) {
                classNode = n1.item(1).getFirstChild();
            }

            cName = classNode.getNodeValue();
            Class c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
