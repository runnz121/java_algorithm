package exercise;

import java.util.*;

public class Ex1Java {

    // 1. Palindrome Check
    public static boolean isPalindrome(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    // 2. Check for Duplicates in Array
    public static boolean hasDuplicates(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        for (int num : arr) {
            if (!seen.add(num)) return true;
        }
        return false;
    }

    // 3. Recursive Factorial
    public static long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    // 4. Flip Pivotal Matrix Diagonal Recursively
    public static void flipDiagonalRecursive(int[][] matrix, int start) {
        int n = matrix.length;
        if (start >= n / 2) return;

        int temp = matrix[start][start];
        matrix[start][start] = matrix[n - 1 - start][n - 1 - start];
        matrix[n - 1 - start][n - 1 - start] = temp;

        flipDiagonalRecursive(matrix, start + 1);
    }

    // 5. Atoi Implementation
    public static int myAtoi(String str) {
        String s = str.trim();
        if (s.isEmpty()) return 0;

        int sign = 1, index = 0;
        long result = 0;

        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            sign = s.charAt(index) == '-' ? -1 : 1;
            index++;
        }

        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            result = result * 10 + (s.charAt(index) - '0');
            if (result * sign > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (result * sign < Integer.MIN_VALUE) return Integer.MIN_VALUE;
            index++;
        }

        return (int) result * sign;
    }

    // 6. Sort Times Descending
    public static List<String> sortTimesDescending(List<String> times) {
        times.sort(Collections.reverseOrder());
        return times;
    }

    // 7. Decimal to Any Base
    public static String convertToBase(int num, int base) {
        if (num == 0) return "0";
        String digits = "0123456789ABCDEF";
        StringBuilder result = new StringBuilder();

        while (num > 0) {
            result.insert(0, digits.charAt(num % base));
            num /= base;
        }
        return result.toString();
    }

    // 8. Binary Search
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }

        return -1;
    }

    // 9. Tree Definition and DFS
    public static void treeDFSExample() {
        Map<Integer, List<Integer>> tree = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        // Example edges
        addEdge(tree, 1, 2);
        addEdge(tree, 1, 3);
        addEdge(tree, 2, 4);
        addEdge(tree, 2, 5);
        addEdge(tree, 3, 6);
        addEdge(tree, 3, 7);

        dfs(tree, visited, 1);
    }

    private static void addEdge(Map<Integer, List<Integer>> tree, int parent, int child) {
        tree.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
        tree.computeIfAbsent(child, k -> new ArrayList<>()).add(parent);
    }

    private static void dfs(Map<Integer, List<Integer>> tree, Set<Integer> visited, int node) {
        if (!visited.add(node)) return;
        System.out.println("Visit: " + node);
        for (int neighbor : tree.getOrDefault(node, new ArrayList<>())) {
            dfs(tree, visited, neighbor);
        }
    }

    // 10. Least Common Multiple of N Numbers
    public static int findLCMOfList(List<Integer> numbers) {
        int result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            result = lcm(result, numbers.get(i));
        }
        return result;
    }

    public static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    // 11. 에리스토 테네스의 채
    public static List<Integer> sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
                // 시작을 i*i로, step을 i로 설정하면 √n 전까지 충분히 마킹
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        int n = 100;
        List<Integer> primes = sieve(n);
        System.out.println("Primes up to " + n + ": " + primes);
    }
}
