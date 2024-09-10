package tw.test.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import tw.test.apis.WSClient;

@ServerEndpoint("/mycenter")
public class MyCenter {
	private static HashSet<Session> sessions;
	private static HashMap<String,WSClient> users;
	private static boolean hasClient1;
	
	public MyCenter() {
		if(sessions == null) {
			sessions = new HashSet<Session>();
			users = new HashMap<String,WSClient>();
		}
	}
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("OnOpen");
		if(sessions.add(session)) {
			WSClient user = new WSClient();
			user.setSession(session);
			users.put(session.getId(),user);
			System.out.println("count : "+sessions.size());
		}
	}

	@OnMessage
	public void onMessage(String mesg, Session session) {
		System.out.println("onMessage:" + mesg);
		JSONObject root = new JSONObject(mesg);
		if(root.getBoolean("isInit")) {
			if(root.getBoolean("isClient1")) {
				if(!hasClient1) {
					hasClient1 = true;
					users.get(session.getId()).setClient1(true);
					System.out.println("isClient1");
				}else {
					try {
						session.close();
					} catch (IOException e) {}
				}
			}else {
				users.get(session.getId()).setClient1(false);
			}
		}else {
			JSONObject sendMesg = new JSONObject();
			if(root.getBoolean("isClear")) {
				sendMesg.put("isClear",true);
			}else {
				sendMesg.put("isClear",false);
				sendMesg.put("isNewLine",root.getBoolean("isNewLine"));
				sendMesg.put("x",root.getInt("x"));
				sendMesg.put("y",root.getInt("y"));
			}
			
			Set<String> ids = users.keySet();
			for(String id:ids) {
				if(!users.get(id).isClient1()) {
					try {
						users.get(id).getSession().getBasicRemote().sendText(sendMesg.toString());
					} catch (Exception e) {}
				}
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
