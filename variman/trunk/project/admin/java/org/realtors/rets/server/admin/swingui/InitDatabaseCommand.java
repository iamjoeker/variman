package org.realtors.rets.server.admin.swingui;

import java.awt.*;
import javax.swing.*;

import org.realtors.rets.server.admin.AdminUtils;
import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: dave
 * Date: May 13, 2004
 * Time: 11:09:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class InitDatabaseCommand
{
    public void execute()
    {
        final AdminFrame frame = AdminFrame.getInstance();
        final InitDatabaseDialog dialog = new InitDatabaseDialog(frame);
        frame.setStatusText("Initializing database...");
        SwingWorker worker = new SwingWorker()
        {
            public Object construct()
            {
                try
                {
                    AdminUtils.initDatabase();
                }
                catch  (Throwable e)
                {
                    LOG.error("Caught", e);
                    return e;
                }
                return null;
            }

            public void finished()
            {
                dialog.dispose();
                Object o = get();
                if (o instanceof Throwable)
                {
                    Throwable t = (Throwable) o;
                    frame.setStatusText("Database initialization failed: " +
                                        t.getMessage());
                }
                else
                {
                    frame.setStatusText("Database initialized successfully");
                }
            }
        };
        worker.start();
        dialog.show();
    }

    private class InitDatabaseDialog extends JDialog
    {
        public InitDatabaseDialog(Frame frame)
        {
            super(frame, true);
            setResizable(false);
            Container content = getContentPane();
            JLabel label = new JLabel("Initializing database...");
            label.setBorder(BorderFactory.createEmptyBorder(35, 35, 35, 35));
            content.add(label);
            pack();
            SwingUtils.centerOnFrame(this, frame);
        }
    }

    private static final Logger LOG =
        Logger.getLogger(InitDatabaseCommand.class);
}
