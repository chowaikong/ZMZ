package me.knox.zmz.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KNOX.
 */

public class Subtitle {
  @SerializedName("id") private int id;
  @SerializedName("cnname") private String cnname;
  @SerializedName("enname") private String enname;
  @SerializedName("resourceid") private int rescourceId;
  @SerializedName("segment") private String segment;
  @SerializedName("source_name") private String source;
  @SerializedName("category") private String category;
  @SerializedName("file") private String file;
  @SerializedName("filename") private String fileName;
  @SerializedName("lang") private String lang;
  @SerializedName("format") private String format;
  @SerializedName("remark") private String remark;
  @SerializedName("views") private String views;
  @SerializedName("dateline") private long dateline;
  @SerializedName("protect_expire") private String protectExpire;
  @SerializedName("resource_info") private ResourceInfo resourceInfo;

  public int getId() {
    return id;
  }

  public String getCnname() {
    return cnname;
  }

  public String getEnname() {
    return enname;
  }

  public int getRescourceId() {
    return rescourceId;
  }

  public String getSegment() {
    return segment;
  }

  public String getSource() {
    return source;
  }

  public String getCategory() {
    return category;
  }

  public String getFile() {
    return file;
  }

  public String getFileName() {
    return fileName;
  }

  public String getLang() {
    return lang;
  }

  public String getFormat() {
    return format;
  }

  public String getRemark() {
    return remark;
  }

  public String getViews() {
    return views;
  }

  public long getDateline() {
    return dateline;
  }

  public String getProtectExpire() {
    return protectExpire;
  }

  public ResourceInfo getResourceInfo() {
    return resourceInfo;
  }
}
