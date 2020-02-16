package org.golie.bot.logic.objects;


import java.util.Set;

public class Image {

    private String url;
    private Set<String> tags;

    public Image(String url, Set<String> tags)
    {
        this.url = url;
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
