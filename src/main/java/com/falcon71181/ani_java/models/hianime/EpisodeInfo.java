package com.falcon71181.ani_java.models.hianime;

/**
 * EpisodeInfo
 */
public class EpisodeInfo {

  private String episodeName;
  private String episodeId;
  private int episodeNo;
  private String filler;

  public String getEpisodeName() {
    return this.episodeName;
  }

  public void setEpisodeName(String episodeName) {
    this.episodeName = episodeName;
  }

  public String getEpisodeId() {
    return this.episodeId;
  }

  public void setEpisodeId(String episodeId) {
    this.episodeId = episodeId;
  }

  public int getEpisodeNo() {
    return this.episodeNo;
  }

  public void setEpisodeNo(int episodeNo) {
    this.episodeNo = episodeNo;
  }

  public String getFiller() {
    return this.filler;
  }

  public void setFiller(String filler) {
    this.filler = filler;
  }

  public EpisodeInfo(String episodeName, String episodeId, int episodeNo, String filler) {
    this.episodeNo = episodeNo;
    this.episodeId = episodeId;
    this.filler = filler;
    this.episodeName = episodeName;
  }
}
