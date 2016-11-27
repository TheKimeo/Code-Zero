package Component;

import java.util.ArrayList;
import java.util.Comparator;

import Command.Command;
import Entity.Entity;
import Level.Level;
import Level.Tile;
import Level.TileStorage;
import Utils.Pair;

public class LevelPhysics implements PhysicsComponent {

	public void update(int frame, Entity e, Level level) {
		e.isRewind = false;
		e.isWalkingLeft = false;
		e.isWalkingRight = false;
		
		ArrayList<Command> commands = e.getCommandStream().getCommands(frame);
		for (Command c : commands) {
			c.execute();
		}
		
		if (e.isWalkingLeft == false && e.frames == e.leftWalkFrames) {
			e.getGraphics().setFrames(e, e.leftIdleFrames, 20);
		}
		if (e.isWalkingRight == false && e.frames == e.rightWalkFrames) {
			e.getGraphics().setFrames(e, e.rightIdleFrames, 20);
		}
		
		if (e.warping) {
			if (e.currentFrame == 4) {
				
				e.x = e.destx;
				e.y = e.desty;
				e.dx = 0.0;
				e.dy = 0.0;
				e.jumpTime = 0;
			} else if (e.currentFrame == 8) {
				e.warping = false;
				if (e.facing)
					e.getGraphics().setFrames(e, e.rightIdleFrames, 20);
				else
					e.getGraphics().setFrames(e, e.leftIdleFrames, 20);
			}
		}
		
		
		ArrayList<TileStorage> inside = level.getTilesWithin(e);
		ArrayList<TileStorage> below = level.getTilesBelow(e);

		double gravity = 0.0f;
		double friction = 0.0f;

		if (e.onCeiling)
			e.jumpTime = 0;

		if (e.jumpTime > 0) {
			e.jumpTime--;
			e.dy = -4.8; //2.4
		}

		if (inside.size() > 0) {
			for (TileStorage t : inside) {
				gravity += t.tile.gravity;
			}
			gravity /= (float)inside.size();

			if (!e.onFloor || below.size() > 0) {
				ArrayList<TileStorage> temp;
				if (e.onFloor) {
					temp = new ArrayList<TileStorage>(below);
					temp.addAll(inside);
				} else {
					temp = inside;
				}
				for (TileStorage t : temp) {
					friction = Math.max(friction, t.tile.friction);
				}
			}
		}
		
		
		e.dy += gravity;
		
		if (Math.abs(e.dx) <= friction)
			e.dx = 0.0f;
		else
			e.dx += (e.dx > 0.0f ? -friction : friction);

		e.dx = clamp(e.dx, -e.maxdx, e.maxdx);
		e.dy = clamp(e.dy, -e.maxdy, e.maxdy);

		
		e.x += e.dx;
		e.y += e.dy;

		collideWithMap(e, level);
		
		if (frame <= e.positions.size()) {
			e.positions.add(new Pair<Double, Double>(e.x, e.y));
		} else {
			e.positions.set(frame, new Pair<Double, Double>(e.x, e.y));
		}
	}
	
	public boolean collideWithTile(Entity e, Tile t, double tileCenterX, double tileCenterY) {
		if(!t.collidable) {
			return false;
		}
		
		double crx = e.width / 2.0 + Tile.TILE_SIZE / 2.0;
		double cry = e.height / 2.0 + Tile.TILE_SIZE / 2.0;
		
		double cx = e.x + e.width / 2.0;
		double cy = e.y + e.height / 2.0;

		double distx = cx - tileCenterX;
		double disty = cy - tileCenterY;

		double cdx = crx - Math.abs(distx);
		double cdy = cry - Math.abs(disty);

		if (cdx > 0.0 && cdy > 0.0) {
			if (Math.abs(distx) > Math.abs(disty)) {
				if (distx > 0.0) {
					e.x += cdx;
					if (e.dx < 0.0) {
						e.dx = 0.0;
					}
				} else {
					e.x -= cdx;
					if (e.dx > 0.0) {
						e.dx = 0.0;
					}
				}
			} else {
				if (disty < 0.0) {
					e.y -= cdy;
					if (e.dy > 0.0) {
						e.dy = 0.0;
                        e.onFloor = true;
                    }
                } else {
					e.y += cdy;
					if (e.dy < 0.0) {
						e.dy = 0.0;
                        e.onCeiling = true;
					}
				}
			}
            return true;
		}
		return false;
	}
	
	public boolean collideWithMap(Entity e, Level l) {
		ArrayList<TileStorage> within = l.getTilesWithin(e);

		e.onCeiling = false;
		e.onFloor = false;
		
		if (within.size() <= 0)
			return false;

		within.sort(new compareTileDistances(e));
		
		boolean didCollide = false;
		for (TileStorage t : within) {
			if (collideWithTile(e, t.tile, t.centerx, t.centery)) {
				didCollide = true;
			}
		}
		
		return didCollide;
	}
	
	private class compareTileDistances implements Comparator<TileStorage> {
		private double centerex;
		private double centerey;
		
		public compareTileDistances(Entity e) {
			this.centerex = e.x + e.boundingBox.width / 2.0;
			this.centerey = e.y + e.boundingBox.height / 2.0;
		}
		
		@Override
		public int compare(TileStorage o1, TileStorage o2) {
			double dist1 = Math.pow(o1.centerx - centerex, 2.0) + Math.pow(o1.centery - centerey, 2.0);
			double dist2 = Math.pow(o2.centerx - centerex, 2.0) + Math.pow(o2.centery - centerey, 2.0);
			return (int) (dist1 - dist2);
		}
	}
	
	private double clamp(double val, double min, double max) {
		return Math.max(min, Math.min(max, val));
	}
	
	public void reset() {
		
	}
}
