/*
 */
package org.realtors.rets.server.metadata.format;

import java.util.Date;

import org.realtors.rets.server.metadata.MClass;

public abstract class ClassFormatter extends MetadataFormatter
{
    public static ClassFormatter getInstance()
    {
        return getInstance(getDefaultFormat());
    }

    public static ClassFormatter getInstance(int format)
    {
        if (format == COMPACT)
        {
            return new CompactClassFormatter();
        }
        else
        {
            throw new IllegalArgumentException("Unknown format: " + format);
        }
    }

    public void setResource(String resource)
    {
        mResource = resource;
    }

    public abstract String format(MClass[] classes);

    protected String mResource;
}