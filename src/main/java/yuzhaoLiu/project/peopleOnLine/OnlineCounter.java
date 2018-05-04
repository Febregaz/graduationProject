package yuzhaoLiu.project.peopleOnLine;

public class OnlineCounter {
    private long online = -2;
    public long getOnline() {
        return this.online;
    }
    public void setOnline(Long online){
        this.online=online;
    }
    public void raise(){
        this.online++;
    }
    public void reduce(){
        this.online--;
    }
}