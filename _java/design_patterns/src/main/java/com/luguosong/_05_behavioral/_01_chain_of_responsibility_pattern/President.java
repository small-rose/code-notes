package com.luguosong._05_behavioral._01_chain_of_responsibility_pattern;

/**
 * 董事长类，充当具体处理者
 * @author luguosong
 * @date 2022/6/6 14:26
 */
public class President extends Approver{
    public President(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < 500000) {
            System.out.println("董事长" + this.name +
                    "审批采购单：" + request.getNumber() +
                    ",金额：" + request.getAmount() +
                    "元，采购目的：" + request.getPurpose());
            //处理请求
        } else {
            this.successor.processRequest(request);
        }
    }
}
