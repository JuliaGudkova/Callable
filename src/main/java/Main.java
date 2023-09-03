import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Callable<Integer> one = () -> {
            int result = 0;
            for (int i = 0; i < 6; i++) {
                System.out.println(Thread.currentThread().getName() + " " + "Привет");
                Thread.sleep(2500);
                result++;
            }
            return result;
        };
        Callable<Integer> two = () -> {
            int result = 0;
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + " " + "Пока");
                Thread.sleep(1500);
                result++;
            }
            return result;
        };
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Future<Integer> task = service.submit(one);
        Future<Integer> task1 = service.submit(two);
        Integer resultOfTask1 = null;
        Integer resultOfTask2 = null;
        try {
            resultOfTask1 = task.get();
            resultOfTask2 = task1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        int result1 = resultOfTask1 + resultOfTask2;
        System.out.println(" Отправлено задач " + result1);
        service.shutdown();

    }
}

