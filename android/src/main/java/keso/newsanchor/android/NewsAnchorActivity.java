package keso.newsanchor.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import keso.newsanchor.core.NewsAnchor;

public class NewsAnchorActivity extends GameActivity {

  @Override
  public void main(){
    platform().assetManager().setPathPrefix("keso/newsanchor/resources");
    PlayN.run(new NewsAnchor());
  }
}
