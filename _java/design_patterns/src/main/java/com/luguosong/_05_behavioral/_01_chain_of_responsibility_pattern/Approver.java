package com.luguosong._05_behavioral._01_chain_of_responsibility_pattern;

/**
 * 审批者类，充当抽象处理者
 *
 * @author luguosong
 * @date 2022/6/6 13:46
 */
public abstract class Approver {
    protected Approver successor;
    protected String name;

    public Approver(String name) {
        this.name = name;
    }

    public void setSuccessor(Approver successor) {
        this.successor = successor;
    }

    //抽象请求处理方法
    public abstract void processRequest(PurchaseRequest request);
}
