/*
 */
package org.realtors.rets.server.metadata.format;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.realtors.rets.server.metadata.EditMask;

public class EditMaskFormatterTest extends FormatterTestCase
{
    protected void setUp()
    {
        EditMask editMask = new EditMask();
        editMask.setEditMaskID("LN_EDITMASK");
        editMask.setValue("[0-9]{4,8}");
        mEditMasks = new EditMask[] {editMask};
    }

    private EditMaskFormatter getFormatter(int format)
    {
        EditMaskFormatter formatter = EditMaskFormatter.getInstance(format);
        formatter.setVersion("1.00.001", getDate());
        formatter.setResourceName("Property");
        return formatter;
    }

    public void testCompactFormatEditMask()
    {
        EditMaskFormatter formatter = getFormatter(MetadataFormatter.COMPACT);
        StringWriter formatted = new StringWriter();
        formatter.format(new PrintWriter(formatted), mEditMasks);
        assertEquals(
            "<METADATA-EDITMASK Resource=\"Property\" Version=\"" + VERSION +
            "\" Date=\"" + DATE + "\">\n" +

            "<COLUMNS>\tEditMaskID\tValue\t</COLUMNS>\n" +

            "<DATA>\tLN_EDITMASK\t[0-9]{4,8}\t</DATA>\n" +

            "</METADATA-EDITMASK>\n",

            formatted.toString());
    }

    private EditMask[] mEditMasks;
}