package keso.newsanchor.core;

import static playn.core.PlayN.*;

import playn.core.Canvas;
import playn.core.CanvasLayer;
import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Path;

public class NewsAnchor implements Game {
	
	private Canvas canvas; 
	private int i = 0;
	
  @Override
  public void init() {
    // create and add background image layer
    Image bgImage = assetManager().getImage("images/bg.png");
    ImageLayer bgLayer = graphics().createImageLayer(bgImage);
    graphics().rootLayer().add(bgLayer);
    
    CanvasLayer layer = graphics().createCanvasLayer(graphics().width(), graphics().height());
    graphics().rootLayer().add(layer);
    
    canvas = layer.canvas();
	canvas.setStrokeWidth(2);
	canvas.setStrokeColor(0xffff0000);
	canvas.strokeRect(1, 1, 46, 46);		

  }

  @Override
  public void paint(float alpha) {
    // the background automatically paints itself, so no need to do anything here!
  }

  @Override
  public void update(float delta) {
	Path path = graphics().createPath();
	path.moveTo(300, 300);
	path.arcTo(100, 350, 400);
	path.lineTo(350, 350);
	path.close();	  
	  
	  
	canvas.clear();
	  
	// Test 
	canvas.setStrokeColor(0xffff0000);
	canvas.drawLine(i, 50, 100, 100);
	  

	canvas.setStrokeColor(0xff000000);
	canvas.setFillColor(0xff00ffff);
	canvas.fillPath(path);
	canvas.strokePath(path);
	
	canvas.setFillColor(0xff000000);
	canvas.drawText("Frames: " + i, 200, 200);

	  
	  i++;
  }

  @Override
  public int updateRate() {
    return 25;
  }
}
