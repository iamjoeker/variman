/*
 */
package org.realtors.rets.server.webapp;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.cfg.Configuration;

import org.realtors.rets.server.PasswordMethod;
import org.realtors.rets.server.SessionHelper;
import org.realtors.rets.server.webapp.auth.NonceTable;
import org.realtors.rets.server.webapp.auth.NonceReaper;
import org.realtors.rets.server.metadata.MSystem;
import org.realtors.rets.server.metadata.MetadataManager;

import org.apache.log4j.Logger;

/**
 * @web.servlet name="init-servlet"
 *   load-on-startup="1"
 * @web.servlet-init-param name="log4j-init-file"
 *   value="WEB-INF/classes/log4j.lcf"
 */
public class InitServlet extends RetsServlet
{
    public void init() throws ServletException
    {
        initLog4J();
        try
        {
            LOG.debug("Running init servlet");
            WebApp.setServletContext(getServletContext());
            PasswordMethod.setDefaultMethod(PasswordMethod.DIGEST_A1,
                                            PasswordMethod.RETS_REALM);
            initRetsConfiguration();
            initHibernate();
            initMetadata();
            initNonceTable();
            LOG.debug("Init servlet completed successfully");
        }
        catch (ServletException e)
        {
            LOG.fatal("Caught", e);
            throw e;
        }
        catch (RuntimeException e)
        {
            LOG.fatal("Caught", e);
            throw e;
        }
    }

    private void initRetsConfiguration()
    {
        ServletContext context = getServletContext();
        String localWebRoot = context.getRealPath("/");
        String getObjectRoot = getInitParameter(context,
                                                "rets-get-object-root",
                                                localWebRoot);
        WebApp.setGetObjectRoot(getObjectRoot);
        LOG.debug("GetObject root: " + getObjectRoot);

        String getObjectPattern = getInitParameter(context,
                                                   "rets-get-object-pattern",
                                                   "pictures/%k-%i.jpg");
        WebApp.setGetObjectPattern(getObjectPattern);
        LOG.debug("GetObject pattern: " + getObjectPattern);
    }

    private String getInitParameter(ServletContext context, String name,
                                    String defaultValue)
    {
        String value = context.getInitParameter(name);
        if (value == null)
        {
            value = defaultValue;
        }
        return value;
    }

    /**
     * Initialize log4j. First, the application's context is checked for the
     * file name, and then the servlet context is checked.
     *
     * @throws ServletException
     */
    private void initLog4J() throws ServletException
    {
        try
        {
            URL prefix = new File(getServletContext().getRealPath("/")).toURL();
            String file =
                getServletContext().getInitParameter("log4j-init-file");
            if (file == null)
            {
                file = getInitParameter("log4j-init-file");
            }

            if (file != null)
            {
                // Resolve file, relative to prefix
                file = new URL(prefix, file).getFile();
                WebApp.setLog4jFile(file);
                WebApp.loadLog4j();
            }
        }
        catch (MalformedURLException e)
        {
            throw new ServletException(e);
        }
    }

    private void initHibernate() throws ServletException
    {
        try
        {
            LOG.debug("Initializing hibernate");
            Configuration cfg = new Configuration();
            cfg.addJar("retsdb2-hbm-xml.jar");
            WebApp.setSessions(cfg.buildSessionFactory());
        }
        catch (HibernateException e)
        {
            throw new ServletException("Could not initialize hibernate", e);
        }
    }

    private void initMetadata() throws ServletException
    {
        LOG.debug("Initializing metadata");
        MSystem system = findSystem();
        LOG.debug("Creating metadata manager");
        MetadataManager manager = new MetadataManager();
        manager.addRecursive(system);
        LOG.debug("Adding metadata to servlet context");
        WebApp.setMetadataManager(manager);
    }

    private MSystem findSystem()
        throws ServletException
    {
        SessionHelper helper = WebApp.createHelper();
        MSystem system = null;
        try
        {
            Session session = helper.beginTransaction();
            List results = new ArrayList();
            Iterator iterator = session.iterate("from MSystem");
            while (iterator.hasNext())
            {
                results.add(iterator.next());
            }
            if (results.size() == 1)
            {
                system = (MSystem) results.get(0);
            }
            helper.commit();
        }
        catch (HibernateException e)
        {
            LOG.warn("Caught", e);
            helper.rollback(LOG);
            throw new ServletException(e);
        }
        finally
        {
            helper.close(LOG);
        }
        return system;
    }

    private void initNonceTable()
    {
        NonceTable nonceTable = new NonceTable();
        WebApp.setNonceTable(nonceTable);
        NonceReaper reaper = new NonceReaper(nonceTable);
        reaper.start();
        WebApp.setNonceReaper(reaper);
    }

    public void destroy()
    {
        NonceReaper reaper = WebApp.getReaper();
        reaper.stop();
    }

    private static final Logger LOG =
        Logger.getLogger(InitServlet.class);
}
