package com.falcon71181.ani_java.models.hianime;

/**
 * TopUpcomingAnimes
 */
public class TopUpcomingAnimes {

  private String animeId;
  private String animeName;
  private String animePoster;
  private String airingOn;
  private String animeRating;
  private int noOfSub;
  private int noOfDub;
  private int totalEp;

  public String getAnimeRating() {
    return this.animeRating;
  }

  public void setAnimeRating(String animeRating) {
    this.animeRating = animeRating;
  }

  public String getAiringOn() {
    return this.airingOn;
  }

  public void setAiringOn(String airingOn) {
    this.airingOn = airingOn;
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

  public TopUpcomingAnimes(String animeId, String animeName, String animePoster, int noOfSub, int noOfDub, int totalEp,
      String airingOn, String animeRating) {
    this.animeId = animeId;
    this.animeName = animeName;
    this.animePoster = animePoster;
    this.airingOn = airingOn;
    this.animeRating = animeRating;
    this.noOfSub = noOfSub;
    this.noOfDub = noOfDub;
    this.totalEp = totalEp;
  }
}
