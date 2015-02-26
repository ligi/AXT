package org.ligi.axt.adapters;

public class LinkWithDescriptionAndTitle extends LinkWithDescription {

    private String title;

    public LinkWithDescriptionAndTitle(String _url, String _description, String title) {
        super(_url, _description);
        this.setTitle(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
