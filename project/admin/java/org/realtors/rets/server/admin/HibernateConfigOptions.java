/*
 * Variman RETS Server
 *
 * Author: Dave Dribin
 * Copyright (c) 2004, The National Association of REALTORS
 * Distributed under a BSD-style license.  See LICENSE.TXT for details.
 */

/*
 * Created on Aug 14, 2003
 *
 */
package org.realtors.rets.server.admin;

/**
 * @author kgarner
 */
public class HibernateConfigOptions {
  private int mC3P0MaxSize;
  private int mC3P0MaxStatements;
  private int mC3P0MinSize;
  private int mC3P0Timeout;
  private String mConnectionDriverClass;
  private String mConnectionPassword;
  private String mConnectionURL;
  private String mConnectionUsername;
  private String mDebug;
  private String mDialect;
  private int mStatementCacheSize;

  /**
   * @return
   */
  public int getC3P0MaxSize() {
    return mC3P0MaxSize;
  }

  /**
   * @param i
   */
  public void setC3P0MaxSize(int i) {
    mC3P0MaxSize = i;
  }

  /**
   * @return
   */
  public int getC3P0MaxStatements() {
    return mC3P0MaxStatements;
  }

  /**
   * @param i
   */
  public void setC3P0MaxStatements(int i) {
    mC3P0MaxStatements = i;
  }

  /**
   * @return
   */
  public int getC3P0MinSize() {
    return mC3P0MinSize;
  }

  /**
   * @param i
   */
  public void setC3P0MinSize(int i) {
    mC3P0MinSize = i;
  }

  /**
   * @return
   */
  public int getC3P0Timeout() {
    return mC3P0Timeout;
  }

  /**
   * @param i
   */
  public void setC3P0Timeout(int i) {
    mC3P0Timeout = i;
  }

  /**
   * @return
   */
  public String getConnectionDriverClass() {
    return mConnectionDriverClass;
  }

  /**
   * @param string
   */
  public void setConnectionDriverClass(String string) {
    mConnectionDriverClass = string;
  }

  /**
   * @return
   */
  public String getConnectionPassword() {
    return mConnectionPassword;
  }

  /**
   * @param string
   */
  public void setConnectionPassword(String string) {
    mConnectionPassword = string;
  }

  /**
   * @return
   */
  public String getConnectionURL() {
    return mConnectionURL;
  }

  /**
   * @param string
   */
  public void setConnectionURL(String string) {
    mConnectionURL = string;
  }

  /**
   * @return
   */
  public String getConnectionUsername() {
    return mConnectionUsername;
  }

  /**
   * @param string
   */
  public void setConnectionUsername(String string) {
    mConnectionUsername = string;
  }

  /**
   * @return
   */
  public String getDebug() {
    return mDebug;
  }

  /**
   * @param string
   */
  public void setDebug(String string) {
    mDebug = string;
  }

  /**
   * @return
   */
  public String getDialect() {
    return mDialect;
  }

  /**
   * @param string
   */
  public void setDialect(String string) {
    mDialect = string;
  }

  /**
   * @return
   */
  public int getStatementCacheSize() {
    return mStatementCacheSize;
  }

  /**
   * @param i
   */
  public void setStatementCacheSize(int i) {
    mStatementCacheSize = i;
  }
}
