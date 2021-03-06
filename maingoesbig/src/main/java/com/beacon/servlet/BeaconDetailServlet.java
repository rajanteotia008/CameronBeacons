package com.beacon.servlet;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.*;
import javax.servlet.http.*;
import com.sun.jersey.core.util.Base64;

public class BeaconDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public BeaconDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String identifier = request.getParameter("identifier");
		String webPage = "https://cloud.estimote.com/v2/devices/"+identifier;
		String name = "renault-3ii";
		String password = "9e8e206ef03d63c9ec64cc31ef00033d";
		String authString = name + ":" + password;
		byte[] authEncBytes = Base64.encode(authString.getBytes());
		String authStringEnc = new String(authEncBytes);
		URL url = new URL(webPage);
		URLConnection urlConnection = url.openConnection();
		urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
		InputStream is = urlConnection.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		int numCharsRead;
		char[] charArray = new char[1024];
		StringBuffer sb = new StringBuffer();
		while ((numCharsRead = isr.read(charArray)) > 0) {
			sb.append(charArray, 0, numCharsRead);
		}
		response.getWriter().write(sb.toString());
	}
}
