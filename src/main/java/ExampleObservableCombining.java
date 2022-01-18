import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

import java.util.concurrent.TimeUnit;

public class ExampleObservableCombining {
}


class ExampleAnd {
    public static void main(String[] args) {
Observable.just("아","~","모","든","것","이","사","라","지","네");
    }
}

class ExampleThen {
    public static void main(String[] args) {

    }
}

class ExampleWhen {
    public static void main(String[] args) {

    }
}

class ExampleCombineLatest {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("1", "2", "3", "4", "5", "6").zipWith(Observable.interval(1000L, TimeUnit.MILLISECONDS), new BiFunction<String, Long, String>() {
            @NonNull
            @Override
            public String apply(@NonNull String string, @NonNull Long aLong) throws Exception {
                return string;
            }
        });
        Observable<String> observable1 = Observable.just("콩", "된장", "메주", "고추장").zipWith(Observable.interval(1500L,1500L, TimeUnit.MILLISECONDS), new BiFunction<String, Long, String>() {
            @NonNull
            @Override
            public String apply(@NonNull String s, @NonNull Long aLong) throws Exception {
                return s;
            }
        });

        Observable combine = Observable.combineLatest(observable, observable1, new BiFunction<String, String, Object>() {
            @NonNull
            @Override
            public Object apply(@NonNull String s, @NonNull String s2) throws Exception {
                return s+s2;
            }
        });

        combine.subscribe(s->{System.out.println(s);});

        while (true){

        }

    }
}

/**
 * join -
 */
class ExampleJoin {
    public static void main(String[] args) {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);
        Observable<Integer> observable1 = Observable.just(99, 11);

    }
}

/**
 * mergeWith - 여러개의 Observable을 결합하여 하나의 Observable로 만드는 연산자
 */
class ExampleMerge {
    public static void main(String[] args) {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Observable<Integer> observable1 = Observable.fromArray(new Integer[]{99, 98, 97, 96, 95, 94, 93, 92, 91});
        Observable<Integer> observable2 = Observable.just(55, 56, 57, 58);

        observable.mergeWith(observable1).mergeWith(observable2).subscribe(s -> System.out.println(s));
    }
}

/**
 * startWith - 명시적으로 하나의 데이터를 발행 하는 연산자
 */
class ExampleStartWith {
    public static void main(String[] args) {
        Observable.fromArray(new String[]{"하나", "둘", "셋", "넷", "다섯"}).startWith("숫자를 세어보겠습니다.").subscribe(s -> {
            System.out.println(s);
        });
    }
}

/**
 * switchMap - Observable 데이터 발행중 새로운 Observable이 생성되면 Observable을 바꾸어 새로운 Observable로 부터 데이터를 발행 받는다.
 */
class ExampleSwitch {
    public static void main(String[] args) {
        //switchMap
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).switchMap(new Function<Integer, ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> apply(@NonNull Integer integer) throws Exception {
                return Observable.just(integer).delay(1000L, TimeUnit.MILLISECONDS);
            }
        }).subscribe(s -> {
            System.out.println("데이터1 : " + s);
        });
        //flatMap
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).flatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(@NonNull Integer integer) throws Exception {
                return Observable.just(integer).delay(2, TimeUnit.SECONDS);
            }
        }).subscribe(s -> {
            System.out.println("데이터2 : " + s);
        });

        while (true) {
        }
    }
}

/**
 * zip - Observable 데이터를 하나로 합쳐주는 연산자
 */
class ExampleZip {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("A", "B", "C");
        Observable<Long> observable2 = Observable.interval(1000L, TimeUnit.MILLISECONDS);

        Observable.zip(observable2, observable, new BiFunction<Long, String, Object>() {
            @NonNull
            @Override
            public Object apply(@NonNull Long aLong, @NonNull String s) throws Exception {
                return aLong + s;
            }
        }).subscribe(s -> {
            System.out.println("데이터 : " + s);
        });

        while (true) {
        }
    }
}
