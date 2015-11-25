package com.ideas.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ideas.model.Box;
import com.ideas.model.Combination;
import com.ideas.model.Item;
import com.ideas.service.PackageService;

public class PackageServiceImpl implements PackageService {

	@Override
	public Box createPackage(BigDecimal maxAllowedWeight, List<Item> items) {
		
		if(maxAllowedWeight.doubleValue() > 100) {
			throw new IllegalArgumentException("Maximum allowed weight for box is 100");
		}
		
		if(items == null || items.isEmpty()) {
			throw new IllegalArgumentException("Box cannot be empty. Please add some items");
		}
		
		if(items.size() > 15) {
			throw new IllegalArgumentException("Box cannot contain more than 15 items");
		}

		List<Combination> combinations = makeCombinations(maxAllowedWeight, items);
		Combination combination = getBestCombinationByCost(combinations);
		
		return new Box(maxAllowedWeight, combination.getItems());

	}

	private List<Combination> makeCombinations(BigDecimal maxAllowedWeight, List<Item> items) {

		List<Combination> combinations = new ArrayList<Combination>();

		for (Item currItem : items) {
			BigDecimal iw = currItem.getWeight();
			/**
			 * we do not want items with maxAllowedWeight
			 * greater than that allowed in package/box
			 */
			if (iw.compareTo(maxAllowedWeight) <= 0) {
				createItemCombinations(maxAllowedWeight, currItem, combinations);
			}
		}
		return combinations;
	}

	private void createItemCombinations(BigDecimal maxAllowedWeight, Item currItem,
										List<Combination> combinations) {
		int size = combinations.size();
		for (int i = 0; i < size; i++) {
			Combination previousCombination = combinations.get(i);
			BigDecimal cw = previousCombination.getWeight().add(currItem.getWeight());
			BigDecimal cc = previousCombination.getCost().add(currItem.getCost());
			if (cw.compareTo(maxAllowedWeight) <= 0) {
				List<Integer> pci = previousCombination.getItems();
				List<Integer> cci = new ArrayList<Integer>(pci);
				cci.add(currItem.getId());
				Combination newCombination = createCombination(cw, cc, cci);
				combinations.add(newCombination);
			}
		}
		Combination combination = createCombination(currItem.getWeight(),
				currItem.getCost(), Arrays.asList(currItem.getId()));
		combinations.add(combination);
	}

	private Combination getBestCombinationByCost(List<Combination> combinations) {
		BigDecimal bestCost = BigDecimal.ZERO;
		BigDecimal bestWeight = BigDecimal.ZERO;
		Combination bestCombination = new Combination();
		for (Combination combination : combinations) {
			BigDecimal combinationWeight = combination.getWeight();
			BigDecimal combinationPrice = combination.getCost();
			if (combinationPrice.compareTo(bestCost) > 0) {
				bestCost = combinationPrice;
				bestCombination = combination;
				bestWeight = combinationWeight;
			} else if (combinationPrice.compareTo(bestCost) == 0) {
				if (combinationWeight.compareTo(bestWeight) < 0) {
					bestCost = combinationPrice;
					bestCombination = combination;
					bestWeight = combinationWeight;
				}
			}
		}
		return bestCombination;
	}

	private Combination createCombination(BigDecimal weight, BigDecimal cost, List<Integer> items) {
		return new Combination(weight, cost, items);
	}
}
