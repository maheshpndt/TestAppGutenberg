
package com.example.gutendex.model_section;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Formats {
    @SerializedName("image/jpeg")
    private Object mImageJpeg;
    @SerializedName("application/epub+zip")
    private String mApplicationEpubZip;
    @SerializedName("application/pdf")
    private String mApplicationPdf;
    @SerializedName("application/rdf+xml")
    private String mApplicationRdfXml;
    @SerializedName("application/x-mobipocket-ebook")
    private String mApplicationXMobipocketEbook;
    @SerializedName("application/zip")
    private String mApplicationZip;
    @SerializedName("text/html; charset=utf-8")
    private String mTextHtmlCharsetUtf8;
    @SerializedName("text/plain; charset=us-ascii")
    private String mTextPlainCharsetUsAscii;
    @SerializedName("text/plain; charset=utf-8")
    private String mTextPlainCharsetUtf8;

    public Object getmImageJpeg() {
        return mImageJpeg;
    }

    @Override
    public String toString() {
        return "Formats{" +
                "mApplicationEpubZip='" + mApplicationEpubZip + '\'' +
                ", mApplicationPdf='" + mApplicationPdf + '\'' +
                ", mApplicationRdfXml='" + mApplicationRdfXml + '\'' +
                ", mApplicationXMobipocketEbook='" + mApplicationXMobipocketEbook + '\'' +
                ", mApplicationZip='" + mApplicationZip + '\'' +
                ", mTextHtmlCharsetUtf8='" + mTextHtmlCharsetUtf8 + '\'' +
                ", mTextPlainCharsetUsAscii='" + mTextPlainCharsetUsAscii + '\'' +
                ", mTextPlainCharsetUtf8='" + mTextPlainCharsetUtf8 + '\'' +
                ", mImageJpeg=" + mImageJpeg +
                '}';
    }

    public void setmImageJpeg(String mImageJpeg) {
        this.mImageJpeg = mImageJpeg;
    }

    public String getApplicationEpubZip() {
        return mApplicationEpubZip;
    }

    public void setApplicationEpubZip(String applicationEpubZip) {
        mApplicationEpubZip = applicationEpubZip;
    }

    public String getApplicationPdf() {
        return mApplicationPdf;
    }

    public void setApplicationPdf(String applicationPdf) {
        mApplicationPdf = applicationPdf;
    }

    public String getApplicationRdfXml() {
        return mApplicationRdfXml;
    }

    public void setApplicationRdfXml(String applicationRdfXml) {
        mApplicationRdfXml = applicationRdfXml;
    }

    public String getApplicationXMobipocketEbook() {
        return mApplicationXMobipocketEbook;
    }

    public void setApplicationXMobipocketEbook(String applicationXMobipocketEbook) {
        mApplicationXMobipocketEbook = applicationXMobipocketEbook;
    }

    public String getApplicationZip() {
        return mApplicationZip;
    }

    public void setApplicationZip(String applicationZip) {
        mApplicationZip = applicationZip;
    }

    public String getTextHtmlCharsetUtf8() {
        return mTextHtmlCharsetUtf8;
    }

    public void setTextHtmlCharsetUtf8(String textHtmlCharsetUtf8) {
        mTextHtmlCharsetUtf8 = textHtmlCharsetUtf8;
    }

    public String getTextPlainCharsetUsAscii() {
        return mTextPlainCharsetUsAscii;
    }

    public void setTextPlainCharsetUsAscii(String textPlainCharsetUsAscii) {
        mTextPlainCharsetUsAscii = textPlainCharsetUsAscii;
    }

    public String getTextPlainCharsetUtf8() {
        return mTextPlainCharsetUtf8;
    }

    public void setTextPlainCharsetUtf8(String textPlainCharsetUtf8) {
        mTextPlainCharsetUtf8 = textPlainCharsetUtf8;
    }

}
