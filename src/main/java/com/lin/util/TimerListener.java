
package com.lin.util;

import java.io.PrintStream;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;

public class TimerListener
	implements EventListener
{

	private static final long serialVersionUID = 0x149f1bc45115d579L;

	public TimerListener()
	{
	}

	public void notify(EventListenerExecution arg0)
		throws Exception
	{
		System.out.println("Ê±¼äµ½ÁË£¬-------------------------------------------");
	}
}
