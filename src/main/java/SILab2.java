import java.util.List;

class Item {
    String name;
    String barcode; //numerical
    int price;
    float discount;

    Item(String name, String code, int price, float discount) {
        this.name = name;
        this.barcode = code;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getPrice() {
        return price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBarcode(String code) {
        this.barcode = code;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}


public class SILab2 {
    public static boolean checkCart(List<Item> allItems, int payment) {
        //1
        if (allItems == null) {//2
            throw new RuntimeException("allItems list can't be null!");//3
        }
        //4
        float sum = 0;
        //5
        for (int i = 0; i < allItems.size(); i++) {
            //6
            Item item = allItems.get(i);
            if (item.getName() == null || item.getName().length() == 0) { //7
                item.setName("unknown"); //8
            }
            //9
            if (item.getBarcode() != null) { //10
                String allowed = "0123456789"; //11
                char chars[] = item.getBarcode().toCharArray();
                for (int j = 0; j < item.getBarcode().length(); j++) { //12
                    char c = item.getBarcode().charAt(j); //13
                    if (allowed.indexOf(c) == -1) { //14
                        throw new RuntimeException("Invalid character in item barcode!"); //15
                    }
                } //16
                if (item.getDiscount() > 0) { //17
                    sum += item.getPrice() * item.getDiscount(); //18
                } else { //19
                    sum += item.getPrice(); //20
                }
            } else {//21
                throw new RuntimeException("No barcode!"); //22
            }
            if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0') { //24
                sum -= 30; //25
            }
        }//26
        if (sum <= payment) { //27
            return true; //28
        } else {
            return false; //29
        }
    }//30
}
