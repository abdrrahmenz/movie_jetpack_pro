package com.abdurrahman.movies.utils;

import java.util.concurrent.Executor;

public class AppExecutorTest extends AppExecutors {
    private static Executor instant = Runnable::run;

    public AppExecutorTest() {
        super(instant, instant, instant);
    }
}
