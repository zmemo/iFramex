package com.memo.tool.preview;

import android.graphics.Rect;
import android.os.Parcel;

import androidx.annotation.Nullable;

import com.previewlibrary.enitity.IThumbViewInfo;

/**
 * title:图片预览实体类
 * describe:
 *
 * @author zhou
 * @date 2019-01-31 17:13
 */
public class PreviewImageInfo implements IThumbViewInfo {


    /**
     * 图片地址
     */
    private String url = "";

    private String videoUrl = "";

    /**
     * 记录坐标
     */
    private Rect mBounds;

    public PreviewImageInfo(String url) {
        this.url = url;
    }

    public PreviewImageInfo(String videoUrl, String url) {
        this.url = url;
        this.videoUrl = videoUrl;
    }

    public static final Creator<PreviewImageInfo> CREATOR = new Creator<PreviewImageInfo>() {
        @Override
        public PreviewImageInfo createFromParcel(Parcel source) {
            return new PreviewImageInfo(source);
        }

        @Override
        public PreviewImageInfo[] newArray(int size) {
            return new PreviewImageInfo[size];
        }
    };


    @Override
    public String getUrl() {//将你的图片地址字段返回
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Rect getBounds() {//将你的图片显示坐标字段返回
        return mBounds;
    }

    public void setBounds(Rect bounds) {
        mBounds = bounds;
    }

    @Nullable
    @Override
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }


    private PreviewImageInfo(Parcel parcel) {
        this.url = parcel.readString();
        this.mBounds = parcel.readParcelable(Rect.class.getClassLoader());
        this.videoUrl = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeParcelable(this.mBounds, flags);
        dest.writeString(this.videoUrl);
    }


}
