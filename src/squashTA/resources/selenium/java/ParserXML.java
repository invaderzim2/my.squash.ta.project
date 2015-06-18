import java.io.File; 
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory; 
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import java.lang.Class.*;

public class ParserXML {
	
	private String login = "7@7.ru";
	private String password = "123456";
	private String browser = "chrome";
	private String loginCode = "55151e0be4b0900d57ce23f9___$6$8a106f15-5a38-46$gfGvx2ZHd5/xV60mwvfsXrfbzFVcoTyba2EfMMiz.D2uwQGVfE4ZJ2kmGudtLn8fkfXYLCJmL2VUfVpQCZ/wj0";
	private String server = "UAT";
	private boolean Ylogin = false;
	private boolean Ypassword = false;
	private boolean Ybrowser = false;
	private boolean YloginCode = false;
	private boolean Yserver = false;
	
	public String setLogin(String alternative) {
	    if (Ylogin) {return login; }
	    if (alternative==null) {return login; }
	    return alternative;
	}
	public String setPassword(String alternative) {
	    if (Ypassword) {return password; }
	    if (alternative==null) {return password; }
	    return alternative;
	}
	public String setBrowser(String alternative) {
	    if (Ybrowser) {return browser; }
	    if (alternative==null) {return browser; }
	    return alternative;
	}
	public String setLoginCode(String alternative) {
	    if (YloginCode) {return loginCode; }
	    if (alternative==null) {return loginCode; }
	    return alternative;
	}
	public String setServer(String alternative) {
	    if (Yserver) {return baseUrl(server); }
	    if (alternative==null) {return baseUrl(server); }
	    return baseUrl(alternative);
	}
	private String baseUrl(String serverName) {
		if (serverName.equals("DEV")) {return "https://game-dev.sw.co.ua/";}
		if (serverName.equals("QA")) {return "https://game-qa.sw.co.ua/";}
		if (serverName.equals("UAT")) {return "https://game-uat.sw.co.ua/";}
		if (serverName.equals("PROD")) {return "https://game.mireyn.ru/";}
		return "No such server";
	}
	public void getData() throws Exception {
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
	    f.setValidating(false);
	    DocumentBuilder builder = f.newDocumentBuilder();
	    Document doc = builder.parse(getClass().getResourceAsStream("xmlDataset.xml"));
	    NodeList children = doc.getChildNodes();
	    Node nLogin = children.item(1);
	    Node nPassword = children.item(1);
	    Node nBrowser = children.item(1);
	    Node nLoginCode = children.item(1);
	    Node nServer = children.item(1);
	    for (int i = 0; i < children.getLength(); i++) {
	         NodeList node = children.item(i).getChildNodes();
	         for (int j = 0; j < node.getLength(); j++) {
	        	 Node node2 = node.item(j);
	        	 if (node2.getAttributes()!=null) {
	        		 NamedNodeMap attributes = node2.getAttributes();
	        		 if (attributes.getNamedItem("login")!=null)
	        		 	{nLogin = attributes.getNamedItem("login"); }
	        		 if (attributes.getNamedItem("password")!=null)
	        		 	{nPassword = attributes.getNamedItem("password"); }
	        		 if (attributes.getNamedItem("browser")!=null)
	        		 	{nBrowser = attributes.getNamedItem("browser"); }
	        		 if (attributes.getNamedItem("loginCode")!=null)
	        		 	{nLoginCode = attributes.getNamedItem("loginCode"); }
	        		 if (attributes.getNamedItem("server")!=null)
	        		 	{nServer = attributes.getNamedItem("server"); }
	        	 }
	         }
	    }

	    if (nLogin!=children.item(1))
	    	if (!(nLogin.getNodeValue().contains("IT_CUF_login")))
	    { login = nLogin.getNodeValue(); Ylogin=true; }

	    if (nPassword!=children.item(1))
	    	if (!(nPassword.getNodeValue().contains("IT_CUF_password")))
	    { password = nPassword.getNodeValue(); Ypassword=true; }

	    if (nBrowser!=children.item(1))
	    	if (!(nBrowser.getNodeValue().contains("IT_CUF_browser")))
	    { browser = nBrowser.getNodeValue(); Ybrowser=true; }
	    
	    if (nLoginCode!=children.item(1))
	    	if (!(nLoginCode.getNodeValue().contains("IT_CUF_loginCode")))
	    { loginCode = nLoginCode.getNodeValue(); YloginCode=true; }
	    
	    if (nServer!=children.item(1))
	    	if (!(nServer.getNodeValue().contains("IT_CUF_server")))
	    { server = nServer.getNodeValue(); Yserver=true; }
	}
}