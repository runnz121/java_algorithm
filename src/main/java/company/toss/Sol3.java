package company.toss;

public class Sol3 {

    public boolean solution(String amountText) {


        return amountText.matches("^(0|[1-9]\\d{0,2}(,\\d{3})*|\\d+)$");
    }

    public static void main(String[] args) {
        Sol3 t = new Sol3();
        boolean solution = t.solution("0100");
        System.out.println(solution);
    }
}
