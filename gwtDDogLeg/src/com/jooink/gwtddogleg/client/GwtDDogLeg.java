package com.jooink.gwtddogleg.client;

import java.util.logging.Level;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.jooink.gwtddogleg.client.test.ExampleCombinatorics;
import com.jooink.gwtddogleg.client.test.ExampleMinimization;
import com.jooink.gwtddogleg.client.test.ExampleNearestNeighbor;
import com.jooink.gwtddogleg.client.test.ExamplePolynomialArithmetic;
import com.jooink.gwtddogleg.client.test.ExamplePolynomialRoot;
import com.jooink.gwtddogleg.client.test.ExampleRobustModelFit;
import com.jooink.gwtejml.client.logPanel.LogPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtDDogLeg implements EntryPoint {

	@Override
	public void onModuleLoad() {
		final ListBox lb=new ListBox();
		lb.addItem("Select an operation");
		lb.addItem("Example Combinatorics");
		lb.addItem("Example Minimization");
		lb.addItem("Example Nearest Neighbor");
		lb.addItem("Example Polynomial Arithmetic");
		lb.addItem("Example Polynomial Root");
		lb.addItem("Example Robust Model Fit");
		RootPanel.get().add(lb);

		HTML ht=new HTML("---");
		RootPanel.get().add(ht);
		final LogPanel lp=new LogPanel(Level.ALL,false,false);
		lp.setTitle("Results");
		RootPanel.get().add(lp.getWidget());
		// --		
		lb.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				lp.clear();
				int index=lb.getSelectedIndex();
				
				switch (index) {
				case 1:
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {    
						@Override
						public void execute() {
							ExampleCombinatorics.init();
						}
					});
					break;
				case 2:
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {    
						@Override
						public void execute() {
							ExampleMinimization.init();
						}
					});
					break;
				case 3:
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {    
						@Override
						public void execute() {
							ExampleNearestNeighbor.init();
						}
					});
					break;
				case 4:
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {    
						@Override
						public void execute() {
							ExamplePolynomialArithmetic.init();
						}
					});					
					break;
				case 5:
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {    
						@Override
						public void execute() {
							ExamplePolynomialRoot.init();
						}
					});					
					break;
				case 6:
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {    
						@Override
						public void execute() {
							ExampleRobustModelFit.init();
						}
					});					
					break;
				}
			}
		});
	}
}
