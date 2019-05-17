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

public class Bullet extends Entity {
	int m_dx, m_dy;
	long m_lastMove;
	boolean m_alive = true;

	Bullet(Model model, int dx, int dy) {
		super(model, 0, 0);
		m_dx = dx;
		m_dy = dy;
		m_x = m_model.m_cowboys.m_x + m_model.m_cowboys.m_w + m_dx;
		m_y = m_model.m_cowboys.m_y + (int) (2 * m_model.m_cowboys.m_h) + m_dy;
	}

	@Override
	boolean collision(int x, int y) {
		Iterator<Entity> iter = m_model.entites();
		while (iter.hasNext()) {
			Entity e = iter.next();
			if (e instanceof Rectangle) {
				Rectangle rect = (Rectangle) e;
				if (rect.collision_rectangle && x <= rect.m_x + rect.m_width && x > rect.m_x
						&& y <= rect.m_y + rect.m_height && y > rect.m_y) {
					m_alive = false;
					return true;
				}
			}
			if (e instanceof Ghost) {
				Ghost rect = (Ghost) e;
				if (x <= rect.m_x + rect.m_width && x > rect.m_x && y <= rect.m_y + rect.m_height && y > rect.m_y) {
					rect.hp--;
					m_alive = false;
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
			m_lastMove = now;
			m_x += m_dx;
			m_y += m_dy;
			collision(m_x, m_y);
		}
	}

	/**
	 * paints this bullet on the screen.
	 * 
	 * @param g
	 */
	@Override
	void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawOval(0, 0, 10, 10);
	}
}
