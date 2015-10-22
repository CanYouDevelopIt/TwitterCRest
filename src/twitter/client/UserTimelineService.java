package twitter.client;

import java.net.URL;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

@Path("/usertimeline")
public class UserTimelineService {

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

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@GET
	@Path("/get")
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
				jStatus.append("image", s.getUser().getProfileImageURL());
				listStatus.put(jStatus);
			}

		} catch (TwitterException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(listStatus.toString()).build();
	}

	@GET
	@Path("/icon")
	@Produces("text/plain")
	public Response getProfileImage(){

		if (TWITTER == null)
			login();
		User user = null;
		URL url = null;
		try{
			user = TWITTER.showUser(TWITTER.getId());
			url = new URL(user.getProfileImageURL());
		}catch(Exception e){
			e.printStackTrace();
		}
		return Response.status(200).entity(url.toString()).build();
	}
	
	@GET
	@Path("/screenname")
	@Produces("text/plain")
	public Response getScreenName(){

		if (TWITTER == null)
			login();
		User user = null;
		URL url = null;
		try{
			user = TWITTER.showUser(TWITTER.getId());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return Response.status(200).entity(user.getScreenName()).build();
	}
	
	@GET
	@Path("/auth")
	@Produces("text/plain")
	public Response OAuth(){
		
		if (TWITTER == null)
			login();
		
		Boolean reponse = (TWITTER != null);
	
		return Response.status(200).entity(reponse.toString()).build();
	}
	
}
