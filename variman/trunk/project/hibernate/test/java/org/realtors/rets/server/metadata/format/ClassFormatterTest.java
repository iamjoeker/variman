/*
 */
package org.realtors.rets.server.metadata.format;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.realtors.rets.server.metadata.ClassStandardNameEnum;
import org.realtors.rets.server.metadata.MClass;

public class ClassFormatterTest extends FormatterTestCase
{
    protected void setUp()
    {
        MClass clazz = new MClass();
        clazz.setClassName("RES");
        clazz.setStandardName(ClassStandardNameEnum.RESIDENTIAL);
        clazz.setVisibleName("Single Family");
        clazz.setDescription("Single Family Residential");
        mClasses = new MClass[] {clazz};
    }

    private ClassFormatter getFormatter(int format)
    {
        ClassFormatter formatter =
            ClassFormatter.getInstance(format);
        formatter.setVersion("1.00.001", getDate());
        formatter.setResourceName("Property");
        return formatter;
    }

    public void testCompactFormatClass()
    {
        ClassFormatter formatter = getFormatter(MetadataFormatter.COMPACT);
        StringWriter formatted = new StringWriter();
        formatter.format(new PrintWriter(formatted), mClasses);
        assertEquals(
            "<METADATA-CLASS Resource=\"Property\" " +
            "Version=\"" + VERSION + "\" " +
            "Date=\"" + DATE + "\">\n" +

            "<COLUMNS>\tClassName\tStandardName\tVisibleName\tDBName\t" +
            "Description\tTableVersion\tTableDate\tUpdateVersion\t" +
            "UpdateDate\t</COLUMNS>\n" +

            "<DATA>\tRES\tResidentialProperty\tSingle Family\t\t" +
            "Single Family Residential" + VERSION_DATE + VERSION_DATE +
            "\t</DATA>\n" +

            "</METADATA-CLASS>\n",
            formatted.toString()
        );
    }

    public void testEmptyCompactyFormatClass()
    {
        ClassFormatter formatter = getFormatter(MetadataFormatter.COMPACT);
        StringWriter formatted = new StringWriter();
        formatter.format(new PrintWriter(formatted), new MClass[0]);
        assertEquals("", formatted.toString());
    }

    private MClass[] mClasses;
}
