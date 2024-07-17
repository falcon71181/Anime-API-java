package com.falcon71181.ani_java.models.hianime;

/**
 * TopAiringAnimes
 */
public class TopAiringAnimes {

  private String animeId;
  private String animeName;
  private String animePoster;

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

  public TopAiringAnimes(String animeId, String animeName, String animePoster) {
    this.animeId = animeId;
    this.animeName = animeName;
    this.animePoster = animePoster;
  }
}
