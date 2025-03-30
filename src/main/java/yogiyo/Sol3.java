package yogiyo;


enum StateInfo {
    LOGGED_IN, LOGGED_OUT, SUSPENDED, ERROR
}

interface BankAccountState {
    public StateInfo login(String password);
    public StateInfo logout();
    public StateInfo unlock(int resetCode);
    public StateInfo withdrawMoney(int amount);
}

class BankAccount {
    private BankAccountState loggedIn;
    private BankAccountState loggedOut;
    private BankAccountState suspended;
    private BankAccountState bankAccountState;
    private int cashBalance;
    private String password;
    private int passwordRetries;
    private int resetCode;

    public BankAccount(int cashBalance, String password, int resetCode) {
        this.cashBalance = cashBalance;
        this.password = password;
        this.resetCode = resetCode;
        this.passwordRetries = 0;
        this.loggedIn = new LoggedIn(this);
        this.loggedOut = new LoggedOut(this);
        this.suspended = new Suspended(this);
        this.bankAccountState = this.loggedOut;
    }

    public void setState(BankAccountState state) {
        this.bankAccountState = state;
    }

    public BankAccountState getState() {
        return this.bankAccountState;
    }

    public BankAccountState getLoggedInState() {
        return this.loggedIn;
    }

    public BankAccountState getLoggedOutState() {
        return this.loggedOut;
    }

    public BankAccountState getSuspendedState() {
        return this.suspended;
    }

    public StateInfo login(String password) {
        return this.bankAccountState.login(password);
    }

    public StateInfo logout() {
        return this.bankAccountState.logout();
    }

    public StateInfo unlock(int resetCode) {
        return this.bankAccountState.unlock(resetCode);
    }

    public StateInfo withdrawMoney(int amount) {
        return this.bankAccountState.withdrawMoney(amount);
    }

    public void setCashBalance(int amount) {
        this.cashBalance = amount;
    }

    public int getCashBalance() {
        return this.cashBalance;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPasswordRetries(int passwordRetries) {
        this.passwordRetries = passwordRetries;
    }

    public int getPasswordRetries() {
        return this.passwordRetries;
    }

    public int getResetCode() {
        return this.resetCode;
    }
}

// YOUR SOLUTION HERE
class LoggedIn implements BankAccountState {

    private BankAccount account;

    public LoggedIn(BankAccount account) {
        this.account = account;
    }

    // 로그인 -> 변경 없음
    @Override
    public StateInfo login(String password) {
        return StateInfo.LOGGED_IN;
    }

    // 로그아웃 -> 은행계좌 상태 변경 (LoggedOut)
    @Override
    public StateInfo logout() {
        account.setState(account.getLoggedOutState());
        return StateInfo.LOGGED_OUT;
    }

    // unlock -> 변경 없음
    @Override
    public StateInfo unlock(int resetCode) {
        return StateInfo.LOGGED_IN;
    }

    // withDraw -> cashBalance 보다 요청금액이 작거나 같음 -> 인출
    @Override
    public StateInfo withdrawMoney(int amount) {
        if (amount <= account.getCashBalance()) {
            account.setCashBalance(account.getCashBalance() - amount);
        }
        return StateInfo.LOGGED_IN;
    }
}

// YOUR SOLUTION HERE
class LoggedOut implements BankAccountState {
    private BankAccount account;

    public LoggedOut(BankAccount account) {
        this.account = account;
    }

    // 로그인 -> 비밀번호 정책에 따른 변경
    @Override
    public StateInfo login(String password) {
        // 비밀번호 일치하는 경우
        if (account.getPassword().equals(password)) {
            account.setPasswordRetries(0);
            account.setState(account.getLoggedInState());
            return StateInfo.LOGGED_IN;
        }
        // 일치 하지 않는 경우
        else {
            int retries = account.getPasswordRetries() + 1;
            account.setPasswordRetries(retries);
            // 재시도 2회 초과시
            if (retries > 2) {
                account.setState(account.getSuspendedState());
                return StateInfo.SUSPENDED;
            }
            return StateInfo.LOGGED_OUT;
        }
    }

    // 로그아웃 -> 변경 없음
    @Override
    public StateInfo logout() {
        return StateInfo.LOGGED_OUT;
    }

    // Unlock -> 변경 없음
    @Override
    public StateInfo unlock(int resetCode) {
        return StateInfo.LOGGED_OUT;
    }

    // withDraw -> 변경 없음
    @Override
    public StateInfo withdrawMoney(int amount) {
        return StateInfo.LOGGED_OUT;
    }
}

class Suspended implements BankAccountState {
    private BankAccount account;

    public Suspended(BankAccount account) {
        this.account = account;
    }

    // 로그인 -> 변경 없음
    @Override
    public StateInfo login(String password) {
        return StateInfo.SUSPENDED;
    }

    // 로그아웃 -> 변경 없음
    @Override
    public StateInfo logout() {
        return StateInfo.SUSPENDED;
    }

    // unlock -> resetCode 일치 여부에 따라 변경
    @Override
    public StateInfo unlock(int resetCode) {
        // restCode 일치시
        if (account.getResetCode() == resetCode) {
            account.setPasswordRetries(0);
            account.setState(account.getLoggedOutState());
            return StateInfo.LOGGED_OUT;
        } else {
            return StateInfo.SUSPENDED;
        }
    }

    // withDraw -> 변경 없음
    @Override
    public StateInfo withdrawMoney(int amount) {
        return StateInfo.SUSPENDED;
    }
}
