package keso.newsanchor.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import keso.newsanchor.core.NewsAnchor;
import keso.newsanchor.core.NewsAnchorOld;

public class NewsAnchorJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assetManager().setPathPrefix("keso/newsanchor/resources");
    PlayN.run(new NewsAnchor());
  }
}
