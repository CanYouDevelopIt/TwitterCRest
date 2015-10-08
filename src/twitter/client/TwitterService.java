package twitter.client;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

@Path("/twitterservice")
public class TwitterService {

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
	@Path("/timeline")
	@Produces("application/json")
	public Response getUserTimeline(){
		
		JSONArray listStatus = new JSONArray();
		List<Status> statuses;
		
		if (TWITTER == null)
			login();

		try {
			statuses = TWITTER.getHomeTimeline();
			
			for (Status s : statuses) {
				JSONObject jStatus = new JSONObject();
				jStatus.append("text", s.getText());
				jStatus.append("date", s.getCreatedAt().toString());
				jStatus.append("user", s.getUser().getScreenName());
				listStatus.put(jStatus);
			}

		} catch (TwitterException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(listStatus.toString()).build();
	}

	@GET
	@Path("/tweet/{text}")
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

	@GET
	@Path("/friendstimeline/{user}")
	@Produces("application/json")
	public Response getFriendsTimeline(@PathParam("user") String user){

		String result ="";
		if (TWITTER == null)
			login();

        try {
            List<Status> statuses;
            statuses = TWITTER.getUserTimeline(user);
            System.out.println("Showing @" + user + "'s user timeline.");
            for (Status status : statuses) {
            	JSONObject jsonObject = new JSONObject();
				jsonObject.put("Status", status);
				result += " "+jsonObject;
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }

		return Response.status(200).entity(result).build();
	}

}
