package login;

import util.PostTool;

public class Post {
	
	public Post(String hostServicername,String id,String propertiesPath,String headMessage,String Text) {
	PostTool post = new PostTool();
	post.setTo(id);
	post.setformImf("toonoisy@qq.com", "gajqmmvsocvzbbff");
	post.setEmailhost("smtp.qq.com");
	post.setProperties(propertiesPath);//"D:\\Webwork\\host.properties"
	post.setmessage(headMessage, Text);
	
	}
	
	

}
