/*
 */
package org.realtors.rets.server.webapp.cct;

import org.realtors.rets.server.cct.StatusEnum;
import org.realtors.rets.server.cct.ValidationResult;

/**
 * Subclasses must implement getDescription(), start(), and validate().
 */
public abstract class BaseCertificationTest implements CertificationTest
{
    public BaseCertificationTest()
    {
        mValidationResults = null;
        mMessage = "";
        mStatus = StatusEnum.NOTRUN;
    }

    public String getMessage()
    {
        return mMessage;
    }

    public StatusEnum getStatus()
    {
        return mStatus;
    }

    public void start()
    {
        mMessage = "";
    }

    public void stop()
    {
        mValidationResults = validate();
        if (mValidationResults.wasSuccessful())
        {
            mStatus = StatusEnum.PASSED;
        }
        else
        {
            mStatus = StatusEnum.FAILED;
        }
        mMessage = mValidationResults.getMessage();
    }

    public void cancel()
    {
        mStatus = StatusEnum.FAILED;
    }

    public void init(String testContext)
    {
        mTestContext = testContext;
    }

    protected StatusEnum mStatus;
    private ValidationResult mValidationResults;
    protected String mTestContext;
    private String mMessage;
}
