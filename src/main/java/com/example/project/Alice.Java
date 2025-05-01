package socialnetwork;

import java.util.*;

public class AliceUser {
    private String username;
    private List<String> timeline = new ArrayList<>();
    private Set<User> following = new HashSet<>();

    public AliceUser(String username) {
        this.username = username;
    }

    public void postMessage(String message) {
        timeline.add(message);
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
}