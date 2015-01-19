package com.jooink.gwtddogleg.client.alg;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.RandomAccess;

public class Alg {

	private static final int SHUFFLE_THRESHOLD = 5;


	/**
	1357:    * Shuffle a list according to a default source of randomness. The algorithm
	1358:    * used iterates backwards over the list, swapping each element with an
	1359:    * element randomly selected from the elements in positions less than or
	1360:    * equal to it (using r.nextInt(int)).
	1361:    * <p>
	1362:    *
	1363:    * This algorithm would result in a perfectly fair shuffle (that is, each
	1364:    * element would have an equal chance of ending up in any position) if r were
	1365:    * a perfect source of randomness. In practice the results are merely very
	1366:    * close to perfect.
	1367:    * <p>
	1368:    *
	1369:    * This method operates in linear time. To do this on large lists which do
	1370:    * not implement {@link RandomAccess}, a temporary array is used to acheive
	1371:    * this speed, since it would be quadratic access otherwise.
	1372:    *
	1373:    * @param l the list to shuffle
	1374:    * @throws UnsupportedOperationException if l.listIterator() does not
	1375:    *         support the set operation
	1376:    */
	public static void shuffle(List<?> l)
	{
		if (defaultRandom == null)
		{
			synchronized (Collections.class)
			{
				if (defaultRandom == null)
					defaultRandom = new Random();
			}
		}
		shuffle(l, defaultRandom);
	}
	/**
   1391:    * Cache a single Random object for use by shuffle(List). This improves
   1392:    * performance as well as ensuring that sequential calls to shuffle() will
   1393:    * not result in the same shuffle order occurring: the resolution of
   1394:    * System.currentTimeMillis() is not sufficient to guarantee a unique seed.
   1395:    */
	private static Random defaultRandom = null;	







	/**
	1399:    * Shuffle a list according to a given source of randomness. The algorithm
	1400:    * used iterates backwards over the list, swapping each element with an
	1401:    * element randomly selected from the elements in positions less than or
	1402:    * equal to it (using r.nextInt(int)).
	1403:    * <p>
	1404:    *
	1405:    * This algorithm would result in a perfectly fair shuffle (that is, each
	1406:    * element would have an equal chance of ending up in any position) if r were
	1407:    * a perfect source of randomness. In practise (eg if r = new Random()) the
	1408:    * results are merely very close to perfect.
	1409:    * <p>
	1410:    *
	1411:    * This method operates in linear time. To do this on large lists which do
	1412:    * not implement {@link RandomAccess}, a temporary array is used to acheive
	1413:    * this speed, since it would be quadratic access otherwise.
	1414:    *
	1415:    * @param l the list to shuffle
	1416:    * @param r the source of randomness to use for the shuffle
	1417:    * @throws UnsupportedOperationException if l.listIterator() does not
	1418:    *         support the set operation
	1419:    */
	public static void shuffle(List<?> l, Random r)
	{
		int lsize = l.size();
		List<Object> list = (List<Object>) l;
		ListIterator<Object> i = list.listIterator(lsize);
		boolean sequential = isSequential(l);
		Object[] a = null; // stores a copy of the list for the sequential case

		if (sequential)
			a = list.toArray();

		for (int pos = lsize - 1; pos > 0; --pos)
		{
			// Obtain a random position to swap with. pos + 1 is used so that the
			// range of the random number includes the current position.
			int swap = r.nextInt(pos + 1);

			// Swap the desired element.
			Object o;
			if (sequential)
			{
				o = a[swap];
				a[swap] = i.previous();
			}
			else
				o = list.set(swap, i.previous());

			i.set(o);
		}
	}

	//	public static void shuffle(List<?> list, Random rnd) {
	//		int size = list.size();
	//		if (size < SHUFFLE_THRESHOLD || list instanceof RandomAccess) {
	//			for (int i=size; i>1; i--)
	//				Collections.swap(list, i-1, rnd.nextInt(i));
	//		} else {
	//			Object arr[] = list.toArray();
	//
	//			// Shuffle array
	//			for (int i=size; i>1; i--) {
	//				//--> Collections.swap(arr, i-1, rnd.nextInt(i));
	//				Collections.swap(list, i-1, rnd.nextInt(i));
	//			}
	//
	//			// Dump array back into list
	//			ListIterator it = list.listIterator();
	//			for (int i=0; i<arr.length; i++) {
	//				it.next();
	//				it.set(arr[i]);
	//			}
	//		}
	//	}

	/**
	  78:    * Constant used to decide cutoff for when a non-RandomAccess list should
	  79:    * be treated as sequential-access. Basically, quadratic behavior is
	  80:    * acceptable for small lists when the overhead is so small in the first
	  81:    * place. I arbitrarily set it to 16, so it may need some tuning.
	  82:    */
	private static final int LARGE_LIST_SIZE = 16;

	/**
	  86:    * Determines if a list should be treated as a sequential-access one.
	  87:    * Rather than the old method of JDK 1.3 of assuming only instanceof
	  88:    * AbstractSequentialList should be sequential, this uses the new method
	  89:    * of JDK 1.4 of assuming anything that does NOT implement RandomAccess
	  90:    * and exceeds a large (unspecified) size should be sequential.
	  91:    *
	  92:    * @param l the list to check
	 * @return <code>true</code> if it should be treated as sequential-access
	 */
	private static boolean isSequential(List<?> l)
	{
		return ! (l instanceof RandomAccess) && l.size() > LARGE_LIST_SIZE;
	}
}

