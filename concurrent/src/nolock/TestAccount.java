package nolock;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

public class TestAccount {
    public static void main(String[] args) {
        Account account = new AccountUnsafe(100000);
        Account.demo(account);
    }

    static class AccountUnsafe implements Account {
        private volatile Integer balance ;

        public AccountUnsafe(Integer balance) {
            this.balance = balance;
        }

        @Override
        public Integer getAccount() {
            return this.balance;
        }

        @Override
        public void withDraw(Integer account) {

//            this.balance -= account;
            synchronized (this) {
                this.balance -= account;
            }
        }
    }

    interface Account{
        Integer getAccount();

        void withDraw(Integer account);

        static void demo(Account account) {
            List<Thread> ts = new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                ts.add(new Thread(()->{
                    account.withDraw(10);
                }));
            }
            ts.forEach(Thread::start);
            long start = System.nanoTime();
            ts.forEach(t -> {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            long end = System.nanoTime();
            System.out.println(account.getAccount() +  " cost:" + (end - start) / 1000_000 + "ms");
        }
    }
}
