package dfs.yogiyo;

import java.util.*;

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
    }

    // 기본 전체 합산 가격
    static int NORMAL_TOTAL_COST = 0;
    // 주문 전체 피자 갯수
    static int TOTAL_QUANTITY = 0;
    // 주문 피자 중 최소 단가
    static int MIN_PER_PRICE = Integer.MAX_VALUE;

    // 피자 정보
    static Map<String, Pizza> PIZZA_MENU_MAP = new HashMap<>();
    // 단가 정보
    static Map<String, List<Integer>> UNIT_PRICE_MAP = new HashMap<>();
    // small 사이즈 피자 정보
    static Map<String, Integer> SMALL_COUNT_MAP = new HashMap<>();
    // large 사이즈 피자 정보
    static Map<String, Integer> LARGE_COUNT_MAP = new HashMap<>();
    // large - medium 차이 정보
    static List<Integer> LARGE_DIFF_LISTS = new ArrayList<>();

    /*
        할인 1 : 전체 피자 개수가 3개 이상이면 가장 저렴한 피자 가격 할인
     */
    private int discount1() {

        if (TOTAL_QUANTITY < 3) return NORMAL_TOTAL_COST;
        return NORMAL_TOTAL_COST - MIN_PER_PRICE;
    }

    /*
        할인 2 : 같은 피자 5개 구매시, 선택한 5개를 100에 구매
        - 한번 적용
        - 비싼거 5개와 100 차이만큼 할인
     */
    private int discount2() {

        int target = NORMAL_TOTAL_COST;

        for (String name : UNIT_PRICE_MAP.keySet()) {
            List<Integer> prices = UNIT_PRICE_MAP.get(name);

            if (prices.size() >= 5) {

                // 비싼것부터 할인
                prices.sort(Comparator.reverseOrder());

                int top5 = 0;
                for (int i = 0; i < 5; i++) {
                    top5 += prices.get(i);
                }

                int discountsPrice = top5 - 100;

                if (discountsPrice > 0) {
                    target = Math.min(target, NORMAL_TOTAL_COST - discountsPrice);
                }
            }
        }

        return target;
    }

    /*
        할인 3 : 같은 피자에 대형, 소형 같이 존재시 대형 한판당 소형 한판 무료
     */
    private int discount3() {

        int toDiscountPrice = 0;

        for (String name : PIZZA_MENU_MAP.keySet()) {

            if (SMALL_COUNT_MAP.containsKey(name) && LARGE_COUNT_MAP.containsKey(name)) {
                int toFreeCount = Math.min(SMALL_COUNT_MAP.get(name), LARGE_COUNT_MAP.get(name));
                toDiscountPrice += toFreeCount * PIZZA_MENU_MAP.get(name).price_S;;
            }
        }

        return NORMAL_TOTAL_COST - toDiscountPrice;
    }

    /*
        할인 4 : 대형 3판 구매시 -> 3판을 중형 가격으로 판매
        - 비싼거 3개 차이 만큼 할인
     */
    private int discount4() {

        if (LARGE_DIFF_LISTS.size() < 3) return NORMAL_TOTAL_COST;

        LARGE_DIFF_LISTS.sort(Comparator.reverseOrder());
        int toDiscountPrice = LARGE_DIFF_LISTS.get(0) + LARGE_DIFF_LISTS.get(1) + LARGE_DIFF_LISTS.get(2);

        return NORMAL_TOTAL_COST - toDiscountPrice;
    }

    public int solution(Pizza[] menu, OrderItem[] order) {

        NORMAL_TOTAL_COST = 0;
        TOTAL_QUANTITY = 0;
        MIN_PER_PRICE = Integer.MAX_VALUE;
        PIZZA_MENU_MAP = new HashMap<>();
        UNIT_PRICE_MAP = new HashMap<>();
        SMALL_COUNT_MAP = new HashMap<>();
        LARGE_COUNT_MAP = new HashMap<>();
        LARGE_DIFF_LISTS = new ArrayList<>();

        // 피자 메뉴 생성
        for (Pizza p : menu) {
            PIZZA_MENU_MAP.put(p.name, p);
        }

        // 주문 정보 기준으로 피자 구분
        for (OrderItem orderItem : order) {

            Pizza pizza = PIZZA_MENU_MAP.get(orderItem.name);
            int unitPrice = 0;

            // 크키별 이름으로 map 생성
            switch (orderItem.size) {

                case "Small":
                    unitPrice = pizza.price_S;
                    SMALL_COUNT_MAP.put(orderItem.name, SMALL_COUNT_MAP.getOrDefault(orderItem.name, 0) + orderItem.quantity);
                    break;

                case "Medium":
                    unitPrice = pizza.price_M;
                    break;

                case "Large":
                    unitPrice = pizza.price_L;
                    LARGE_COUNT_MAP.put(orderItem.name, LARGE_COUNT_MAP.getOrDefault(orderItem.name, 0) + orderItem.quantity);

                    int diff = pizza.price_L - pizza.price_M;

                    for (int i = 0; i < orderItem.quantity; i++) {
                        if (diff > 0) {
                            LARGE_DIFF_LISTS.add(diff);
                        }
                    }
                    break;
            }

            NORMAL_TOTAL_COST += unitPrice * orderItem.quantity;
            TOTAL_QUANTITY += orderItem.quantity;
            MIN_PER_PRICE = Math.min(MIN_PER_PRICE, unitPrice);

            // 각 피자 이름별로 단가 목록 생성
            UNIT_PRICE_MAP.putIfAbsent(orderItem.name, new ArrayList<>());
            List<Integer> prices = UNIT_PRICE_MAP.get(orderItem.name);

            for (int i = 0; i < orderItem.quantity; i++) {
                prices.add(unitPrice);
            }
        }

        // 할인 미적용 비용이 기본 값
        int answer = NORMAL_TOTAL_COST;

        // 각 할인 정책별 적용 비용 계산 후 최저 비용 선택 (할인 하나만 적용 가능)
        answer = Math.min(answer, discount1());
        answer = Math.min(answer, discount2());
        answer = Math.min(answer, discount3());
        answer = Math.min(answer, discount4());

        return answer;
    }
}




















//
//
//package yogiyo;
//
//        import java.util.*;
//
//public class Sol2 {
//
//    static class Pizza {
//        public String name;
//        public int price_S;
//        public int price_M;
//        public int price_L;
//
//        public Pizza(String name, int s, int m, int l) {
//            this.name = name;
//            this.price_S = s;
//            this.price_M = m;
//            this.price_L = l;
//        }
//    }
//
//    static class OrderItem {
//        public String name;
//        public String size;
//        public int quantity;
//
//        public OrderItem(String name, String size, int quantity) {
//            this.name = name;
//            this.size = size;
//            this.quantity = quantity;
//        }
//    }
//
//    // 기본 전체 합산 가격
//    int normalTotalCost = 0;
//    // 주문 전체 피자 갯수
//    int totalQuantity = 0;
//    // 주문 피자 중 최소 단가
//    int minUnitPrice = Integer.MAX_VALUE;
//
//    static Map<String, List<Integer>> unitPricesMap = new HashMap<>();
//    static Map<String, Integer> smallCount = new HashMap<>();
//    static Map<String, Integer> largeCount = new HashMap<>();
//    static List<Integer> largeDiffs = new ArrayList<>();
//
//    /*
//        할인 1 : 전체 피자 개수가 3개 이상이면 가장 저렴한 피자 가격 할인
//     */
//    private int discount1(int normalTotalCost,
//                          int totalQuantity,
//                          int minUnitPrice) {
//
//        if (totalQuantity < 3) return normalTotalCost;
//        return normalTotalCost - minUnitPrice;
//    }
//
//    /*
//        할인 2 : 같은 피자 5개 구매시, 선택한 5개를 100에 구매
//        - 한번 적용
//        - 비싼거 5개와 100 차이만큼 할인
//     */
//    private int discount2(Map<String, List<Integer>> unitPricesMap,
//                          int normalTotalCost) {
//
//        int target = normalTotalCost;
//
//        for (String name : unitPricesMap.keySet()) {
//            List<Integer> prices = unitPricesMap.get(name);
//
//            if (prices.size() >= 5) {
//
//                // 비싼것부터 할인
//                prices.sort(Comparator.reverseOrder());
//
//                int top5 = 0;
//                for (int i = 0; i < 5; i++) {
//                    top5 += prices.get(i);
//                }
//
//                int discountsPrice = top5 - 100;
//
//                if (discountsPrice > 0) {
//                    target = Math.min(target, normalTotalCost - discountsPrice);
//                }
//            }
//        }
//
//        return target;
//    }
//
//    /*
//        할인 3 : 같은 피자에 대형, 소형 같이 존재시 대형 한판당 소형 한판 무료
//     */
//    private int discount3(Map<String, Integer> smallCount,
//                          Map<String, Integer> largeCount,
//                          Map<String, Pizza> pizzaMeuMap,
//                          int normalTotalCost) {
//
//        int toDiscountPrice = 0;
//
//        for (String name : pizzaMeuMap.keySet()) {
//
//            if (smallCount.containsKey(name) && largeCount.containsKey(name)) {
//                int toFreeCount = Math.min(smallCount.get(name), largeCount.get(name));
//                toDiscountPrice += toFreeCount * pizzaMeuMap.get(name).price_S;;
//            }
//        }
//
//        return normalTotalCost - toDiscountPrice;
//    }
//
//    /*
//        할인 4 : 대형 3판 구매시 -> 3판을 중형 가격으로 판매
//        - 비싼거 3개 차이 만큼 할인
//     */
//    private int discount4(List<Integer> largeDiffs,
//                          int normalTotalCost) {
//
//        if (largeDiffs.size() < 3) return normalTotalCost;
//
//        largeDiffs.sort(Comparator.reverseOrder());
//        int toDiscountPrice = largeDiffs.get(0) + largeDiffs.get(1) + largeDiffs.get(2);
//
//        return normalTotalCost - toDiscountPrice;
//    }
//
//    public int solution(Pizza[] menu, OrderItem[] order) {
//
//        // 피자 정보 생성
//        Map<String, Pizza> pizzaMenuMap = new HashMap<>();
//
//        for (Pizza p : menu) {
//            pizzaMenuMap.put(p.name, p);
//        }
//
////        // 기본 전체 합산 가격
////        int normalTotalCost = 0;
////        // 주문 전체 피자 갯수
////        int totalQuantity = 0;
////        // 주문 피자 중 최소 단가
////        int minUnitPrice = Integer.MAX_VALUE;
//
////        Map<String, List<Integer>> unitPricesMap = new HashMap<>();
////        Map<String, Integer> smallCount = new HashMap<>();
////        Map<String, Integer> largeCount = new HashMap<>();
////        List<Integer> largeDiffs = new ArrayList<>();
//
//        for (OrderItem orderItem : order) {
//            Pizza pizza = pizzaMenuMap.get(orderItem.name);
//            int unitPrice = 0;
//            switch (orderItem.size) {
//                case "Small":
//                    unitPrice = pizza.price_S;
//                    smallCount.put(orderItem.name, smallCount.getOrDefault(orderItem.name, 0) + orderItem.quantity);
//                    break;
//                case "Medium":
//                    unitPrice = pizza.price_M;
//                    break;
//                case "Large":
//                    unitPrice = pizza.price_L;
//                    largeCount.put(orderItem.name, largeCount.getOrDefault(orderItem.name, 0) + orderItem.quantity);
//                    int diff = pizza.price_L - pizza.price_M;
//                    for (int i = 0; i < orderItem.quantity; i++) {
//                        if (diff > 0) {
//                            largeDiffs.add(diff);
//                        }
//                    }
//                    break;
//            }
//
//            normalTotalCost += unitPrice * orderItem.quantity;
//            totalQuantity += orderItem.quantity;
//            minUnitPrice = Math.min(minUnitPrice, unitPrice);
//
//            // 각 피자 이름별로 단가 목록 생성
//            unitPricesMap.putIfAbsent(orderItem.name, new ArrayList<>());
//            List<Integer> prices = unitPricesMap.get(orderItem.name);
//            for (int i = 0; i < orderItem.quantity; i++) {
//                prices.add(unitPrice);
//            }
//        }
//
//        // 할인 미적용 비용이 기본 값
//        int answer = normalTotalCost;
//
//        // 각 할인 정책별 적용 비용 계산 후 최저 비용 선택 (할인 하나만 적용 가능)
//        answer = Math.min(answer, discount1(normalTotalCost, totalQuantity, minUnitPrice));
//        answer = Math.min(answer, discount2(unitPricesMap, normalTotalCost));
//        answer = Math.min(answer, discount3(smallCount, largeCount, pizzaMenuMap, normalTotalCost));
//        answer = Math.min(answer, discount4(largeDiffs, normalTotalCost));
//
//        return answer;
//    }
//
//}
