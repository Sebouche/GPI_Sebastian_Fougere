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

public class Rectangle extends Entity{
	Color m_color;
	int m_width;
	int m_height;
	boolean death_rectangle;
	boolean collision_rectangle;

	Rectangle(Model model, int x, int y, int width, int height, Color color, boolean death_rectangle,
			boolean collision_rectangle) {
		super(model,x,y);
		m_width = width;
		m_height = height;
		m_color = color;
		this.death_rectangle = death_rectangle;
		this.collision_rectangle = collision_rectangle;
	}


	/**
	 * paints this square on the screen.
	 * 
	 * @param g
	 */
	@Override
	void paint(Graphics g) {
		if (true) {
			g.setColor(this.m_color);
			g.drawRect(0, 0, m_width, m_height);
			g.fillRect(0, 0, m_width, m_height);
		}
	}
}
