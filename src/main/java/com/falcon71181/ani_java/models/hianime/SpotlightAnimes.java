package com.falcon71181.ani_java.models.hianime;

/**
 * SpotlightAnimes
 */
public class SpotlightAnimes {

  private String animeId;
  private String animeName;
  private int animeRank;
  private String animePoster;
  private String animeDescription;

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

  public int getAnimeRank() {
    return this.animeRank;
  }

  public void setAnimeRank(int animeRank) {
    this.animeRank = animeRank;
  }

  public String getAnimePoster() {
    return this.animePoster;
  }

  public void setAnimePoster(String animePoster) {
    this.animePoster = animePoster;
  }

  public String getAnimeDescription() {
    return this.animeDescription;
  }

  public void setAnimeDescription(String animeDescription) {
    this.animeDescription = animeDescription;
  }

  public SpotlightAnimes(String animeId, String animeName, int animeRank, String animePoster,
      String animeDescription) {
    this.animeId = animeId;
    this.animeName = animeName;
    this.animeRank = animeRank;
    this.animePoster = animePoster;
    this.animeDescription = animeDescription;
  }
}
