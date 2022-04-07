/*
 * package service.aplication.redesocial.twitter;
 * 
 * import java.util.List;
 * 
 * import twitter4j.Status; import twitter4j.Twitter; import
 * twitter4j.TwitterException; import twitter4j.TwitterFactory; import
 * twitter4j.User; import twitter4j.auth.RequestToken; import
 * twitter4j.conf.ConfigurationBuilder;
 * 
 * public class TwitterImplements {
 * 
 * public static void main(String[] args) {
 * 
 * ConfigurationBuilder cb = new ConfigurationBuilder(); // the following is set
 * without accesstoken- desktop client
 * cb.setDebugEnabled(true).setUser("pablomaiden").setPassword("81264059pa").
 * setOAuthConsumerKey("zm22F9rFEB0RBP2qPjp0bMKwE").setOAuthConsumerSecret(
 * "zY8OyBPDorKXxlwxCHvVOaz8AhbXCnZF8hmAIbOPRF2qr5wUj6");
 * 
 * try { // gets Twitter instance with default credentials Twitter twitter = new
 * TwitterFactory(cb.build()).getInstance();
 * 
 * RequestToken requestToken; requestToken = twitter.getOAuthRequestToken();
 * 
 * User user = twitter.verifyCredentials(); List<Status> statuses =
 * twitter.getHomeTimeline(); System.out.println("Showing @" +
 * user.getScreenName() + "'s home timeline."); for (Status status : statuses) {
 * System.out.println("@" + status.getUser().getScreenName() + " - " +
 * status.getText()); } } catch (TwitterException te) { te.printStackTrace();
 * System.out.println("Failed to get timeline: " + te.getMessage());
 * System.exit(-1); } } }
 */