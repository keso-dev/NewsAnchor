package keso.newsanchor.core.entities;

import static playn.core.PlayN.assetManager;
import static playn.core.PlayN.graphics;
import keso.newsanchor.core.NewsAnchorWorld;

import playn.core.Layer;
import playn.core.PlayN;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;

public abstract class Entity {
  Layer layer;
  Image image;
  float x, y, angle;

  public Entity(final NewsAnchorWorld newsAnchorWorld, float px, float py, float pangle) {

    this.x = px;
    this.y = py;
    this.angle = pangle;

    String imageName = getImageName();
    if (imageName != null) {
      image = assetManager().getImage("images/" + getImageName());
      final ImageLayer imageLayer = graphics().createImageLayer(image);
      layer = imageLayer;
      initPreLoad(newsAnchorWorld);

      image.addCallback(new ResourceCallback<Image>() {
        @Override
        public void done(Image image) {
          // since the image is loaded, we can use its width and height
          imageLayer.setWidth(image.width());
          imageLayer.setHeight(image.height());
          imageLayer.setOrigin(image.width() / 2f, image.height() / 2f);
          imageLayer.setScale(getWidth() / image.width(), getHeight() / image.height());
          imageLayer.setTranslation(x, y);
          imageLayer.setRotation(angle);
          initPostLoad(newsAnchorWorld);
        }

        @Override
        public void error(Throwable err) {
          PlayN.log().error("Error loading image: " + err.getMessage());
        }
      });
    } else {
      layer = graphics().createCanvasLayer((int)getWidth(), (int)getHeight()); 
      initPreLoad(newsAnchorWorld);
      initPostLoad(newsAnchorWorld);
    }
  }

  /**
   * Perform pre-image load initialization (e.g., attaching to PeaWorld layers).
   *
   * @param newsAnchorWorld
   */
  public abstract void initPreLoad(final NewsAnchorWorld newsAnchorWorld);

  /**
   * Perform post-image load initialization (e.g., attaching to PeaWorld layers).
   *
   * @param newsAnchorWorld
   */
  public abstract void initPostLoad(final NewsAnchorWorld newsAnchorWorld);

  public void paint(float alpha) {
  }

  public void update(float delta) {
  }

  public void setPos(float x, float y) {
    layer.setTranslation(x, y);
  }

  public void setAngle(float a) {
    layer.setRotation(a);
  }

  abstract float getWidth();

  abstract float getHeight();

  abstract String getImageName();

  public Image getImage() {
    return image;
  }
}
