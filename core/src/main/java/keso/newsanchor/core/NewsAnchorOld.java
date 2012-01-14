package keso.newsanchor.core;

import static playn.core.PlayN.*;


import net.sourceforge.htmlunit.corejs.javascript.debug.DebugFrame;

import playn.core.Canvas;
import playn.core.CanvasLayer;
import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Path;
import pythagoras.f.Point;
import pythagoras.f.Vector;

public class NewsAnchorOld implements Game {
	
	private Canvas canvas; 
	private int i = 0;
	private FractalTerrain terrain;
	
  @Override
  public void init() {
	terrain = new FractalTerrain(8, 0.7);

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
	  //terrain = new FractalTerrain(8, 0.7);

	  
	  canvas.clear();

	//Draw terrain
	Path path = graphics().createPath();
	

	
	Vector v1 = new Vector(100, 350);
	Vector v2 = new Vector(350, 350);
	Vector v3 = new Vector(500, 100);
	Vector v4 = new Vector(100, 100);

	path.moveTo(v1.x, v1.y);
	addSegmentToPath(path, v1, v2);
	addSegmentToPath(path, v2, v3);
	addSegmentToPath(path, v3, v4);
	addSegmentToPath(path, v4, v1);
	path.close();	  
	
	canvas.setStrokeColor(0xff000000);
	//canvas.setFillColor(0xff00ffff);
	//canvas.fillPath(path);
	canvas.strokePath(path);
	  
	  
	  
	// Draw animated line 
	canvas.setStrokeColor(0xffff0000);
	canvas.drawLine(i, 50, 100, 100);
	

	// Text
	canvas.setFillColor(0xff000000);
	canvas.drawText("Frames: " + i, 200, 200);

	  
	  i++;
  }

  private void addSegmentToPath(Path path, Vector start, Vector end)
  {
	for (int i = 0; i < 50; i++)
	{
		Vector v = getTerrainPosition(start, end, i/50.0f);
		v.addLocal(start);	
		
		//System.out.println("x:" + v.x + ", y:" + v.y);
		path.lineTo(v.x, v.y);
	}

  }
  
  private Vector getTerrainPosition(Vector start, Vector end, float i)
  {
	  Vector v1 = end.subtract(start);		// vector from start to end
	  Vector v2 = new Vector(v1.y, -v1.x);	// orthognal vector to v1
	  v2.normalizeLocal();
	  	  
	  Vector v = v1.scale(i);

	  // Get altitude from FractalTerrain
	  float altitude = (float)terrain.getAltitude(i, 0);		  

	  // Scale altitude so that it is minimal close to start and end points
	  float totalDistance = start.distance(end);
	  float ratioStart = start.distance(start.add(v)) / totalDistance;
	  float ratioEnd = end.distance(start.add(v)) / totalDistance;	  
	  altitude = altitude * ratioStart * ratioEnd; 
	  
	  // Scale and invert v2
	  v2.scaleLocal(altitude*400.0f);
	  v2.negateLocal();
	  
	  v.addLocal(v2);
	  	  
	  return v;
  }
  
  @Override
  public int updateRate() {
    return 25;
  }
}
