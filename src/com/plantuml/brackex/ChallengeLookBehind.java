/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2024, Arnaud Roques
 *
 * Project Info:  https://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 * 
 * https://plantuml.com/patreon (only 1$ per month!)
 * https://plantuml.com/paypal
 * 
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 *
 */
package com.plantuml.brackex;

public class ChallengeLookBehind implements Challenge {

	private final Challenge origin;
	private final LookAround ahead;

	public ChallengeLookBehind(Challenge origin, LookAround ahead) {
		this.origin = origin;
		this.ahead = ahead;
	}

	@Override
	public ChallengeResult runChallenge(TextNavigator string, int position) {
		final TextNavigator stringReversed = string.reverse(position);
		final int tmp = origin.runChallenge(stringReversed, 0).getInt();
		switch (ahead) {
		case LOOK_BEHIND_POSITIVE:
			if (tmp < 0)
				return new ChallengeResult(NO_MATCH);
			return new ChallengeResult(0);

		case LOOK_BEHIND_NEGATIVE:
			if (tmp < 0)
				return new ChallengeResult(0);
			return new ChallengeResult(NO_MATCH);
		}
		throw new IllegalStateException();
	}

}
