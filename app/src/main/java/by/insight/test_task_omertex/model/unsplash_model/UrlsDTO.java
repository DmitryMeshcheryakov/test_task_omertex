package by.insight.test_task_omertex.model.unsplash_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class UrlsDTO implements Serializable {
    @SerializedName("raw")
    @Expose
    private String raw;
    @SerializedName("full")
    @Expose
    private String full;
    @SerializedName("regular")
    @Expose
    private String regular;
    @SerializedName("small")
    @Expose
    private String small;
    @SerializedName("thumb")
    @Expose
    private String thumb;

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public UrlsDTO withRaw(String raw) {
        this.raw = raw;
        return this;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public UrlsDTO withFull(String full) {
        this.full = full;
        return this;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public UrlsDTO withRegular(String regular) {
        this.regular = regular;
        return this;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public UrlsDTO withSmall(String small) {
        this.small = small;
        return this;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public UrlsDTO withThumb(String thumb) {
        this.thumb = thumb;
        return this;
    }

}
