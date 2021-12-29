import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

/**********************************
 ************  Subject  ***********
 **********************************/

/**
 * PublishSubject
 */
class ExamplePublishSubject {
    public static void main(String[] args) {
        String a = "안녕";
//        Observable<String> observable = Observable.just(a);
        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe(s -> {
            System.out.println("첫번째 구독자 : " + s);
        });
        subject.onNext("첫번째 데이터");
        subject.onNext("두번째 데이터");
        subject.onNext("세번째 데이터");
        subject.subscribe(s -> {
            System.out.println("두번째 구독자 : " + s);
        });
        subject.onNext("네번째 데이터");
        subject.onNext("다섯번째 데이터");
//        subject.onComplete();
//        observable.subscribe(subject);

    }
}

/**
 * BehaviorSubject
 */
class ExampleBehaviorSubject {
    public static void main(String[] args) {
        BehaviorSubject<String> subject = BehaviorSubject.create();
        subject.subscribe(s -> {
            System.out.println("첫번째 구독자 : " + s);
        });
        subject.onNext("첫번째 데이터");
        subject.onNext("두번째 데이터");
        subject.onNext("세번째 데이터");
        subject.subscribe(s -> {
            System.out.println("두번째 구독자 : " + s);
        });
        subject.onNext("네번째 데이터");
        subject.onNext("다섯번째 데이터");
        subject.subscribe(s -> {
            System.out.println("세번째 구독자 : " + s);
        });
        subject.onComplete();

    }
}

/**
 * AsyncSubject
 */
class ExampleAsyncSubject {
    public static void main(String[] args) {
        String[] strings = new String[]{"1", "2", "3"};
        Observable<String> source = Observable.fromArray(strings);

        AsyncSubject<String> subject = AsyncSubject.create();
        subject.onNext("1");
        subject.onNext("2");
        subject.onNext("3");
        subject.subscribe(s -> {

            System.out.println("첫번째 구독자 : " + s);
        });
        subject.onNext("4");
        subject.onNext("5");
        subject.subscribe(s -> {
            System.out.println("두번째 구독자 : " + s);
        });
        subject.onNext("6");
        subject.onNext("7");
        subject.onComplete();
        subject.subscribe(s -> {
            System.out.println("세번째 구독자 : " + s);
        });
        subject.subscribe(s -> {
            System.out.println("네번째 구독자 : " + s);
        });



        
    }

}

/**
 * ReplaySubject
 */
class ExampleReplaySubject {
    public static void main(String[] args) {
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.subscribe(s -> {
            System.out.println("첫번째 구독자 : " + s);
        });
        subject.onNext("첫번째 데이터");
        subject.onNext("두번째 데이터");
        subject.onNext("세번째 데이터");
        subject.subscribe(s -> {
            System.out.println("두번째 구독자 : " + s);
        });
        subject.onNext("네번째 데이터");
        subject.onNext("다섯번째 데이터 ");
        subject.onComplete();


    }
}

class ApplySubject {
    public static void main(String[] args) {
        PublishSubject<String> subject = PublishSubject.create();

        Disposable scheduler = subject.subscribe(s->{System.out.println("첫번째 구독자 : " + s);});
        Disposable disposable = subject.subscribe(s -> {
            System.out.println("두번째 구독자 : " + s);
        });
        subject.onNext("무");
        subject.onNext("야");
        subject.onNext("호");
        scheduler.dispose();
        subject.onNext("무");
        subject.onNext("한");
        subject.onNext("도");
        subject.onNext("전");
        disposable.dispose();

    }
}