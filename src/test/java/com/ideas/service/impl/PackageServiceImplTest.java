package com.ideas.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.ideas.Constants;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ideas.ItemsHelper;
import com.ideas.model.Box;
import com.ideas.model.Item;
import com.ideas.service.PackageService;

//TODO consider using data provider for test case
public class PackageServiceImplTest {
	
	@Rule
    public ExpectedException thrown= ExpectedException.none();

	/**
	 * in a spring based project inject this
	 */
	private static PackageService packageService;

	@BeforeClass
	public static void beforeClass() throws Exception{
		packageService = new PackageServiceImpl();
	}
	
	@AfterClass
	public static void afterClass() throws Exception{
		packageService = null;
	}

	@Test
	public void testCreatePackageWithNoItems() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Box cannot be empty. Please add some items");
		Box box = packageService.createPackage(BigDecimal.TEN, null);
	}
	
	@Test
	public void testCreatePackageWithWeightMoreThan100() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Maximum allowed weight for box is 100");
		Box box = packageService.createPackage(BigDecimal.valueOf(101), ItemsHelper.createItems("(1,1,$1)"));
	}
	
	@Test
	public void testCreateItemWithWeightGreaterThan100() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Maximum allowed weight for item is 100");
		List<Item> items = ItemsHelper.createItems("(1,101,$1)");
	}
	
	@Test
	public void testCreateItemWithPriceGreaterThan100() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Maximum allowed price for item is 100");
		List<Item> items = ItemsHelper.createItems("(1,10,$101)");
	}

	@Test
	public void testCreatePackageWithMoreThan15Items() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Box cannot contain more than 15 items");
		List<Item> items = ItemsHelper.createItems("(1,90.72,$13) (2,33.80,$40) (3,43.15,$10) " +
				"(4,37.97,$16) (5,46.81,$36) (6,48.77,$79) (7,81.80,$45) (8,19.36,$79) (9,6.76,$64) " +
				"(10,10,$100) (11,10,$100) (12,10,$100) (13,10,$100) (14,10,$100) (15,10,$100) (16,10,$100)");
		Box pack = packageService.createPackage(BigDecimal.TEN, items);
	}

	@Test
	public void testCreatePackage1() throws Exception {
		List<Item> items = ItemsHelper.createItems("(1,53.38,$45) (2,88.62,$98) (3,78.48,$3) (4,72.30,$76) (5,30.18,$9) (6,46.34,$48)");
		List<Integer> expectedItems = Arrays.asList(4);
		Box pack = packageService.createPackage(BigDecimal.valueOf(81), items);
		assertThat(pack.getItems()).isNotEmpty();
		assertThat(pack.getItems()).containsAll(expectedItems);
		prettyPrint(1, pack.getItems());
	}	
	
	@Test
	public void testCreatePackage2() throws Exception {
		List<Item> items = ItemsHelper.createItems("(1,15.3,$34)");
		Box pack = packageService.createPackage(BigDecimal.valueOf(8), items);
		assertThat(pack.getItems()).isEmpty();
		prettyPrint(2, pack.getItems());
	}	
	
	@Test
	public void testCreatePackage3() throws Exception {
		List<Item> items = ItemsHelper.createItems("(1,85.31,$29) (2,14.55,$74) (3,3.98,$16) (4,26.24,$55) (5,63.69,$52) (6,76.25,$75) (7,60.02,$74) (8,93.18,$35) (9,89.95,$78)");
		List<Integer> expectedItems = Arrays.asList(2,7);
		Box pack = packageService.createPackage(BigDecimal.valueOf(75), items);
		assertThat(pack.getItems()).isNotEmpty();
		assertThat(pack.getItems()).containsAll(expectedItems);
		prettyPrint(3, pack.getItems());
	}	
	
	@Test
	public void testCreatePackage4() throws Exception {
		List<Item> items = ItemsHelper.createItems("(1,90.72,$13) (2,33.80,$40) (3,43.15,$10) (4,37.97,$16) (5,46.81,$36) (6,48.77,$79) (7,81.80,$45) (8,19.36,$79) (9,6.76,$64)");
		List<Integer> expectedItems = Arrays.asList(8,9);
		Box pack = packageService.createPackage(BigDecimal.valueOf(56), items);
		assertThat(pack.getItems()).isNotEmpty();
		assertThat(pack.getItems()).containsAll(expectedItems);
		prettyPrint(4, pack.getItems());
	}

	private void prettyPrint(int boxNumber, List<Integer> boxItems) {
		String header1 = "Box No.";
		String header2 =  "Items Chosen";
		System.out.println(header1 + "\t\t" + header2);
		System.out.println(boxNumber + "\t\t\t" + convertListToCommaSeparatedString(boxItems));
	}

	private String convertListToCommaSeparatedString(List<Integer> items) {
		int size = items.size();
		if(size == 0) {
			return Constants.HYPHEN.getValue();
		}
		//The string builder used to construct the string
		StringBuilder commaSepValueBuilder = new StringBuilder();
		//Looping through the list
		for ( int i = 0; i< size; i++){
			//append the value into the builder
			commaSepValueBuilder.append(items.get(i));

			//if the value is not the last element of the list
			//then append the comma(,) as well
			if ( i != size-1){
				commaSepValueBuilder.append(Constants.COMMA.getValue() + Constants.SPACE.getValue());
			}
		}
		return commaSepValueBuilder.toString();
	}

} 
