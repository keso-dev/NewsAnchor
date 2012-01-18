package keso.newsanchor.core.entities;

import static playn.core.PlayN.assetManager;
import static playn.core.PlayN.graphics;
import keso.newsanchor.core.NewsAnchorWorld;

import playn.core.Layer;
import playn.core.PlayN;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;

public abstract class Entity
{
  float x;
  float y;
  float angle;

  public Entity(final NewsAnchorWorld newsAnchorWorld, float px, float py, float pangle)
  {
    this.x = px;
    this.y = py;
    this.angle = pangle;
  }

  public void paint(float alpha)
  {
  }

  public void update(float delta)
  {
  }

  public void setPos(float x, float y)
  {
  }

  public void setAngle(float a)
  {
  }
}
