package com.whitepeoples.wooso.model.entity.EnumTypes;

public enum PlanType {
    BASIC(100),
    PREMIUM(14900);

    private final int price;

    PlanType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
