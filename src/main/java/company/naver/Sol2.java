package company.naver;

import java.util.*;

/**
 *
 you are ordering pizza for a party. you are given an order (a list of items to buy) and a pizzeria menu (a list of all available items and their prices).

 the pizzeria offers the following discounts:
 1. buy 3 the cheapse one is free.
 2. buy 5 for 100
 3. for every large pizza. get a free small one
 4. buy 3 large pay for 3 medioum

 your goal is to minimize the total cost of the order by using at most one of the discouns the pizzeria offers.

 precise descriptions of how every discount works can be found in the examples section

 Specification:

 Assume that the following declarations are given:
 class Pizza {
 public String name;
 public int price_S;
 public int price_M;
 public int price_L;
 }

 class OrderItem {
 public String name;
 public String size;
 public int quantity;
 }

 The menu is constructed as an array of Pizza objects, where:
 1. name denotes the name of the pizza: every name appears in the menu array exactly once;
 2. price_S, price_M and price_L denote the prices for "Small", "Medium" and "Large" versions of this pizza respectively.

 Similarly, the order is constructed as an array of OrderItem objects, where:
 1. name denotes the name of the pizza you want to order.
 2. size specifies the size of the pizza you want to order ("Small", "Medium" or "Large");
 3. quantity specifies how many pizzas with this name and size you want to order.

 Every (name, size) pair appears in the order array at most once.
 Write function:
 class Solution { public int solution(Pizza[] menu, OrderItem[] order);}

 that, given menu array of length N and an order array of length M, returns the minimum amount of money you need to pay when using one of the discounts the pizzeria offers (or not using discounts, if none of them is applicable to the given order).

 Examples:
 1. Buy 3 the cheapest one is free
 When you order contains at least three pizzas (not necessarily different). you can choose to not pay for a single cheapest pizza in the whole order. You can use this discount at most once per order and you cannot split your order.

 For example for:
 menu = [
 Pizza(name="greek", price_S=7, price_M=5, price_L=10),
 Pizza(name="texas", price_S=8, price_M=9, price_L=13),
 Pizza(name="european", price_S=5, price_M=10, price_L=13)
 ]

 order = [
 OrderItem(name="texas", size="Medium", quantity=1),
 OrderItem(name="european", size="Small", quantity=2)
 ]

 your function should return 14, because you pay only for one texas and one european pizza; the other european is free.

 Notice that larger pizza can cost less than smaller pizzas.

 2. Buy 5 for 100
 When you order contains at least five pizzas with the same name.
 you can choose five of them to cost 100 in total.
 As with the previous discount, you can use one at most once per order and you cannot split your order.

 For example, for:
 menu = [
 Pizza(name="margherita", price_S=90, price_M=80, price_L=100),
 Pizza(name="hawaii", price_S=80, price_M=90, price_L=120),
 Pizza(name="capricciosa", price_S=50, price_M=70, price_L=130),
 Pizza(name="greek", price_S=50, price_M=70, price_L=130),
 ]

 order = [
 OrderItem(name="greek", size="Small", quantity=5),
 OrderItem(name="margherita", size="Small", quantity=4),
 OrderItem(name="hawaii", size="Large", quantity=1),
 OrderItem(name="margherita", size="Medium", quantity=2),
 OrderItem(name="capricciosa", size="Small", quantity=7),
 ]

 your function should return 900,
 because you can choose to pay 100 for one "Medium" and four "Small" margherita and pay the normal price for everything else.

 Notice that you could use the discount on five greek pizzas or on five capricciosa but it would result in higher total cost.
 Similary. you could use the "Buy 3, the cheapest one is free discount, but it would also result in higher total cost.

 3. For Every Large pizza, get a free Small One
 When your order contains a "Large" and a "Small" pizza with the same name,
 you can get the "Small" one for free. You can use this discount as many times a you want,
 provided the conditions are met and you are not using any other discount.

 For Exampls for:
 menu = [
 Pizza(name="margherita", price_S=7, price_M=8, price_L=10),
 Pizza(name="hawaii", price_S=8, price_M=9, price_L=12),
 Pizza(name="capricciosa", price_S=5, price_M=7, price_L=13)
 ]

 order = [
 OrderItem(name="margherita", size="Small", quantity=3),
 OrderItem(name="capricciosa", size="Large", quantity=2),
 OrderItem(name="hawaii", size="Large", quantity=3),
 OrderItem(name="margherita", size="Large", quantity=1),
 OrderItem(name="hawaii", size="Medium", quantity=1),
 OrderItem(name="capricciosa", size="Small", quantity=5),
 OrderItem(name="capricciosa", size="Medium", quantity=1)
 ]

 your function should return 117, because you can choose not to pay for one "Small" margherita and two "Small" capricciosa.
 Notice that you still have to pay for three "Small" capricciosa because there are not enough "Large" ones to pair them with.

 4. Buy 3 Large, pay for 3 Medium
 when your order contains at least three "Large" pizzas
 you can choose exactly three and pay for them as if they were "Medium" pizzas with same names.
 You can use this discount at most once per order and you cannot split your order.

 For Exampls for:
 menu = [
 Pizza(name="boston", price_S=7, price_M=5, price_L=10),
 Pizza(name="hawaii", price_S=8, price_M=9, price_L=12),
 Pizza(name="newyorker", price_S=8, price_M=9, price_L=130),
 Pizza(name="philadelphia", price_S=5, price_M=10, price_L=13)
 ]

 order = [
 OrderItem(name="boston", size="Small", quantity=3),
 OrderItem(name="hawaii", size="Large", quantity=3),
 OrderItem(name="newyorker", size="Large", quantity=1),
 OrderItem(name="boston", size="Large", quantity=2),
 OrderItem(name="philadelphia", size="Large", quantity=2)
 ]

 your function should return 102, because you can choose to pay for one "Large" newyorker and two "Large" boston pizzas as if they were "Medium" ones.

 Notice that choosing any other three large pizzas results in a smaller discount.

 Corner case : No discounts applicable
 when the number of ordered pizzas is insufficient to use any discount, your program should return the total cost of the order.

 For examples for:
 menu = [
 Pizza(name="margherita", price_S=7, price_M=8, price_L=10),
 Pizza(name="hawaii", price_S=8, price_M=9, price_L=12),
 Pizza(name="capricciosa", price_S=5, price_M=7, price_L=13)
 ]

 order = [
 OrderItem(name="margherita", size="Small", quantity=1),
 OrderItem(name="hawaii", size="Large", quantity=1)
 ]

 your function return 19
 */

public class Sol2 {

    static class Pizza {
        public String name;
        public int price_S;
        public int price_M;
        public int price_L;

        public Pizza(String name, int s, int m, int l) {
            this.name = name;
            this.price_S = s;
            this.price_M = m;
            this.price_L = l;
        }
    }

    static class OrderItem {
        public String name;
        public String size;
        public int quantity;

        public OrderItem(String name, String size, int quantity) {
            this.name = name;
            this.size = size;
            this.quantity = quantity;
        }


        private int costNoDiscount(Pizza[] menu, OrderItem[] order) {
            int total = 0;
            Map<String, Pizza> map = new HashMap<>();
            for (Pizza p : menu) {
                map.put(p.name, p);
            }

            for (OrderItem oi : order) {
                Pizza p = map.get(oi.name);
                int unitPrice = 0;
                switch (oi.size) {
                    case "Small":
                        unitPrice = p.price_S;
                        break;
                    case "Medium":
                        unitPrice = p.price_M;
                        break;
                    case "Large":
                        unitPrice = p.price_L;
                        break;
                }
                total += unitPrice * oi.quantity;
            }
            return total;
        }

        private int costCheapestFree(Pizza[] menu, OrderItem[] order) {
            int totalPizzas = 0;
            List<Integer> allPrices = new ArrayList<>();

            Map<String, Pizza> map = new HashMap<>();
            for (Pizza p : menu) {
                map.put(p.name, p);
            }

            int totalWithoutDiscount = 0;

            for (OrderItem oi : order) {
                Pizza p = map.get(oi.name);
                int unitPrice = 0;
                switch (oi.size) {
                    case "Small":
                        unitPrice = p.price_S;
                        break;
                    case "Medium":
                        unitPrice = p.price_M;
                        break;
                    case "Large":
                        unitPrice = p.price_L;
                        break;
                }
                totalPizzas += oi.quantity;
                for (int i = 0; i < oi.quantity; i++) {
                    allPrices.add(unitPrice);
                }
                totalWithoutDiscount += unitPrice * oi.quantity;
            }

            if (totalPizzas < 3) {
                return Integer.MAX_VALUE;
            }

            Collections.sort(allPrices);
            int cheapest = allPrices.get(0);

            return totalWithoutDiscount - cheapest;
        }

        private int costFiveFor100(Pizza[] menu, OrderItem[] order) {
            Map<String, Pizza> map = new HashMap<>();
            for (Pizza p : menu) {
                map.put(p.name, p);
            }

            int baseCost = costNoDiscount(menu, order);
            int bestCost = baseCost;


            Map<String, List<Integer>> nameToPrices = new HashMap<>();

            for (OrderItem oi : order) {
                Pizza p = map.get(oi.name);
                int unitPrice = 0;
                switch (oi.size) {
                    case "Small":
                        unitPrice = p.price_S;
                        break;
                    case "Medium":
                        unitPrice = p.price_M;
                        break;
                    case "Large":
                        unitPrice = p.price_L;
                        break;
                }
                // (oi.quantity)만큼 동일 가격
                nameToPrices.putIfAbsent(oi.name, new ArrayList<>());
                for (int i = 0; i < oi.quantity; i++) {
                    nameToPrices.get(oi.name).add(unitPrice);
                }
            }

            for (String pizzaName : nameToPrices.keySet()) {
                List<Integer> prices = nameToPrices.get(pizzaName);
                if (prices.size() < 5) continue; // 할인 불가


                prices.sort(Comparator.reverseOrder());

                int sumOfTop5 = 0;
                for (int i = 0; i < 5; i++) {
                    sumOfTop5 += prices.get(i);
                }

                int discountedCost = baseCost - sumOfTop5 + 100;

                bestCost = Math.min(bestCost, discountedCost);
            }

            return bestCost;
        }

        private int costLargeFreeSmall(Pizza[] menu, OrderItem[] order) {

            Map<String, Pizza> map = new HashMap<>();
            for (Pizza p : menu) {
                map.put(p.name, p);
            }


            Map<String, Map<String, Integer>> info = new HashMap<>();

            for (OrderItem oi : order) {
                info.putIfAbsent(oi.name, new HashMap<>());
                Map<String, Integer> sizeMap = info.get(oi.name);
                sizeMap.put(oi.size, sizeMap.getOrDefault(oi.size, 0) + oi.quantity);
            }

            int totalCost = 0;

            for (String pizzaName : info.keySet()) {
                Pizza p = map.get(pizzaName);
                Map<String, Integer> sizeMap = info.get(pizzaName);
                int smallCount = sizeMap.getOrDefault("Small", 0);
                int mediumCount = sizeMap.getOrDefault("Medium", 0);
                int largeCount = sizeMap.getOrDefault("Large", 0);

                int priceS = p.price_S;
                int priceM = p.price_M;
                int priceL = p.price_L;

                int freeSmall = Math.min(largeCount, smallCount);

                int paidSmall = smallCount - freeSmall;

                totalCost += (paidSmall * priceS) + (mediumCount * priceM) + (largeCount * priceL);
            }

            return totalCost;
        }

        private int costThreeLargePayMedium(Pizza[] menu, OrderItem[] order) {

            int baseCost = costNoDiscount(menu, order);

            // 메뉴 map
            Map<String, Pizza> map = new HashMap<>();
            for (Pizza p : menu) {
                map.put(p.name, p);
            }

            List<Integer> diffs = new ArrayList<>();

            for (OrderItem oi : order) {
                if (!map.containsKey(oi.name)) continue;
                Pizza p = map.get(oi.name);

                if ("Large".equals(oi.size)) {
                    int diff = (p.price_L - p.price_M);
                    for (int i = 0; i < oi.quantity; i++) {
                        diffs.add(diff);
                    }
                }
            }

            if (diffs.size() < 3) {
                return Integer.MAX_VALUE;
            }

            diffs.sort(Comparator.reverseOrder());
            int top3Sum = diffs.get(0) + diffs.get(1) + diffs.get(2);
            int discountedCost = baseCost - top3Sum;

            return discountedCost;
        }


        public int solution(Pizza[] menu, OrderItem[] order) {
            int noDiscount = costNoDiscount(menu, order);

            int discountCheapest = costCheapestFree(menu, order);

            int discountFive100 = costFiveFor100(menu, order);

            int discountLargeFreeSmall = costLargeFreeSmall(menu, order);

            int discountThreeLargePayMedium = costThreeLargePayMedium(menu, order);

            int answer = Math.min(noDiscount,
                    Math.min(discountCheapest,
                            Math.min(discountFive100,
                                    Math.min(discountLargeFreeSmall, discountThreeLargePayMedium))));

            return answer;
        }
    }
}
