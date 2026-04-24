package tr.com.huseyinaydin.domain.models;

public enum VoteType {
    NONE(-1),
    DOWNVOTE(0),
    UPVOTE(1);

    private final int value;

    VoteType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
