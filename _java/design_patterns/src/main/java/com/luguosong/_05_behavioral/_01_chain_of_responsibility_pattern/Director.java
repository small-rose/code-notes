package com.luguosong._05_behavioral._01_chain_of_responsibility_pattern;

/**
 * 主任类，充当具体处理者
 * @author luguosong
 * @date 2022/6/6 14:08
 */
public class Director extends Approver {
    public Director(String name) {
        super(name);
    }

    /**
     * 具体请求处理方法
     * @param request
     */
    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < 50000) {
            System.out.println("主任" + this.name +
                    "审批采购单：" + request.getNumber() +
                    ",金额：" + request.getAmount() +
                    "元，采购目的：" + request.getPurpose());
            //处理请求
        } else {
            this.successor.processRequest(request);
        }
    }
}
