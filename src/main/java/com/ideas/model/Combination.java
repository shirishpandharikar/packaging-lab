package com.ideas.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Combination {

    private BigDecimal weight = BigDecimal.ZERO;
    private BigDecimal cost = BigDecimal.ZERO;
    private List<Integer> items = new ArrayList<Integer>();

    public Combination(BigDecimal weight, BigDecimal cost, List<Integer> items) {
        this.weight = weight;
        this.cost = cost;
        this.items = items;
    }

    public Combination() {
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public List<Integer> getItems() {
        return items;
    }

    public void setItems(List<Integer> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Combination{" +
                "weight=" + weight +
                ", cost=" + cost +
                ", items=" + items +
                '}';
    }
}
