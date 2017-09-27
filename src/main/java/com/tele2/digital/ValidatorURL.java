package com.tele2.digital;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ValidatorURL {
    private static ExecutorService poolExecutor = Executors.newFixedThreadPool(5);

    public static void validateURL(Incident incident){

        List<Redirect> redirects = incident.getListRedirects();
        List<Future<Integer>> futures = new ArrayList<Future<Integer>>();

        for(Redirect redirect: redirects){
            String url_destination = redirect.getUrlDestination();
            futures.add(poolExecutor.submit(new TaskExecutor(url_destination)));
        }

        for(int i = 0; i < redirects.size(); i++){
            while(!futures.get(i).isDone()){
                try{
                    Thread.sleep(50);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            try {
                redirects.get(i).setValidateStatusCode(futures.get(i).get());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        shotdown();
        System.out.println("ThreadPoolExecutor - shotdown");
    }

    public static void shotdown(){
        poolExecutor.shutdown();
    }

}
