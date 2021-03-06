package com.ideas.model;

import java.math.BigDecimal;
import java.util.List;

public class Box {

	private BigDecimal allowedWeight = BigDecimal.ZERO;
	private List<Integer> items;

	public Box() {
	}

	public Box(BigDecimal allowedWeight, List<Integer> items) {
		this.allowedWeight = allowedWeight;
		this.items = items;
	}
	
	public BigDecimal getAllowedWeight() {
		return allowedWeight;
	}

	public List<Integer> getItems() {
		return items;
	}
}
