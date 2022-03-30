/*
 * scalajs-fake-insecure-java-securerandom (https://github.com/scala-js/scala-js-fake-insecure-java-securerandom)
 *
 * Copyright EPFL.
 *
 * Licensed under Apache License 2.0
 * (https://www.apache.org/licenses/LICENSE-2.0).
 *
 * See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 */

package org.scalajs.testsuite.javalib.security

import org.junit.Test
import org.junit.Assert._

import java.security.SecureRandom

class SecureRandomTest {
  @Test def nextBytes(): Unit = {
    val random = new SecureRandom()

    val buffer = new Array[Byte](24)
    assertTrue("confidence check", buffer.count(_ == 0) == 24)

    random.nextBytes(buffer)

    // the likelihood of this failing should be one in 4 billion
    assertTrue("nextBytes() must fill the provided buffer", buffer.count(_ == 0) < 4)

    val buffer2 = new Array[Byte](24)
    random.nextBytes(buffer2)
    assertFalse("nextBytes() must return different results", buffer2.sameElements(buffer))
  }

  @Test def nextInt(): Unit = {
    val random = new SecureRandom()

    val i1 = random.nextInt()
    val i2 = random.nextInt()
    assertNotEquals(i2, i1)
  }

  @Test def nextFloat(): Unit = {
    /* `nextFloat()` actually relies on `next(n)` clearing the higher order
     * bits for the resulting float not to exceed 1.0f, so this test is an
     * indirect way of testing that specification.
     */

    val random = new SecureRandom()
    for (_ <- 0 until 100) {
      val f = random.nextFloat()
      assertTrue(f >= 0.0f && f < 1.0f)
    }
  }
}
