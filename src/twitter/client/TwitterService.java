package twitter.client;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

@Path("/twitterservice")
public class TwitterService {
		
	public static Twitter TWITTER;
		
	public static void loadTwitter(){
		
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
	@Path("/test")
	@Produces("application/json")
	public Response getUserTimeline(){

		List<Status> statuses;
		String result ="";
		loadTwitter();

		try {
			statuses = TWITTER.getHomeTimeline();
			
			for (Status s : statuses) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("Status", s);
				result += " "+jsonObject;
			}
			System.out.println("ici");

		} catch (TwitterException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(result).build();
	}
}
