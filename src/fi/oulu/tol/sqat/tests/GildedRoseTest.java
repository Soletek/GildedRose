package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

// Example scenarios for testing
//   Item("+5 Dexterity Vest", 10, 20));
//   Item("Aged Brie", 2, 0));
//   Item("Elixir of the Mongoose", 5, 7));
//   Item("Sulfuras, Hand of Ragnaros", 0, 80));
//   Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
//   Item("Conjured Mana Cake", 3, 6));

	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_10_11() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(11, itemBrie.getQuality());
	}
    
	@Test
	public void testUpdateEndOfDay_AgedBrie_5Days() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 0) );
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(8, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_AgedBrie_5Days_MaximumQuality() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 1, 46) );
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(50, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_Sulfuras() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80) );
		
		// Act
		store.updateEndOfDay();
				
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(80, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_EmptyGildedRose() {
		// Arrange
		GildedRose store = new GildedRose();
		
		// Act
		store.updateEndOfDay();
				
		// Assert
		List<Item> items = store.getItems();
		assertEquals(0, items.size());
	}
	
	@Test
	public void testUpdateEndOfDay_ElixirOfMongoose() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Elixir of the Mongoose", 5, 7));
		
		// Act
		store.updateEndOfDay();
				
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(6, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_Vest() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("+5 Dexterity Vest", 10, 20) );
		
		// Act
		store.updateEndOfDay();
				
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(19, item.getQuality());
		assertEquals(9, item.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_Vest_3Days() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("+5 Dexterity Vest", 10, 20) );
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
				
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(17, item.getQuality());
		assertEquals(7, item.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_TwoVests() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("+5 Dexterity Vest", 10, 20) );
		store.addItem(new Item("+1 Dexterity Vest", 2, 4) );
		
		// Act
		store.updateEndOfDay();

		// Assert
		List<Item> items = store.getItems();
		Item vest1 = items.get(0);
		Item vest2 = items.get(1);
		assertEquals(19, vest1.getQuality());
		assertEquals(3, vest2.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_ConjuredManaCake() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Conjured Mana Cake", 3, 6));
		
		// Act
		store.updateEndOfDay();

		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(5, item.getQuality());
		assertEquals(2, item.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_ConjuredManaCake_2Days() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Conjured Mana Cake", 3, 6));
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(4, item.getQuality());
		assertEquals(1, item.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_ConjuredManaCake_3Days() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Conjured Mana Cake", 3, 6));
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();

		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(3, item.getQuality());
		assertEquals(0, item.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_ConjuredManaCake_4Days() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Conjured Mana Cake", 3, 6));
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();

		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(1, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_ConjuredManaCake_5Days() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Conjured Mana Cake", 3, 6));
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();

		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(0, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_BackstagePasses_15DaysLeft() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		
		// Act
		store.updateEndOfDay();

		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(21, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_BackstagePasses_8DaysLeft() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 8, 30));
		
		// Act
		store.updateEndOfDay();

		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(32, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_BackstagePasses_6DaysLeft() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 6, 35));
		
		// Act
		store.updateEndOfDay();

		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(37, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_BackstagePasses_5DaysLeft() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 35));
		
		// Act
		store.updateEndOfDay();

		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(38, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_BackstagePasses_4Days_After_3DaysLeft() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 3, 40));
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();

		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(0, item.getQuality());
	}
}
