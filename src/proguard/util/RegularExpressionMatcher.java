/* $Id: RegularExpressionMatcher.java,v 1.1 2003/10/21 03:26:47 eric Exp $
 *
 * ProGuard -- obfuscation and shrinking package for Java class files.
 *
 * Copyright (c) 2002 Eric Lafortune (eric@graphics.cornell.edu)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package proguard.util;


/**
 * This interface provides a method to determine whether strings match a given
 * regular expression.
 *
 * @author Eric Lafortune
 */
public interface RegularExpressionMatcher
{
    /**
     * Checks whether the given string matches.
     * @param string the string to match.
     * @return a boolean indicating whether the regular expression matches the
     *         given string.
     */
    public boolean matches(String string);
}
