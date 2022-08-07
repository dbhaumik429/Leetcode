package lambda.CompletableFuture;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompletableFutureInRecursion {

    private static List<Integer> getBalancesBulk(List<String> accountIds){

        if(accountIds.contains("5"))
            throw new NullPointerException("lol!!!");

        return List.of(100,200,300);
    }



    private static List<Integer> getBalances(String accountId){

        int acId = Integer.parseInt(accountId);

        if(acId == 5)
            throw new NullPointerException("lol!!!");

        if(acId % 2 == 0)
            return List.of(1,2,3);

        return List.of(100,200,300);
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Map<String, List<Integer>> balances = new HashMap<>();

        long startProcessingTime = System.currentTimeMillis();


        List<CompletableFuture<Void>> collect = normalCall(executorService, balances);
        try{
            for (CompletableFuture<Void> voidCompletableFuture : collect) {
                //voidCompletableFuture.get();
                voidCompletableFuture.join();
            }
        }
        //catch(java.lang.InterruptedException |java.util.concurrent.ExecutionException e ){
        catch(Exception e ){
            e.printStackTrace();
        }

        long timeTaken = System.currentTimeMillis() - startProcessingTime;
        System.out.println("Total time taken -> " + timeTaken);

        executorService.shutdown();

        System.out.println(balances);
    }


    private static List<CompletableFuture<Void>> normalCall(ExecutorService executorService, Map<String, List<Integer>> balances) {
        List<String> accIds = IntStream.range(0,11).mapToObj(String::valueOf).collect(Collectors.toList());


        List<CompletableFuture<Void>> collect = accIds.stream().map(a -> CompletableFuture.supplyAsync(() ->{
                            //System.out.println("first -> " + a +" running on "+ Thread.currentThread().getName());
                            return getBalances(a);
                        } , executorService)
                        .thenAccept(x -> {
                            //System.out.println("Second -> " + a +" running on "+ Thread.currentThread().getName());

                            if (x != null) {
                                balances.put(a, x);
                            }else
                                balances.put(a, null);
                        })
                )
                .collect(Collectors.toList());

        return collect;
    }
}
