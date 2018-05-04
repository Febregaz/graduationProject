package yuzhaoLiu.project.peopleOnLine;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {

    OnlineCounter onlineCounter = new OnlineCounter();

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ServletContext context = httpSessionEvent.getSession().getServletContext();
        onlineCounter.raise();
        context.setAttribute("onLine" , onlineCounter.getOnline());
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ServletContext context = httpSessionEvent.getSession().getServletContext();
        if(onlineCounter.getOnline()>=1){
            onlineCounter.reduce();
            context.setAttribute("onLine" , onlineCounter.getOnline());
        }
    }

}
