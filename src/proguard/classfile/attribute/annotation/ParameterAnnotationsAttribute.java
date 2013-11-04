/*
 * ProGuard -- shrinking, optimization, obfuscation, and preverification
 *             of Java bytecode.
 *
 * Copyright (c) 2002-2007 Eric Lafortune (eric@graphics.cornell.edu)
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package proguard.classfile.attribute.annotation;

import proguard.classfile.*;
import proguard.classfile.attribute.Attribute;
import proguard.classfile.attribute.annotation.visitor.AnnotationVisitor;

/**
 * This Attribute represents a runtime parameter annotations attribute.
 *
 * @author Eric Lafortune
 */
public abstract class ParameterAnnotationsAttribute extends Attribute
{
    public int            u2parametersCount;
    public int[]          u2parameterAnnotationsCount;
    public Annotation[][] parameterAnnotations;


    protected ParameterAnnotationsAttribute()
    {
    }


    /**
     * Applies the given visitor to all annotations.
     */
    public void annotationsAccept(Clazz clazz, Method method, AnnotationVisitor annotationVisitor)
    {
        // Loop over all parameters.
        for (int parameterIndex = 0; parameterIndex < u2parametersCount; parameterIndex++)
        {
            int          annotationsCount = u2parameterAnnotationsCount[parameterIndex];
            Annotation[] annotations      = parameterAnnotations[parameterIndex];

            // Loop over all parameter annotations.
            for (int index = 0; index < annotationsCount; index++)
            {
                // We don't need double dispatching here, since there is only one
                // type of Annotation.
                annotationVisitor.visitAnnotation(clazz, method, parameterIndex, annotations[index]);
            }
        }
    }
}