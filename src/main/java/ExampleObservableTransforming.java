import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ExampleObservableTransforming {
}

/**
 * Buffer
 */
class ExampleObservableBuffer {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).buffer(3).subscribe(System.out::println);

        Observable.just(1,2,3,4,5,6,7,8,9,10).buffer(4,5).subscribe(System.out::println);

        Observable.just(1,2,3,4,5,6,7,8,9,10).buffer(4,  new Callable<Collection<? super Integer>>() {
            @Override
            public Collection<? super Integer> call() throws Exception {
                return new ArrayList<>(Arrays.asList(1,2,3,4,5));
            }
        }).subscribe(System.out::println);

        Observable.just(1,2,3,4,5,6,7,8,9,10).buffer(50,1000, TimeUnit.SECONDS).subscribe(System.out::println);


    }
}


