/*
 */
package org.realtors.rets.server.metadata.format;

import junit.framework.TestCase;
import org.realtors.rets.common.metadata.types.*;

public class ClassFormatterLookupTest extends TestCase {
  public void testCompactFormatters() {
    ClassFormatterLookup lookup =
      new ClassFormatterLookup(MetadataFormatter.COMPACT);
    assertTrue(lookup.lookupFormatter(MSystem.class) instanceof
      CompactSystemFormatter);
    assertTrue(lookup.lookupFormatter(MResource.class) instanceof
      CompactResourceFormatter);
    assertTrue(lookup.lookupFormatter(MClass.class) instanceof
      CompactClassFormatter);
    assertTrue(lookup.lookupFormatter(MTable.class) instanceof
      CompactTableFormatter);
    assertTrue(lookup.lookupFormatter(MUpdate.class) instanceof
      CompactUpdateFormatter);
    assertTrue(lookup.lookupFormatter(MUpdateType.class) instanceof
      CompactUpdateTypeFormatter);
    assertTrue(lookup.lookupFormatter(MObject.class) instanceof
      CompactObjectFormatter);
    assertTrue(lookup.lookupFormatter(MSearchHelp.class) instanceof
      CompactSearchHelpFormatter);
    assertTrue(lookup.lookupFormatter(MEditMask.class) instanceof
      CompactEditMaskFormatter);
    assertTrue(lookup.lookupFormatter(MLookup.class) instanceof
      CompactLookupFormatter);
    assertTrue(lookup.lookupFormatter(MUpdateHelp.class) instanceof
      CompactUpdateHelpFormatter);
    assertTrue(lookup.lookupFormatter(MLookupType.class) instanceof
      CompactLookupTypeFormatter);
    assertTrue(lookup.lookupFormatter(MValidationLookup.class) instanceof
      CompactValidationLookupFormatter);
    assertTrue(lookup.lookupFormatter(MValidationLookupType.class) instanceof
      CompactValidationLookupTypeFormatter);
    assertTrue(lookup.lookupFormatter(MValidationExternal.class) instanceof
      CompactValidationExternalFormatter);
    assertTrue(lookup.lookupFormatter(MValidationExternalType.class)
      instanceof CompactValidationExternalTypeFormatter);
    assertTrue(lookup.lookupFormatter(MValidationExpression.class) instanceof
      CompactValidationExpressionFormatter);
    assertTrue(lookup.lookupFormatter(MForeignKey.class) instanceof
      CompactForeignKeyFormatter);
  }

  public void testStandardFormatters() {
    ClassFormatterLookup lookup =
      new ClassFormatterLookup(MetadataFormatter.STANDARD);
    assertTrue(lookup.lookupFormatter(MSystem.class) instanceof
      StandardSystemFormatter);
    assertTrue(lookup.lookupFormatter(MResource.class) instanceof
      StandardResourceFormatter);
    assertTrue(lookup.lookupFormatter(MClass.class) instanceof
      StandardClassFormatter);
    assertTrue(lookup.lookupFormatter(MTable.class) instanceof
      StandardTableFormatter);
    assertTrue(lookup.lookupFormatter(MUpdate.class) instanceof
      StandardUpdateFormatter);
    assertTrue(lookup.lookupFormatter(MUpdateType.class) instanceof
      StandardUpdateTypeFormatter);
    assertTrue(lookup.lookupFormatter(MObject.class) instanceof
      StandardObjectFormatter);
    assertTrue(lookup.lookupFormatter(MSearchHelp.class) instanceof
      StandardSearchHelpFormatter);
    assertTrue(lookup.lookupFormatter(MEditMask.class) instanceof
      StandardEditMaskFormatter);
    assertTrue(lookup.lookupFormatter(MLookup.class) instanceof
      StandardLookupFormatter);
    assertTrue(lookup.lookupFormatter(MUpdateHelp.class) instanceof
      StandardUpdateHelpFormatter);
    assertTrue(lookup.lookupFormatter(MLookupType.class) instanceof
      StandardLookupTypeFormatter);
    assertTrue(lookup.lookupFormatter(MValidationLookup.class) instanceof
      StandardValidationLookupFormatter);
    assertTrue(lookup.lookupFormatter(MValidationLookupType.class) instanceof
      StandardValidationLookupTypeFormatter);
    assertTrue(lookup.lookupFormatter(MValidationExternal.class) instanceof
      StandardValidationExternalFormatter);
    assertTrue(lookup.lookupFormatter(MValidationExternalType.class)
      instanceof StandardValidationExternalTypeFormatter);
    assertTrue(lookup.lookupFormatter(MValidationExpression.class) instanceof
      StandardValidationExpressionFormatter);
    assertTrue(lookup.lookupFormatter(MForeignKey.class) instanceof
      StandardForeignKeyFormatter);
  }
}
