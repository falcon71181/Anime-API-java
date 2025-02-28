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

import com.falcon71181.ani_java.models.hianime.CategoryAnimes;
import com.falcon71181.ani_java.models.hianime.EpisodeInfo;
import com.falcon71181.ani_java.models.hianime.LatestEpisodes;
import com.falcon71181.ani_java.models.hianime.SpotlightAnimes;
import com.falcon71181.ani_java.models.hianime.Top10Animes;
import com.falcon71181.ani_java.models.hianime.TopAiringAnimes;
import com.falcon71181.ani_java.models.hianime.TopUpcomingAnimes;
import com.falcon71181.ani_java.models.hianime.TrendingAnimes;

/**
 * HianimeScrapper
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

      Elements top10AnimeInDayContainer = doc
          .select("#main-sidebar .block_area-realtime [id^=\"top-viewed-day\"] ul li");
      List<Top10Animes> top10AnimesInDay = new ArrayList<>();
      top10AnimeInDayContainer.forEach(element -> {
        final String animeId = element.select(".film-detail .film-name .dynamic-name").attr("href").substring(1);
        final String animeName = element.select(".film-detail .film-name .dynamic-name").text().trim();
        final String animePoster = element.select(".film-poster a .film-poster-img").attr("data-src").trim();
        final int noOfSub = Integer
            .parseInt(String.valueOf(element.select(".film-detail .fd-infor .tick-item.tick-sub").text()).length() > 0
                ? element.select(".film-detail .fd-infor .tick-item.tick-sub").text()
                : "0");
        final int noOfDub = Integer
            .parseInt(String.valueOf(element.select(".film-detail .fd-infor .tick-item.tick-dub").text()).length() > 0
                ? element.select(".film-detail .fd-infor .tick-item.tick-dub").text()
                : "0");
        final int totalEp = Integer
            .parseInt(String.valueOf(element.select(".film-detail .fd-infor .tick-item.tick-eps").text()).length() > 0
                ? element.select(".film-detail .fd-infor .tick-item.tick-eps").text()
                : "0");
        final int animeRank = Integer.parseInt(element.select(".film-number span").text().trim());
        top10AnimesInDay.add(new Top10Animes(animeId, animeName, animePoster, animeRank, noOfSub, noOfDub, totalEp));
      });
      Elements top10AnimeInWeekContainer = doc
          .select("#main-sidebar .block_area-realtime [id^=\"top-viewed-week\"] ul li");
      List<Top10Animes> top10AnimesInWeek = new ArrayList<>();
      top10AnimeInWeekContainer.forEach(element -> {
        final String animeId = element.select(".film-detail .film-name .dynamic-name").attr("href").substring(1);
        final String animeName = element.select(".film-detail .film-name .dynamic-name").text().trim();
        final String animePoster = element.select(".film-poster a .film-poster-img").attr("data-src").trim();
        final int animeRank = Integer.parseInt(element.select(".film-number span").text().trim());
        final int noOfSub = Integer
            .parseInt(String.valueOf(element.select(".film-detail .fd-infor .tick-item.tick-sub").text()).length() > 0
                ? element.select(".film-detail .fd-infor .tick-item.tick-sub").text()
                : "0");
        final int noOfDub = Integer
            .parseInt(String.valueOf(element.select(".film-detail .fd-infor .tick-item.tick-dub").text()).length() > 0
                ? element.select(".film-detail .fd-infor .tick-item.tick-dub").text()
                : "0");
        final int totalEp = Integer
            .parseInt(String.valueOf(element.select(".film-detail .fd-infor .tick-item.tick-eps").text()).length() > 0
                ? element.select(".film-detail .fd-infor .tick-item.tick-eps").text()
                : "0");
        top10AnimesInWeek.add(new Top10Animes(animeId, animeName, animePoster, animeRank, noOfSub, noOfDub, totalEp));
      });
      Elements top10AnimeInMonthContainer = doc
          .select("#main-sidebar .block_area-realtime [id^=\"top-viewed-month\"] ul li");
      List<Top10Animes> top10AnimesInMonth = new ArrayList<>();
      top10AnimeInMonthContainer.forEach(element -> {
        final String animeId = element.select(".film-detail .film-name .dynamic-name").attr("href").substring(1);
        final String animeName = element.select(".film-detail .film-name .dynamic-name").text().trim();
        final String animePoster = element.select(".film-poster a .film-poster-img").attr("data-src").trim();
        final int animeRank = Integer.parseInt(element.select(".film-number span").text().trim());
        final int noOfSub = Integer
            .parseInt(String.valueOf(element.select(".film-detail .fd-infor .tick-item.tick-sub").text()).length() > 0
                ? element.select(".film-detail .fd-infor .tick-item.tick-sub").text()
                : "0");
        final int noOfDub = Integer
            .parseInt(String.valueOf(element.select(".film-detail .fd-infor .tick-item.tick-dub").text()).length() > 0
                ? element.select(".film-detail .fd-infor .tick-item.tick-dub").text()
                : "0");
        final int totalEp = Integer
            .parseInt(String.valueOf(element.select(".film-detail .fd-infor .tick-item.tick-eps").text()).length() > 0
                ? element.select(".film-detail .fd-infor .tick-item.tick-eps").text()
                : "0");
        top10AnimesInMonth.add(new Top10Animes(animeId, animeName, animePoster, animeRank, noOfSub, noOfDub, totalEp));
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
      homeData.put("top-viewed-day", top10AnimesInDay);
      homeData.put("top-viewed-week", top10AnimesInWeek);
      homeData.put("top-viewed-month", top10AnimesInMonth);
      return homeData;
    } catch (Exception e) {
      logger.warn(e.getMessage());
      return new HashMap<>();
    }
  }

  public HashMap<String, List<?>> scrapeCategory(String category, String page) {
    HashMap<String, List<?>> categoryData = new HashMap<>();
    try {
      String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36";

      Document doc = Jsoup.connect(this.siteUrl + "/" + category + "?page=" + page)
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

      Elements animeContainer = doc
          .select("#main-content .tab-content .film_list-wrap .flw-item");
      List<CategoryAnimes> categoryAnimes = new ArrayList<>();
      animeContainer.forEach(element -> {
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
        categoryAnimes.add(new CategoryAnimes(animeId, animeName, animePoster, noOfSub, noOfDub, totalEp,
            airingOn, animeRating));
      });

      Elements top10AnimeInDayContainer = doc
          .select("#main-sidebar .block_area-realtime [id^=\"top-viewed-day\"] ul li");
      List<Top10Animes> top10AnimesInDay = new ArrayList<>();
      top10AnimeInDayContainer.forEach(element -> {
        final String animeId = element.select(".film-detail .film-name .dynamic-name").attr("href").substring(1);
        final String animeName = element.select(".film-detail .film-name .dynamic-name").text().trim();
        final String animePoster = element.select(".film-poster a .film-poster-img").attr("data-src").trim();
        final int noOfSub = Integer
            .parseInt(String.valueOf(element.select(".film-detail .fd-infor .tick-item.tick-sub").text()).length() > 0
                ? element.select(".film-detail .fd-infor .tick-item.tick-sub").text()
                : "0");
        final int noOfDub = Integer
            .parseInt(String.valueOf(element.select(".film-detail .fd-infor .tick-item.tick-dub").text()).length() > 0
                ? element.select(".film-detail .fd-infor .tick-item.tick-dub").text()
                : "0");
        final int totalEp = Integer
            .parseInt(String.valueOf(element.select(".film-detail .fd-infor .tick-item.tick-eps").text()).length() > 0
                ? element.select(".film-detail .fd-infor .tick-item.tick-eps").text()
                : "0");
        final int animeRank = Integer.parseInt(element.select(".film-number span").text().trim());
        top10AnimesInDay.add(new Top10Animes(animeId, animeName, animePoster, animeRank, noOfSub, noOfDub, totalEp));
      });
      Elements top10AnimeInWeekContainer = doc
          .select("#main-sidebar .block_area-realtime [id^=\"top-viewed-week\"] ul li");
      List<Top10Animes> top10AnimesInWeek = new ArrayList<>();
      top10AnimeInWeekContainer.forEach(element -> {
        final String animeId = element.select(".film-detail .film-name .dynamic-name").attr("href").substring(1);
        final String animeName = element.select(".film-detail .film-name .dynamic-name").text().trim();
        final String animePoster = element.select(".film-poster a .film-poster-img").attr("data-src").trim();
        final int animeRank = Integer.parseInt(element.select(".film-number span").text().trim());
        final int noOfSub = Integer
            .parseInt(String.valueOf(element.select(".film-detail .fd-infor .tick-item.tick-sub").text()).length() > 0
                ? element.select(".film-detail .fd-infor .tick-item.tick-sub").text()
                : "0");
        final int noOfDub = Integer
            .parseInt(String.valueOf(element.select(".film-detail .fd-infor .tick-item.tick-dub").text()).length() > 0
                ? element.select(".film-detail .fd-infor .tick-item.tick-dub").text()
                : "0");
        final int totalEp = Integer
            .parseInt(String.valueOf(element.select(".film-detail .fd-infor .tick-item.tick-eps").text()).length() > 0
                ? element.select(".film-detail .fd-infor .tick-item.tick-eps").text()
                : "0");
        top10AnimesInWeek.add(new Top10Animes(animeId, animeName, animePoster, animeRank, noOfSub, noOfDub, totalEp));
      });
      Elements top10AnimeInMonthContainer = doc
          .select("#main-sidebar .block_area-realtime [id^=\"top-viewed-month\"] ul li");
      List<Top10Animes> top10AnimesInMonth = new ArrayList<>();
      top10AnimeInMonthContainer.forEach(element -> {
        final String animeId = element.select(".film-detail .film-name .dynamic-name").attr("href").substring(1);
        final String animeName = element.select(".film-detail .film-name .dynamic-name").text().trim();
        final String animePoster = element.select(".film-poster a .film-poster-img").attr("data-src").trim();
        final int animeRank = Integer.parseInt(element.select(".film-number span").text().trim());
        final int noOfSub = Integer
            .parseInt(String.valueOf(element.select(".film-detail .fd-infor .tick-item.tick-sub").text()).length() > 0
                ? element.select(".film-detail .fd-infor .tick-item.tick-sub").text()
                : "0");
        final int noOfDub = Integer
            .parseInt(String.valueOf(element.select(".film-detail .fd-infor .tick-item.tick-dub").text()).length() > 0
                ? element.select(".film-detail .fd-infor .tick-item.tick-dub").text()
                : "0");
        final int totalEp = Integer
            .parseInt(String.valueOf(element.select(".film-detail .fd-infor .tick-item.tick-eps").text()).length() > 0
                ? element.select(".film-detail .fd-infor .tick-item.tick-eps").text()
                : "0");
        top10AnimesInMonth.add(new Top10Animes(animeId, animeName, animePoster, animeRank, noOfSub, noOfDub, totalEp));
      });

      Elements genreContainer = doc
          .select("#main-sidebar .block_area.block_area_sidebar.block_area-genres .sb-genre-list li");
      List<String> genres = new ArrayList<>();
      genreContainer.forEach(element -> {
        final String genre = element.text().trim();
        genres.add(new String(genre));
      });

      categoryData.put("animes", categoryAnimes);
      categoryData.put("top-viewed-day", top10AnimesInDay);
      categoryData.put("top-viewed-week", top10AnimesInWeek);
      categoryData.put("top-viewed-month", top10AnimesInMonth);
      categoryData.put("genres", genres);

      return categoryData;
    } catch (Exception e) {
      logger.warn(e.getMessage());
      return new HashMap<>();
    }
  }

  public HashMap<String, String> scrapeAbout(String animeID) {
    try {
      HashMap<String, String> aboutData = new HashMap<>();

      String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36";

      Document doc = Jsoup.connect(this.siteUrl + "/" + animeID)
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

      Elements infoContainer = doc
          .select("#ani_detail .container .anis-content");
      // logger.error(infoContainer.html());
      infoContainer.forEach(element -> {
        final String animeId = element.select(".anisc-detail .film-buttons a.btn-play").attr("href").substring(1)
            .split("/")[1];
        final String animeName = element.select(".anisc-detail .film-name.dynamic-name").text().trim();
        final String animePoster = element.select(".film-poster .film-poster-img").attr("src");
        final String noOfSub = (element.select(".film-stats .tick .tick-sub").text().length() > 0
            ? element.select(".film-stats .tick .tick-sub").text()
            : "0");
        final String noOfDub = (element.select(".film-stats .tick .tick-dub").text().length() > 0
            ? element.select(".film-stats .tick .tick-dub").text()
            : "0");
        final String totalEp = (element.select(".film-stats .tick .tick-eps").text().length() > 0
            ? element.select(".film-stats .tick .tick-eps").text()
            : "0");
        final String animeRating = element.select(".film-stats .tick .tick-pg").text().trim();
        final String animeEpisodeInfo = element.select(".film-stats .tick").text().trim();
        final String[] infoArray = animeEpisodeInfo.split(" ");
        final String duration = infoArray[infoArray.length - 1];
        final String category = infoArray[4];
        final String animeDescription = element.select(".anisc-detail .film-description .text").text();
        aboutData.put("animeId", animeId);
        aboutData.put("animeName", animeName);
        aboutData.put("animeDescription", animeDescription);
        aboutData.put("animePoster", animePoster);
        aboutData.put("animeRating", animeRating);
        aboutData.put("animeEpisodeInfo", animeEpisodeInfo);
        aboutData.put("duration", duration);
        aboutData.put("sub", noOfSub);
        aboutData.put("dub", noOfDub);
        aboutData.put("eps", totalEp);
        aboutData.put("category", category);
      });

      Elements genreContainer = doc
          .select("#ani_detail .container .anis-content .anisc-info .item-list a");
      List<String> genres = new ArrayList<>();
      genreContainer.forEach(element -> {
        genres.add(element.text().trim());
      });

      Elements producerContainer = doc
          .select("#ani_detail .container .anis-content .anisc-info .item-title");
      producerContainer.forEach(element -> {
        final String fieldKey = element.select(".item-head").text().trim();
        final String fieldValue = element.select(".name").text().trim();

        aboutData.put(fieldKey, fieldValue);
      });
      // logger.error(moreInfo.toString());

      return aboutData;
    } catch (Exception e) {
      logger.warn(e.getMessage());
      return new HashMap<>();
    }
  }

  public HashMap<String, ?> scrapeEpisodesInfo(String animeID) {
    try {
      HashMap<String, ?> episodeData = new HashMap<>();

      String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36";

      String[] anime_no_array = animeID.split("-");
      Document doc = Jsoup.connect(this.siteUrl + "/ajax/v2/episode/list/" + anime_no_array[anime_no_array.length - 1])
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

      // logger.error(doc.html());
      Elements episodeInfoContainer = doc
          .select(".detail-infor-content .ss-list a");
      List<EpisodeInfo> episodeInfos = new ArrayList<>();
      episodeInfoContainer.forEach(element -> {
        final String episodeName = element.select("title").text().trim();
        final String episodeId = element.select("href").text();
        final int episodeNo = Integer.valueOf(element.select("data-number").text());
        logger.error(episodeName + episodeId);

        episodeInfos.add(new EpisodeInfo(episodeName, episodeId, episodeNo, "true"));
      });
      logger.error(episodeInfos.toString());
      // episodeData.put("episodes", episodeInfos);

      return episodeData;
    } catch (Exception e) {
      logger.warn(e.getMessage());
      return new HashMap<>();
    }
  }

  public List<TopUpcomingAnimes> scrapeaTozAnime(String page_no) {
    List<TopUpcomingAnimes> atozAnimeList = new ArrayList<>();
    try {
      String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36";

      Document doc = Jsoup.connect(this.siteUrl + "/az-list/" + "?page=" + page_no)
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

      Elements animeContainer = doc
          .select("#main-wrapper div div.page-az-wrap section div.tab-content div div.film_list-wrap .flw-item");
      animeContainer.forEach(element -> {
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

        atozAnimeList.add(
            new TopUpcomingAnimes(animeId, animeName, animePoster, noOfSub, noOfDub, totalEp, airingOn, animeRating));
      });

      return atozAnimeList;
    } catch (Exception e) {
      logger.warn(e.getMessage());
      return new ArrayList<>();
    }
  }
}
