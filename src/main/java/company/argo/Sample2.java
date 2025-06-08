package company.argo;

import java.util.Comparator;
import java.util.List;

public class Sample2 {

    static class UserLog {
        String userId;
        int loginCount;
        int lastLoginTime;

        public UserLog(String userId, int loginCount, int lastLoginTime) {
            this.userId = userId;
            this.loginCount = loginCount;
            this.lastLoginTime = lastLoginTime;
        }
    }

    public static void main(String[] args) {
        List<UserLog> logs = List.of(
                new UserLog("alice", 5, 1000),
                new UserLog("bob", 7, 950),
                new UserLog("charlie", 5, 1200),
                new UserLog("david", 5, 1000)
        );

        logs.stream()
                .sorted(Comparator
                        .comparingInt((UserLog u) -> -u.loginCount)
                        .thenComparingInt(u -> -u.lastLoginTime)
                        .thenComparing(u -> u.userId))
                .map(u -> u.userId)
                .forEach(System.out::println);
    }
}
