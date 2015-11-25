package com.ideas.model;

import java.math.BigDecimal;

public class Item {

	private int id;
	private BigDecimal weight = BigDecimal.ZERO;
	private BigDecimal cost = BigDecimal.ZERO;

	public Item(int id, BigDecimal weight, BigDecimal cost) {
		this.id = id;
		this.weight = weight;
		this.cost = cost;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Item{");
		sb.append("id=").append(id);
		sb.append(", weight=").append(weight);
		sb.append(", cost=").append(cost);
		sb.append('}');
		return sb.toString();
	}

}
