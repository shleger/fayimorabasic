package jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * $Revision: $
 * $Author:  $
 * $Date:  $
 */
public class JmxEx {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException,
            InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("my.example:type=Hello1");
        Hello1 mBean = new Hello1();
        mbs.registerMBean(mBean, name);


        System.out.println("Waiting forever for hello1...");
        Thread.sleep(Long.MAX_VALUE);
    }
}


