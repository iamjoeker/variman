package org.realtors.rets.server.activation;

/**
 * The ActivationManager is the main interface to the Activation system.  From here you can check if Variman
 * has been activated, as well as enter your activation information.
 */
public interface ActivationManager {

  /**
   * Checks if Variman has been activated.
   *
   * @param host
   * @return True if activated has been successfully completed.
   */
  boolean isActivated(String host);
}
