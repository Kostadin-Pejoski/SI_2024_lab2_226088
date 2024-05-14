import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {
    @Test
    public void nullCartTest(){
        List<Item> items = null;
        int price = 0;
        RuntimeException ex = assertThrows(RuntimeException.class,()->SILab2.checkCart(items,price));
        assertEquals("allItems list can't be null!",ex.getMessage());
    }
    @Test
    public void noBarCodeTest(){
        List<Item> items = new ArrayList<>();
        Item i1 = new Item("smoki","1234",5,0.5f);
        Item i2 = new Item("kafe","12345",51,0.52f);
        Item i3 = new Item("cigari",null,5,0.53f);
        Item i4 = new Item("vodka","1234123",5,0.56f);
        items.add(i1);
        items.add(i2);
        items.add(i3);
        items.add(i4);
        RuntimeException ex = assertThrows(RuntimeException.class,()->SILab2.checkCart(items,64));
        assertEquals("No barcode!",ex.getMessage());
    }
    @Test
    public void invalidCharInBarCodeTest(){
        List<Item> items = new ArrayList<>();
        Item i1 = new Item("smoki","1234",5,0.5f);
        Item i2 = new Item("kafe","12345",51,0.52f);
        Item i3 = new Item("cigari","123#$%^&&g",5,0.53f);
        Item i4 = new Item("vodka","1234123",5,0.56f);
        items.add(i1);
        items.add(i2);
        items.add(i3);
        items.add(i4);
        RuntimeException ex = assertThrows(RuntimeException.class,()->SILab2.checkCart(items,64));
        assertEquals("Invalid character in item barcode!",ex.getMessage());
    }
    @Test
    public void ValidPaymentNoDiscountTest(){
        List<Item> items = new ArrayList<>();
        Item i1 = new Item("smoki","1234",15,0);
        Item i2 = new Item("kafe","12345",50,0);
        Item i3 = new Item("cigari","123",150,0);
        Item i4 = new Item("vodka","1234123",345,0);
        items.add(i1);
        items.add(i2);
        items.add(i3);
        items.add(i4);
        assertTrue(SILab2.checkCart(items,560));
    }

    @Test
    public void InValidPaymentNoDiscountTest(){
        List<Item> items = new ArrayList<>();
        Item i1 = new Item("smoki","1234",15,0);
        Item i2 = new Item("kafe","12345",50,0);
        Item i3 = new Item("cigari","123",150,0);
        Item i4 = new Item("vodka","1234123",345,0);
        items.add(i1);
        items.add(i2);
        items.add(i3);
        items.add(i4);
        assertFalse(SILab2.checkCart(items,100));
    }
    @Test
    public void testUnknownItem(){
        List<Item> items = new ArrayList<>();
        Item i1 = new Item(null,"1234",15,0);
        Item i2 = new Item("kafe","12345",50,0);
        Item i3 = new Item("","12345",50,0);
        items.add(i1);
        items.add(i2);
        items.add(i3);
        SILab2.checkCart(items,50);
        assertEquals("unknown",items.get(0).getName());
        assertEquals("unknown",items.get(2).getName());
    }

    @Test
    public void ValidPaymentWithDiscountTest(){
        List<Item> items = new ArrayList<>();
        Item i1 = new Item("smoki","1234",100,0.2f);
        Item i2 = new Item("kafe","12345",100,0.3f);
        Item i3 = new Item("cigari","123",100,0.4f);
        Item i4 = new Item("vodka","1234123",100,0.55f);
        items.add(i1);
        items.add(i2);
        items.add(i3);
        items.add(i4);
        assertTrue(SILab2.checkCart(items,145));
    }
    @Test
    public void InValidPaymentWithDiscountTest(){
        List<Item> items = new ArrayList<>();
        Item i1 = new Item("smoki","1234",100,0.2f);
        Item i2 = new Item("kafe","12345",100,0.3f);
        Item i3 = new Item("cigari","123",100,0.4f);
        Item i4 = new Item("vodka","1234123",100,0.55f);
        items.add(i1);
        items.add(i2);
        items.add(i3);
        items.add(i4);
        assertFalse(SILab2.checkCart(items,144));
    }

    @Test
    public void priceLessThan300WithDscountAndBarCode(){
        List<Item> items = new ArrayList<>();
        Item i1 = new Item("item","023",100,0.4f);
        items.add(i1);
        assertFalse(SILab2.checkCart(items,10));
    }
    @Test
    public void priceMoreThan300WithOutDscountAndBarCode(){
        List<Item> items = new ArrayList<>();
        Item i1 = new Item("item","023",350,0);
        items.add(i1);
        assertFalse(SILab2.checkCart(items,320));
    }

    @Test
    public void priceMoreThan300WithDscountWithoutBarCode(){
        List<Item> items = new ArrayList<>();
        Item i1 = new Item("item","123",1000,0.25f);
        items.add(i1);
        assertFalse(SILab2.checkCart(items,220));
    }
    @Test
    public void priceMoreThan300WithDscountWithBarCode(){
        List<Item> items = new ArrayList<>();
        Item i1 = new Item("item","0123",1000,0.25f);
        items.add(i1);
        assertTrue(SILab2.checkCart(items,220));
    }

}