package cn.com.lgs.prototype_pattern.shallow_clone;

/**
 * 工作周报类，充当原型角色
 * <p>
 * 实现Cloneable接口表示这个类可以克隆
 *
 * @author 10545
 * @date 2022/3/24 21:59
 */
public class WeeklyLog implements Cloneable {
    private Attachment attachment;
    private String name;
    private String date;
    private String content;

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected WeeklyLog clone() {
        try {
            return (WeeklyLog) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("不支持复制");
            return null;
        }
    }
}
