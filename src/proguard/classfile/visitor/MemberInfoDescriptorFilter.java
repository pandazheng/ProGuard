/* $Id: MemberInfoDescriptorFilter.java,v 1.1 2003/02/10 18:59:58 eric Exp $
 *
 * ProGuard -- obfuscation and shrinking package for Java class files.
 *
 * Copyright (c) 2002-2003 Eric Lafortune (eric@graphics.cornell.edu)
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
import proguard.classfile.util.*;


/**
 * This <code>MemberInfoVisitor</code> delegates its visits to another given
 * <code>MemberInfoVisitor</code>, but only when the visited member
 * has a descriptor that matches a given regular expression.
 *
 * @author Eric Lafortune
 */
public class MemberInfoDescriptorFilter implements MemberInfoVisitor
{
    private MemberInfoVisitor        memberInfoVisitor;
    private RegularExpressionMatcher regularExpressionMatcher;


    /**
     * Creates a new MemberInfoDescriptorFilter.
     * @param memberInfoVisitor the <code>MemberInfoVisitor</code> to which visits
     *                          will be delegated.
     * @param regularExpression the regular expression against which class names
     *                          will be matched.
     */
    public MemberInfoDescriptorFilter(MemberInfoVisitor memberInfoVisitor,
                                      String            regularExpression)
    {
        this.memberInfoVisitor        = memberInfoVisitor;
        this.regularExpressionMatcher = new RegularExpressionMatcher(regularExpression);
    }


    // Implementations for MemberInfoVisitor

    public void visitProgramFieldInfo(ProgramClassFile programClassFile, ProgramFieldInfo programFieldInfo)
    {
        if (accepted(programFieldInfo.getDescriptor(programClassFile)))
        {
            memberInfoVisitor.visitProgramFieldInfo(programClassFile, programFieldInfo);
        }
    }


    public void visitProgramMethodInfo(ProgramClassFile programClassFile, ProgramMethodInfo programMethodInfo)
    {
        if (accepted(programMethodInfo.getDescriptor(programClassFile)))
        {
            memberInfoVisitor.visitProgramMethodInfo(programClassFile, programMethodInfo);
        }
    }


    public void visitLibraryFieldInfo(LibraryClassFile libraryClassFile, LibraryFieldInfo libraryFieldInfo)
    {
        if (accepted(libraryFieldInfo.getDescriptor(libraryClassFile)))
        {
            memberInfoVisitor.visitLibraryFieldInfo(libraryClassFile, libraryFieldInfo);
        }
    }


    public void visitLibraryMethodInfo(LibraryClassFile libraryClassFile, LibraryMethodInfo libraryMethodInfo)
    {
        if (accepted(libraryMethodInfo.getDescriptor(libraryClassFile)))
        {
            memberInfoVisitor.visitLibraryMethodInfo(libraryClassFile, libraryMethodInfo);
        }
    }


    // Small utility methods.

    private boolean accepted(String name)
    {
        return regularExpressionMatcher.matches(name);
    }
}