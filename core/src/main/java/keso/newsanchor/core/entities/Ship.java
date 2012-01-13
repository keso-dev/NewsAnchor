package keso.newsanchor.core.entities;

import keso.newsanchor.core.NewsAnchorWorld;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import playn.core.Canvas;
import playn.core.CanvasLayer;

public class Ship extends DynamicPhysicsEntity {
  
  private Canvas canvas;
  private float width = 15;
  private float height = 15;

	public Ship(NewsAnchorWorld newsAnchorWorld, World world, float x, float y,
			float angle) {
		super(newsAnchorWorld, world, x, y, angle);
	// TODO Auto-generated method stub
	}

	@Override
	Body initPhysicsBody(World world, float x, float y, float angle) {
	  FixtureDef fixtureDef = new FixtureDef();
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyType.STATIC;
    bodyDef.position = new Vec2(0, 0);
    Body body = world.createBody(bodyDef);

    PolygonShape polygonShape = new PolygonShape();
    Vec2[] polygon = new Vec2[4];
    polygon[0] = new Vec2(-getWidth()/2f, -getHeight()/2f);
    polygon[1] = new Vec2(getWidth()/2f, -getHeight()/2f);
    polygon[2] = new Vec2(getWidth()/2f, getHeight()/2f);
    polygon[3] = new Vec2(-getWidth()/2f, getHeight()/2f);
    polygonShape.set(polygon, polygon.length);
    fixtureDef.shape = polygonShape;
    fixtureDef.friction = 0.1f;
    fixtureDef.restitution = 0.8f;
    body.createFixture(fixtureDef);
    body.setTransform(new Vec2(x, y), angle);
    return body;
	}
	
	@Override
	public void initPreLoad(NewsAnchorWorld newsAnchorWorld) {
	  canvas = ((CanvasLayer)layer).canvas();
	  defineShip();
	  super.initPreLoad(newsAnchorWorld);
	}

	@Override
	public void initPostLoad(NewsAnchorWorld newsAnchorWorld) {
	  super.initPostLoad(newsAnchorWorld);
	}
	
	/***
	 * Define the ships structure.. 
	 */
	private void defineShip() {
	  canvas.setStrokeColor(0xffff0000); // 0xaarrggbb
	  canvas.drawLine(0, 0, 5, 10);
	  canvas.drawLine(5, 10, 10, 0);
	  canvas.drawLine(10, 0, 0, 0);
  }

  @Override
	float getWidth() {
		return 15;
	}

	@Override
	float getHeight() {
		return 15;
	}

	@Override
	String getImageName() {
		// TODO Auto-generated method stub
		return null;
	}

}
