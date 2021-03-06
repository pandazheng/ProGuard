/* $Id: DirectoryReader.java,v 1.6 2003/12/06 22:15:38 eric Exp $
 *
 * ProGuard -- obfuscation and shrinking package for Java class files.
 *
 * Copyright (c) 2002-2004 Eric Lafortune (eric@graphics.cornell.edu)
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
package proguard.classfile.io;

import java.io.*;


/**
 * This DataEntryPump can read a given directory, recursively, applying a given
 * DataEntryReader to all files it comes across.
 *
 * @author Eric Lafortune
 */
public class DirectoryReader implements DataEntryPump
{
    private File directory;


    public DirectoryReader(File directory)
    {
        this.directory = directory;
    }


    // Implementation for DataEntryPump.

    public void pumpDataEntries(DataEntryReader dataEntryReader)
    throws IOException
    {
        readFiles(directory, dataEntryReader);
    }


    /**
     * Reads the given subdirectory recursively, applying the given DataEntryReader
     * to all files that are encountered.
     */
    private void readFiles(File subdirectory, DataEntryReader dataEntryReader)
    throws IOException
    {
        File[] files = subdirectory.listFiles();

        for (int index = 0; index < files.length; index++)
        {
            File file = files[index];
            if (file.isDirectory())
            {
                // Recurse into the subdirectory.
                readFiles(file, dataEntryReader);
            }
            else
            {
                try
                {
                    // Delegate the actual reading to the data reader.
                    dataEntryReader.readFile(file, directory);
                }
                catch (IOException ex)
                {
                }
            }
        }
    }
}
