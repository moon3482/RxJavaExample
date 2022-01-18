import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;


/*****************************************
 ********** Observable 생성자 *************
 *****************************************/

/**
 * Observable
 */

/**
 * 기본 Observable Create 생성연산자에 따라 동작이 달라진다.
 */
class ExampleObservable {
    public static void main(String[] args) {
//        String[] strArray = {"hi", "hello", "안녕", "나는", "제니퍼에요"};
        System.out.println();
        Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                        emitter.onNext("1");
                        emitter.onNext("2");
                        emitter.onComplete();
//                        emitter.onError(new Throwable("에러가 남"));
                        emitter.onNext("3");
                        emitter.onNext("5");

                    }
                })
                .subscribe(next -> {
                    System.out.println(next);
                }, error -> {
                    System.out.println(error);
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("complete!!");
                    }
                });

    }
}


/**
 * Single
 */
/**
 * 특징 : 데이터를 1개만 발행하는 Observable
 */

class ExampleSingle {
    public static void main(String[] args) {
        Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<String> emitter) throws Exception {
                emitter.onSuccess("데이터 발행!");
                emitter.onSuccess("데이터");
            }
        }).subscribe(
                s -> {
                    System.out.println(s);
                },
                e -> {
                    System.out.println(e);
                }
        );

//        System.out.println("-----------Just------------");
//        Single.just(1)
//                .subscribe(s->{System.out.println(s);});
    }
}

/**
 * Maybe
 */

/**
 * 특징 : 데이터를 발행안할 수 도(Null) 1개만 발행 할 수 도 있는 Observable
 */
class ExampleMaybe {
    public static void main(String[] args) {
        Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull MaybeEmitter<String> emitter) throws Exception {
//                emitter.onSuccess("데이터 발행!");
//                emitter.onSuccess("데이터발행2");
                //emitter.onError(new Throwable("에러 발생!"));
                emitter.onComplete();
            }
        }).subscribe(
                s -> {
                    System.out.println(s);
                },
                e -> {
                    System.out.println(e);
                },
                () -> {
                    System.out.println("Complete!!!");
                }
        );


    }
}

/**
 * Completable
 */

/**
 * 데이터를 발행하지 않고
 */

class ExampleCompletable {
    public static void main(String[] args) {
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
//                emitter.onError(new Throwable("에러"));
                emitter.onComplete();
            }
        }).subscribe(new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("컴플리트!");
            }
        });

        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                emitter.onComplete();
            }
        }).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("컴플리트");
            }
        }).subscribe();
    }

}

class ApplyObservable {
    public static void main(String[] args) {

        Observer<Integer> observer = new Observer<Integer>() {
            Disposable disposable;
            @Override
            public void onSubscribe(@NonNull Disposable d) {
             disposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                if (integer %2 == 0){
                    System.out.println( "2의 배수! 값은 : "+integer);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("에러났음!");
            }

            @Override
            public void onComplete() {
                System.out.println("컴플리트!");
            }
        };

        Observable.just(1,2,3,4,5,6,7,8,9,10)
                .subscribe(observer);



//        Observable<String> observable = Observable.just("안녕", "하세", "요쿠", "르트");
//
//        observable.subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                System.out.println(s);
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                    System.out.println("에러남!"+throwable.toString());
//            }
//        }, new Action() {
//            @Override
//            public void run() throws Exception {
//                    System.out.println("끝남!");
//            }
//        }, new Consumer<Disposable>() {
//            @Override
//            public void accept(Disposable disposable) throws Exception {
//                System.out.println("구독!");
//            }
//        });
//
//        observable.subscribe(
//                s -> System.out.println("두번째 구독자 : " + s),
//                e->{System.out.println("두번째 구독자 : " +e);},
//                ()->{System.out.println("두번째 구독자 : 끝남!");},
//                o->{System.out.println("두번째 구독자 : 구독!" );});


    }


}






