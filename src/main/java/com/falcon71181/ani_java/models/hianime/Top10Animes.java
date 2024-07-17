package com.falcon71181.ani_java.models.hianime;

/**
 * Top10Animes
 */
public class Top10Animes {

  private String animeId;
  private String animeName;
  private String animePoster;
  private int animeRank;
  private int noOfSub;
  private int noOfDub;
  private int totalEp;

  public int getAnimeRank() {
    return this.animeRank;
  }

  public void setAnimeRank(int animeRank) {
    this.animeRank = animeRank;
  }

  public int getNoOfSub() {
    return this.noOfSub;
  }

  public void setNoOfSub(int noOfSub) {
    this.noOfSub = noOfSub;
  }

  public int getNoOfDub() {
    return this.noOfDub;
  }

  public void setNoOfDub(int noOfDub) {
    this.noOfDub = noOfDub;
  }

  public int getTotalEp() {
    return this.totalEp;
  }

  public void setTotalEp(int totalEp) {
    this.totalEp = totalEp;
  }

  public String getAnimeId() {
    return this.animeId;
  }

  public void setAnimeId(String animeId) {
    this.animeId = animeId;
  }

  public String getAnimeName() {
    return this.animeName;
  }

  public void setAnimeName(String animeName) {
    this.animeName = animeName;
  }

  public String getAnimePoster() {
    return this.animePoster;
  }

  public void setAnimePoster(String animePoster) {
    this.animePoster = animePoster;
  }

  public Top10Animes(String animeId, String animeName, String animePoster, int animeRank, int noOfSub, int noOfDub,
      int totalEp) {
    this.animeId = animeId;
    this.animeName = animeName;
    this.animePoster = animePoster;
    this.animeRank = animeRank;
    this.noOfSub = noOfSub;
    this.noOfDub = noOfDub;
    this.totalEp = totalEp;
  }
}
