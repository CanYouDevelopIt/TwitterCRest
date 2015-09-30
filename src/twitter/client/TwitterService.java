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

@Path("/twitterservice")
public class TwitterService {

	@GET
	@Produces("application/json")
	public Response getUserTimeline(){

		Twitter twitter = TwitterFactory.getSingleton();
		List<Status> statuses;
		String result ="";
		try {
			statuses = twitter.getHomeTimeline();
			
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
