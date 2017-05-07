
package sekhar.mitra.dora.blink.model.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment_ {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("authorname")
    @Expose
    private String authorname;
    @SerializedName("datecreate")
    @Expose
    private String datecreate;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("path_alias")
    @Expose
    private String pathAlias;
    @SerializedName("realname")
    @Expose
    private String realname;
    @SerializedName("_content")
    @Expose
    private String content;
    @SerializedName("iconurls")
    @Expose
    private Iconurls iconurls;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(String datecreate) {
        this.datecreate = datecreate;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getPathAlias() {
        return pathAlias;
    }

    public void setPathAlias(String pathAlias) {
        this.pathAlias = pathAlias;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Iconurls getIconurls() {
        return iconurls;
    }

    public void setIconurls(Iconurls iconurls) {
        this.iconurls = iconurls;
    }

}
