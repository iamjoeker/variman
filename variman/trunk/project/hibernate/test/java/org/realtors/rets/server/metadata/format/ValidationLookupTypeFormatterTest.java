/*
 */
package org.realtors.rets.server.metadata.format;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.realtors.rets.server.metadata.ValidationLookupType;

public class ValidationLookupTypeFormatterTest extends FormatterTestCase
{
    protected void setUp()
    {
        ValidationLookupType validationLookupType = new ValidationLookupType();
        validationLookupType.setValidText("135");
        validationLookupType.setParent1Value("AREA2");
        validationLookupType.setParent2Value(null);
        mValidationLookupTypes =
            new ValidationLookupType[] {validationLookupType};
    }

    private ValidationLookupTypeFormatter getFormatter(int format)
    {
        ValidationLookupTypeFormatter formatter =
            ValidationLookupTypeFormatter.getInstance(format);
        formatter.setVersion("1.00.001", getDate());
        formatter.setValidationLookupName("School");
        formatter.setResourceName("Property");
        return formatter;
    }
    
    public void testCompactFormatLookupType()
    {
        ValidationLookupTypeFormatter formatter =
            getFormatter(MetadataFormatter.COMPACT);
        StringWriter formatted = new StringWriter();
        formatter.format(new PrintWriter(formatted), mValidationLookupTypes);
        assertEquals(
            "<METADATA-VALIDATION_LOOKUP_TYPE Resource=\"Property\" " +
            "ValidationLookup=\"School\" Version=\"" + VERSION + "\" " +
            "Date=\"" + DATE + "\">\n" +

            "<COLUMNS>\tValidText\tParent1Value\tParent2Value\t</COLUMNS>\n" +

            "<DATA>\t135\tAREA2\t\t</DATA>\n" +

            "</METADATA-VALIDATION_LOOKUP_TYPE>\n",
            formatted.toString());
    }

    protected ValidationLookupType[] mValidationLookupTypes;
}
