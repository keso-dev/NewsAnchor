package keso.newsanchor.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

import keso.newsanchor.core.NewsAnchor;
import keso.newsanchor.core.NewsAnchorOld;

public class NewsAnchorHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assetManager().setPathPrefix("newsanchor/");
    PlayN.run(new NewsAnchor());
  }
}
