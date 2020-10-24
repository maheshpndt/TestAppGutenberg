
package com.example.gutendex.model_section;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Author {

    @SerializedName("birth_year")
    private Long mBirthYear;
    @SerializedName("death_year")
    private Long mDeathYear;
    @SerializedName("name")
    private String mName;

    public Long getBirthYear() {
        return mBirthYear;
    }

    public void setBirthYear(Long birthYear) {
        mBirthYear = birthYear;
    }

    public Long getDeathYear() {
        return mDeathYear;
    }

    public void setDeathYear(Long deathYear) {
        mDeathYear = deathYear;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
