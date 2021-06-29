package cn.hzy.demo.thread.pool;

@FunctionalInterface
public interface DenyPolicy {

    void reject(Runnable runnable,ThreadPool threadPool);

    class DiscardDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            System.out.println("reject");
        }
    }

    class AbortDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RunnableDenyException("the runnable "+runnable+" will be abort.");
        }
    }
    class RunnerDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if(!threadPool.isShutdown()){
                runnable.run();
            }
        }
    }
}
