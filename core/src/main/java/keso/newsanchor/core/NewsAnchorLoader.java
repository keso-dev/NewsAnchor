/**
 * Copyright 2011 The PlayN Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package keso.newsanchor.core;

import keso.newsanchor.core.entities.Entity;
import keso.newsanchor.core.entities.Ship;
import playn.core.AssetWatcher;
import playn.core.PlayN;
import playn.core.GroupLayer;
import playn.core.Json;
import playn.core.ResourceCallback;

public class NewsAnchorLoader {

  public static void CreateWorld(String level, final GroupLayer worldLayer, final ResourceCallback<NewsAnchorWorld> callback) {

    final NewsAnchorWorld newsAnchorWorld = new NewsAnchorWorld(worldLayer);

    Ship ship = new Ship(newsAnchorWorld, newsAnchorWorld.world, 5, 5, 0);
    newsAnchorWorld.add(ship);

	/*
	
    // load the level
    PlayN.assetManager().getText(level, new ResourceCallback<String>() {

    	@Override
      public void done(String resource) {
        // create an asset watcher that will call our callback when all assets
        // are loaded
        AssetWatcher assetWatcher = new AssetWatcher(new AssetWatcher.Listener() {
          @Override
          public void done() {
            callback.done(newsAnchorWorld);
          }

          @Override
          public void error(Throwable e) {
            callback.error(e);
          }
        });

        // parse the level
    
        Json.Object document = PlayN.json().parse(resource);

        // parse the entities, adding each asset to the asset watcher
        Json.Array jsonEntities = document.getArray("Entities");
        for (int i = 0; i < jsonEntities.length(); i++) {
          Json.Object jsonEntity = jsonEntities.getObject(i);
          String type = jsonEntity.getString("type");
          float x = (float) jsonEntity.getNumber("x");
          float y = (float) jsonEntity.getNumber("y");
          float a = (float) jsonEntity.getNumber("a");

          Entity entity = null;
          
          
          if (Pea.TYPE.equalsIgnoreCase(type)) {
            entity = new Pea(peaWorld, peaWorld.world, x, y, a);
          } else if (Block.TYPE.equalsIgnoreCase(type)) {
            entity = new Block(peaWorld, peaWorld.world, x, y, a);
          }
          
          
          if (entity != null) {
            assetWatcher.add(entity.getImage());
            newsAnchorWorld.add(entity);
          }
        }
        

        
        // start the watcher (it will call the callback when everything is
        // loaded)
        assetWatcher.start();
      }

      @Override
      public void error(Throwable err) {
        callback.error(err);
      }
    });
    */
  }

}
