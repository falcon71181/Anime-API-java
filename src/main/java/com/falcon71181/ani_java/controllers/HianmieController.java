package com.falcon71181.ani_java.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.falcon71181.ani_java.scrappers.*;

/**
 * HianmieController
 */
@RestController
@RequestMapping("/hianime")
public class HianmieController {

  private static final Logger logger = LoggerFactory.getLogger(HianmieController.class);

  private HianimeScrapper hianimeScrapper;

  @Autowired
  public HianmieController(HianimeScrapper hianimeScrapper) {
    this.hianimeScrapper = hianimeScrapper;
  }

  @GetMapping(value = "/home")
  public ResponseEntity<?> getHomeData() {
    // logger.info(hianimeScrapper.scrapeHome().toString());
    return new ResponseEntity<>(hianimeScrapper.scrapeHome(), HttpStatus.OK);
  }
}
