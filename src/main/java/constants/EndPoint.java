package constants;

public enum EndPoint {

    LOGIN("/");

    public final String url;

    EndPoint(String url) {
        this.url = url;
    }
}
