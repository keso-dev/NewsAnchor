package keso.newsanchor.core.entities;

import static playn.core.PlayN.assetManager;
import static playn.core.PlayN.graphics;
import keso.newsanchor.core.NewsAnchorWorld;

import playn.core.PlayN;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;

public abstract class Entity {
  ImageLayer layer;
  Image image;
  float x, y, angle;

  public Entity(final NewsAnchorWorld peaWorld, float px, float py, float pangle) {

    this.x = px;
    this.y = py;
    this.angle = pangle;

    image = assetManager().getImage("peas/images/" + getImageName());
    layer = graphics().createImageLayer(image);
    initPreLoad(peaWorld);

    image.addCallback(new ResourceCallback<Image>() {
      @Override
      public void done(Image image) {
        // since the image is loaded, we can use its width and height
        layer.setWidth(image.width());
        layer.setHeight(image.height());
        layer.setOrigin(image.width() / 2f, image.height() / 2f);
        layer.setScale(getWidth() / image.width(), getHeight() / image.height());
        layer.setTranslation(x, y);
        layer.setRotation(angle);
        initPostLoad(peaWorld);
      }

      @Override
      public void error(Throwable err) {
        PlayN.log().error("Error loading image: " + err.getMessage());
      }
    });
  }

  /**
   * Perform pre-image load initialization (e.g., attaching to PeaWorld layers).
   *
   * @param peaWorld
   */
  public abstract void initPreLoad(final NewsAnchorWorld peaWorld);

  /**
   * Perform post-image load initialization (e.g., attaching to PeaWorld layers).
   *
   * @param peaWorld
   */
  public abstract void initPostLoad(final NewsAnchorWorld peaWorld);

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
