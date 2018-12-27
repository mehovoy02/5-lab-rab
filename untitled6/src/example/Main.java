package example;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 7, 1L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(512));//конструктор для нескольких данных
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//.setRejectedExecutionHandler - способ реакции на поступающие задачи; .CallerRunsPolicy() - если нет места в очереди,то обработкой задачи будет заниматься гл. поток

        executor.submit(() -> System.out.println("I am in Runnable!"));//.submit - этот метод принимает разные параметры

      Future<Boolean> future =  executor.submit(() -> {//...и возращает значение Future типа Boolean.Future управляет процессами обработки текущей задачи
          String str = ";)";

          if(";)".equals(str)){
              return Boolean.TRUE;
          }else{
              return Boolean.FALSE;
          }

      });
      Boolean result = future.get(5,TimeUnit.SECONDS);

        if(result){
          System.out.println("ok");
      }
      executor.shutdown();//завершает работу с executor
    }
}