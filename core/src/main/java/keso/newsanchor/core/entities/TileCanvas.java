package keso.newsanchor.core.entities;

import static playn.core.PlayN.graphics;
import playn.core.Canvas;
import playn.core.CanvasLayer;
import playn.core.Layer;
import playn.core.PlayN;

public class TileCanvas {

  private CanvasLayer canvasLayer;
  
  public TileCanvas(int width, int height)
  {
    canvasLayer = graphics().createCanvasLayer(width, height); 
  }
  
  public Canvas getCanvas()
  {
    return canvasLayer.canvas();
  }
  
  public Layer getLayer()
  {
    return canvasLayer;
  }
  
  public void setPos(float x, float y)
  {
    canvasLayer.setTranslation(x, y);
  }

  public void setAngle(float angle) {
    canvasLayer.setRotation(angle);    
  }
  
}
