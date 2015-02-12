package org.ikya.test;

import java.util.Date;

import org.ocpsoft.prettytime.PrettyTime;

public class DateTest {

	public static void main(String[] args) {
		PrettyTime p = new PrettyTime();
		System.out.println(p.format(new Date()));
		// prints: “moments from now”

		System.out.println(p.format(new Date(
				System.currentTimeMillis() + 1000 * 60 * 10)));
		// prints: “10 minutes from now”
	}

}
