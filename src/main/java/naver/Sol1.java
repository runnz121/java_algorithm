package naver;

/**
 * there is a metro rail line with k stations numbered from 0 to k-1. there is a direct connection between stations if their numbers differ by one. passengers can ride the metro line in both directions.
 *
 * the fee for boarding the train is 1. every time a passenger travels betwwen two adjacent stations the fee increases by 2. the metro system changes money at the end of each day based on the passenger's travel history for that day.
 *
 * there is a total fee limit for a single day. the passenger cannot be charged more than the limit, even if there associated travel fees exeed the limit. the limit depends on the maximum station number the passenger visited during the day.
 *
 * you are given arrays start and dest of lenght N, describing all the metro rides the passenger took during the day. During the X-th ride, the passenger boarded the train at station start[X] and left the train at station dest[X]. you are also given an array limit of length K. if the largest station number the passenger visited during the day is Y, then the fee limit for this passenger for that day is limit[Y].
 *
 * write function:
 * class solution {public int solution(int[] start, int[] dest, int limit);}
 *
 * that given arrays start and dest, both of length N, and the array limit of length K, returns the amount of money the passenger will be charged at the end of the day.
 *
 * Examples:
 *
 * 1. For start=[1,0,2,4], dest=[2,2,0,5] and limit=[3,17,7,4,5,17] the function should return 16.
 *
 * the total costs is 16. the largest visited station number is 5. The fee limit for station 5 is 17, so it does not apply in this case.
 *
 * 2. For start=[1,2,0,2,3], dest=[2,1,2,1,2] and limit=[4,8,18,16,20] the function should return 16
 * the total cost would be 17 but is capped at 16(the daily limit for station 3)
 *
 * 3. For start=[2,2] dest=[4,3] and limit = [1,1,1,1,9,1,1] the function should return 8.
 */
public class Sol1 {

    public int solution(int[] start, int[] dest, int[] limit) {
        int maxStation = 0;
        for (int i = 0; i < start.length; i++) {
            maxStation = Math.max(maxStation, start[i]);
            maxStation = Math.max(maxStation, dest[i]);
        }

        int dailyLimit = limit[maxStation];

        int totalCost = 0;
        for (int i = 0; i < start.length; i++) {
            int distance = Math.abs(start[i] - dest[i]);
            int cost = 1 + 2 * distance;
            totalCost += cost;
        }

        return Math.min(totalCost, dailyLimit);
    }

    public static void main(String[] args) {
        Sol1 s1 = new Sol1();
    }
}
