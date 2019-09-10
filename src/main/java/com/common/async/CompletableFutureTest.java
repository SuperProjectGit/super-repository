package com.common.async;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 *
 * CompletableFuture
 * 1. 支持对异步方法的超时调用  orTimeout() completeOnTimeout()
 * 2. 支持延迟调用 Executor delayedExecutor(long delay, TimeUnit unit, Executor executor)
                Executor delayedExecutor(long delay, TimeUnit unit)
 * @author subo
 * @create 2019-08-21 19:46
 **/
public class CompletableFutureTest {

  public static void main(String[] args) {
    //runAsync();
    //System.out.println(supplyAsync());
    //then();
    //thenAsync();
    //compose();
    //combine();
    //all();
    //any();
    //exceptionally();
    handle();
  }

  public static void runAsync() {
    try {
      CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
        try {
          System.out.println(Thread.currentThread().getName());
          TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
      completableFuture.get();
      System.out.println(Thread.currentThread().getName());
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static String supplyAsync() {
    String str = "test";
    try {
      CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
        try {
          System.out.println(Thread.currentThread().getName());
          TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return "supply async is ok!";
      });
      str = completableFuture.get();
      System.out.println(Thread.currentThread().getName());
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return str;
  }

  public static void then() {
    try {
      CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
        try {
          System.out.println(Thread.currentThread().getName());
          TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return "supply async is ok!";
      }).thenApply(temp -> {
        System.out.println(Thread.currentThread().getName());
        String t = temp + "then apply first";
        System.out.println(t);
        return t;
      });
      completableFuture.thenAccept(obj -> {
        System.out.println(Thread.currentThread().getName());
        String t = obj + "then accept first";
        System.out.println(t);
      }).thenRun(() -> {
        System.out.println(Thread.currentThread().getName());
        System.out.println("then run first");
      }).get();
      System.out.println(Thread.currentThread().getName());
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void thenAsync() {
    try {
      CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
        System.out.println(Thread.currentThread().getName());
        return "supply async is ok!";
      }).thenApplyAsync(temp -> {
        System.out.println(Thread.currentThread().getName());
        String t = temp + "then apply async first";
        System.out.println(t);
        return t;
      });
      CompletableFuture<Void> re = completableFuture.thenAcceptAsync(obj -> {
        System.out.println(Thread.currentThread().getName());
        String t = obj + "then accept async first";
        System.out.println(t);
      }).thenRunAsync(() -> {
        System.out.println(Thread.currentThread().getName());
        System.out.println("then run async first");
      });
      TimeUnit.SECONDS.sleep(4);
      System.out.println(Thread.currentThread().getName());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void compose() {
    try {
      CompletableFuture<Integer> temp = CompletableFuture.supplyAsync(() -> {
        Integer a = 1;
        System.out.println(Thread.currentThread().getName() + a);
        return a;
      }).thenCompose(obj -> CompletableFuture.supplyAsync(() -> {
        Integer a = 1;
        Integer t = obj + a;
        System.out.println(Thread.currentThread().getName() + t);
        return t;
      }));
      TimeUnit.SECONDS.sleep(4);
      System.out.println(Thread.currentThread().getName() + temp.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  public static void combine() {
    try {
      CompletableFuture<Integer> temp = CompletableFuture.supplyAsync(() -> {
        Integer a = 1;
        System.out.println(Thread.currentThread().getName() + a);
        return a;
      });
      CompletableFuture<Integer> temp1 = CompletableFuture.supplyAsync(() -> {
        Integer a = 2;
        System.out.println(Thread.currentThread().getName() + a);
        return a;
      });
      CompletableFuture<Integer> t = temp.thenCombineAsync(temp1, (t1, t2) -> {
        Integer obj = t1 + t2;
        System.out.println(Thread.currentThread().getName() + obj);
        return obj;
      });
      TimeUnit.SECONDS.sleep(4);
      System.out.println(Thread.currentThread().getName() + t.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  public static void all() {
    try {
      CompletableFuture<Integer> list[] = new CompletableFuture[3];
      for (int i = 0; i< 3; i++) {
        list[i] = CompletableFuture.supplyAsync(() -> new Random().nextInt(10));
      }
      CompletableFuture.allOf(list).get();
      //System.out.println(Stream.of(list).map(CompletableFuture::join).collect(Collectors.toList()));
      TimeUnit.SECONDS.sleep(4);
      System.out.println(Thread.currentThread().getName());
      Arrays.stream(list).forEach(obj -> {
        try {
          System.out.println(obj.get());
        } catch (ExecutionException e) {
          e.printStackTrace();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  public static void any() {
    try {
      CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
        try {
          TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return "f1 wait 4 seconds";
      });
      CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
        try {
          TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return "f2 wait 2 seconds";
      });
      CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> {
        try {
          TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return "f3 wait 6 seconds";
      });
      System.out.println(CompletableFuture.anyOf(f1, f2, f3).get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  /**
   * 只有出现异常才会处理
   */
  public static void exceptionally() {
    try {
      int age = -1;
      CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
        if (age < 0) {
          throw new IllegalArgumentException("age must greater than 0");
        }
        if (age < 18) {
          return "juveniles";
        }
        return "adult";
      }).exceptionally(throwable -> {
        System.out.println("exceptionally execute");
        throwable.printStackTrace();
        return "exception:" + throwable.getMessage();
      });
      System.out.println(completableFuture.get());
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * 只有出现异常才会处理
   */
  public static void handle() {
    try {
      int age = 1;
      CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
        if (age < 0) {
          throw new IllegalArgumentException("age must greater than 0");
        }
        if (age < 18) {
          return "juveniles";
        }
        return "adult";
      }).handle((res, throwable) -> {
        System.out.println("handle execute");
        if (null != throwable) {
          return "exception:" + throwable.getMessage();
        }
        return res;
      });
      System.out.println(completableFuture.get());
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
