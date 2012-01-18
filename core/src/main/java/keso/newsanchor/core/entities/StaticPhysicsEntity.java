package keso.newsanchor.core.entities;

import keso.newsanchor.core.NewsAnchorWorld;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

public abstract class StaticPhysicsEntity extends Entity implements PhysicsEntity
{
  private Body body;

  public StaticPhysicsEntity(final NewsAnchorWorld newsAnchorWorld, World world, float x, float y, float angle)
  {
    super(newsAnchorWorld, x, y, angle);
    body = initPhysicsBody(world, x, y, angle);
  }

  abstract Body initPhysicsBody(World world, float x, float y, float angle);

  @Override
  public void setPos(float x, float y)
  {
    throw new RuntimeException("Error setting position on static entity");
  }

  @Override
  public void setAngle(float a)
  {
    throw new RuntimeException("Error setting angle on static entity");
  }

  @Override
  public Body getBody()
  {
    return body;
  }
}
