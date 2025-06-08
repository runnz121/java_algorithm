package Sort

import java.util.*
import kotlin.Comparator

class SortKotlin {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            val sorter = SortKotlin()

            println("=== primitiveCollectionSort ===")
            sorter.primitiveCollectionSort()

            println("\n=== customSort ===")
            sorter.customSort()

            println("\n=== customSortTwoConditionOverride ===")
            sorter.customSortTwoConditionOverride()

            println("\n=== customSortTwoCondition ===")
            sorter.customSortTwoCondition()
        }
    }

    fun primitiveCollectionSort() {

        val nums = arrayOf(5, 2, 8, 1, 3)

        // 배열 오름차순
        nums.sort()
        println(nums.joinToString())  // 1, 2, 3, 5, 8

        // 배열 내림차순
        nums.sortDescending()
        println(nums.joinToString())  // 8, 5, 3, 2, 1

        // 리스트 소팅(불변 리스트로 새 객체 반환)
        val list = listOf("banana", "apple", "cherry")
        val asc = list.sorted()              // [apple, banana, cherry]
        val desc = list.sortedDescending()  // [cherry, banana, apple]
        println(asc)
        println(desc)
    }

    fun customSort() {
        val people = listOf(
            Person("Alice", 30),
            Person("Bob", 25),
            Person("Charlie", 28)
        )

        // 나이 오름차순
        val byAge = people.sortedBy { it.age }
        println(byAge) // [Person(name=Bob, age=25), Person(name=Charlie, age=28), Person(name=Alice, age=30)]

        // 이름 내림차순
        val byNameDesc = people.sortedByDescending { it.name }
        println(byNameDesc)
    }

    // 이름 기준 내림차순, 나이 기준 오름차순 -> override 버전
    fun customSortTwoConditionOverride() {
        val people = mutableListOf(
            Person("Alice",   30),
            Person("Charlie", 28),
            Person("Bob",     25),
            Person("Charlie", 22)
        )

        people.sortWith(object : Comparator<Person> {
            override fun compare(o1: Person, o2: Person): Int {
                // 1) 이름 내림차순
                val nameCmp = o2.name.compareTo(o1.name)
                if (nameCmp != 0) {
                    return nameCmp
                }
                // 2) 이름이 같으면 나이 오름차순
                return o1.age.compareTo(o2.age)
            }
        })

        println(people)
    }

    fun customSortTwoCondition() {
        val people = listOf(
            Person("Alice",   30),
            Person("Charlie", 28),
            Person("Bob",     25),
            Person("Charlie", 22)
        )

        // 이름 내림차순, 나이 오름차순
        val sorted = people.sortedWith(
            compareByDescending<Person> { it.name }
                .thenBy { it.age }
        )

        println(sorted)
    }

    data class Person(val name: String, val age: Int)
}