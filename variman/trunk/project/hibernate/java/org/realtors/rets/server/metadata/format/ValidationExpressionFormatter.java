/*
 */
package org.realtors.rets.server.metadata.format;

import java.io.PrintWriter;
import java.util.List;

import org.realtors.rets.server.metadata.ValidationExpression;

public abstract class ValidationExpressionFormatter extends MetadataFormatter
{
    public static ValidationExpressionFormatter getInstance(int format)
    {
        if (format == COMPACT)
        {
            return new CompactValidationExpressionFormatter();
        }
        else
        {
            throw new IllegalArgumentException("Unknown format: " + format);
        }
    }

    public void setResourceName(String resourceName)
    {
        mResourceName = resourceName;
    }

    public abstract void format(PrintWriter out,
                                ValidationExpression[] validationExpressions);

    public void format(PrintWriter out, List validationExpressions)
    {
        format(out, (ValidationExpression[])
            validationExpressions.toArray(
                new ValidationExpression[validationExpressions.size()]));
    }

    protected String mResourceName;
}
