
package sekhar.mitra.dora.blink.model.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("comment")
    @Expose
    private Comment_ comment;
    @SerializedName("stat")
    @Expose
    private String stat;

    public Comment_ getComment() {
        return comment;
    }

    public void setComment(Comment_ comment) {
        this.comment = comment;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

}
