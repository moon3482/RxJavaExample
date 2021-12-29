import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
/**********************************
 *********** 생산 연산자  ***********
 **********************************/


/**
 * create
 */
class ExampleObservableCreate {
    public static void main(String[] args) {

        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello");
                emitter.onNext("Rxjava!");
//                emitter.onError(new Throwable("에러가 발생"));
                emitter.onComplete();
            }
        });
        observable.subscribe(
                new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println(throwable);
                    }
                },
                new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Complete!!!");
                    }
                }
        );
    }
}

/**
 * Just
 */

class ExampleObservableJust {
    public static void main(String[] args) {


        Observable observable = Observable.just("hi", "bye", "ok", "no");

        observable.subscribe(
                s -> {
                    System.out.println(s);
                },
                e -> System.out.println(e),
                () -> {
                    System.out.println("Complete");
                }
        );

    }
}

/**
 * Defer
 */
class ExampleObservableDefer {
    public static void main(String[] args) {
        String a = "Hi";
        Observable observable = Observable.defer(() -> Observable.just(a));


        observable.subscribe(s -> {
            System.out.println(s);
        }, e -> {
            System.out.println(e);
        }, () -> {
            System.out.println("Complete!!");
        });
        observable.subscribe(s -> {
            System.out.println(s);
        }, e -> {
            System.out.println(e);
        }, () -> {
            System.out.println("Complete!!");
        });
    }
}

/**
 * Empty
 */
class ExampleObservableEmpty {
    public static void main(String[] args) {
        Observable.empty().subscribe(
                s -> {
                    System.out.println(s);
                },
                e -> {
                    System.out.println(e);
                },
                () -> {
                    System.out.println("Complete!!");
                }
        );
    }
}

/**
 * Never
 */
class ExampleObservableNever {
    public static void main(String[] args) {
        Observable.never()
                .subscribe(
                        s -> {
                            System.out.println(s);
                        },
                        e -> {
                            System.out.println(e);
                        },
                        () -> {
                            System.out.println("Complete!!");
                        }
                );
    }
}

/**
 * Throw
 */
class ExampleObservableThrow {
    public static void main(String[] args) {
        Observable.error(new Throwable("에러입니다!"))
                .subscribe(
                        s -> {
                            System.out.println(s);
                        },
                        e -> {
                            System.out.println(e);
                        },
                        () -> {
                            System.out.println("Complete!!");
                        }
                );
    }
}

/**
 * From
 */
class ExampleObservableFrom {
    public static void main(String[] args) {
        String[] strArray = {"hello", "Rx", "Java", "Hi"};
        Observable.fromArray(strArray)
                .subscribe(System.out::println);

        Integer[] intArray = {1, 2, 3, 4, 5, 6};
        Observable.fromArray(intArray)
                .subscribe(System.out::println);

        List<String> stringArrayList = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        Observable.fromIterable(stringArrayList)
                .subscribe(System.out::println);

        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return "Callable 데이터 발행";
            }
        };
        Observable.fromCallable(callable).subscribe(System.out::println);
        Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable 데이터발행");
            }
        }).subscribe(System.out::println, System.out::println);

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture schedule = executor.schedule(() -> {
            return "무야호";
        }, 1, TimeUnit.MILLISECONDS);
        Observable<String> observable = Observable.fromFuture(schedule);
        observable.subscribe(System.out::println, System.out::println, System.out::println);
    }
}


/**
 * Interval
 */
class ExampleObservableInterval {
    public static void main(String[] args) {
        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .subscribe(System.out::println);

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Range
 */
class ExampleObservableRange {
    public static void main(String[] args) {
        Observable.range(20, 10)
                .subscribe(System.out::println);
    }
}

/**
 * Repeat
 */
class ExampleObservableRepeat {
    public static void main(String[] args) {
        Observable.just(1, 2, 3).repeat(3)
                .subscribe(System.out::println);
    }
}

/**
 * Start
 */
class ExampleObservableStart {
    public static void main(String[] args) {

    }
}

/**
 * Timer
 */
class ExampleObservableTimer {
    public static void main(String[] args) {
        Observable.timer(5, TimeUnit.SECONDS).blockingSubscribe(s -> System.out.println("발행!"));
    }
}

