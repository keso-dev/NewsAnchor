package keso.newsanchor.core.entities;

import static playn.core.PlayN.assetManager;
import static playn.core.PlayN.graphics;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;
import playn.core.ResourceCallback;

public class TileImage
{
  private ImageLayer imageLayer;

  public TileImage(String imageName)
  {
    Image image = assetManager().getImage("images/" + imageName);
    imageLayer = graphics().createImageLayer(image);

    // TODO: remove these and replace with something useful
    final int width = 15;
    final int height = 15;

    image.addCallback(new ResourceCallback<Image>()
    {
      @Override
      public void done(Image image)
      {
        // since the image is loaded, we can use its width and height
        imageLayer.setWidth(image.width());
        imageLayer.setHeight(image.height());
        imageLayer.setOrigin(image.width() / 2f, image.height() / 2f);
        imageLayer.setScale(width / image.width(), height / image.height());

        // TODO: These values need to be set
        //        imageLayer.setTranslation(x, y);
        //        imageLayer.setRotation(angle);
      }

      @Override
      public void error(Throwable err)
      {
        PlayN.log().error("Error loading image: " + err.getMessage());
      }
    });
  }

  public void update(float x, float y, float angle)
  {
    imageLayer.setTranslation(x, y);
    imageLayer.setRotation(angle);
  }
}
