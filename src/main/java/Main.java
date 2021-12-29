


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class Main {
    public static void main(String[] args) {


        int i = 1;
        String k = 1 == i ? "one" : "not one";

        System.out.println(k);


        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("dkdkdkkdk");
                emitter.onComplete();
            }
        });

        Observable.just("a")
                .doOnNext(System.out::println)
                .doOnNext(s -> {
                    System.out.println(s + s + s);
                })
                .subscribe();

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println(s);
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };


    }
}
