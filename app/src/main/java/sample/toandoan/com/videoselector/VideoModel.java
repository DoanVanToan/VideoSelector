package sample.toandoan.com.videoselector;

import android.database.Cursor;
import android.provider.MediaStore;

/**
 * Created by doan.van.toan on 1/15/18.
 */

public class VideoModel {
    private String mId;
    private String mFilePath;
    private String mCreatedAt;
    private String mModifyAt;
    private String mTitle;
    private String mDisplayName;
    private long mSize;
    private String mThumb;

    public VideoModel(Cursor cursor) {
        mId = cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns._ID));
        mFilePath = cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA));
        mCreatedAt = cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATE_ADDED));
        mModifyAt = cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATE_MODIFIED));
        mTitle = cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.TITLE));
        mDisplayName = cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DISPLAY_NAME));
        mThumb = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA));
        mSize = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns.SIZE));

    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getFilePath() {
        return mFilePath;
    }

    public void setFilePath(String filePath) {
        mFilePath = filePath;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getModifyAt() {
        return mModifyAt;
    }

    public void setModifyAt(String modifyAt) {
        mModifyAt = modifyAt;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public long getSize() {
        return mSize;
    }

    public void setSize(long size) {
        mSize = size;
    }

    @Override
    public String toString() {
        return "VideoModel{" +
                "mId='" + mId + '\'' +
                ", mFilePath='" + mFilePath + '\'' +
                ", mThumb='" + mThumb + '\'' +
                '}';
    }
}
