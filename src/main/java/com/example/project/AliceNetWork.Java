public class SocialNetwork {
    private Map<String, User> users = new HashMap<>();

    public User registerUser(String username) {
        User user = new User(username, this);
        users.put(username, user);
        return user;
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public List<String> getTimelineOf(String username) {
        User user = users.get(username);
        return user != null ? user.getTimeline() : new ArrayList<>();
    }
}