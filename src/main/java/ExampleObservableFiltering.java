import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

import java.util.concurrent.TimeUnit;

public class ExampleObservableFiltering {


}

/**
 * Debounce - 이벤트 발생 후 설정시간동안 아무런 이벤트가 발생하지 않으면 데이터가 발행되는 필터링
 */
class ExampleDebounce {
    public static void main(String[] args) {

        Observable.interval(1000L, 1000L, TimeUnit.MILLISECONDS).debounce(900L, TimeUnit.MILLISECONDS).subscribe(s -> {
            System.out.println("데이터1 : " + s);
        });
        Observable.interval(1000L, 1000L, TimeUnit.MILLISECONDS).map(new Function<Long, Object>() {
            @Override
            public Object apply(@NonNull Long aLong) throws Exception {
                System.out.println("map데이터 : " + aLong);
                return aLong;
            }
        }).debounce(2000L, TimeUnit.MILLISECONDS).subscribe(s -> {
            System.out.println("데이터2 : " + s);
        });

        while (true) {
        }
    }
}

/**
 * Distinct - 한번도 발행되지 않은 항목을 발행시키는 필터
 */
class ExampleDistinct {
    public static void main(String[] args) {
        Observable.just(1, 2, 1, 1, 2, 3, 3, 4, 1, 5).distinct().subscribe(s -> {
            System.out.println("데이터 : " + s);
        });
    }
}

/**
 * ElementAt - 특정 순서에 있는 데이터를 발행하는 필터
 */
class ExampleElementAt {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).elementAt(4).subscribe(s -> {
            System.out.println("데이터 : " + s);
        });
    }
}

/**
 * filter - 원하는 조건의 데이터만 발행해주는 필터
 */
class ExampleFilter {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filter(obj -> obj % 2 == 0).subscribe(s -> {
            System.out.println("데이터 : " + s);
        });
    }
}

/**
 * first - 첫번째 데이터만 발행하는 필터 만약 데이터가 없다면 기본값으로 설정한 데이터를 발행한다.
 */
class ExampleFirst {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).first(1000).subscribe(s -> {
            System.out.println("데이터 : " + s);
        });
        Observable.empty().first(1000).subscribe(s -> {
            System.out.println("데이터 : " + s);
        });
    }
}

/**
 * IgnoreElements - onNext 를 모두 무시하고 onError 와 onComplete 만 발행하는 필터
 */
class ExampleIgnoreElements {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
                emitter.onError(new Throwable("에러났어요~"));
//                emitter.onComplete();
            }
        }).ignoreElements().subscribe(()->{System.out.println("컴플리트!");},e->{System.out.println(e);});
    }
}

class ExampleLast {
    public static void main(String[] args) {

    }
}

class ExampleSample {
    public static void main(String[] args) {

    }
}

class ExampleSkip {
    public static void main(String[] args) {

    }
}

class ExampleSkipLast {
    public static void main(String[] args) {

    }
}

class ExampleTake {
    public static void main(String[] args) {

    }
}

class ExampleTakeLast {
    public static void main(String[] args) {

    }
}