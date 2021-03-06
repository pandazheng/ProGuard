/* $Id: CompoundCommand.java,v 1.4 2002/05/19 15:53:37 eric Exp $
 *
 * ProGuard -- obfuscation and shrinking package for Java class files.
 *
 * Copyright (C) 2002 Eric Lafortune (eric@graphics.cornell.edu)
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
package proguard;

import proguard.classfile.*;

import java.util.*;


/**
 * This <code>Command</code> delegates execution to zero or more other commands.
 *
 * @author Eric Lafortune
 */
public class CompoundCommand implements Command
{
    private Vector commands = new Vector();


    /**
     * Adds a command to the list of commands to be executed.
     */
    public void addCommand(Command command)
    {
        commands.add(command);
    }


    // Implementations for Command

    public void execute(int       phase,
                        ClassPool programClassPool,
                        ClassPool libraryClassPool)
    {
        for (int i = 0; i < commands.size(); i++)
        {
            ((Command)commands.get(i)).execute(phase,
                                               programClassPool,
                                               libraryClassPool);
        }
    }
}
