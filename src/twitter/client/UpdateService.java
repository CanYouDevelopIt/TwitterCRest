package twitter.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


@Path("/update")
public class UpdateService {

	public static Twitter TWITTER;

	public static void login(){

		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			.setOAuthConsumerKey("tn9B8rJuMH3bmgKVp7FvQD0xh")
			.setOAuthConsumerSecret("INhpHm7gPicywvRdTUdFEUu7A28hpU7z095LfavDJOeBy4F7y1")
			.setOAuthAccessToken("634219313-PkCQMvKxBVdBYODAGIkxScEC5rfHhojOPmZ1709U")
			.setOAuthAccessTokenSecret("ikVJ073Z4RyNVOsNkLkAZ58DLFs2SwGG2eYSoorcaxUxo");
			TwitterFactory tf = new TwitterFactory(cb.build());
			TWITTER = tf.getInstance();
			//TWITTER.setOAuthConsumer("tn9B8rJuMH3bmgKVp7FvQD0xh", "INhpHm7gPicywvRdTUdFEUu7A28hpU7z095LfavDJOeBy4F7y1");
			//requestToken = TWITTER.getOAuthRequestToken();
			//openWebpage(new URI(requestToken.getAuthorizationURL()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@GET
	@Path("/send/{text}")
	@Produces("text/xml")
	public void sendTweet(@PathParam("text") String text) {

		if (TWITTER == null)
			login();

		try {
			TWITTER.updateStatus(text);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
