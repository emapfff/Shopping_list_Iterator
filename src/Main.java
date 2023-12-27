//Emil Davlityarov
//e.davlityarov@innopolis.university
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        ShopList shop = new ShopList();
        System.out.println("Grocery shop list:");
        shop.forEach(System.out::println);
        ArrayList<Item> customer1 = new ArrayList<>();
        System.out.println("Customer 1 list:");
        customer1.add(new Item("lemon", 1));
        customer1.add(new Item("bread", 1));
        customer1.forEach(System.out::println);
        shop.buy(customer1);
        System.out.println("Updated grocery shop list:");
        shop.forEach(System.out::println);
        System.out.println("Customer 2 list:");
        ArrayList<Item> customer2 =  new ArrayList<>();
        customer2.add(new Item("milk", 5));
        customer2.add(new Item("bread", 3));
        customer2.forEach(System.out::println);
        shop.buy(customer2);
        System.out.println("Updated grocery shop list:");
        shop.forEach(System.out::println);
        System.out.println("Customer 3 list:");
        ArrayList<Item> customer3 =  new ArrayList<>();
        customer3.add(new Item("lemon", 1));
        customer3.add(new Item("apples", 20));
        customer3.forEach(System.out::println);
        shop.buy(customer3);
        System.out.println("Updated grocery shop list:");
        shop.forEach(System.out::println);
    }
}

class Item{
    private final String name;
    private int count;
    Item(String name, int count){
        this.name = name;
        this.count  = count;
    }
    public void decrease(int n){
        this.count -= n;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + count;
    }
}


class ShopList implements Iterable<Item>{
    private static ArrayList<Item> itemList;
    public ShopList(){
        itemList = new ArrayList<>();
        lazyLoading();
    }
    public Iterator<Item> iterator() {
        return new ShopIterator(0);
    }
    private void lazyLoading(){
        try {
            File file = new File("C:\\Patterns\\Assignment5\\Task4\\src\\shop.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] strings = line.split(", ");
                Item item = new Item(strings[0], Integer.parseInt(strings[1]));
                itemList.add(item);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
    private static class ShopIterator implements Iterator<Item> {
        public int index;
        public ShopIterator(int index) {
            this.index = 0;
        }
        @Override
        public boolean hasNext() {
            return index < itemList.size();
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = itemList.get(index);
            index++;
            return item;
        }
    }
    public void buy(ArrayList<Item> buyItems){
        for(Item buyI : buyItems){
            Item changeItem = null;
            for(Item item: itemList){
                if (buyI.getName().equals(item.getName())){
                    changeItem = item;
                    break;
                }
            }
            if (changeItem != null) {
                if (changeItem.getCount() >= buyI.getCount()) {
                    for (Item item: itemList){
                        if (item.getName().equals(changeItem.getName())){
                            item.decrease(buyI.getCount());
                            if (item.getCount() == 0){
                                itemList.remove(item);
                            }
                            break;
                        }
                    }
                } else {
                    System.out.println("Sorry, you can buy only " + changeItem.getCount() + " " + changeItem.getName() );
                    for(Item item: itemList){
                        if (item.getName().equals(changeItem.getName())){
                            itemList.remove(item);
                            break;
                        }
                    }
                }
            } else {
                System.out.println("Sorry, " + buyI.getName() + "s are not available");
            }
        }
    }
}
