package org.realtors.rets.common.metadata.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.realtors.rets.client.RetsVersion;
import org.realtors.rets.common.metadata.AttrType;
import org.realtors.rets.common.metadata.MetaObject;
import org.realtors.rets.common.metadata.MetadataElement;
import org.realtors.rets.common.metadata.MetadataType;

public class MEditMask extends MetaObject {
  public static final String METADATAENTRYID = "MetadataEntryID";
  public static final String EDITMASKID = "EditMaskID";
  public static final String VALUE = "Value";
  private static final String METADATATYPENAME = "EditMask";
  private static final List<MetadataElement> sAttributes =
    new ArrayList<MetadataElement>() {{
      add(new MetadataElement(METADATAENTRYID, sRETSID, RetsVersion.RETS_1_7, sREQUIRED));
      add(new MetadataElement(EDITMASKID, sRETSNAME, sREQUIRED));
      add(new MetadataElement(VALUE, sText256, sREQUIRED));
    }};

  public MEditMask() {
    this(DEFAULT_PARSING);
  }

  public MEditMask(boolean strictParsing) {
    super(strictParsing);
  }

  /**
   * Add an attribute to the class static attributes.
   *
   * @param name     Attribute Name
   * @param type     Attribute Type
   * @param required TRUE, the attribute is required. FALSE otherwise.
   */
  public static void addAttribute(String name, AttrType<?> type, boolean required) {
    MetadataElement element = new MetadataElement(name, type, required);
    sAttributes.add(element);
  }

  /**
   * Update (or add) the attribute. This is intended for use where the
   * metadata model is being changed or expanded.
   *
   * @param name     Attribute Name
   * @param type     Attribute Type
   * @param required TRUE, the attribute is required. FALSE otherwise.
   */
  public static void updateAttribute(String name, AttrType<?> type, boolean required) {
    boolean found = false;
    if (sAttributes == null)
      return;

    clearAttributeMapCache();
    MetadataElement element = new MetadataElement(name, type, required);

    for (int i = 0; i < sAttributes.size(); i++) {
      if (sAttributes.get(i).getName().equals(name)) {
        found = true;
        sAttributes.set(i, element);
        break;
      }
    }
    if (!found) {
      sAttributes.add(element);
    }
  }

  /*
   * Add the attributes to the map. This must be done here to
   * make sure static initialization properly takes place.
   */
  @Override
  protected void addAttributesToMap(Map<String, AttrType<?>> attributeMap) {
    for (MetadataElement element : sAttributes) {
      attributeMap.put(element.getName(), element.getType());
    }
  }

  /**
   * Returns whether or not the attribute is required.
   *
   * @param name Name of the attribute.
   * @return TRUE if the attribute is required, FALSE otherwise.
   */
  @Override
  public boolean isAttributeRequired(String name) {
    for (MetadataElement element : MEditMask.sAttributes) {
      if (element.getName().equals(name))
        return element.isRequired();
    }

    return false;
  }

  public String getMetadataEntryID() {
    return getStringAttribute(METADATAENTRYID);
  }

  public void setMetadataEntryID(String metadataEntryId) {
    String metadataEntryIdStr = sRETSID.render(metadataEntryId);
    setAttribute(METADATAENTRYID, metadataEntryIdStr);
  }

  public String getEditMaskID() {
    return getStringAttribute(EDITMASKID);
  }

  public void setEditMaskID(String editMaskId) {
    String editMaskIdStr = sRETSNAME.render(editMaskId);
    setAttribute(EDITMASKID, editMaskIdStr);
  }

  public String getValue() {
    return getStringAttribute(VALUE);
  }

  public void setValue(String value) {
    String valueStr = sText256.render(value);
    setAttribute(VALUE, valueStr);
  }

  public MResource getMResource() {
    MResource resource = (MResource) getParent();
    return resource;
  }

  public void setMResource(MResource resource) {
    setParent(resource);
  }

  @Override
  public MetadataType[] getChildTypes() {
    return sNO_CHILDREN;
  }

  @Override
  protected String getIdAttr() {
    return EDITMASKID;
  }

  @Override
  public final String getMetadataTypeName() {
    return METADATATYPENAME;
  }

  @Override
  public final MetadataType getMetadataType() {
    return MetadataType.EDITMASK;
  }
}
