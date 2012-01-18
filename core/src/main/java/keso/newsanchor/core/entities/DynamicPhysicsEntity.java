package keso.newsanchor.core.entities;

import keso.newsanchor.core.NewsAnchorWorld;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import playn.core.PlayN;

public abstract class DynamicPhysicsEntity extends Entity implements PhysicsEntity
{
  // for calculating interpolation
  private float prevX, prevY, prevA;
  private Body body;

  public DynamicPhysicsEntity(NewsAnchorWorld newsAnchorWorld, World world, float x, float y, float angle)
  {
    super(newsAnchorWorld, x, y, angle);
    body = initPhysicsBody(world, x, y, angle);
    body.setTransform(new Vec2(x, y), angle);
  }

  /**
   * This method needs to be implemented by the entity subclasses where they
   * can define the physics properties of the entity.
   */
  abstract Body initPhysicsBody(World world, float x, float y, float angle);

  @Override
  public void paint(float alpha)
  {
    // interpolate based on previous state
    x = (body.getPosition().x * alpha) + (prevX * (1f - alpha));
    y = (body.getPosition().y * alpha) + (prevY * (1f - alpha));
    angle = (body.getAngle() * alpha) + (prevA * (1f - alpha));
  }

  @Override
  public void update(float delta)
  {
    // store state for interpolation in paint()
    prevX = body.getPosition().x;
    prevY = body.getPosition().y;
    prevA = body.getAngle();
  }

  public void setLinearVelocity(float x, float y)
  {
    body.setLinearVelocity(new Vec2(x, y));
  }

  public void setAngularVelocity(float w)
  {
    body.setAngularVelocity(w);
  }

  @Override
  public void setPos(float x, float y)
  {
    getBody().setTransform(new Vec2(x, y), getBody().getAngle());
    prevX = x;
    prevY = y;
  }

  @Override
  public void setAngle(float a)
  {
    getBody().setTransform(getBody().getPosition(), a);
    prevA = a;
  }

  @Override
  public Body getBody()
  {
    return body;
  }
}
