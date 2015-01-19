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

import org.ddogleg.combinatorics.Combinations;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example that demonstrates how to compute all the combinations and permutations of a set.
 * 
 * @author Peter Abeles
 */
public class ExampleCombinatorics {
	
	static Logger lg=Logger.getLogger(ExampleCombinatorics.class.getName());

	public static void init() {
		List<String> words = new ArrayList<String>();
		words.add("cat");
		words.add("dog");
		words.add("bird");
		words.add("moose");

		Combinations<String> alg = new Combinations<String>(words,2);
		
		long N = alg.computeTotalCombinations();
		lg.log(Level.INFO,"Total number of combinations = "+N);

		List<String> bucket = new ArrayList<String>();
		int i = 0;
		do {
			alg.getBucket(bucket);
			//lg.log(Level.INFO,"i = "+i+" || ");
			String row="i = "+i+" || ";
			for( String s : bucket ) {
				//lg.log(Level.INFO,s+" ");
				row+=s+" ";
			}
			lg.log(Level.INFO,row);
			i++;
		} while( alg.next() );

		lg.log(Level.INFO,"Reverse");
		i = 0;
		do {
			alg.getBucket(bucket);
			
//			lg.log(Level.INFO,"i = "+i+" || ");
			String row="i = "+i+" || ";
			for( String s : bucket ) {
				row+=s+" ";//lg.log(Level.INFO,s+" ");
			}
			lg.log(Level.INFO,row);
			i++;
		} while( alg.previous() );
	}
}
