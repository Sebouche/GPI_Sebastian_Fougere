/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ricm3.game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;

public class Model extends GameModel {
	BufferedImage m_cowboySprite;
	BufferedImage m_explosionSprite;
	BufferedImage m_cursorSprite;
	BufferedImage m_ghostSprite;
	Cowboy m_cowboys;
	//Cursor m_cursor;
	LinkedList<Entity> m_entity;
	Random rand= new Random();
	Camera m_camera;
	public Model() {
		loadSprites();
		m_entity = new LinkedList<Entity>();
		m_cowboys = new Cowboy(this, 0, m_cowboySprite, 4, 6, 20, 300, 3F);
		m_entity.add(m_cowboys);
		m_cowboys.setExplosion(m_explosionSprite, 11, 10);
		//m_cursor = new Cursor(this,m_cursorSprite);
		
		m_entity.add(new Rectangle(this,0, 500,1500,20,new Color(0,  255, 0),false,true));
		m_entity.add(new Rectangle(this,0, 520,1500,200,new Color(160, 160, 60),false,true));
		m_entity.add(new Rectangle(this,1700, 500,1500,200,new Color(0, 255, 0),false,true));
		m_entity.add(new Rectangle(this,1700, 520,1500,200,new Color(160, 160, 60),false,true));
		m_entity.add(new Rectangle(this,1300, 450,200,50,new Color(160, 160, 160),false,true));
		m_entity.add(new Rectangle(this,1500, 540,200,200,new Color(255, 50, 0),true,true));
		m_entity.add(new Rectangle(this,400, 370,100,20,new Color(0, 255, 0),false,true));
		m_entity.add(new Rectangle(this,600,350 ,100,20,new Color(0, 255, 0),false,true));
		m_entity.add(new Rectangle(this,800, 370,100,20,new Color(0, 255, 0),false,true));
		m_entity.add(new Rectangle(this,-10,-10 ,10,1000,new Color(120, 120, 120),false,true));
		m_entity.add(new Rectangle(this,1700, 450,200,50,new Color(160, 160, 160),false,true));
		m_entity.add(new Rectangle(this,400, 370,100,20,new Color(0, 255, 0),false,true));
		m_entity.add(new Rectangle(this,400, 370,100,20,new Color(0, 255, 0),false,true));

		Ghost g=new Ghost(this, 0, m_ghostSprite, 4, 10, 600, 400, 2F);
		g.setExplosion(m_explosionSprite, 11, 10);
		m_entity.add(g);
		g=new Ghost(this, 0, m_ghostSprite, 4, 10, 1950, 400, 2F);
		g.setExplosion(m_explosionSprite, 11, 10);
		m_entity.add(g);
		
		m_camera=new Camera(this);
		

	}

	@Override
	public void shutdown() {
	}

	public Cowboy cowboys() {
		return m_cowboys;
	}

	public Iterator<Entity> entites() {
		return m_entity.iterator();
	}

	/**
	 * Simulation step.
	 * 
	 * @param now is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {
		Iterator<Entity> iter = m_entity.iterator();
		while (iter.hasNext()) {
			Entity s = iter.next();
			s.step(now);
			if(s instanceof Bullet) {
				Bullet b=(Bullet) s;
				if(!b.m_alive) {
					iter.remove();
				}
			}
		}
		m_camera.step(now);
	}

	private void loadSprites() {
		/*
		 * Cowboy with rifle, western style; png; 48x48 px sprite size Krasi Wasilev (
		 * http://freegameassets.blogspot.com)
		 */
		File imageFile = new File("game.sample/sprites/winchester.png");
		try {
			m_cowboySprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		/*
		 * Long explosion set; png file; 64x64 px sprite size Krasi Wasilev (
		 * http://freegameassets.blogspot.com)
		 */
		imageFile = new File("game.sample/sprites/explosion01_set_64.png");
		try {
			m_explosionSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		
		imageFile = new File("game.sample/sprites/cursor.png");
		try {
			m_cursorSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		
		imageFile = new File("game.sample/sprites/ghost_sprite.png");
		try {
			m_ghostSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}

}
