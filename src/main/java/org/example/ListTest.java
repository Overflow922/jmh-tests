/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package org.example;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ListTest {

    List<Integer> arrayList = new ArrayList<>();
    List<Integer> linkedList = new LinkedList<>();


    @Benchmark
    @Measurement(iterations = 5, batchSize = 5000, timeUnit = TimeUnit.MICROSECONDS)
    @Warmup(iterations = 5, batchSize = 5000)
    @BenchmarkMode(Mode.SingleShotTime)
    public List<Integer> arrayListInsertMiddle() {
        arrayList.add(arrayList.size() / 2, 0);
        return arrayList;
    }

    @Benchmark
    @Measurement(iterations = 5, batchSize = 5000, timeUnit = TimeUnit.MICROSECONDS)
    @Warmup(iterations = 5, batchSize = 5000)
    @BenchmarkMode(Mode.SingleShotTime)
    public List<Integer> arrayListInBeginning() {
        arrayList.add(0, 0);
        return arrayList;
    }

    @Benchmark
    @Measurement(iterations = 5, batchSize = 5000, timeUnit = TimeUnit.MICROSECONDS)
    @Warmup(iterations = 5, batchSize = 5000)
    @BenchmarkMode(Mode.SingleShotTime)
    public List<Integer> linkedListInsertMiddle() {
        linkedList.add(linkedList.size() / 2, 0);
        return linkedList;
    }

    @Benchmark
    @Measurement(iterations = 5, batchSize = 5000, timeUnit = TimeUnit.MICROSECONDS)
    @Warmup(iterations = 5, batchSize = 5000)
    @BenchmarkMode(Mode.SingleShotTime)
    public List<Integer> linkedListInsertBeginning() {
        linkedList.add(0, 0);
        return linkedList;
    }

    @Setup(Level.Iteration)
    public void setup() {
        arrayList.clear();
        linkedList.clear();
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ListTest.class.getSimpleName())
                .forks(5)
                .build();

        new Runner(opt).run();
    }
}
