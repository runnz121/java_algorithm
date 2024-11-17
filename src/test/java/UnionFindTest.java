import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class UnionFindTest {

    static int[] parent;

    int find (int a) {
        if (parent[a] == a) return a;
        // 자기가 속해있는 부모 노드를 찾아서 반환한다.
        return find(parent[a]);
    }

    void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    @Test
    void test() {

        parent = new int[5];

        for (int i = 0; i < 5; i++) {
            parent[i] = i;
        }

//        union(1, 2);

        int a = find(2);

        System.out.println(a);

        System.out.println(Arrays.toString(parent));
    }
}
