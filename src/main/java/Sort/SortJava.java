package Sort;

import java.util.*;

public class SortJava {

    public static void main(String[] args) {
        SortJava sj = new SortJava();

        // 원시 타입 정렬
        System.out.println("==primitiveTypeSort==");
        sj.primitiveTypeSort();

        // 컬렉션 정렬
        System.out.println("\n==collectionSort==");
        sj.collectionSort();

        // 커스텀 정렬
        System.out.println("\n==customSort==");
        sj.customSort();

        // 오버라이드 커스텀 정렬
        System.out.println("\n==overrideCustomSort==");
        sj.overrideCustomSort();

        // 두 조건 커스텀 정렬 오버라이드
        System.out.println("\n==customSortTwoConditionOverride==");
        sj.customSortTwoConditionOverride();

        // 두 조건 커스텀 정렬
        System.out.println("\n==customSortTwoCondition==");
        sj.customSortTwoCondition();
    }

    // 원시타입 정렬
    private void primitiveTypeSort() {
        int[] nums = {5, 2, 8, 1, 3};
        // 오름차순 정렬
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));  // [1, 2, 3, 5, 8]

        // 내림차순 정렬 (박싱 필요)
        Integer[] boxed = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(boxed, (a, b) -> b - a);
        System.out.println(Arrays.toString(boxed)); // [8, 5, 3, 2, 1]
    }

    // 컬랙션 정렬
    private void collectionSort() {
        List<String> list = new ArrayList<>(Arrays.asList("banana", "apple", "cherry"));

        // 오름차순 정렬
        Collections.sort(list);
        System.out.println(list); // [apple, banana, cherry]

        // 내림차순 정렬
        Collections.sort(list, Collections.reverseOrder());
        System.out.println(list); // [cherry, banana, apple]

        // Stream + Comparator 활용 (길이 기준 오름차순)
        List<String> sortedByLength = list.stream()
                .sorted(Comparator.comparingInt(String::length))
                .toList();

        System.out.println(sortedByLength);
    }

    private void customSort() {
        List<Person> people = List.of(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 28)
        );

        // 나이 오름차순
        List<Person> byAge = new ArrayList<>(people);
        byAge.sort(Comparator.comparingInt(p -> p.age));
        System.out.println(byAge); // [Bob(25), Charlie(28), Alice(30)]

        // 이름 내림차순
        List<Person> byNameDesc = new ArrayList<>(people);
        byNameDesc.sort(Comparator.comparing((Person p) -> p.name).reversed());
        System.out.println(byNameDesc); // [Charlie(28), Bob(25), Alice(30)]
    }

    private void overrideCustomSort() {
        List<Person> people = List.of(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 28)
        );

        // 나이 오름차순
        List<Person> byAge = new ArrayList<>(people);
        byAge.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                // Integer.compare로 안전하게 비교
                return Integer.compare(o1.age, o2.age);
            }
        });
        System.out.println(byAge); // [Bob(25), Charlie(28), Alice(30)]

        // 이름 내림차순
        List<Person> byNameDesc = new ArrayList<>(people);
        byNameDesc.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                // o2.name.compareTo(o1.name) ⇒ 내림차순
                return o2.name.compareTo(o1.name);
            }
        });
        System.out.println(byNameDesc); // [Charlie(28), Bob(25), Alice(30)]
    }

    private void customSortTwoConditionOverride() {
        List<Person> people = new ArrayList<>(Arrays.asList(
                new Person("Alice",   30),
                new Person("Charlie", 28),
                new Person("Bob",     25),
                new Person("Charlie", 22)
        ));

        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                // 1) 이름 내림차순
                int nameCmp = o2.getName().compareTo(o1.getName());
                if (nameCmp != 0) {
                    return nameCmp;
                }
                // 2) 이름이 같으면 나이 오름차순
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        });

        System.out.println(people);
    }

    private void customSortTwoCondition() {
        List<Person> people = Arrays.asList(
                new Person("Alice",   30),
                new Person("Charlie", 28),
                new Person("Bob",     25),
                new Person("Charlie", 22)
        );

        // 이름 내림차순, 나이 오름차순
        people.sort(
                Comparator
                        .comparing(Person::getName, Comparator.reverseOrder())
                        .thenComparingInt(Person::getAge)
        );

        System.out.println(people);
    }

    public static class Person {
        String name;
        int age;
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() { return name; }
        public int getAge()  { return age; }
        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }
}
