package cn.com.lgs.prototype_pattern.deep_clone;

import java.io.Serializable;

/**
 * 附件类
 *
 * @author 10545
 * @date 2022/3/24 23:08
 */
public class Attachment implements Serializable {
    private String name; //附件名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void download() {
        System.out.println("下载附件，文件名为：" + name);
    }
}
