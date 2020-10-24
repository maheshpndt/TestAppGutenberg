
package com.example.gutendex.model_section;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Result {

    public Result(List<Author> mAuthors, List<String> mBookshelves, Long mDownloadCount, Formats mFormats, Long mId, List<String> mLanguages, String mMediaType, List<String> mSubjects, String mTitle) {
        this.mAuthors = mAuthors;
        this.mBookshelves = mBookshelves;
        this.mDownloadCount = mDownloadCount;
        this.mFormats = mFormats;
        this.mId = mId;
        this.mLanguages = mLanguages;
        this.mMediaType = mMediaType;
        this.mSubjects = mSubjects;
        this.mTitle = mTitle;
    }

    @SerializedName("authors")
    private List<Author> mAuthors;

    @Override
    public String toString() {
        return "Result{" +
                "mAuthors=" + mAuthors +
                ", mBookshelves=" + mBookshelves +
                ", mDownloadCount=" + mDownloadCount +
                ", mFormats=" + mFormats +
                ", mId=" + mId +
                ", mLanguages=" + mLanguages +
                ", mMediaType='" + mMediaType + '\'' +
                ", mSubjects=" + mSubjects +
                ", mTitle='" + mTitle + '\'' +
                '}';
    }

    @SerializedName("bookshelves")
    private List<String> mBookshelves;
    @SerializedName("download_count")
    private Long mDownloadCount;
    @SerializedName("formats")
    private Formats mFormats;
    @SerializedName("id")
    private Long mId;
    @SerializedName("languages")
    private List<String> mLanguages;
    @SerializedName("media_type")
    private String mMediaType;
    @SerializedName("subjects")
    private List<String> mSubjects;
    @SerializedName("title")
    private String mTitle;

    public List<Author> getAuthors() {
        return mAuthors;
    }

    public void setAuthors(List<Author> authors) {
        mAuthors = authors;
    }

    public List<String> getBookshelves() {
        return mBookshelves;
    }

    public void setBookshelves(List<String> bookshelves) {
        mBookshelves = bookshelves;
    }

    public Long getDownloadCount() {
        return mDownloadCount;
    }

    public void setDownloadCount(Long downloadCount) {
        mDownloadCount = downloadCount;
    }

    public Formats getFormats() {
        return mFormats;
    }

    public void setFormats(Formats formats) {
        mFormats = formats;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public List<String> getLanguages() {
        return mLanguages;
    }

    public void setLanguages(List<String> languages) {
        mLanguages = languages;
    }

    public String getMediaType() {
        return mMediaType;
    }

    public void setMediaType(String mediaType) {
        mMediaType = mediaType;
    }

    public List<String> getSubjects() {
        return mSubjects;
    }

    public void setSubjects(List<String> subjects) {
        mSubjects = subjects;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
