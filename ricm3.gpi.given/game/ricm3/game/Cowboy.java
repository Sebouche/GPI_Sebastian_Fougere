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
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Iterator;

/**
 * This class is to illustrate a simple animated character. It does not much,
 * but it shows how to load an animation sprite and how to cycle through the
 * entire animation.
 * 
 * Pay attention to the finite state automaton, that is, the entire behavior
 * behaves as an automaton that progress a step at a time. Look at the method
 * step(long).
 * 
 * About the behavior of the cowboy. The cowboy can rotate around, that is the
 * animation. But since using arms may be dangerous, his gun may explode.
 * 
 * @author Pr. Olivier Gruber
 */

public class Cowboy extends Entity {
	BufferedImage m_sprite;
	int m_life = 3;
	int m_w, m_h;
	int m_width, m_height;
	int m_dx, m_dy;
	int m_ndy = 0;
	int m_nrows, m_ncols;
	int m_step;
	int m_nsteps;
	int m_idx = 20;
	int m_shoot_cooldown=0;
	int m_orientation = 1;
	int hitbox[];
	float m_scale;
	long m_lastMove, m_lastReverse;
	boolean m_canExplode;
	boolean m_explode;
	BufferedImage[] m_sprites;
	Explosion m_explosion;

	Cowboy(Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale) {
		super(model, x, y);
		m_sprite = sprite;
		m_ncols = columns;
		m_nrows = rows;
		m_dx = 0;
		m_dy = 1;
		m_scale = scale;
		splitSprite();
		m_width = (int) (m_scale * m_w);
		m_height = (int) (m_scale * m_h);
		hitbox = new int[4];
		hitbox[0] = 60;
		hitbox[1] = 20;
		hitbox[2] = m_width - 110;
		hitbox[3] = m_height - 40;
	}

	/*
	 * This is about splitting the animated sprite into the basic sub-images
	 * constituting the animation.
	 */
	void splitSprite() {
		int width = m_sprite.getWidth(null);
		int height = m_sprite.getHeight(null);
		m_sprites = new BufferedImage[m_nrows * m_ncols];
		m_w = width / m_ncols;
		m_h = height / m_nrows;
		m_step = 8;
		for (int i = 0; i < m_nrows; i++) {
			for (int j = 0; j < m_ncols; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * m_ncols) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
			}
		}
	}

	void setExplosion(BufferedImage sprite, int rows, int columns) {
		m_explosion = new Explosion(m_model, sprite, rows, columns);
	}

	@Override
	boolean collision(int x, int y) {
		Iterator<Entity> iter = m_model.entites();
		while (iter.hasNext()) {
			Entity e = iter.next();
			if (e instanceof Rectangle) {
				Rectangle rect = (Rectangle) e;
				if (rect.collision_rectangle && x <= rect.m_x + rect.m_width && (int) (x + hitbox[2]) > rect.m_x
						&& y <= rect.m_y + rect.m_height && (int) (y + hitbox[3]) > rect.m_y) {
					if (rect.death_rectangle) {
						this.m_canExplode = true;
					}
					return true;
				}
			}
			if (e instanceof Ghost) {
				Ghost rect = (Ghost) e;
				if (x <= rect.m_x + rect.m_width && (int) (x + hitbox[2]) > rect.m_x && y <= rect.m_y + rect.m_height
						&& (int) (y + hitbox[3]) > rect.m_y) {
					this.m_canExplode = true;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Simulation step. This is essentially a finite state automaton. Here, you
	 * decide what happens as time flies.
	 * 
	 * @param now is the current time in milliseconds.
	 */
	@Override
	void step(long now) {
		long elapsed = now - m_lastMove;
		if (elapsed >= 1L) {
			m_lastMove=now;
			if(m_shoot_cooldown>0) {
				m_shoot_cooldown--;
			}
			if (m_explode) {
				m_explosion.step(now);
				return;
			}
			if (m_canExplode) {
				m_explode = true;
				return;
			}
			if (m_dx == 1) {
				m_idx = 20;
				m_orientation = 1;
			}
			if (m_dx == -1) {
				m_idx = 8;
				m_orientation = -1;
			}
			if (m_dx != 0) {
				if (!collision(hitbox[0] + m_x + m_dx, hitbox[1] + m_y)) {
					m_x += m_dx;
				}
			}
			if (m_dy != 0) {
				if (m_dy == -1) {
					m_ndy++;
				}
				if (!collision(hitbox[0] + m_x, hitbox[1] + m_y + m_dy)) {
					if (m_dy == -1 && m_ndy < 150) {
						m_y += m_dy;
					} else {
						m_dy = 1;
						m_y += m_dy;
					}
				} else {
					m_ndy = 0;
				}
			}
		}
	}

	/**
	 * paints this cowboy on the screen.
	 * 
	 * @param g
	 */
	@Override
	void paint(Graphics g) {
		if (m_explode) {
			m_width = (int) (m_scale * m_w);
			m_height = (int) (m_scale * m_h);
			int x = m_width / 2 - 15;
			int y = m_height / 2 - 15;
			Graphics g_child = g.create(x, y, m_width, m_height);
			m_explosion.paint(g_child);
			g_child.dispose();
			if (m_explosion.done()) {
				m_explode = false;
				m_canExplode = false;
				m_life -= 1;
				m_x = 20;
				m_y = 300;
				m_explosion.m_idx = 0;
			}
		} else {
			g.setColor(Color.BLACK);
			Image img = m_sprites[m_idx];
			m_width = (int) (m_scale * m_w);
			m_height = (int) (m_scale * m_h);
			g.drawImage(img, 0, 0, m_width, m_height, null);
		}
	}
}
