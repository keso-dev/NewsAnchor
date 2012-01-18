package keso.newsanchor.core;

import static playn.core.PlayN.assetManager;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.pointer;

import playn.core.Game;
import playn.core.PlayN;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;
import playn.core.ResourceCallback;

public class NewsAnchor implements Game
{
  // scale difference between screen space (pixels) and world space (physics).
  public static float physUnitPerScreenUnit = 1 / 26.666667f;

  ImageLayer bgLayer;

  // main layer that holds the world. note: this gets scaled to world space
  GroupLayer worldLayer;

  // main world
  NewsAnchorWorld world = null;
  boolean worldLoaded = false;

  @Override
  public void init()
  {
    // load and show our background image
    Image bgImage = assetManager().getImage("images/bg.png");
    bgLayer = graphics().createImageLayer(bgImage);
    graphics().rootLayer().add(bgLayer);

    // create our world layer (scaled to "world space")
    worldLayer = graphics().createGroupLayer();
    worldLayer.setScale(1f / physUnitPerScreenUnit);
    graphics().rootLayer().add(worldLayer);

    NewsAnchorLoader.CreateWorld("peas/levels/level1.json", worldLayer, new ResourceCallback<NewsAnchorWorld>()
    {
      @Override
      public void done(NewsAnchorWorld resource)
      {
        world = resource;
        worldLoaded = true;
      }

      @Override
      public void error(Throwable err)
      {
        PlayN.log().error("Error loading pea world: " + err.getMessage());
      }
    });

    // hook up our pointer listener
    pointer().setListener(new Pointer.Adapter()
    {
      @Override
      public void onPointerStart(Pointer.Event event)
      {
        if (worldLoaded)
        {
          /*
          Pea pea = new Pea(world, world.world, physUnitPerScreenUnit * event.x(),
                            physUnitPerScreenUnit * event.y(), 0);
          world.add(pea);
          */
        }
      }
    });
  }
 
  public void shutdown()
  {
    bgLayer.destroy();
    bgLayer = null;
    worldLayer.destroy();
    worldLayer = null;
    world = null;
    worldLoaded = false;
  }

  @Override
  public void paint(float alpha)
  {
    if (worldLoaded)
    {
      world.paint(alpha);
    }
  }

  @Override
  public void update(float delta)
  {
    if (worldLoaded)
    {
      world.update(delta);
    }
  }

	@Override
	public int updateRate()
	{
		return 25;
	}
}
