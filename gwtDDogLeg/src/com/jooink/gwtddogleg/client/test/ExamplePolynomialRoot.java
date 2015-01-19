/*
 * Copyright (c) 2012-2013, Peter Abeles. All Rights Reserved.
 *
 * This file is part of DDogleg (http://ddogleg.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jooink.gwtddogleg.client.test;

import org.ddogleg.solver.Polynomial;
import org.ddogleg.solver.PolynomialOps;
import org.ddogleg.solver.PolynomialRoots;
import org.ddogleg.solver.RootFinderType;
import org.ejml.data.Complex64F;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple example that demonstrates how the roots of a polynomial can be found.
 *
 * @author Peter Abeles
 */
public class ExamplePolynomialRoot {
	static Logger lg=Logger.getLogger(ExamplePolynomialRoot.class.getName());

	public static void init() {
		// Select which algorithm to use
		PolynomialRoots finder = PolynomialOps.createRootFinder(6, RootFinderType.EVD);

		// Create an arbitrary 3rd order polynomial
		Polynomial poly = Polynomial.wrap( 2 , 0.2 , 5 , 3 );

		// Find the roots
		if( !finder.process(poly) )
			throw new RuntimeException("Failed to find solution!");

		// Print the solution
		List<Complex64F> roots = finder.getRoots();

		lg.log(Level.INFO, "Total roots found: "+roots.size());

		for( Complex64F c : roots ) {
			if( !c.isReal() ) {
				lg.log(Level.INFO, "root is imaginary: "+c);
				continue;
			}

			double value = poly.evaluate(c.real);
			lg.log(Level.INFO, "Polynomial value at "+c.real+" is "+value);
		}
	}
}
