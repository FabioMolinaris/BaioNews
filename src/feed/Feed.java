package feed;

import java.util.ArrayList;
import java.util.List;

public class Feed {

    private String title;
    private String link;
    private String category;
    private String language;
    private String copyright;
    private String pubDate;
    private String guId;


    private List<FeedMessage> entries = new ArrayList<FeedMessage>();

    public Feed(String title, String link, String category, String language,
                    String copyright, String pubDate, String guid) {
            this.title = title;
            this.link = link;
            this.category = category;
            this.language = language;
            this.copyright = copyright;
            this.pubDate = pubDate;
            this.setGuId(guid);
    }

    public List<FeedMessage> getMessages() {
            return entries;
    }

    public String getTitle() {
            return title;
    }

    public String getLink() {
            return link;
    }

    public String getCategory() {
            return category;
    }

    public String getLanguage() {
            return language;
    }

    public String getCopyright() {
            return copyright;
    }

    public String getPubDate() {
            return pubDate;
    }

    @Override
    public String toString() {
            return "Feed [copyright=" + copyright + ", description=" + category
                            + ", language=" + language + ", link=" + link + ", pubDate="
                            + pubDate + ", title=" + title + "]";
    }

	public String getGuId() {
		return guId;
	}

	public void setGuId(String guId) {
		this.guId = guId;
	}

}
