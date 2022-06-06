package com.luguosong._05_behavioral._01_chain_of_responsibility_pattern;

/**
 * 采购单类，充当请求类
 *
 * @author luguosong
 * @date 2022/6/6 13:39
 */
public class PurchaseRequest {
    private double amount; //采购金额
    private int number; //采购单编号
    private String purpose; //采购目的

    public PurchaseRequest(double amount, int number, String purpose) {
        this.amount = amount;
        this.number = number;
        this.purpose = purpose;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
