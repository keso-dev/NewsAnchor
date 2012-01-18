package keso.newsanchor.core.entities;

import keso.newsanchor.core.NewsAnchorWorld;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import playn.core.Canvas;
import playn.core.CanvasLayer;

public class Ship extends DynamicPhysicsEntity
{  
  private TileCanvas tile;
  private int width = 15;
  private int height = 15;

	public Ship(NewsAnchorWorld newsAnchorWorld, World world, float x, float y, float angle)
	{
		super(newsAnchorWorld, world, x, y, angle);

		tile = new TileCanvas(width, height);
    defineShip();
    
    newsAnchorWorld.dynamicLayer.add(tile.getLayer());
	}
	  
	@Override
	public void paint(float alpha)
	{
	  super.paint(alpha);
	  
	  // Prepare tile for drawing - should we do this in update method instead?
	  tile.setPos(x, y);
    tile.setAngle(angle);
	}
	
  public void setPos(float x, float y)
  {
    super.setPos(x, y);
    tile.setPos(x, y);
  }

  public void setAngle(float a)
  {
    super.setAngle(a);
    tile.setAngle(a);
  }
  
  /***
   * Define the ships structure.. 
   */
  private void defineShip()
  {
    Canvas canvas = tile.getCanvas();
    canvas.setStrokeColor(0xffff0000); // 0xaarrggbb
    canvas.drawLine(0, 0, 5, 10);
    canvas.drawLine(5, 10, 10, 0);
    canvas.drawLine(10, 0, 0, 0);
  }

  @Override
  Body initPhysicsBody(World world, float x, float y, float angle)
  {
    FixtureDef fixtureDef = new FixtureDef();
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyType.DYNAMIC;
    bodyDef.position = new Vec2(0, 0);
    Body body = world.createBody(bodyDef);

    CircleShape circleShape = new CircleShape();
    circleShape.m_radius = 2;
    fixtureDef.shape = circleShape;
    fixtureDef.density = 0.4f;
    fixtureDef.friction = 0.1f;
    fixtureDef.restitution = 0.35f;
    circleShape.m_p.set(0, 0);
    body.createFixture(fixtureDef);
    body.setLinearDamping(0.2f);
    body.setTransform(new Vec2(x, y), angle);
    return body;
  }
}
