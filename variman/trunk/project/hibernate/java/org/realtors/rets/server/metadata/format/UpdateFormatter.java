/*
 */
package org.realtors.rets.server.metadata.format;

import java.io.PrintWriter;

import org.realtors.rets.server.metadata.Update;

public abstract class UpdateFormatter extends MetadataFormatter
{
    public static UpdateFormatter getInstance()
    {
        return getInstance(getDefaultFormat());
    }

    public static UpdateFormatter getInstance(int format)
    {
        if (format == COMPACT)
        {
            return new CompactUpdateFormatter();
        }
        else
        {
            throw new IllegalArgumentException("Unknown format: " + format);
        }
    }

    public abstract void format(PrintWriter out, Update[] updates);

    public void setClassName(String className)
    {
        mClassName = className;
    }

    public void setResourceName(String resourceName)
    {
        mResourceName = resourceName;
    }

    protected String mClassName;
    protected String mResourceName;
}
