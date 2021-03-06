/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2020 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos.scalar;

import org.cactoos.Scalar;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;

/**
 * Find the greater among items.
 *
 * <p>
 * Here is how you can use it to find the max of a set of numbers:
 * </p>
 *
 * <pre>
 * int max = new MaxOf(1, 2, 3, 4).intValue();
 * long max = new MaxOf(1L, 2L, 3L).longValue();
 * int max = new MaxOf(numbers.toArray(new Integer[numbers.size()])).intValue();
 * </pre>
 *
 * <p>
 * This class implements {@link Scalar}, which throws a checked
 * {@link Exception}. This may not be convenient in many cases. To make it more
 * convenient and get rid of the checked exception you can use the
 * {@link Unchecked} decorator. Or you may use {@link IoChecked} to wrap it in
 * an IOException.
 * </p>
 *
 * <p>
 * There is no thread-safety guarantee.
 * @see Unchecked
 * @see IoChecked
 * @since 0.24
 */
public final class MaxOf extends NumberEnvelope {

    /**
     * Serialization marker.
     */
    private static final long serialVersionUID = -6057839494957475355L;

    /**
     * Ctor.
     * @param src Numbers
     */
    public MaxOf(final Integer... src) {
        this(new IterableOf<>(src));
    }

    /**
     * Ctor.
     * @param src Numbers
     */
    public MaxOf(final Long... src) {
        this(new IterableOf<>(src));
    }

    /**
     * Ctor.
     * @param src Numbers
     */
    public MaxOf(final Double... src) {
        this(new IterableOf<>(src));
    }

    /**
     * Ctor.
     * @param src Numbers
     */
    public MaxOf(final Float... src) {
        this(new IterableOf<>(src));
    }

    /**
     * Ctor.
     * @param src The iterable
     */
    public MaxOf(final Iterable<Number> src) {
        super(
            new Folded<>(
                Long.MIN_VALUE,
                Math::max,
                new Mapped<>(Number::longValue, src)
            ),
            new Folded<>(
                Integer.MIN_VALUE,
                Math::max,
                new Mapped<>(Number::intValue, src)
            ),
            new Folded<>(
                -Float.MAX_VALUE,
                Math::max,
                new Mapped<>(Number::floatValue, src)
            ),
            new Folded<>(
                -Double.MAX_VALUE,
                Math::max,
                new Mapped<>(Number::doubleValue, src)
            )
        );
    }
}
