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

import java.util.logging.Level;
import java.util.logging.Logger;

import org.ddogleg.solver.Polynomial;
import org.ddogleg.solver.PolynomialOps;

/**
 * Demonstration of basic arithmetic using polynomials
 *
 * @author Peter Abeles
 */
public class ExamplePolynomialArithmetic {
	static Logger lg=Logger.getLogger(ExamplePolynomialArithmetic.class.getName());

	public static void init() {
		Polynomial a = Polynomial.wrap(5,-2,3,0.5,30.4);
		Polynomial b = Polynomial.wrap(-0.4,8.4,-2.3);

		lg.log(Level.INFO, "a = "+a);
		lg.log(Level.INFO, "b = "+b);
		lg.log(Level.INFO, "a + b = "+PolynomialOps.add(a,b,null));
		lg.log(Level.INFO, "a * b = "+PolynomialOps.multiply(a, b, null));

		// Declare storage for the quotient and the remainder
		Polynomial q = new Polynomial(10);
		Polynomial r = new Polynomial(10);
		PolynomialOps.divide(a, b, q,r);
		
		lg.log(Level.INFO, "a / b = ( "+q+" , "+r+" )");
		lg.log(Level.INFO, "Derivative a = "+PolynomialOps.derivative(a,null));
	}
}
