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

public class Cursor {
	BufferedImage m_sprite;
	int m_w, m_h;
	int m_width, m_height;
	int m_x, m_y;
	int m_dx, m_dy;
	float m_scale;
	long m_lastMove;
	Model m_model;

	Cursor(Model model,BufferedImage sprite) {
		m_model = model;
		m_sprite=sprite;
		m_x = 0;
		m_y = 0;
		m_w=m_sprite.getWidth();
		m_h=m_sprite.getHeight();
		m_scale=0.05F;
	}

	
	/**
	 * paints this cowboy on the screen.
	 * 
	 * @param g
	 */
	void paint(Graphics g) {
		g.setColor(Color.BLACK);
		m_width = (int) (m_scale * m_w);
		m_height = (int) (m_scale * m_h);
		g.drawImage(m_sprite, 0, 0, m_width, m_height, null);
	}
}
