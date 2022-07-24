package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {
    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출: " +url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect(){
        System.out.println("connect: " + url);
    }
    public void call(String m){
        System.out.println("call: "+url+" message: "+m);
    }
    public void disconnect(){
        System.out.println("disconnect: "+url);
    }

    // 의존 관계 주입 후 호출
    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메시지");
    }

    // 종료 전 호출
    @Override
    public void destroy() throws Exception {
        disconnect();
    }
}
