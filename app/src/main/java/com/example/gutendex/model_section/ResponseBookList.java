
package com.example.gutendex.model_section;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseBookList {

    @SerializedName("count")
    private Long mCount;
    @SerializedName("next")
    private String mNext;
    @SerializedName("previous")
    private String mPrevious;
    @SerializedName("results")
    private List<Result> mResults;

    @Override
    public String toString() {
        return "ResponseBookList{" +
                "mCount=" + mCount +
                ", mNext='" + mNext + '\'' +
                ", mPrevious=" + mPrevious +
                ", mResults=" + mResults +
                '}';
    }

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

    public String getNext() {
        return mNext;
    }

    public void setNext(String next) {
        mNext = next;
    }

    public String getPrevious() {
        return mPrevious;
    }

    public void setPrevious(String previous) {
        mPrevious = previous;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }

}
