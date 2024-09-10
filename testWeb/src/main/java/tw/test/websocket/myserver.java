package tw.test.websocket;

import java.util.HashMap;
import java.util.HashSet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/myserver")
public class myserver {
	
	private static HashSet<Session> sessions;
	private static HashMap<String,Session> users;

	public myserver() {
		System.out.println("MyServer");
		if(sessions == null) {
			sessions = new HashSet<Session>();
			users = new HashMap<String,Session>();
		}
	}
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("OnOpen");
		if(sessions.add(session)) {
			users.put(session.getId(),session);
			System.out.println("count : "+sessions.size());
		}
	}

	@OnMessage
	public void onMessage(String mesg, Session session) {
		System.out.println("onMessage" + mesg);
		
		for(Session user :sessions) {
			try {
				user.getBasicRemote().sendText(mesg);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		users.remove(session.getId());
		sessions.remove(session);
		System.out.println("onClose : " + sessions.size());
	}
	
	@OnError
	public void onError(Session session, Throwable t) {
		System.out.println("onError :" + t.toString());
	}
}