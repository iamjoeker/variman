package org.realtors.rets.common.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.realtors.rets.client.RetsVersion;
import org.realtors.rets.common.metadata.attrib.AttrAlphanum;
import org.realtors.rets.common.metadata.attrib.AttrBoolean;
import org.realtors.rets.common.metadata.attrib.AttrDate;
import org.realtors.rets.common.metadata.attrib.AttrEnum;
import org.realtors.rets.common.metadata.attrib.AttrGenericText;
import org.realtors.rets.common.metadata.attrib.AttrIDAlphanum;
import org.realtors.rets.common.metadata.attrib.AttrNumeric;
import org.realtors.rets.common.metadata.attrib.AttrPlaintext;
import org.realtors.rets.common.metadata.attrib.AttrPositiveNumeric;
import org.realtors.rets.common.metadata.attrib.AttrText;
import org.realtors.rets.common.metadata.attrib.AttrTimeZone;
import org.realtors.rets.common.metadata.attrib.AttrVersion;
import org.realtors.rets.common.util.CaseInsensitiveTreeMap;

public abstract class MetaObject implements Serializable {
	private static final Log LOG = LogFactory.getLog(MetaObject.class);

	/** a standard parser used by different child types */
	protected static final AttrType sAlphanum = new AttrAlphanum(0, 0, "Alpha Numeric.");
	protected static final AttrType sAlphanum64 = new AttrAlphanum(1, 64, "Alpha Numeric, 1 to 64 characters.");
	protected static final AttrType sAlphanum32 = new AttrAlphanum(1, 32, "Alpha Numeric, 1 to 32 characters.");
	protected static final AttrType sAlphanum24 = new AttrAlphanum(1, 24, "Alpha Numeric, 1 to 24 characters.");
	protected static final AttrType sAlphanum10 = new AttrAlphanum(1, 10, "Alpha Numeric, 1 to 10 characters.");
	protected static final AttrType sPlaintext = new AttrPlaintext(0, 0, "Any printable ASCII character.");
	protected static final AttrType sPlaintext1024 = new AttrPlaintext(1, 1024, "Any printable ASCII character, 1 to 1024 characters.");
	protected static final AttrType sPlaintext512 = new AttrPlaintext(1, 512, "Any printable ASCII character, 1 to 512 characters.");
	protected static final AttrType sPlaintext128 = new AttrPlaintext(1, 128, "Any printable ASCII character, 1 to 128 characters.");
	protected static final AttrType sPlaintext64 = new AttrPlaintext(1, 64, "Any printable ASCII character, 1 to 64 characters.");
	protected static final AttrType sPlaintext32 = new AttrPlaintext(1, 32, "Any printable ASCII character, 1 to 32 characters.");
	protected static final AttrType sText = new AttrText(0, 0, "Any printable ASCII including CRLF, SP and HT.");
	protected static final AttrType sText1024 = new AttrText(1, 1024, "Any printable ASCII including CRLF, SP and HT - 1 to 1024 characters.");
	protected static final AttrType sText512 = new AttrText(1, 512, "Any printable ASCII including CRLF, SP and HT - 1 to 512 characters.");
	protected static final AttrType sText256 = new AttrText(1, 256, "Any printable ASCII including CRLF, SP and HT - 1 to 256 characters.");
	protected static final AttrType sText128 = new AttrText(1, 128, "Any printable ASCII including CRLF, SP and HT - 1 to 128 characters.");
	protected static final AttrType sText64 = new AttrText(1, 64, "Any printable ASCII including CRLF, SP and HT - 1 to 64 characters.");
	protected static final AttrType sText32 = new AttrText(1, 32, "Any printable ASCII including CRLF, SP and HT - 1 to 32 characters.");
	protected static final AttrType sAttrBoolean = new AttrBoolean("0 for FALSE, 1 for TRUE.");
	protected static final AttrType sAttrDate = new AttrDate("A Date/Timestamp in yyyy-mm-ddThh:mm:ssZ format.");
	protected static final AttrType sAttrNumeric = new AttrNumeric("A positive or negative whole number.");
	protected static final AttrType sAttrVersion = new AttrVersion("A RETS version in MM.mm.rrrrr format.");
	// RETS 1.7.2
	public static final AttrType sPOSITIVENUM = new AttrPositiveNumeric("A positive whole number.");
	public static final AttrType sRETSNAME = new AttrIDAlphanum(1,64, "Alpha Numeric including an underscore.");
	public static final AttrType sRETSID = new AttrAlphanum(1,32, "Alph Numeric, 1 to 32 characters.");
	public static final AttrType sTIMEZONEOFFSET = new AttrTimeZone("Z for UTC, [+-]00:00 for offset to UTC.");
	
	public  static final AttrType sAttrMetadataEntryId = sRETSID;
	// Used by MUpdateType.
	protected static final AttrType sAttributes1to5 = new AttrGenericText(0, 10, "12345,", "1,2,3,4,5 singly, or in combination.");
	// Used by MValidationExpression
	protected static final String[] VALIDATIONEXPRESSIONTYPES = "ACCEPT,REJECT,SET".split(",");
	protected static final AttrType sExpressionType = new AttrEnum(VALIDATIONEXPRESSIONTYPES, "'ACCEPT' or 'REJECT' or 'SET'.");
	
    protected static final Map<String, AttrType> sNameToAttributeMap =
    	new HashMap<String,AttrType>()
    {{
    	put("sAlphanum", sAlphanum);
    	put("sAlphanum64", sAlphanum64);
    	put("sAlphanum32", sAlphanum32);
    	put("sAlphanum24", sAlphanum24);
    	put("sAlphanum10", sAlphanum10);
    	put("sPlaintext", sPlaintext);
    	put("sPlaintext1024", sPlaintext1024);
    	put("sPlaintext512", sPlaintext512);
    	put("sPlaintext128", sPlaintext128);
    	put("sPlaintext64", sPlaintext64);
    	put("sPlaintext32", sPlaintext32);
    	put("sText", sText);
    	put("sText1024", sText1024);
    	put("sText512", sText512);
    	put("sText256", sText256);
    	put("sText128", sText128);
    	put("sText64", sText64);
    	put("sText32", sText32);
    	put("sAttrBoolean", sAttrBoolean);
    	put("sAttrDate", sAttrDate);
    	put("sAttrNumeric", sAttrNumeric);
    	put("sAttrVersion", sAttrVersion);
    	put("sAttributes1to5", sAttributes1to5);
    	put("sExpressionType", sExpressionType);
    	// RETS 1.7.2
    	put("sPOSITIVENUM", sPOSITIVENUM);
    	put("sRETSNAME", sRETSNAME);
    	put("sRETSID", sRETSID);
    	put("sTIMEZONEOFFSET", sTIMEZONEOFFSET);
    }};
    
    protected static final Map<AttrType<?>, String> sAttributeToNameMap =
    	new HashMap<AttrType<?>,String>()
    {{
    	put(sAlphanum, "sAlphanum");
    	put(sAlphanum64, "sAlphanum64");
    	put(sAlphanum32, "sAlphanum32");
    	put(sAlphanum24, "sAlphanum24");
    	put(sAlphanum10, "sAlphanum10");
    	put(sPlaintext, "sPlaintext");
    	put(sPlaintext1024, "sPlaintext1024");
    	put(sPlaintext512, "sPlaintext512");
    	put(sPlaintext128, "sPlaintext128");
    	put(sPlaintext64, "sPlaintext64");
    	put(sPlaintext32, "sPlaintext32");
    	put(sText, "sText");
    	put(sText1024, "sText1024");
    	put(sText512, "sText512");
    	put(sText256, "sText256");
    	put(sText128, "sText128");
    	put(sText64, "sText64");
    	put(sText32, "sText32");
    	put(sAttrBoolean, "sAttrBoolean");
    	put(sAttrDate, "sAttrDate");
    	put(sAttrNumeric, "sAttrNumeric");
    	put(sAttrVersion, "sAttrVersion");
    	put(sAttributes1to5, "sAttributes1to5");
    	put(sExpressionType, "sExpressionType");
    	// RETS 1.7.2
    	put(sPOSITIVENUM, "sPOSITIVENUM");
    	put(sRETSNAME, "sRETSNAME");
    	put(sRETSID, "sRETSID");
    	put(sTIMEZONEOFFSET, "sTIMEZONEOFFSET");
    }};

	protected static final MetadataType[] sNoChildren = new MetadataType[0];

	public static final boolean STRICT_PARSING = true;
	public static final boolean LOOSE_PARSING = false;
	public static final boolean DEFAULT_PARSING = LOOSE_PARSING;
	
	/*
	 * Used by subordinate classes to indicate that an attribute is required or
	 * optional.
	 */
	protected static final boolean sREQUIRED = true;
	protected static final boolean sOPTIONAL = false;

	/** the metadata path to this object */
	protected String path;
	/** map of child type to map of child id to child object */
	protected Map childTypes;
	/** map of attribute name to attribute object (as parsed by attrtype) */
	protected Map attributes;
	/** map of attribute name to AttrType parser */
	protected Map attrTypes;

	private static Map<CacheKey,Map> sAttributeMapCache = new HashMap<CacheKey,Map>();
	private MetaCollector mCollector;
	private boolean strict;

	public MetaObject(boolean strictParsing) {
		this.strict = strictParsing;
		if (strictParsing) {
			this.attributes = new LinkedHashMap();
		} else {
			this.attributes = new CaseInsensitiveTreeMap();
		}
		this.attrTypes = this.getAttributeMap(strictParsing);
		MetadataType[] types = getChildTypes();
		this.childTypes = new LinkedHashMap();
		for (int i = 0; i < types.length; i++) {
			this.childTypes.put(types[i], null);
		}
	}

	private Map getAttributeMap(boolean strictParsing) {
		synchronized (sAttributeMapCache) {
			Map<CacheKey,Map> map = sAttributeMapCache.get(new CacheKey(this, strictParsing));
			if (map == null) {
				if (strictParsing) {
					map = new LinkedHashMap();
				} else {
					map = new CaseInsensitiveTreeMap();
				}
				addAttributesToMap(map);
				// Let's make sure no one mucks with the map later
				map = Collections.unmodifiableMap(map);
				sAttributeMapCache.put(new CacheKey(this, strictParsing), map);
				if (LOG.isDebugEnabled()) {
					LOG.debug("Adding to attribute cache: " + this.getClass().getName() + ", " + strictParsing);
				}
			}
			return map;
		}
	}

	public static void clearAttributeMapCache() {
		synchronized (sAttributeMapCache) {
			sAttributeMapCache.clear();
		}
	}

	public Collection getChildren(MetadataType type) {
		if (!this.childTypes.containsKey(type)) {
			// throw new IllegalArgumentException?
			return null;
		}
		Object o = this.childTypes.get(type);
		if (o == null) {
			if (!fetchChildren(type)) {
				return Collections.EMPTY_SET;
			}
			o = this.childTypes.get(type);
		}
		if (o instanceof Map) {
			Map m = (Map) o;
			return m.values();
		}
		return (Collection) o;
	}

	private boolean fetchChildren(MetadataType type) {
		this.childTypes.put(type, new HashMap());
		try {
			MetaObject[] children = null;
			if (this.mCollector != null) {
				children = this.mCollector.getMetadata(type, getPath());
			}
			if (children == null) {
				return false;
			}
			for (int i = 0; i < children.length; i++) {
				MetaObject child = children[i];
				addChild(type, child);
			}
		} catch (MetadataException e) {
			LOG.error(toString() + " unable to fetch " + type.name() + " children");
			return false;
		}
		return true;
	}

	public MetaObject getChild(MetadataType type, String id) {
		if (id == null) {
			return null;
		}
		try {
			if (this.childTypes.get(type) == null && this.mCollector != null) {
				if (!fetchChildren(type)) {
					return null;
				}
			}
			Map m = (Map) this.childTypes.get(type);
			if (m == null) {
				return null;
			}
			return (MetaObject) m.get(id);
		} catch (ClassCastException e) {
			return null;
		}
	}
	/**
	 * Obtain the attribute for the given name. Note that this is used to determine
	 * attributes for the subclass identified attributes.
	 * @param key A String containing the name of the attribute.
	 * @return The attribute as an Object.
	 * @see getAttributeFromName()
	 */
	public Object getAttribute(String key) {
		return this.attributes.get(key);
	}

	/**
	 * Returns the known attributes for the subclass.
	 * @return A set of the known attributes.
	 */
	public Set getKnownAttributes() {
		return this.attrTypes.keySet();
	}
	
	/**
	 * Returns the attribute type for the given attribute.
	 * @param name A string containing the attribute name.
	 * @return The attribute type handler.
	 */
	public AttrType<?> getAttributeType(String name)
	{
		return (AttrType<?>)this.attrTypes.get(name);
	}
	
	/**
	 * Returns the names of all of the known attributes for this subclass.
	 * @return A String array containing the attribute names.
	 */
	public String [] getAttributeNames() {
		ArrayList<String> names = new ArrayList<String>();
		Iterator iter = getKnownAttributes().iterator();

		while (iter.hasNext()) {
			String key = (String) iter.next();
			names.add(key);
		}
		String [] stringArray = new String[names.size()];
		stringArray = names.toArray(stringArray);
		
		return stringArray;
	}

	/**
	 * Returns the attribute given the name. This is from the set of all
	 * known attributes.
	 * @param name A String containing the known name of the attribute.
	 * @return The Attribute
	 */
	public static AttrType<?> getAttributeFromName(String name)
    {
    	return sNameToAttributeMap.get(name);
    }
    
	/**
	 * Given an attribute, return it's name. This is from the set of all
	 * known attributes.
	 * @param attribute The attribute
	 * @return A string containing the attribute's name.
	 */
    public static String getNameFromAttribute(AttrType<?> attribute)
    {
    	return sAttributeToNameMap.get(attribute);
    }

	public String getAttributeAsString(String key) {
		Object value = this.attributes.get(key);
		if (value == null) {
			return null;
		}
		if (this.attrTypes.containsKey(key)) {
			AttrType type = (AttrType) this.attrTypes.get(key);
			return type.render(value);
		}
		return value.toString();
	}

	protected Object getTypedAttribute(String key, Class type) {
		AttrType atype = (AttrType) this.attrTypes.get(key);
		if (atype == null) {
			return null;
		}
		if (atype.getType() == type) {
			return this.attributes.get(key);
		} 
		LOG.warn("type mismatch, expected " + type.getName() + " but" + " got " + atype.getType().getName());
		return null;
	}

	public Date getDateAttribute(String key) {
		return (Date) getTypedAttribute(key, Date.class);
	}

	public String getStringAttribute(String key) {
		return (String) getTypedAttribute(key, String.class);
	}

	public int getIntAttribute(String key) {
		Integer i = (Integer) getTypedAttribute(key, Integer.class);
		if (i == null) {
			return 0;
		}
		return i.intValue();
	}

	public boolean getBooleanAttribute(String key) {
		Boolean b = (Boolean) getTypedAttribute(key, Boolean.class);
		if (b == null) {
			return false;
		}
		return b.booleanValue();
	}

	/**
	 * Parse and set the attribute value.
	 * @param key A string containing the name of the attribute.
	 * @param value A String containing the value of the attribute to be parsed.
	 */
	public void setAttribute(String key, String value) 
	{
		if (value == null) {
			// LOG.warning()
			return;
		}
		if (this.attrTypes.containsKey(key)) {
			AttrType type = (AttrType) this.attrTypes.get(key);
			try {
				this.attributes.put(key, type.parse(value,this.strict));
			} catch (MetaParseException e) {
				LOG.warn(toString() + " couldn't parse attribute " + key + ", value " + value + ": " + e.getMessage());
			}
		} else {
			this.attributes.put(key, value);
			if (!key.startsWith("X-"))
				LOG.warn("Unknown key (" + toString() + "): " + key);
		}
	}
	/**
	 * Set the attribute and override the strict parsing setting.
	 * @param key A String containing the name of the attribute.
	 * @param value A String containing the value of the attribute to be parsed.
	 * @param strictParsing A Boolean indicating whether or not to use strict parsing.
	 */
	public void setAttribute(String key, String value, boolean strictParsing) 
		throws MetaParseException
	{
		if (value == null) 
		{
			// LOG.warning()
			return;
		}
		/*
		 * See if we know about this attribute already. If so, parse it using the corresponding
		 * type parser.
		 */
		if (this.attrTypes.containsKey(key)) 
		{
			AttrType type = (AttrType) this.attrTypes.get(key);
			try 
			{
				this.attributes.put(key, type.parse(value,strictParsing));
			} catch (MetaParseException e) 
			{
				LOG.warn(toString() + " couldn't parse attribute " + key + ", value " + value + ": " + e.getMessage());
				if (strictParsing)
					throw e;
			}
		} 
		else 
		{
			this.attributes.put(key, value);
			if (!key.startsWith("X-"))
				LOG.warn("Unknown key (" + toString() + "): " + key);
		}
	}

	public void addChild(MetadataType type, MetaObject child) {
		if (this.childTypes.containsKey(type)) {
			Object obj = this.childTypes.get(type);
			Map map;
			if (obj == null) {
				map = new HashMap();
				this.childTypes.put(type, map);
			} else {
				map = (Map) obj;
			}
			if (child == null) {
				return;
			}
			String id = child.getId();

			child.setPath(this.getPath());
			child.setCollector(this.mCollector);
			if (id != null) {
				map.put(id, child);
			}
			return;
		}
	}
	public void deleteChild(MetadataType type, MetaObject child) 
	{
		if (this.childTypes.containsKey(type)) 
		{
			Object obj = this.childTypes.get(type);
			
			if (obj == null || child == null) 
				return;
			
			Map map = (Map) obj;
			
			String id = child.getId();

			if (id != null) 
			{
				map.remove(id);
			}
			return;
		}
	}

	public String getId() {
		String idAttr = getIdAttr();
		if (idAttr == null) {
			return getMetadataTypeName();
		}
		return getAttributeAsString(idAttr);
	}

	public String getPath() {
		return this.path;
	}

	protected void setPath(String parent) {
		if (parent == null || parent.equals("")) {
			this.path = getId();
		} else {
			this.path = parent + ":" + getId();
		}
	}

	@Override
	public String toString() {
		ToStringBuilder tsb = new ToStringBuilder(this);
		Iterator iter = getKnownAttributes().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			tsb.append(key, getAttributeAsString(key));
		}
		return tsb.toString();
	}

	public void setCollector(MetaCollector c) {
		this.mCollector = c;
		Iterator iterator = this.childTypes.keySet().iterator();
		while (iterator.hasNext()) {
			MetadataType type = (MetadataType) iterator.next();
			Map map = (Map) this.childTypes.get(type);
			if (map == null) {
				continue;
			}
			Collection children = map.values();
			for (Iterator iter = children.iterator(); iter.hasNext();) {
				MetaObject object = (MetaObject) iter.next();
				object.setCollector(c);
			}
		}
	}
	
	/**
	 * Replace the attribute type for the given attribute.
	 * @param name A string containing the attribute name.
	 * @param attrType An attribute type.
	 * @return The prior attribute type.
	 */
	public AttrType<?> replaceAttributeType(String name, AttrType<?> attrType)
	{
		AttrType<?> oldType = (AttrType<?>)this.attrTypes.get(name);
		this.attrTypes.put(name, attrType);
		return oldType;
	}

	public abstract MetadataType[] getChildTypes();

	protected abstract String getIdAttr();

	/**
	 * Adds attributes to an attribute map.  This is called by the MetaObject
	 * constructor to initialize a map of attributes.  This map may be cached,
	 * so this method may not be called for every object construction.
	 *
	 * @param attributeMap Map to add attributes to
	 */
	protected abstract void addAttributesToMap(Map attributeMap);
	
	public abstract String getMetadataTypeName();
	
	public abstract MetadataType getMetadataType();
	
}

class CacheKey {
	private Class mClass;
	private boolean strictParsing;

	public CacheKey(MetaObject metaObject, boolean strictParsing) {
		this.mClass = metaObject.getClass();
		this.strictParsing = strictParsing;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CacheKey)) {
			return false;
		}
		CacheKey rhs = (CacheKey) obj;
		return new EqualsBuilder().append(this.mClass, rhs.mClass).append(this.strictParsing, rhs.strictParsing).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.mClass).append(this.strictParsing).toHashCode();
	}

}

