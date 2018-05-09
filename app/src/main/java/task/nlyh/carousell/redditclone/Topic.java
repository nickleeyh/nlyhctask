package task.nlyh.carousell.redditclone;

public class Topic {
    private String content;
    private int upvoteCount = 0, downvoteCount = 0;

    public Topic () {}

    public Topic(String content) {
        this.content = content;
    }

    public Topic(String content, int upvoteCount, int downvoteCount) {
        this.content = content;
        this.upvoteCount = upvoteCount;
        this.downvoteCount = downvoteCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUpvoteCount() {
        return upvoteCount;
    }

    public void setUpvoteCount(int upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    public int getDownvoteCount() {
        return downvoteCount;
    }

    public void setDownvoteCount(int downvoteCount) {
        this.downvoteCount = downvoteCount;
    }

    public void incrementUpvoteCount (int increment) {
        upvoteCount += increment;
    }

    public void incrementDownvoteCount (int increment) {
        downvoteCount += increment;
    }
}
