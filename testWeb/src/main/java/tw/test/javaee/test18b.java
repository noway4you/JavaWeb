package tw.test.javaee;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test18b")
public class test18b extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		double rate = Double.parseDouble(request.getParameter("rate"));
		
		BufferedImage img = new BufferedImage(400,20,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0,0,400,20);
		g2d.setColor(Color.RED);
		g2d.fillRect(0,0,(int)(400*rate/100),20);
		
		response.setContentType("image/jpeg");
		ImageIO.write(img,"jpeg",response.getOutputStream());
		response.flushBuffer();
		
	}

}
