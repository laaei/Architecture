package 事件系统软件体系结构;

public class KWICSubject extends Subject{
    public void startKWIC(){
        for (int i = 0;i<3;i++){
            super.notifyOneObserver(i);
        }
    }
}
