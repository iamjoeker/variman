/*
 */
package org.realtors.rets.server.metadata;

import java.util.Date;
import java.util.List;
import java.util.Arrays;

import org.realtors.rets.server.metadata.ServerMetadata;

public class MetadataSegment
{
    public MetadataSegment(ServerMetadata[] data, String[] levels,
                           String version, Date date)
    {
        mData = data;
        mDataList = Arrays.asList(data);
        mLevels = levels;
        mVersion = version;
        mDate = date;
    }

    public MetadataSegment(List data, String[] levels, String version,
                           Date date)
    {
        mData = (ServerMetadata[])
            data.toArray(new ServerMetadata[data.size()]);
        mDataList = data;
        mLevels = levels;
        mVersion = version;
        mDate = date;
    }

    public ServerMetadata[] getData()
    {
        return mData;
    }

    public List getDataList()
    {
        return mDataList;
    }

    public String[] getLevels()
    {
        return mLevels;
    }

    public String getVersion()
    {
        return mVersion;
    }

    public Date getDate()
    {
        return mDate;
    }

    private ServerMetadata[] mData;
    private List mDataList;
    private String[] mLevels;
    private String mVersion;
    private Date mDate;
}
