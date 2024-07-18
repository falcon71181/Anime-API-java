package com.falcon71181.ani_java.models.hianime;

/**
 * AnimeInfo
 */
public class AnimeInfo {

  private String animeId;
  private String animeName;
  private String animePoster;
  private String animeRating;
  private String animeQuality;
  private String description;
  private String category;
  private int noOfSub;
  private int noOfDub;
  private int totalEp;

  public String getCategory() {
    return this.category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAnimeQuality() {
    return this.animeQuality;
  }

  public void setAnimeQuality(String animeQuality) {
    this.animeQuality = animeQuality;
  }

  public String getAnimeRating() {
    return this.animeRating;
  }

  public void setAnimeRating(String animeRating) {
    this.animeRating = animeRating;
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

  public AnimeInfo(String animeId, String animeName, String animePoster, int noOfSub, int noOfDub, int totalEp,
      String animeRating, String animeQuality, String description, String category) {
    this.animeId = animeId;
    this.animeName = animeName;
    this.animePoster = animePoster;
    this.animeRating = animeRating;
    this.animeQuality = animeQuality;
    this.description = description;
    this.category = category;
    this.noOfSub = noOfSub;
    this.noOfDub = noOfDub;
    this.totalEp = totalEp;
  }
}
