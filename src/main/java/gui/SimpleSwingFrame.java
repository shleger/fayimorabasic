package gui;

import sun.awt.HorizBagLayout;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * $Revision: $
 * $Author:  $
 * $Date:  $
 */
public class SimpleSwingFrame {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        JPanel mainframe=new JPanel();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 200, 200);
        JButton jb=new JButton();
        jb.setText("Leech");
        mainframe.add(jb);
        JTextField link=new JTextField(50);
        mainframe.add(link);
        window.getContentPane().add(mainframe);
        window.pack();
        window.setVisible(true);


//        org.apache.logging.log4j.jmx.gui.ClientGUI
//        String serviceUrl = args[0];
//                if (!serviceUrl.startsWith("service:jmx")) {
//                        serviceUrl = "service:jmx:rmi:///jndi/rmi://" + args[0] + "/jmxrmi";
//                    }
//               final JMXServiceURL url = new JMXServiceURL(serviceUrl);
//                final Map<String, String> paramMap = new HashMap<String, String>();
//                for (final Object objKey : System.getProperties().keySet()) {
//                    final String key = (String) objKey;
//                    paramMap.put(key, System.getProperties().getProperty(key));
//                }
//        final JMXConnector connector = JMXConnectorFactory.connect(url, paramMap);
//        final Client client = new Client(connector);
//        final String title = "Log4j JMX Client - " + url;
    }
}
