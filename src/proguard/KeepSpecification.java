/*
 * ProGuard -- shrinking, optimization, obfuscation, and preverification
 *             of Java bytecode.
 *
 * Copyright (c) 2002-2008 Eric Lafortune (eric@graphics.cornell.edu)
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

/**
 * This class stores a specification of keep option, with a purpose and a class
 * specification.
 *
 * @author Eric Lafortune
 */
public class KeepSpecification extends ClassSpecification
{
    public final boolean markClasses;
    public final boolean markConditionally;
    public final boolean allowShrinking;
    public final boolean allowOptimization;
    public final boolean allowObfuscation;


    /**
     * Creates a new KeepSpecification for all possible classes.
     * @param markClasses        specifies whether to mark the classes.
     *                           If false, only class members are marked.
     *                           If true, the classes are marked as well.
     * @param markConditionally  specifies whether to mark the classes and
     *                           class members conditionally. If true, classes
     *                           and class members are marked, on the condition
     *                           that all specified class members are present.
     * @param allowShrinking     specifies whether shrinking is allowed.
     * @param allowOptimization  specifies whether optimization is allowed.
     * @param allowObfuscation   specifies whether obfuscation is allowed.
     */
    public KeepSpecification(boolean            markClasses,
                             boolean            markConditionally,
                             boolean            allowShrinking,
                             boolean            allowOptimization,
                             boolean            allowObfuscation)
    {
        this.markClasses       = markClasses;
        this.markConditionally = markConditionally;
        this.allowShrinking    = allowShrinking;
        this.allowOptimization = allowOptimization;
        this.allowObfuscation  = allowObfuscation;
    }


    /**
     * Creates a new KeepSpecification.
     * @param markClasses        specifies whether to mark the classes.
     *                           If false, only class members are marked.
     *                           If true, the classes are marked as well.
     * @param markConditionally  specifies whether to mark the classes and
     *                           class members conditionally. If true, classes
     *                           and class members are marked, on the condition
     *                           that all specified class members are present.
     * @param allowShrinking     specifies whether shrinking is allowed.
     * @param allowOptimization  specifies whether optimization is allowed.
     * @param allowObfuscation   specifies whether obfuscation is allowed.
     * @param classSpecification the specification of classes and class members.
     */
    public KeepSpecification(boolean            markClasses,
                             boolean            markConditionally,
                             boolean            allowShrinking,
                             boolean            allowOptimization,
                             boolean            allowObfuscation,
                             ClassSpecification classSpecification)
    {
        super(classSpecification);

        this.markClasses       = markClasses;
        this.markConditionally = markConditionally;
        this.allowShrinking    = allowShrinking;
        this.allowOptimization = allowOptimization;
        this.allowObfuscation  = allowObfuscation;
    }


    // Implementations for Object.

    public boolean equals(Object object)
    {
        if (object == null ||
            this.getClass() != object.getClass())
        {
            return false;
        }

        KeepSpecification other = (KeepSpecification)object;
        return
            this.markClasses       == other.markClasses       &&
            this.markConditionally == other.markConditionally &&
            this.allowShrinking    == other.allowShrinking    &&
            this.allowOptimization == other.allowOptimization &&
            this.allowObfuscation  == other.allowObfuscation  &&
            super.equals(other);
    }

    public int hashCode()
    {
        return
            (markClasses       ? 0 :  1) ^
            (markConditionally ? 0 :  2) ^
            (allowShrinking    ? 0 :  4) ^
            (allowOptimization ? 0 :  8) ^
            (allowObfuscation  ? 0 : 16) ^
            super.hashCode();
    }

    public Object clone()
    {
//        try
//        {
            return super.clone();
//        }
//        catch (CloneNotSupportedException e)
//        {
//            return null;
//        }
    }
}
