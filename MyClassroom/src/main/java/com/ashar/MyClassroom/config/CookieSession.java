//
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//public class CookieSession {
//	
//	
//	public CookieSession() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	// function to create cookies for login details of restaurant and NGOs
//	public void setCookieForLogin(Login login, HttpServletResponse res, String email, String password) {
//		Cookie cookie1 = new Cookie(email, login.getUsername());
//		Cookie cookie2 = new Cookie(password, login.getPassword());
//		
//		cookie1.setMaxAge(30*60);
//		cookie1.setSecure(true);
//		cookie1.setHttpOnly(true);
//		
//		cookie2.setMaxAge(30*60);
//		cookie2.setSecure(true);
//		cookie2.setHttpOnly(true);
//		
//		res.addCookie(cookie1);
//		res.addCookie(cookie2);
//	}	
//	
//	public void deleteCookies(HttpServletResponse res, String email, String password) {
//		Cookie cookie1 = new Cookie(email, null);
//		Cookie cookie2 = new Cookie(password, null);
//		
//		cookie1.setMaxAge(0);
//		cookie1.setSecure(true);
//		cookie1.setHttpOnly(true);
//		
//		cookie2.setMaxAge(0);
//		cookie2.setSecure(true);
//		cookie2.setHttpOnly(true);
//		
//		res.addCookie(cookie1);
//		res.addCookie(cookie2);
//	}
//	
//	public String getCookieValue(HttpServletRequest req, String name ) {
//		
//		Cookie cookies[] = req.getCookies();
//		
//		for(Cookie c : cookies) {
//			if(c.getName().equals(name)) {
//				return c.getValue();
//			}
//		}
//		return null;
//	}
//	
//}
