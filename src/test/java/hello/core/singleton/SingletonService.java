package hello.core.singleton;

public class SingletonService {
    // static 영역에 1개만 생성
    private static final SingletonService instance = new SingletonService();

    // 조회 방법
    public static SingletonService getInstance(){
        return instance;
    }
    // 다른 곳에서 new 불가능
    private SingletonService(){
    }
}
