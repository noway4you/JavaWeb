Here is JSP07
<hr>
Lottery = <%= (int)(Math.random()*49+1) %>
<hr>
<% 
	int x = Integer.parseInt(request.getParameter("x")); 
	int y = Integer.parseInt(request.getParameter("y")); 
	out.print(x+" + "+y+" = "+(x+y));	
%>