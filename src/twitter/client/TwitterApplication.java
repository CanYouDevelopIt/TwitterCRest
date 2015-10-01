package twitter.client;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterApplication {

	public static Twitter TWITTER;
	private static RequestToken requestToken;
	private static final String CONSUMER_KEY = "tn9B8rJuMH3bmgKVp7FvQD0xh";
	private static final String CONSUMER_SECRET = "INhpHm7gPicywvRdTUdFEUu7A28hpU7z095LfavDJOeBy4F7y1";
	private static final String ACCESS_KEY = "634219313-PkCQMvKxBVdBYODAGIkxScEC5rfHhojOPmZ1709U";
	private static final String ACCESS_SECRET = "ikVJ073Z4RyNVOsNkLkAZ58DLFs2SwGG2eYSoorcaxUxo";
		
	public void load(){
		
	    try {
	        ConfigurationBuilder cb = new ConfigurationBuilder();
	        cb.setDebugEnabled(true)
	                .setOAuthConsumerKey(CONSUMER_KEY)
	                .setOAuthConsumerSecret(CONSUMER_SECRET)
	                .setOAuthAccessToken(ACCESS_KEY)
	                .setOAuthAccessTokenSecret(ACCESS_SECRET);

	        TwitterFactory tf = new TwitterFactory(cb.build());
	        TWITTER = tf.getInstance();
	        TWITTER.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
            requestToken = TWITTER.getOAuthRequestToken();
	    }
	    catch (TwitterException te) {
	    	System.out.println("TWEET FAILED");
	    }
		
	}
	
}
