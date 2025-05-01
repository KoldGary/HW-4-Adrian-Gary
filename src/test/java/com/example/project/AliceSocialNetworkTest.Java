package socialnetwork;

import java.util.*;
import java.util.regex.*;

public class User {
    private String username;
    private List<String> timeline = new ArrayList<>();
    private Set<User> following = new HashSet<>();
    private List<String> mentions = new ArrayList<>();
    private SocialNetwork network;

    public User(String username, SocialNetwork network) {
        this.username = username;
        this.network = network;
    }

    public void postMessage(String message) {
        String processedMessage = convertUrlsToLinks(message);
        timeline.add(processedMessage);
        notifyMentionedUsers(processedMessage);
    }

    public List<String> getTimeline() {
        return timeline;
    }

    public void follow(User other) {
        following.add(other);
    }

    public List<String> getAggregatedTimeline() {
        List<String> aggregated = new ArrayList<>();
        for (User user : following) {
            aggregated.addAll(user.getTimeline());
        }
        return aggregated;
    }

    public List<String> getMentions() {
        return mentions;
    }

    private void notifyMentionedUsers(String message) {
        for (String word : message.split("\\s+")) {
            if (word.startsWith("@")) {
                String mentionedUsername = word.substring(1);
                User mentionedUser = network.getUser(mentionedUsername);
                if (mentionedUser != null) {
                    mentionedUser.receiveMention(message);
                }
            }
        }
    }

    private void receiveMention(String message) {
        mentions.add(message);
    }

    private String convertUrlsToLinks(String message) {
        String urlRegex = "(https?://[\\w\\-\\.\\?\\,\\'/\\\\\\+&%\\$#_=~]+)";
        Pattern pattern = Pattern.compile(urlRegex);
        Matcher matcher = pattern.matcher(message);
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String url = matcher.group(1);
            matcher.appendReplacement(result, "<a href=\"" + url + "\">" + url + "</a>");
        }
        matcher.appendTail(result);
        return result.toString();
    }

    public String getUsername() {
        return username;
    }
}