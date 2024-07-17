package com.falcon71181.ani_java.scrappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.falcon71181.ani_java.models.hianime.LatestEpisodes;
import com.falcon71181.ani_java.models.hianime.SpotlightAnimes;
import com.falcon71181.ani_java.models.hianime.TopAiringAnimes;
import com.falcon71181.ani_java.models.hianime.TopUpcomingAnimes;
import com.falcon71181.ani_java.models.hianime.TrendingAnimes;

/**
 * HomeScrapper
 */
@Service
public class HianimeScrapper {

  @Value("${website.hianime}")
  private String siteUrl;

  @Value("${headers.user_agent_header}")
  private String userAgentHeader;

  @Value("${headers.accept_encodeing_header}")
  private String acceptEncodeingHeader;

  @Value("${headers.accept_header}")
  private String acceptHeader;

  private static final Logger logger = LoggerFactory.getLogger(HianimeScrapper.class);

  public HashMap<String, List<?>> scrapeHome() {
    HashMap<String, List<?>> homeData = new HashMap<>();
    try {
      String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36";

      Document doc = Jsoup.connect(this.siteUrl + "/home")
          .userAgent(userAgent)
          .header("accept",
              "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
          .header("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
          .header("priority", "u=0, i")
          .header("sec-ch-ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"")
          .header("sec-ch-ua-mobile", "?0")
          .header("sec-ch-ua-platform", "\"macOS\"")
          .header("sec-fetch-dest", "document")
          .header("sec-fetch-mode", "navigate")
          .header("sec-fetch-site", "none")
          .header("sec-fetch-user", "?1")
          .header("upgrade-insecure-requests", "1")
          .get();

      Elements spotlightAnimeContainer = doc.select("#slider .swiper-wrapper .swiper-slide");
      List<SpotlightAnimes> spotlightAnimes = new ArrayList<>();
      spotlightAnimeContainer.forEach(element -> {
        final String animeId = element.select(".deslide-item-content .desi-buttons a").last().attr("href").trim()
            .substring(1);
        final String animeName = element.select(".deslide-item-content .desi-head-title.dynamic-name").text().trim();
        final int animeRank = Integer
            .valueOf(element.select(".deslide-item-content .desi-sub-text").text().trim().split(" ")[0]
                .substring(1));
        final String animePoster = element.select(".deslide-cover .deslide-cover-img .film-poster-img").attr("data-src")
            .trim();
        final String animeDescription = element.select(".deslide-item-content .desi-description").text();

        spotlightAnimes.add(new SpotlightAnimes(animeId, animeName, animeRank, animePoster, animeDescription));
      });

      Elements trendingAnimeContainer = doc.select("#anime-trending #trending-home .swiper-wrapper .swiper-slide");
      List<TrendingAnimes> trendingAnimes = new ArrayList<>();
      trendingAnimeContainer.forEach(element -> {
        final String animeId = element.select(".item .film-poster").attr("href").substring(1);
        final String animeName = element.select(".item .number .film-title.dynamic-name").text();
        final String animePoster = element.select(".item .film-poster .film-poster-img").attr("data-src").trim();

        trendingAnimes.add(new TrendingAnimes(animeId, animeName, animePoster));
      });

      Elements lastestEpisodeContainer = doc
          .select("#main-content .block_area_home:nth-of-type(1) .tab-content .film_list-wrap .flw-item");
      List<LatestEpisodes> lastestEpisodes = new ArrayList<>();
      lastestEpisodeContainer.forEach(element -> {
        final String animeId = element.select(".film-detail .film-name .dynamic-name").attr("href").substring(1);
        final String animeName = element.select(".film-detail .film-name .dynamic-name").text().trim();
        final String animePoster = element.select(".film-poster .film-poster-img").attr("data-src");
        final int noOfSub = Integer.valueOf(element.select(".film-poster .tick .tick-sub").text().length() > 0
            ? element.select(".film-poster .tick .tick-sub").text()
            : "0");
        final int noOfDub = Integer.valueOf(element.select(".film-poster .tick .tick-dub").text().length() > 0
            ? element.select(".film-poster .tick .tick-dub").text()
            : "0");
        final int totalEp = Integer.valueOf(element.select(".film-poster .tick .tick-eps").text().length() > 0
            ? element.select(".film-poster .tick .tick-eps").text()
            : "0");
        final String episodeLength = element.select(".film-detail .fd-infor .fdi-duration").text().trim();
        final String animeRating = element.select(".film-poster .tick-rate").text().trim();
        lastestEpisodes.add(
            new LatestEpisodes(animeId, animeName, animePoster, noOfSub, noOfDub, totalEp, episodeLength, animeRating));
      });

      Elements topUpcomingAnimeContainer = doc
          .select("#main-content .block_area_home:nth-of-type(3) .tab-content .film_list-wrap .flw-item");
      List<TopUpcomingAnimes> topUpcomingAnimes = new ArrayList<>();
      topUpcomingAnimeContainer.forEach(element -> {
        final String animeId = element.select(".film-detail .film-name .dynamic-name").attr("href").substring(1);
        final String animeName = element.select(".film-detail .film-name .dynamic-name").text().trim();
        final String animePoster = element.select(".film-poster .film-poster-img").attr("data-src");
        final int noOfSub = Integer.valueOf(element.select(".film-poster .tick .tick-sub").text().length() > 0
            ? element.select(".film-poster .tick .tick-sub").text()
            : "0");
        final int noOfDub = Integer.valueOf(element.select(".film-poster .tick .tick-dub").text().length() > 0
            ? element.select(".film-poster .tick .tick-dub").text()
            : "0");
        final int totalEp = Integer.valueOf(element.select(".film-poster .tick .tick-eps").text().length() > 0
            ? element.select(".film-poster .tick .tick-eps").text()
            : "0");
        final String airingOn = element.select(".film-detail .fd-infor .fdi-duration").text().trim();
        final String animeRating = element.select(".film-poster .tick-rate").text().trim();
        topUpcomingAnimes.add(new TopUpcomingAnimes(animeId, animeName, animePoster, noOfSub, noOfDub, totalEp,
            airingOn, animeRating));
      });

      Elements topAiringAnimeContainer = doc
          .select("#anime-featured .row div:nth-of-type(1) .anif-block-ul ul li");
      List<TopAiringAnimes> topAiringAnimes = new ArrayList<>();
      topAiringAnimeContainer.forEach(element -> {
        final String animeId = element.select(".film-detail .film-name .dynamic-name").attr("href").substring(1);
        final String animeName = element.select(".film-detail .film-name .dynamic-name").text().trim();
        final String animePoster = element.select(".film-poster a .film-poster-img").attr("data-src").trim();
        topAiringAnimes.add(new TopAiringAnimes(animeId, animeName, animePoster));
      });

      Elements genreContainer = doc
          .select("#main-sidebar .block_area.block_area_sidebar.block_area-genres .sb-genre-list li");
      List<String> genres = new ArrayList<>();
      genreContainer.forEach(element -> {
        final String genre = element.text().trim();
        genres.add(new String(genre));
      });

      homeData.put("spotlight", spotlightAnimes);
      homeData.put("trending", trendingAnimes);
      homeData.put("latestEpisodes", lastestEpisodes);
      homeData.put("topUpcoming", topUpcomingAnimes);
      homeData.put("topAiring", topAiringAnimes);
      homeData.put("genres", genres);
      return homeData;
    } catch (Exception e) {
      logger.warn(e.getMessage());
      return new HashMap<>();
    }
  }
}
