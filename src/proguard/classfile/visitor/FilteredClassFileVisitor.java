/* $Id: FilteredClassFileVisitor.java,v 1.4 2002/05/12 13:33:41 eric Exp $
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
package proguard.classfile.visitor;

import proguard.classfile.*;


/**
 * This <code>ClassFileVisitor</code> delegates its visits to another given
 * <code>ClassFileVisitor</code>, but only when the visited class file
 * has the proper access flags.
 *
 * @see ClassConstants
 *
 * @author Eric Lafortune
 */
public class FilteredClassFileVisitor implements ClassFileVisitor
{
    private ClassFileVisitor classFileVisitor;
    private int              requiredSetAccessFlags;
    private int              requiredUnsetAccessFlags;


    /**
     * Creates a new FilteredClassFileVisitor.
     * @param classFileVisitor         the <code>ClassFileVisitor</code> to
     *                                 which visits will be delegated.
     * @param requiredSetAccessFlags   the class access flags that should be 
     *                                 set.
     * @param requiredUnsetAccessFlags the class access flags that should be
     *                                 unset.
     */
    public FilteredClassFileVisitor(ClassFileVisitor classFileVisitor,
                                    int              requiredSetAccessFlags,
                                    int              requiredUnsetAccessFlags)
    {
        this.classFileVisitor         = classFileVisitor;
        this.requiredSetAccessFlags   = requiredSetAccessFlags;
        this.requiredUnsetAccessFlags = requiredUnsetAccessFlags;
    }


    // Implementations for ClassFileVisitor

    public void visitProgramClassFile(ProgramClassFile programClassFile)
    {
        int accessFlags = programClassFile.getAccessFlags();
        if ((requiredSetAccessFlags   & ~accessFlags) == 0 &&
            (requiredUnsetAccessFlags &  accessFlags) == 0)
        {
            classFileVisitor.visitProgramClassFile(programClassFile);
        }
    }


    public void visitLibraryClassFile(LibraryClassFile libraryClassFile)
    {
        int accessFlags = libraryClassFile.getAccessFlags();
        if ((requiredSetAccessFlags   & ~accessFlags) == 0 &&
            (requiredUnsetAccessFlags &  accessFlags) == 0)
        {
            classFileVisitor.visitLibraryClassFile(libraryClassFile);
        }
    }
}
